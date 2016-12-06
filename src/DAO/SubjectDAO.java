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

	private int sID, yr_lvl;
	private String url = "jdbc:mysql://localhost:3306/absked?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
	private Connection conn = null;
	private Statement st;
	private ResultSet rs;
	private String query, tag;

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

	public ArrayList<Subject> listSubjects(String sem, int yr) {
		if (sem.equals("All"))
			query = "SELECT * FROM subjects";
		else {
			String con = " where semester='" + sem + "'" + "and yr_level=" + yr;
			query = "SELECT * FROM subjects" + con;
		}
		ArrayList<Subject> subjectList = new ArrayList<Subject>();
		try {
			openDBConnection();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setID(rs.getInt("subjID"));
				subject.setSubject(rs.getString("course_code"));
				subject.setDescription(rs.getString("description"));
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

	public ArrayList<Subject> subjectsBySem(String sem) {
		ArrayList<Subject> subjectList = new ArrayList<Subject>();
		try {
			openDBConnection();
			query = "SELECT * FROM subjects where semester='" + sem + "'";
			rs = st.executeQuery(query);
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setID(rs.getInt("subjID"));
				subject.setSubject(rs.getString("course_code"));
				subject.setDescription(rs.getString("description"));
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

	public void addSubject(String subject, double lecUnits, double labUnits, int yr_lvl, Object semester, String tag) {
		try {
			openDBConnection();
			query = "INSERT INTO subjects(description, lec_units, lab_units, yr_level, semester, tag) VALUES ('" + subject + "', " + lecUnits
					+ ", " + labUnits+ ", " + yr_lvl + ", '" + semester + "', '"+ tag +"')";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.execute();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Subject DAO) Error adding subject:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void editSubject(int sID, String subject, double lecUnits, double labUnits, int yr_lvl, Object semester, String tag) {
		try {
			openDBConnection();
			query = "UPDATE subjects SET description='" + subject + "', lec_units=" + lecUnits + ", lab_units =" +labUnits +", yr_level= " + yr_lvl
					+ ", semester= '" + semester + "', tag= '" + tag + "' WHERE subjID=" + sID;
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
				// subject.setUnits(rs.getDouble("units"));
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
			query = "SELECT subjID from subjects where course_code ='" + name + "'";
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

	public int getYrLvl(int id) {
		try {
			openDBConnection();
			query = "SELECT yr_level from subjects where subjID =" + id;
			rs = st.executeQuery(query);
			while (rs.next()) {
				yr_lvl = rs.getInt("yr_level");
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Subject DAO) Error retrieving year level:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return yr_lvl;
	}

	public String getTag(int id) {
		try {
			openDBConnection();
			query = "SELECT tag from subjects where subjID =" + id;
			rs = st.executeQuery(query);
			while (rs.next()) {
				tag = rs.getString("tag");
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Subject DAO) Error retrieving subject tag:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return tag;
	}

	public void dropTableSubjectRoomMatching() {
		try {
			openDBConnection();
			query = "Drop table IF EXISTS subject_room_matchings";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
			createTableSubjectRoomMatching();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error dropping table subject_room_matching:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void createTableSubjectRoomMatching() {
		try {
			openDBConnection();
			query = "Create table subject_room_matchings (id int PRIMARY KEY AUTO_INCREMENT, subjID int references subject(subjID), roomID int references rooms(roomID))";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error creating table subject_room_matchings:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void dropTableSubjectFacultyMatching() {
		try {
			openDBConnection();
			query = "Drop table IF EXISTS subject_faculty_matchings";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
			createTableSubjectFacultyMatching();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error dropping table subject_faculty_matching:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void createTableSubjectFacultyMatching() {
		try {
			openDBConnection();
			query = "Create table subject_faculty_matchings (id int PRIMARY KEY AUTO_INCREMENT, subjID int references subject(subjID), facID int references faculty(facID))";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error creating table subject_room_matchings:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public ArrayList<Integer> getMatchedRoom(int subject) {
		ArrayList<Integer> subjectList = new ArrayList<Integer>();
		try {
			openDBConnection();
			query = "SELECT roomID FROM `subject_room_matchings` WHERE subjID = " +subject;
			rs = st.executeQuery(query);
			while (rs.next()) {
				subjectList.add(rs.getInt("roomID"));
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Subject DAO) Error retrieving list of matched rooms\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return subjectList;
	}
	
	public void subjectRoomMatch(int subject, int room) {
		try {
			openDBConnection();
			query = "INSERT INTO subject_room_matchings(subjID, roomID) VALUES (" + subject + ", " + room + ")";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.execute();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Subject DAO) Error matching subject to room:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public ArrayList<Integer> getMatchedFaculty(int subject, double unit) {
		ArrayList<Integer> subjectList = new ArrayList<Integer>();
		try {
			openDBConnection();
			query = "SELECT facID FROM `subject_faculty_matchings` left outer join faculty USING (facID) where subjID = "+subject+" and wload + "+unit+" <= 21.0";
			rs = st.executeQuery(query);
			while (rs.next()) {
				subjectList.add(rs.getInt("facID"));
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Subject DAO) Error retrieving list of matched faculty:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return subjectList;
	}

	public void subjectFacultyMatch(int subject, int faculty) {
		try {
			openDBConnection();
			query = "INSERT INTO subject_faculty_matchings(subjID, facID) VALUES (" + subject + ", " + faculty + ")";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.execute();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Subject DAO) Error matching subject to faculty:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
