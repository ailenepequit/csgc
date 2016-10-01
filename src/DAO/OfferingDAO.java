package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Graph.Models.Offering;

public class OfferingDAO {

	private String url = "jdbc:mysql://localhost:3306/absked?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private String query;

	public void openDBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			st = conn.createStatement();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(OfferingDAO) Error connecting to database.\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public ArrayList<Offering> listOfferings() {
		ArrayList<Offering> offeringList = new ArrayList<Offering>();
		try {
			openDBConnection();
			query = "SELECT offerID, sy, offerings.semester, TIME_FORMAT(start_time, '%h:%i %p') as st, TIME_FORMAT(end_time, '%h:%i %p') as et, day, subjects.description, faculty.fname, faculty.mname, faculty.lname, slots, room_desc from offerings inner join subjects on subjects.subjID = offerings.subjID inner join faculty on faculty.facID = offerings.facID left join rooms on rooms.roomID = offerings.roomID";
			rs = st.executeQuery(query);
			while (rs.next()) {
				Offering offering = new Offering();
				offering.setID(rs.getInt("offerID"));
				offering.setSY(rs.getString("sy"));
				offering.setSemester(rs.getString("offerings.semester"));
				offering.setStartTime(rs.getString("st"));
				offering.setEndTime(rs.getString("et"));
				offering.setDay(rs.getString("day"));
				offering.setSubject(rs.getString("description"));
				offering.setFaculty(rs.getString("fname"), rs.getString("mname"), rs.getString("lname"));
				offering.setClassSize(rs.getInt("slots"));
				offering.setRoom(rs.getString("room_desc"));
				offeringList.add(offering);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error retrieving list of offerings:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return offeringList;
	}

//	public ArrayList<Offering> listOfferingsByRoom(int value) {
//		ArrayList<Offering> offeringList = new ArrayList<Offering>();
//		try {
//			openDBConnection();
//			query = "SELECT offerings.offerID, sy, offerings.semester, TIME_FORMAT(dayscheds.start_time, '%h:%i %p') as st, TIME_FORMAT(dayscheds.end_time, '%h:%i %p') as et, days.dayname, subjects.description, faculty.fname, faculty.mname, faculty.lname, slots, room_desc from offerings inner join subjects on subjects.subjID = offerings.subjID left join dayscheds on dayscheds.offerno = offerings.offerno left join days on dayscheds.daycode = days.daycode inner join faculty on faculty.facID = offerings.facID left join rooms on rooms.roomID = offerings.roomID where rooms.roomID="
//					+ value;
//			rs = st.executeQuery(query);
//			while (rs.next()) {
//				Offering offering = new Offering();
//				offering.setID(rs.getInt("offerno"));
//				offering.setSY(rs.getString("sy"));
//				offering.setSemester(rs.getString("offerings.semester"));
//				offering.setStartTime(rs.getString("st"));
//				offering.setEndTime(rs.getString("et"));
//				offering.setDay(rs.getString("dayname"));
//				offering.setSubject(rs.getString("description"));
//				offering.setFaculty(rs.getString("fname"), rs.getString("mname"), rs.getString("lname"));
//				offering.setClassSize(rs.getInt("slots"));
//				offering.setRoom(rs.getString("room_desc"));
//				offeringList.add(offering);
//			}
//			conn.close();
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null,
//					"(Offering DAO) Error retrieving list of offerings by room:\n" + e.getMessage() + "\n", "Error",
//					JOptionPane.ERROR_MESSAGE);
//		}
//		return offeringList;
//	}

	public void addOfferings(int facID, Object roomID, int subjID, String sy, String semester, int slots) {
		try {
			openDBConnection();
			query = "INSERT INTO offerings(facID, roomID, subjID, sy, offerings.semester, slots) VALUES ("
					+ facID + "," + roomID + "," + subjID + ",'" + sy + "','" + semester + "'," + slots + ")";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Offering DAO) Error adding new offering:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

//	public void deleteAllDayscheds() {
//		try {
//			openDBConnection();
//			query = "Delete from dayscheds WHERE 1";
//			PreparedStatement preparedStmt = conn.prepareStatement(query);
//			preparedStmt.executeUpdate();
//			conn.close();
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "(Offering DAO) Error deleting daysched:\n" + e.getMessage() + "\n",
//					"Error", JOptionPane.ERROR_MESSAGE);
//		}
//	}
//
//	public void addDaysched(int offerno, int daycode, String start_time, String end_time) {
//		try {
//			openDBConnection();
//			query = "INSERT INTO dayscheds(offerno, daycode, start_time, end_time) VALUES (" + offerno + "," + daycode
//					+ ",'" + start_time + "','" + end_time + "')";
//			PreparedStatement preparedStmt = conn.prepareStatement(query);
//			preparedStmt.executeUpdate();
//			conn.close();
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null,
//					"(Offering DAO) Error adding daysched:\n" + e.getMessage() + "\n" + start_time, "Error",
//					JOptionPane.ERROR_MESSAGE);
//		}
//	}

	public void editOfferings(int offerno, int facID, Object roomID, int subjID, String sy, String semester,
			int slots) {
		try {
			openDBConnection();
			query = "UPDATE offerings SET facID=" + facID + ", roomID=" + roomID + ", subjID=" + subjID
					+ ", sy='" + sy + "', offerings.semester='" + semester + "', slots=" + slots
					+ " where offerno=" + offerno;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Offering DAO) Error editing offering:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

//	public void editDaysched(int offerno, int daycode, String start_time, String end_time) {
//		try {
//			openDBConnection();
//			query = "UPDATE dayscheds SET daycode=" + daycode + ", start_time='" + start_time + "', end_time='"
//					+ end_time + "' where offerno=" + offerno;
//			PreparedStatement preparedStmt = conn.prepareStatement(query);
//			preparedStmt.executeUpdate();
//			conn.close();
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "(Offering DAO) Error editing daysched:\n" + e.getMessage() + "\n",
//					"Error", JOptionPane.ERROR_MESSAGE);
//		}
//	}

	public void deleteOffering(int id) {
		try {
			openDBConnection();
			query = "Delete from offerings WHERE offerno=" + id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Offering DAO) Error deleting offering:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

//	public void deleteDaysched(int id) {
//		try {
//			openDBConnection();
//			query = "Delete from rooms WHERE roomID=" + id;
//			PreparedStatement preparedStmt = conn.prepareStatement(query);
//			preparedStmt.executeUpdate();
//			conn.close();
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "(Offering DAO) Error deleting daysched:\n" + e.getMessage() + "\n",
//					"Error", JOptionPane.ERROR_MESSAGE);
//		}
//	}

	public int getLastID() {
		int last_id = 0;
		try {
			openDBConnection();
			query = "SELECT MAX(offerno) from offerings";
			rs = st.executeQuery(query);
			while (rs.next()) {
				last_id = rs.getInt(1);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error getting last id from offerings:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return last_id;
	}

	public int getDaycode(String name) {
		int daycode = 0;
		try {
			openDBConnection();
			query = "SELECT daycode from days where dayname='" + name + "'";
			rs = st.executeQuery(query);
			while (rs.next()) {
				daycode = rs.getInt(1);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Offering DAO) Error retrieving daycode:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return daycode;
	}

//	public ArrayList<Offering> courseListByCapacity(int capacity) {
//		ArrayList<Offering> offeringList = new ArrayList<Offering>();
//		try {
//			openDBConnection();
//			query = "SELECT offerings.offerno, sy, offerings.semester, TIME_FORMAT(dayscheds.start_time, '%h:%i %p') as st, TIME_FORMAT(dayscheds.end_time, '%h:%i %p') as et, days.dayname, subjects.description, faculty.fname, faculty.mname, faculty.lname, slots, room_desc from offerings inner join subjects on subjects.subjID = offerings.subjID left join dayscheds on dayscheds.offerno = offerings.offerno left join days on dayscheds.daycode = days.daycode inner join faculty on faculty.facID = offerings.facID left join rooms on rooms.roomID = offerings.roomID  where slots <="
//					+ capacity;
//			rs = st.executeQuery(query);
//			while (rs.next()) {
//				Offering offering = new Offering();
//				offering.setID(rs.getInt("offerno"));
//				offering.setSY(rs.getString("sy"));
//				offering.setSemester(rs.getString("offerings.semester"));
//				offering.setStartTime(rs.getString("st"));
//				offering.setEndTime(rs.getString("et"));
//				offering.setDay(rs.getString("dayname"));
//				offering.setSubject(rs.getString("description"));
//				offering.setFaculty(rs.getString("fname"), rs.getString("mname"), rs.getString("lname"));
//				offering.setClassSize(rs.getInt("slots"));
//				offering.setRoom(rs.getString("room_desc"));
//				offeringList.add(offering);
//			}
//			conn.close();
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null,
//					"(Offering DAO) Error retrieving list of courses by capacity:\n" + e.getMessage() + "\n", "Error",
//					JOptionPane.ERROR_MESSAGE);
//		}
//		return offeringList;
//	}

//	public ArrayList<Offering> listOfferingsByFaculty(int facID) {
//		ArrayList<Offering> offeringList = new ArrayList<Offering>();
//		try {
//			openDBConnection();
//			query = "SELECT offerings.offerno, sy, offerings.semester, TIME_FORMAT(dayscheds.start_time, '%h:%i %p') as st, TIME_FORMAT(dayscheds.end_time, '%h:%i %p') as et, days.dayname, subjects.description, faculty.fname, faculty.mname, faculty.lname, slots, room_desc from offerings inner join subjects on subjects.subjID = offerings.subjID left join dayscheds on dayscheds.offerno = offerings.offerno left join days on dayscheds.daycode = days.daycode inner join faculty on faculty.facID = offerings.facID left join rooms on rooms.roomID = offerings.roomID where faculty.facID="
//					+ facID;
//			rs = st.executeQuery(query);
//			while (rs.next()) {
//				Offering offering = new Offering();
//				offering.setID(rs.getInt("offerno"));
//				offering.setSY(rs.getString("sy"));
//				offering.setSemester(rs.getString("offerings.semester"));
//				offering.setStartTime(rs.getString("st"));
//				offering.setEndTime(rs.getString("et"));
//				offering.setDay(rs.getString("dayname"));
//				offering.setSubject(rs.getString("description"));
//				offering.setFaculty(rs.getString("fname"), rs.getString("mname"), rs.getString("lname"));
//				offering.setClassSize(rs.getInt("slots"));
//				offering.setRoom(rs.getString("room_desc"));
//				offeringList.add(offering);
//			}
//			conn.close();
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null,
//					"(Offering DAO) Error retrieving list of offerings by room:\n" + e.getMessage() + "\n", "Error",
//					JOptionPane.ERROR_MESSAGE);
//		}
//		return offeringList;
//	}

//	public ArrayList<Offering> listOfferingsByUnits(String sign) {
//		ArrayList<Offering> offeringList = new ArrayList<Offering>();
//		try {
//			openDBConnection();
//			query = "SELECT offerings.offerno, sy, offerings.semester, TIME_FORMAT(dayscheds.start_time, '%h:%i %p') as st, TIME_FORMAT(dayscheds.end_time, '%h:%i %p') as et, days.dayname, subjects.description, faculty.fname, faculty.mname, faculty.lname, slots, room_desc from offerings inner join subjects on subjects.subjID = offerings.subjID left join dayscheds on dayscheds.offerno = offerings.offerno left join days on dayscheds.daycode = days.daycode inner join faculty on faculty.facID = offerings.facID left join rooms on rooms.roomID = offerings.roomID where units "
//					+ sign + " 2.0 order by RAND()";
//			rs = st.executeQuery(query);
//			while (rs.next()) {
//				Offering offering = new Offering();
//				offering.setID(rs.getInt("offerno"));
//				offering.setSY(rs.getString("sy"));
//				offering.setSemester(rs.getString("offerings.semester"));
//				offering.setStartTime(rs.getString("st"));
//				offering.setEndTime(rs.getString("et"));
//				offering.setDay(rs.getString("dayname"));
//				offering.setSubject(rs.getString("description"));
//				offering.setFaculty(rs.getString("fname"), rs.getString("mname"), rs.getString("lname"));
//				offering.setClassSize(rs.getInt("slots"));
//				offering.setRoom(rs.getString("room_desc"));
//				offeringList.add(offering);
//			}
//			conn.close();
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null,
//					"(Offering DAO) Error retrieving list of offerings by room:\n" + e.getMessage() + "\n", "Error",
//					JOptionPane.ERROR_MESSAGE);
//		}
//		return offeringList;
//	}

	public ArrayList<Offering> getCoursesWithSameFaculty(int i) {
		ArrayList<Offering> offeringList = new ArrayList<Offering>();
		try {
			openDBConnection();
			query = "SELECT description from subjects inner join offerings on offerings.subjID = subjects.subjID where facID ="
					+ i;
			rs = st.executeQuery(query);
			while (rs.next()) {
				Offering offering = new Offering();
				offering.setSubject(rs.getString("description"));
				offeringList.add(offering);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error retrieving offering list with faculty:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return offeringList;
	}

	public ArrayList<Offering> listCoursesWithFaculty() {
		ArrayList<Offering> offeringList = new ArrayList<Offering>();
		try {
			openDBConnection();
			query = "SELECT distinct subjects.subjID, description, fname, mname, lname FROM subjects inner join offerings on offerings.subjID = subjects.subjID inner join faculty on offerings.facID = faculty.facID order by description";
			rs = st.executeQuery(query);
			while (rs.next()) {
				Offering course = new Offering();
				course.setID(rs.getInt("subjID"));
				course.setSubject(rs.getString("description"));
				course.setFaculty(rs.getString("fname"), rs.getString("mname"), rs.getString("lname"));
				offeringList.add(course);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error retrieving course list with faculty:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return offeringList;
	}

	public void setRoom(int id, int room) {
		try {
			openDBConnection();
			query = "";
			if (room == 0)
				query = "UPDATE offerings SET roomID=NULL WHERE offerno=" + id;
			else
				query = "UPDATE offerings SET roomID=" + room + " WHERE offerno=" + id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error setting room to offering:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
