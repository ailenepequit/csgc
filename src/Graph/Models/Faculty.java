package Graph.Models;

import java.util.ArrayList;
import java.util.Date;

import DAO.FacultyDAO;

public class Faculty {

	int id;
	String fname, mname, lname, gender, phone, address;
	String availStartTime, availEndTime;
	Date bday;
	FacultyDAO f = new FacultyDAO();
	private boolean available[][];

	public Faculty() {
		available = new boolean[Slot.HOUR_19_21+1][Slot.SATURDAY+1];
        for (int i = 0; i <=Slot.HOUR_19_21; i++) {
            for (int j = 0; j <=Slot.SATURDAY; j++) {
                available[i][j] = true;
            }
        }
	}

	public void setID(int id) {
		this.id = id;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setBday(Date bday) {
		this.bday = bday;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAvailStartTime(String availStartTime) {
		this.availStartTime = availStartTime;
	}

	public void setAvailEndTime(String availEndTime) {
		this.availEndTime = availEndTime;
	}

	public int getID() {
		return id;
	}

	public int getFacID(String name) {
		return f.getID(name);
	}

	public String getName() {
		return fname + " " + mname + " " + lname;
	}

	public String getFname() {
		return fname;
	}

	public String getMname() {
		return mname;
	}

	public String getLname() {
		return lname;
	}

	public String getGender() {
		return gender;
	}

	public String getPhone() {
		return phone;
	}

	public Date getBday() {
		return bday;
	}

	public String getAddress() {
		return address;
	}

	public String getAvailStartTime() {
		return availStartTime;
	}

	public String getAvailEndTime() {
		return availEndTime;
	}

	public int count() {
		return f.count();
	}

	public ArrayList<Faculty> facultyList() {
		return f.facultyList();
	}

	public Object[] toObjectArray() {
		return new Object[] { getID(), getName(), getGender(), getBday(), getPhone(), getAddress() };
	}

	public void deleteFaculty(int id) {
		f.deleteFaculty(id);
	}

	public void editFaculty(int id, String fname, String mname, String lname, String gender, String bday, String phone,
			String address) {
		f.editFaculty(id, fname, mname, lname, gender, bday, phone, address);
	}

	public void addFaculty(String fname, String mname, String lname, String gender, String bday, String phone,
			String address) {
		f.addFaculty(fname, mname, lname, gender, bday, phone, address);
	}

	public boolean isAvailable(int day, int time) {
		if (time == Slot.NULL_HOUR || day == Slot.NULL_DAY)
			return false;
		return available[time][day];
	}
	
	public void setAvailable(int day, int time, boolean av) { available[time][day] = av; }


	@Override
	public int hashCode() {
		return 31 * 1 + id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) // Null references are not equal to this instance.
			return false;
		if (!obj.getClass().equals(Faculty.class)) // Neither are they instances of different classes
			return false;
		return (((Faculty) obj).getID() == this.id);
	}
}
