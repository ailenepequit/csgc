package Graph.Models;

import java.util.ArrayList;

import DAO.OfferingDAO;
import DAO.RoomDAO;

public class Offering {

	private OfferingDAO offeringDAO = new OfferingDAO();
	private RoomDAO r = new RoomDAO();
	private Faculty f = new Faculty();
	private int id, class_size, degree, color;
	private String sy, sem, faculty, subject, start_time, end_time, days, room;
	
	public Offering(){
		
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
	
	public void setSY(String sy) {
		this.sy = sy;
	}

	public String getSY() {
		return sy;
	}

	public void setSemester(String sem) {
		this.sem = sem;
	}

	public String getSemester() {
		return sem;
	}

	public void setStartTime(String start_time) {
		this.start_time = start_time;
	}

	public void setEndTime(String end_time) {
		this.end_time = end_time;
	}

	public String getTimeslot() {
		return start_time + "-" + end_time;
	}

	public void setDay(String days) {
		this.days = days;
	}

	public String getDay() {
		return days;
	}

	public void setFaculty(String fname, String mname, String lname) {
		f.setFname(fname);
		f.setMname(mname);
		f.setLname(lname);
		this.faculty = f.getName();
	}

	public String getFaculty() {
		return faculty;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getRoom() {
		return room;
	}

	public void setClassSize(int class_size) {
		this.class_size = class_size;
	}

	public int getClassSize() {
		return class_size;
	}

	public Object getStartTime() {
		return start_time;
	}

	public Object getEndTime() {
		return end_time;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int getDegree() {
		return degree;
	}

	public void setAssignedColor(int color) {
		this.color = color;
	}

	public int getAssignedColor() {
		return color;
	}

	public ArrayList<Room> compatibleRooms() {
		return r.roomCanHold(class_size);
	}
	
	public void setRoom(int id, int room) {
		offeringDAO.setRoom(id, room);
	}
	

//	public String toString() {
//		return this.subject + " " + this.faculty + " " + this.class_size + " " + this.timeslot;
//	}

	public Object[] toTimetableArray() {
		return new Object[] { new Integer(getID()), getSY(), getSemester(), getStartTime(), getEndTime(), getDay(),
				getSubject(), getFaculty(), new Integer(getClassSize()), getRoom() };
	}
	

	public ArrayList<Offering> courseListByRoom(int room) {
		return offeringDAO.listOfferingsByRoom(room);
	}

	public ArrayList<Offering> courseListByFaculty(int facID) {
		return offeringDAO.listOfferingsByFaculty(facID);
	}
	
	public ArrayList<Offering> courseListByUnits(String sign) {
		return offeringDAO.listOfferingsByUnits(sign);
	}
	
	public ArrayList<Offering> courseListWithFaculty() {
		return offeringDAO.listCoursesWithFaculty();
	}

	public ArrayList<Offering> offeringsList() {
		return offeringDAO.listOfferings();
	}

	public ArrayList<Offering> sameFacultyList(int i) {
		return offeringDAO.getCoursesWithSameFaculty(i);
	}

	public void addOffering(int facID, Object rm, int subjID, String schoolyear, String semester, int slots) {
		offeringDAO.addOfferings(facID, rm, subjID, schoolyear, semester, slots);
	}

	public void editOffering(int id, int facID, Object roomID, int subjID, String schoolyear, String semester,
			int slots) {
		offeringDAO.editOfferings(id, facID, roomID, subjID, schoolyear, semester, slots);
	}

	public void deleteOffering(int id) {
		offeringDAO.deleteDaysched(id);
		offeringDAO.deleteOffering(id);
	}


	public void deleteDaysched(int id) {
		offeringDAO.deleteDaysched(id);
	}

	public void addDaySched(int offerno, int daycode, String start_time, String end_time) {
		offeringDAO.addDaysched(offerno, daycode, start_time, end_time);
	}

	public void editDaySched(int offerno, int daycode, String start_time, String end_time) {
		offeringDAO.editDaysched(offerno, daycode, start_time, end_time);
	}

	public int getLastOfferno() {
		return offeringDAO.getLastID();
	}

	public int getDaycode(String day) {
		return offeringDAO.getDaycode(day);
	}
	
	public void deleteAllDayscheds(){
		offeringDAO.deleteAllDayscheds();
	}
}
