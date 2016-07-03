package Graph.Models;

import java.util.ArrayList;

import DAO.RoomDAO;

public class Room {

	private int id, capacity;
	private String name, type, building;
	private int color;
	private RoomDAO r = new RoomDAO();

	public Room() {

	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public int getID(String name) {
		return r.getID(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getBuilding() {
		return building;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getColor() {
		return color;
	}

	public int getRoom(int colorid) {
		return id;
	}

	public boolean isAvailable(boolean status) {
		return status;
	}

	public String toString() {
		return this.name + " " + this.building + " " + this.type + " " + this.capacity;
	}

	public Object[] toObjectArray() {
		return new Object[] { getID(), getBuilding(), getName(), getType(), getCapacity() };
	}

	public ArrayList<Room> roomList() {
		return r.getAllRooms();
	}

	public void addRoom(String name, int b, String type, int capacity) {
		r.addRoom(name, b, type, capacity);
	}

	public void editRoom(int id, String name, int b, String type, int capacity) {
		r.editRoom(id, name, b, type, capacity);
	}

	public void deleteRoom(int id) {
		r.deleteRoom(id);
	}

	public int count() {
		return r.countRooms();
	}

	public ArrayList<Room> compatibleCourses() {
		return r.roomCanHold(capacity);
	}
	
	public ArrayList<Room> getLabRooms(){
		return r.getLabRooms();
	}
	
	public ArrayList<Room> getLecRooms(){
		return r.getLecRooms();
	}
}
