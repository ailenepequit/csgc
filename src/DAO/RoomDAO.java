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
	private int id;
	private ArrayList<Room> roomlist = new ArrayList<Room>();
	private Connection conn = null;
	private String url = "jdbc:mysql://127.0.0.1:3306/absked";

	public ArrayList<Room> getAllRooms() {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			ResultSet srs = st.executeQuery("SELECT * FROM rooms");

			while (srs.next()) {
				Room room = new Room();
				Building b = new Building(srs.getInt("buildingID"));
				room.setID(srs.getInt("roomID"));
				room.setBuilding(b.getBname());
				room.setName(srs.getString("room_desc"));
				room.setType(srs.getString("type"));
				room.setCapacity(srs.getInt("capacity"));
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
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			ResultSet srs = st.executeQuery("SELECT roomID from rooms where room_desc ='" + name + "'");
			while (srs.next()) {
				id = srs.getInt("roomID");
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Room DAO) Error retrieving room ID:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return id;
	}

	public void addRoom(String name, int b, String type, int capacity) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			String query = "INSERT INTO rooms(buildingID, room_desc, type, capacity) VALUES (" + b + ",'" + name + "','"
					+ type + "'," + capacity + ")";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Room DAO) Error adding room:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void editRoom(int id, String name, int b, String type, int capacity) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			String query = "UPDATE rooms SET room_desc='" + name + "', buildingID=" + b + ", type='" + type
					+ "', capacity=" + capacity + " WHERE roomID=" + id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Room DAO) Error editing room:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void deleteRoom(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			String query = "Delete from rooms WHERE roomID=" + id;
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
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/absked";
			Connection conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			ResultSet srs = st.executeQuery("SELECT Count(*) FROM rooms");
			while (srs.next()) {
				count = srs.getInt(1);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Room DAO) Error room count:\n" + e.getMessage() + "\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return count;
	}

	public ArrayList<Room> roomCanHold(int class_size) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			ResultSet srs = st.executeQuery("SELECT * FROM rooms where capacity >= " + class_size);

			while (srs.next()) {
				Room room = new Room();
				Building b = new Building(srs.getInt("buildingID"));
				room.setID(srs.getInt("roomID"));
				room.setBuilding(b.getBname());
				room.setName(srs.getString("room_desc"));
				room.setType(srs.getString("type"));
				room.setCapacity(srs.getInt("capacity"));
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
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			ResultSet srs = st.executeQuery("SELECT * FROM rooms where type='Lecture'");

			while (srs.next()) {
				Room room = new Room();
				Building b = new Building(srs.getInt("buildingID"));
				room.setID(srs.getInt("roomID"));
				room.setBuilding(b.getBname());
				room.setName(srs.getString("room_desc"));
				room.setType(srs.getString("type"));
				room.setCapacity(srs.getInt("capacity"));
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
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			ResultSet srs = st.executeQuery("SELECT * FROM rooms where type='Laboratory'");

			while (srs.next()) {
				Room room = new Room();
				Building b = new Building(srs.getInt("buildingID"));
				room.setID(srs.getInt("roomID"));
				room.setBuilding(b.getBname());
				room.setName(srs.getString("room_desc"));
				room.setType(srs.getString("type"));
				room.setCapacity(srs.getInt("capacity"));
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
