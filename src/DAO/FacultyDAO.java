package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Graph.Models.Faculty;

public class FacultyDAO {
	private int id;
	private String url = "jdbc:mysql://localhost:3306/absked?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
	private Connection conn = null;

	public int getID(String name) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			ResultSet srs = st.executeQuery(
					"SELECT * FROM `faculty` WHERE CONCAT(fname, ' ', mname, ' ', lname) LIKE '" + name + "'");
			while (srs.next()) {
				id = srs.getInt("facID");
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Faculty DAO) Error retrieving course ID:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return id;
	}

	public ArrayList<Faculty> facultyList() {
		ArrayList<Faculty> faclist = new ArrayList<Faculty>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			// ResultSet srs = st.executeQuery(
			// "SELECT facID, fname, mname, lname, gender, bday, phone, address,
			// TIME_FORMAT(avail_start_time, '%h:%i %p') as ast,
			// TIME_FORMAT(avail_end_time, '%h:%i %p') as aet from faculty");
			ResultSet srs = st
					.executeQuery("SELECT facID, fname, mname, lname, gender, bday, phone, address from faculty");

			while (srs.next()) {
				Faculty faculty = new Faculty();
				faculty.setID(srs.getInt("facID"));
				faculty.setFname(srs.getString("fname"));
				faculty.setMname(srs.getString("mname"));
				faculty.setLname(srs.getString("lname"));
				faculty.setGender(srs.getString("gender"));
				faculty.setBday(srs.getDate("bday"));
				faculty.setPhone(srs.getString("phone"));
				faculty.setAddress(srs.getString("address"));
				faclist.add(faculty);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Faculty DAO) Error retrieving faculty list:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return faclist;
	}

	public int count() {
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			ResultSet srs = st.executeQuery("SELECT Count(*) FROM faculty");
			while (srs.next()) {
				count = srs.getInt(1);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Faculty DAO) Error faculty count:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return count;
	}

	public void deleteFaculty(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			String query = "Delete from faculty WHERE facID=" + id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Faculty DAO) Error deleting faculty:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void editFaculty(int id, String fname, String mname, String lname, String gender, String bday, String phone,
			String address) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			String query = "UPDATE faculty SET fname='" + fname + "', mname='" + mname + "', lname='" + lname
					+ "', gender='" + gender + "', bday='" + bday + "', phone='" + phone + "',address='" + address
					+ "' WHERE facID=" + id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Faculty DAO) Error editing faculty:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void addFaculty(String fname, String mname, String lname, String gender, String bday, String phone,
			String address) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			String query = ("INSERT INTO faculty (fname, mname, lname, gender, bday, phone, address) VALUES ('" + fname
					+ "','" + mname + "','" + lname + "','" + gender + "','" + bday + "','" + phone + "','" + address
					+ "'");
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Faculty DAO) Error adding faculty:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
