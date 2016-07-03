package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Graph.Models.Subject;

public class SubjectDAO {

	private int id;
	private String url = "jdbc:mysql://localhost:3306/absked?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
	private Connection conn = null;

	public SubjectDAO() {

	}

	public ArrayList<Subject> listSubjects() {
		ArrayList<Subject> subjectList = new ArrayList<Subject>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			ResultSet srs = st.executeQuery("SELECT * FROM subjects");
			while (srs.next()) {
				Subject subject = new Subject();
				subject.setID(srs.getInt("subjID"));
				subject.setSubject(srs.getString("description"));
				subject.setUnits(srs.getDouble("units"));
				subject.setYrLvl(srs.getInt("yr_level"));
				subject.setSemester(srs.getString("semester"));
				subjectList.add(subject);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Subject DAO) Error retrieving subject list:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return subjectList;
	}

	public int countSubjects() {
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			ResultSet srs = st.executeQuery("SELECT Count(*) FROM subjects");
			while (srs.next()) {
				count = srs.getInt(1);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Subject DAO) Error courses count:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return count;
	}

	public void addSubject(String subject, double units, int yr_lvl, Object semester) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			String query = "INSERT INTO subjects(description, units, yr_level, semester) VALUES ('" + subject + "', " + units + ", " + yr_lvl + ", '" + semester + "')";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.execute();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Subject DAO) Error adding subject:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void editSubject(int id, String subject, double units, int yr_lvl, Object semester) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			String query = "UPDATE subjects SET description='" + subject + "', units=" + units + ", yr_level= " + yr_lvl + ", semester= '" + semester + "' WHERE subjID=" + id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Subject DAO) Error editing subject:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void deleteSubject(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			String query = "Delete from subjects where subjID=" + id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Subject DAO) Error deleting subject:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public ArrayList<Subject> getLabSubjects() {
		ArrayList<Subject> subjectList = new ArrayList<Subject>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			ResultSet srs = st.executeQuery("SELECT * FROM `subjects` WHERE description LIKE '%Lab'");
			while (srs.next()) {
				Subject subject = new Subject();
				subject.setID(srs.getInt("subjID"));
				subject.setSubject(srs.getString("description"));
				subject.setUnits(srs.getDouble("units"));
				subjectList.add(subject);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Subject DAO) Error retrieving laboratory courses:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return subjectList;
	}

	public int getID(String name) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			ResultSet srs = st.executeQuery("SELECT subjID from subjects where description ='" + name + "'");
			while (srs.next()) {
				id = srs.getInt("subjID");
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Subject DAO) Error retrieving subject ID:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return id;
	}
}
