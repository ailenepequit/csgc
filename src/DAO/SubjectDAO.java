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

	private int sID;
	private String url = "jdbc:mysql://localhost:3306/absked?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
	private Connection conn = null;
	private Statement st;
	private ResultSet rs;
	private String query;

	public void openDBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			st = conn.createStatement();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(SubjectDAO) Error connecting to database.\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public ArrayList<Subject> listSubjects(String cond) {
		if(cond.equals("All"))
			query = "SELECT * FROM subjects";
		else{
			String con = " where semester='"+ cond +"'";
			query = "SELECT * FROM subjects" + con;
		}
		ArrayList<Subject> subjectList = new ArrayList<Subject>();
		try {
			openDBConnection();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setID(rs.getInt("subjID"));
				subject.setSubject(rs.getString("description"));
				subject.setLecUnits(rs.getDouble("lec_units"));
				subject.setLabUnits(rs.getDouble("lab_units"));
				subject.setYrLvl(rs.getInt("yr_level"));
				subject.setSemester(rs.getString("semester"));
				subject.setTag(rs.getString("tag"));
				subjectList.add(subject);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Subject DAO) Error retrieving subject list:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return subjectList;
	}

	public int countSubjects() {
		int count = 0;
		
		try {
			openDBConnection();
			query = "SELECT Count(*) FROM subjects";
			rs = st.executeQuery(query);
			while (rs.next()) {
				count = rs.getInt(1);
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
			openDBConnection();
			query = "INSERT INTO subjects(description, units, yr_level, semester) VALUES ('" + subject + "', " + units
					+ ", " + yr_lvl + ", '" + semester + "')";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.execute();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Subject DAO) Error adding subject:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void editSubject(int sID, String subject, double units, int yr_lvl, Object semester) {
		try {
			openDBConnection();
			query = "UPDATE subjects SET description='" + subject + "', units=" + units + ", yr_level= " + yr_lvl
					+ ", semester= '" + semester + "' WHERE subjID=" + sID;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Subject DAO) Error editing subject:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void deleteSubject(int sID) {
		try {
			openDBConnection();
			query = "Delete from subjects where subjID=" + sID;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Subject DAO) Error deleting subject:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public ArrayList<Subject> getLabSubjects() {
		ArrayList<Subject> subjectList = new ArrayList<Subject>();
		try {
			openDBConnection();
			query = "SELECT * FROM `subjects` WHERE description LIKE '%Lab'";
			rs = st.executeQuery(query);
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setID(rs.getInt("subjID"));
				subject.setSubject(rs.getString("description"));
				subject.setUnits(rs.getDouble("units"));
				subjectList.add(subject);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Subject DAO) Error retrieving laboratory courses:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return subjectList;
	}

	public int getID(String name) {
		try {
			openDBConnection();
			query = "SELECT subjID from subjects where description ='" + name + "'";
			rs = st.executeQuery(query);
			while (rs.next()) {
				sID = rs.getInt("subjID");
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Subject DAO) Error retrieving subject ID:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return sID;
	}
}
