package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Graph.Models.Building;

public class BuildingDAO {

	private String bname, query;
	private int bID;
	private String url = "jdbc:mysql://127.0.0.1:3300/absked";
	private Connection conn = null;
	private Statement st;
	private ResultSet rs;
	
	public void openDBConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			st = conn.createStatement();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(FacultyDAO) Error connecting to database.\n", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public String getBname(int bID) {
		try {
			openDBConnection();
			query = "SELECT bname FROM buildings WHERE buildingID =" + bID;
			rs = st.executeQuery(query);
			while (rs.next()) {
				bname = rs.getString("bname");
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Buidling DAO) Get Building name Error:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return bname;
	}

	public int getID(String bname) {
		try {
			openDBConnection();
			query = "SELECT buildingID FROM buildings WHERE bname ='" + bname + "'";
			rs = st.executeQuery(query);
			while (rs.next()) {
				bID = rs.getInt("buildingID");
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Buidling DAO) Get Building ID Error:\n" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return bID;
	}

	public ArrayList<Building> buildingList() {
		ArrayList<Building> buildingList = new ArrayList<Building>();
		try {
			openDBConnection();
			query = "SELECT * FROM buildings";
			rs = st.executeQuery(query);
			while (rs.next()) {
				Building b = new Building();
				b.setID(rs.getInt("buildingID"));
				b.setBname(rs.getString("bname"));
				b.setLocation(rs.getString("location"));
				buildingList.add(b);
			}
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "(Building DAO) Error retrieving building list" + e.getMessage() + "\n",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return buildingList;
	}
}
