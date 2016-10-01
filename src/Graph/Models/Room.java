package Graph.Models;

import java.util.ArrayList;

import DAO.RoomDAO;

public class Room {

	private int id, capacity;
	private RoomSize roomSize;
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
			case "Lecture":
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

	public ArrayList<Room> compatibleCourses() {
		return r.roomCanHold(capacity);
	}
	
//	public ArrayList<Room> getLabRooms(){
//		return r.getLabRooms();
//	}
//	
//	public ArrayList<Room> getLecRooms(){
//		return r.getLecRooms();
//	}
	
	public void setSize(RoomSize roomSize) {
		if (Room.isValidRoomSize(roomSize))
			this.roomSize = roomSize;
	}

	public RoomSize getSize() {
		return roomSize;
	}
	
	public int compareTo(Room o) {
		if (o.getSize() == this.roomSize)
			return 0;
		else if (o.getSize() == RoomSize.MEDIUM && this.roomSize == RoomSize.LARGE)
			return -1;
		return 1;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) // Null references are not equal to this instance.
			return false;
		if (!o.getClass().equals(Room.class)) // Neither are they instances of
												// different classes
			return false;
		return (((Room) o).getID()==(this.id));
	}
	
	public static RoomSize getRoomSize(int room_size) {
		if (room_size <= 20)
			return RoomSize.SMALL;
		if (room_size <= 30 && room_size > 20)
			return RoomSize.MEDIUM;
		if (room_size > 30)
			return RoomSize.LARGE;
		return RoomSize.NULL;
	}

	public static boolean isValidRoomSize(RoomSize roomSize) {
		return (roomSize == RoomSize.LARGE || roomSize == RoomSize.MEDIUM);
	}
	
}
