package Graph.Models;

import java.awt.Color;
import java.util.ArrayList;
import DAO.OfferingDAO;

public class Offering {

	private OfferingDAO offeringDAO = new OfferingDAO();
	private Faculty f = new Faculty();
	private Subject s = new Subject();
	private int id, subjID, class_size, degree,blockID;
	private double units;
	Color colr;
	private String sy, sem, faculty, description, room, timeslot, block_no, tag, color;

	public Offering() {

	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public void setSubjID(int id) {
		this.subjID = id;
	}

	public int getSubjID() {
		return subjID;
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

	public void setUnits(double units) {
		this.units = units;
	}

	public double getUnits() {
		return units;
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

	public void setBlockID(int id) {
		this.blockID = id;
	}

	public int getBlockID() {
		return blockID;
	}

	public void setBlockNo(String string) {
		this.block_no = string;
	}

	public String getBlockNo() {
		return block_no;
	}

	public int getYrLvl(int id) {
		return s.getYrLvl(id);
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

	public String getDescription() {
		return description;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int getDegree() {
		return degree;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public String getTag(int id) {
		return s.getTag(id);
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}

	public String getTimeslot() {
		return timeslot;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	public void setRoom(int id, int room) {
		offeringDAO.setRoom(id, room);
	}

	public ArrayList<Offering> getOfferings(String cond) {
		return offeringDAO.getOffering(cond);
	}

	public void setTimeslot(int id, String timeslot) {
		offeringDAO.setTimeslot(id, timeslot);
	}

	public Object[] toTimetableArray() {
		return new Object[] { getID(), getDescription(), getUnits(), getTimeslot(), getRoom(), getFaculty(),
				new Integer(getClassSize()), };
	}

	public ArrayList<Offering> offeringByBlock(String block_no) {
		return offeringDAO.offeringByBlock(block_no);
	}

	public ArrayList<Offering> offeringsList() {
		return offeringDAO.listOfferings();
	}
}
