package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Graph.Models.Building;
import Graph.Models.Room;

public class RoomDAO {
	private int rID;
	private Connection conn = null;
	private String url = "jdbc:mysql://127.0.0.1:3300/absked";
	private Statement st;
	private ResultSet rs;
	private String query;

	public void openDBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			st = conn.createStatement();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(RoomDAO) Error connecting to database.\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public ArrayList<Room> getAllRooms() {
		ArrayList<Room> roomlist = new ArrayList<Room>();
		try {
			openDBConnection();
			query = "SELECT * FROM rooms";
			rs = st.executeQuery(query);
			while (rs.next()) {
				Room room = new Room();
				Building b = new Building(rs.getInt("buildingID"));
				room.setID(rs.getInt("roomID"));
				room.setBuilding(b.getBname());
				room.setName(rs.getString("room_desc"));
				room.setType(rs.getString("type"));
				room.setCapacity(rs.getInt("capacity"));
				roomlist.add(room);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Room DAO) Error retrieving all rooms:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return roomlist;
	}

	public int getID(String name) {
		try {
			openDBConnection();
			query = "SELECT roomID from rooms where room_desc ='" + name + "'";
			rs = st.executeQuery(query);
			while (rs.next()) {
				rID = rs.getInt("roomID");
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Room DAO) Error retrieving room ID:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return rID;
	}

	public void addRoom(String name, int b, String type, int capacity) {
		try {
			openDBConnection();
			query = "INSERT INTO rooms(buildingID, room_desc, type, capacity) VALUES (" + b + ",'" + name + "','" + type
					+ "'," + capacity + ")";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Room DAO) Error adding room:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void editRoom(int rID, String name, int b, String type, int capacity) {
		try {
			openDBConnection();
			query = "UPDATE rooms SET room_desc='" + name + "', buildingID=" + b + ", type='" + type + "', capacity="
					+ capacity + " WHERE roomID=" + rID;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Room DAO) Error editing room:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void deleteRoom(int rID) {
		try {
			openDBConnection();
			query = "Delete from rooms WHERE roomID=" + rID;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Room DAO) Error deleting room:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public int countRooms() {
		int count = 0;
		try {
			openDBConnection();
			query = "SELECT Count(*) FROM rooms";
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

	public ArrayList<Room> roomCanHold(int class_size) {
		ArrayList<Room> roomlist = new ArrayList<Room>();
		try {
			openDBConnection();
			query = "SELECT * FROM rooms where capacity >= " + class_size;
			rs = st.executeQuery(query);

			while (rs.next()) {
				Room room = new Room();
				Building b = new Building(rs.getInt("buildingID"));
				room.setID(rs.getInt("roomID"));
				room.setBuilding(b.getBname());
				room.setName(rs.getString("room_desc"));
				room.setType(rs.getString("type"));
				room.setCapacity(rs.getInt("capacity"));
				roomlist.add(room);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Room DAO) Error retrieving room list:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return roomlist;
	}

	public ArrayList<Room> getLecRooms() {
		ArrayList<Room> roomlist = new ArrayList<Room>();
		try {
			openDBConnection();
			query = "SELECT * FROM rooms where type='Lecture'";
			rs = st.executeQuery(query);

			while (rs.next()) {
				Room room = new Room();
				Building b = new Building(rs.getInt("buildingID"));
				room.setID(rs.getInt("roomID"));
				room.setBuilding(b.getBname());
				room.setName(rs.getString("room_desc"));
				room.setType(rs.getString("type"));
				room.setCapacity(rs.getInt("capacity"));
				roomlist.add(room);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Room DAO) Error retrieving room list:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return roomlist;
	}

	public ArrayList<Room> getLabRooms() {
		ArrayList<Room> roomlist = new ArrayList<Room>();
		try {
			openDBConnection();
			query = "SELECT * FROM rooms where type='Laboratory'";
			rs = st.executeQuery(query);

			while (rs.next()) {
				Room room = new Room();
				Building b = new Building(rs.getInt("buildingID"));
				room.setID(rs.getInt("roomID"));
				room.setBuilding(b.getBname());
				room.setName(rs.getString("room_desc"));
				room.setType(rs.getString("type"));
				room.setCapacity(rs.getInt("capacity"));
				roomlist.add(room);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Room DAO) Error retrieving room list:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return roomlist;
	}
}
