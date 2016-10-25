package Graph.Models;

import java.util.ArrayList;
import DAO.RoomDAO;

public class Room {

	private int id, capacity;
	private String name, type, building, color;
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

	public String toString() {
		return this.name + " " + this.building + " " + this.type + " " + this.capacity;
	}

	public Object[] toObjectArray() {
		return new Object[] { getID(), getBuilding(), getName(), getType(), getCapacity() };
	}

	public ArrayList<Room> roomList(String cond) {
		switch(cond){
			case "All": 
				return r.getAllRooms();
			case "Lec":
				return r.getLecRooms();
			case "Lab":
				return r.getLabRooms();
			default:
				return r.getAllRooms();
		}
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
	
	public ArrayList<Room> getLabRooms(){
		return r.getLabRooms();
	}
	
	public ArrayList<Room> getLecRooms(){
		return r.getLecRooms();
	}
	
	public void setColor(String color){
		this.color = color;
	}
	
	public String getColor(){
		return color;
	}

	public void setRoomColor(String room, String color) {
		r.setRoomColor(room, color);
	}
	
	public String getRoomColor(String room){
		return r.getRoomColor(room);
	}
	
	public Object[] legendRoomArray(){
		return new Object[] { getColor() ,getName()};
	}
}
