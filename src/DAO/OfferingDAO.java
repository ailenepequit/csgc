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
			query = "SELECT offerID, sy, offerings.semester, block_subjects.description, block_subjects.blockID, block_subjects.block_no, block_subjects.color, timeslot, room_desc, IFNULL(fname,'') as fname, IFNULL(mname,'') as mname, IFNULL(lname,'') as lname, block_subjects.slots, block_subjects.units, subjects.tag from offerings left join block_subjects on block_subjects.blockID = offerings.blockID left join subjects on block_subjects.subjID = subjects.subjID left join rooms on offerings.roomID = rooms.roomID left join faculty on faculty.facID = offerings.facID";
			rs = st.executeQuery(query);
			while (rs.next()) {
				Offering offering = new Offering();
				offering.setID(rs.getInt("offerID"));
				offering.setSY(rs.getString("sy"));
				offering.setSemester(rs.getString("offerings.semester"));
				offering.setTimeslot(rs.getString("timeslot"));
				offering.setBlockID(rs.getInt("block_subjects.blockID"));
				offering.setBlockNo(rs.getString("block_subjects.block_no"));
				offering.setUnits(rs.getDouble("block_subjects.units"));
				offering.setDescription(rs.getString("block_subjects.description"));
				offering.setFaculty(rs.getString("fname"),rs.getString("mname"), rs.getString("lname"));
				offering.setClassSize(rs.getInt("block_subjects.slots"));
				offering.setRoom(rs.getString("room_desc"));
				offering.setTag(rs.getString("subjects.tag"));
				offering.setColor(rs.getString("block_subjects.color"));
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

	public void insertSubjects(int blockID, String sy, String semester) {
		try {
			openDBConnection();
			query = "INSERT INTO offerings(blockID, sy, semester) VALUES (" + blockID + ", '" + sy + "', '" + semester
					+ "')";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Offering DAO) Error adding new offering:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setRoom(int id, int room) {
		try {
			openDBConnection();
			query = "";
			if (room == 0)
				query = "UPDATE offerings SET roomID=NULL WHERE offerID=" + id;
			else
				query = "UPDATE offerings SET roomID=" + room + " WHERE offerID=" + id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error setting room to offering:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setBlockSubjectTimeslot(int offerID, String timeslot) {
		try {
			openDBConnection();
			query = "UPDATE block_subjects_timeslot SET timeslot='" + timeslot + "' WHERE offerID=" + offerID;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error setting timeslot to offering:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setTimeslot(int id, String timeslot) {
		try {
			openDBConnection();
			query = "UPDATE offerings SET timeslot='" + timeslot + "' WHERE offerID=" + id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error setting timeslot to offering:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setFaculty(int id, int faculty) {
		try {
			openDBConnection();
			query = "UPDATE offerings SET facID='" + faculty + "' WHERE offerID=" + id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error assigning faculty to offering:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void deleteAllOfferings() {
		try {
			openDBConnection();
			query = "Delete from offerings where offerID > 0";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
			dropTableOfferings();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Offering DAO) Error deleting offering:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void dropTableOfferings() {
		try {
			openDBConnection();
			query = "Drop table IF EXISTS offerings";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
			createTableOfferings();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error dropping table offering:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void createTableOfferings() {
		try {
			openDBConnection();
			query = "CREATE TABLE offerings (offerID int(11) AUTO_INCREMENT primary key NOT NULL, blockID int(11) references block_subjects_timeslot(blockID), roomID int(11) DEFAULT NULL references rooms(roomID),facID int(11) DEFAULT NULL references faculty(facID),sy varchar(10) DEFAULT NULL,semester varchar(5) DEFAULT NULL, timeslot varchar(50) DEFAULT NULL, tag varchar(20) DEFAULT NULL)";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error creating table offering:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public ArrayList<Offering> offeringByBlock(String block_no) {
		ArrayList<Offering> offeringList = new ArrayList<Offering>();
		try {
			openDBConnection();
			query = "SELECT offerID, sy, offerings.semester, block_subjects.description, block_subjects.blockID, block_subjects.block_no, timeslot, room_desc, IFNULL(fname,'') as fname, IFNULL(mname,'') as mname, IFNULL(lname,'')  as lname, block_subjects.slots, block_subjects.units, subjects.tag from offerings left join block_subjects on block_subjects.blockID = offerings.blockID left join subjects on block_subjects.subjID = subjects.subjID left join rooms on offerings.roomID = rooms.roomID left join faculty on faculty.facID = offerings.facID where block_subjects.block_no = '"
					+ block_no + "'";
			rs = st.executeQuery(query);
			while (rs.next()) {
				Offering offering = new Offering();
				offering.setID(rs.getInt("offerID"));
				offering.setSY(rs.getString("sy"));
				offering.setSemester(rs.getString("offerings.semester"));
				offering.setTimeslot(rs.getString("timeslot"));
				offering.setBlockID(rs.getInt("block_subjects.blockID"));
				offering.setBlockNo(rs.getString("block_subjects.block_no"));
				offering.setUnits(rs.getDouble("block_subjects.units"));
				offering.setDescription(rs.getString("block_subjects.description"));
				offering.setFaculty(rs.getString("fname"), rs.getString("mname"), rs.getString("lname"));
				offering.setClassSize(rs.getInt("block_subjects.slots"));
				offering.setTag(rs.getString("subjects.tag"));
				offering.setRoom(rs.getString("room_desc"));
				offeringList.add(offering);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error retrieving list of offerings by yr_lvl:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return offeringList;
	}

	public ArrayList<Offering> offeringByTime(String timeslot) {
		ArrayList<Offering> offeringList = new ArrayList<Offering>();
		try {
			openDBConnection();
			query = "SELECT offerID, sy, offerings.semester, block_subjects.description, block_subjects.blockID, block_subjects.block_no, timeslot, room_desc, IFNULL(fname,'') as fname, IFNULL(mname,'') as mname, IFNULL(lname,'') as lname, block_subjects.slots, block_subjects.units, subjects.tag from offerings left join block_subjects on block_subjects.blockID = offerings.blockID left join subjects on block_subjects.subjID = subjects.subjID left join rooms on offerings.roomID = rooms.roomID left join faculty on faculty.facID = offerings.facID where timeslot = '"
					+ timeslot + "'";
			rs = st.executeQuery(query);
			while (rs.next()) {
				Offering offering = new Offering();
				offering.setID(rs.getInt("offerID"));
				offering.setSY(rs.getString("sy"));
				offering.setSemester(rs.getString("offerings.semester"));
				offering.setTimeslot(rs.getString("timeslot"));
				offering.setBlockID(rs.getInt("block_subjects.blockID"));
				offering.setBlockNo(rs.getString("block_subjects.block_no"));
				offering.setUnits(rs.getDouble("block_subjects.units"));
				offering.setDescription(rs.getString("block_subjects.description"));
				//offering.setFaculty(rs.getString("fname"),rs.getString("mname"), rs.getString("lname"));
				offering.setClassSize(rs.getInt("block_subjects.slots"));
				offering.setTag(rs.getString("subjects.tag"));
				//offering.setRoom(rs.getString("room_desc"));
				offeringList.add(offering);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error retrieving list of offerings by yr_lvl:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return offeringList;

	}
	
	public ArrayList<Offering> getOffering(String cond) {
		ArrayList<Offering> offeringList = new ArrayList<Offering>();
		try {
			openDBConnection();
			query = "SELECT offerID, sy, offerings.semester, block_subjects.description, block_subjects.blockID, block_subjects.block_no, timeslot, room_desc, IFNULL(fname,'') as fname, IFNULL(mname,'') as mname, IFNULL(lname,'') as lname, block_subjects.slots, block_subjects.units, block_subjects.color, subjects.tag from offerings left join block_subjects on block_subjects.blockID = offerings.blockID left join subjects on block_subjects.subjID = subjects.subjID left join rooms on offerings.roomID = rooms.roomID left join faculty on faculty.facID = offerings.facID where "+ cond;
			rs = st.executeQuery(query);
			while (rs.next()) {
				Offering offering = new Offering();
				offering.setID(rs.getInt("offerID"));
				offering.setSY(rs.getString("sy"));
				offering.setSemester(rs.getString("offerings.semester"));
				offering.setTimeslot(rs.getString("timeslot"));
				offering.setBlockID(rs.getInt("block_subjects.blockID"));
				offering.setBlockNo(rs.getString("block_subjects.block_no"));
				offering.setUnits(rs.getDouble("block_subjects.units"));
				offering.setDescription(rs.getString("block_subjects.description"));
				offering.setFaculty(rs.getString("fname"),rs.getString("mname"), rs.getString("lname"));
				offering.setClassSize(rs.getInt("block_subjects.slots"));
				offering.setRoom(rs.getString("room_desc"));
				offering.setColor(rs.getString("block_subjects.color"));
				offeringList.add(offering);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"(Offering DAO) Error retrieving list of sorted offerings:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return offeringList;
	}
	
	public void updateOfferingTimeslot(int id, String timeslot, int room, int faculty){
		try {
			openDBConnection();
			query = "UPDATE offerings SET timeslot='" + timeslot + "', roomID=" + room + ", facID = "+faculty+" where offerID=" + id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Offering DAO) Error updating offering:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public int countTimeslot(String timeslot) {
		int count = 0;
		try {
			openDBConnection();
			query = "SELECT Count(*) FROM offerings where timeslot= '"+timeslot+"'";
			rs = st.executeQuery(query);
			while (rs.next()) {
				count = rs.getInt(1);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Room DAO) Error room count:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return count;
	}
}
