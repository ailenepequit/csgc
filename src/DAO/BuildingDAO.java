package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Graph.Models.Building;

public class BuildingDAO {

	private String bname;
	private int id;
	private String url = "jdbc:mysql://127.0.0.1:3306/absked";
	private Connection conn = null;

	public String getBname(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			ResultSet srs = st.executeQuery("SELECT bname FROM buildings WHERE buildingID =" + id);
			while (srs.next()) {
				bname = srs.getString("bname");
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Buidling DAO) Get Building name Error:\n" + e.getMessage() + "\n", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return bname;
	}

	public int getID(String bname) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			ResultSet srs = st.executeQuery("SELECT buildingID FROM buildings WHERE bname ='" + bname + "'");
			while (srs.next()) {
				id = srs.getInt("buildingID");
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Buidling DAO) Get Building ID Error:\n" + e.getMessage() + "\n", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return id;
	}

	public ArrayList<Building> buildingList() {
		ArrayList<Building> buildingList = new ArrayList<Building>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			ResultSet srs = st.executeQuery("SELECT * FROM buildings");
			while (srs.next()) {
				Building b = new Building();
				b.setID(srs.getInt("buildingID"));
				b.setBname(srs.getString("bname"));
				b.setLocation(srs.getString("location"));
				buildingList.add(b);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Building DAO) Error retrieving building list" + e.getMessage() + "\n", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return buildingList;
	}
}
