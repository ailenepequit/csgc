package Graph.Models;

import java.util.ArrayList;

import DAO.BuildingDAO;

public class Building {

	private int id;
	private String bname, location;
	BuildingDAO b = new BuildingDAO();

	public Building() {

	}

	public Building(int id) {
		setBname(b.getBname(id));
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getBname() {
		return bname;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public int getID(String bname) {
		return b.getID(bname);
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public ArrayList<Building> buildingList() {
		return b.buildingList();
	}
}
