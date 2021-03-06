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
	
	private int fID;
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
			JOptionPane.showMessageDialog(null, "(FacultyDAO) Error connecting to database.\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public int getID(String name) {
		try {
			openDBConnection();
			query = "SELECT facID FROM `faculty` WHERE CONCAT(fname, ' ', mname, ' ', lname) LIKE '" + name + "'";
			rs = st.executeQuery(query);
			while (rs.next()) {
				fID = rs.getInt("facID");
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Faculty DAO) Error retrieving course ID:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return fID;
	}
	public void setFacultyColor(String name, String color) {
		try {
			openDBConnection();
			query = "UPDATE faculty SET color='" + color + "' WHERE CONCAT(fname, ' ', mname, ' ', lname) LIKE '" + name +"'";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Faculty DAO) Error setting faculty color:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	public String getFacultyColor(String name) {
		String color = "";
		try {
			openDBConnection();
			query = "SELECT color FROM `faculty` WHERE CONCAT(fname, ' ', mname, ' ', lname) LIKE '" + name + "'";
			rs = st.executeQuery(query);
			while (rs.next()) {
				color = rs.getString("color");
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Faculty DAO) Error retrieving faculty color:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return color;
	}
	public ArrayList<Faculty> facultyList() {
		ArrayList<Faculty> faclist = new ArrayList<Faculty>();
		try {
			openDBConnection();
			query = "SELECT * from faculty";
			rs = st.executeQuery(query);
			while (rs.next()) {
				Faculty faculty = new Faculty();
				faculty.setID(rs.getInt("facID"));
				faculty.setFname(rs.getString("fname"));
				faculty.setMname(rs.getString("mname"));
				faculty.setLname(rs.getString("lname"));
				faculty.setGender(rs.getString("gender"));
				faculty.setBday(rs.getDate("bday"));
				faculty.setPhone(rs.getString("phone"));
				faculty.setAddress(rs.getString("address"));
				faculty.setSpecialization(rs.getString("specialization"));
				faculty.setWload(rs.getDouble("wload"));
				faculty.setColor(rs.getString("color"));
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
			openDBConnection();
			query = "SELECT Count(*) FROM faculty";
			rs = st.executeQuery(query);
			while (rs.next()) {
				count = rs.getInt(1);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Faculty DAO) Error faculty count:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return count;
	}

	public void deleteFaculty(int fID) {
		try {
			openDBConnection();
			query = "Delete from faculty WHERE facID=" + fID;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Faculty DAO) Error deleting faculty:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void editFaculty(int fID, String fname, String mname, String lname, String gender, String bday, String phone,
			String address) {
		try {
			openDBConnection();
			query = "UPDATE faculty SET fname='" + fname + "', mname='" + mname + "', lname='" + lname
					+ "', gender='" + gender + "', bday='" + bday + "', phone='" + phone + "',address='" + address
					+ "' WHERE facID=" + fID;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Faculty DAO) Error editing faculty:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void editFaculty(int fID, String fname, String mname, String lname, String spec) {
		try {
			openDBConnection();
			query = "UPDATE faculty SET fname='" + fname + "', mname='" + mname + "', lname='" + lname
					+ "', specialization='" + spec + "' WHERE facID=" + fID;
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
			openDBConnection();
			query = ("INSERT INTO faculty (fname, mname, lname, gender, bday, phone, address) VALUES ('" + fname
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
	
	public void addFaculty(String fname, String mname, String lname, String spec) {
		try {
			openDBConnection();
			query = ("INSERT INTO faculty (fname, mname, lname, specialization) VALUES ('" + fname
					+ "','" + mname + "','" + lname + "','" + spec + "'");
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Faculty DAO) Error adding faculty:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public ArrayList<Faculty> availableFaculty_Timeslot(String timeslot) {
		ArrayList<Faculty> faclist = new ArrayList<Faculty>();
		try {
			openDBConnection();
			query = "SELECT * from faculty where facID NOT IN (SELECT DISTINCT faculty.facID AS facID from faculty LEFT OUTER JOIN offerings USING (facID) WHERE offerings.timeslot = '"+timeslot+"')";
			rs = st.executeQuery(query);
			while (rs.next()) {
				Faculty faculty = new Faculty();
				faculty.setID(rs.getInt("facID"));
				faculty.setFname(rs.getString("fname"));
				faculty.setMname(rs.getString("mname"));
				faculty.setLname(rs.getString("lname"));
				faculty.setGender(rs.getString("gender"));
				faculty.setBday(rs.getDate("bday"));
				faculty.setPhone(rs.getString("phone"));
				faculty.setAddress(rs.getString("address"));
				faculty.setSpecialization(rs.getString("specialization"));
				faculty.setWload(rs.getDouble("wload"));
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
	
	public void updateWorkLoad(int id, double unit) {
		try {
			openDBConnection();
			query = "UPDATE faculty SET wload = wload + " + unit + " WHERE facID = " + id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Faculty DAO) Error updating faculty workload units:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void clearWorkLoad(){
		try {
			openDBConnection();
			query = "UPDATE faculty SET wload = 0";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Faculty DAO) Error updating faculty workload units:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
