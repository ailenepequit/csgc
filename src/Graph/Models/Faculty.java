package Graph.Models;

import java.util.ArrayList;
import java.util.Date;

import DAO.FacultyDAO;

public class Faculty {

	int id;
	double wload;
	String fname = "", mname = "", lname = "", gender, phone, address, color;
	String availStartTime, availEndTime, spec;
	Date bday;
	FacultyDAO f = new FacultyDAO();

	public Faculty() {

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

	public void setSpecialization(String spec) {
		this.spec = spec;
	}

	public void setWload(double wload) {
		this.wload = wload;
	}
	
	public void setColor(String color){
		this.color = color;
	}
	
	public String getColor(){
		return color;
	}
	
	public String getFacultyColor(String name){
		return f.getFacultyColor(name);
	}
	
	public void setFacultyColor(String name, String color){
		f.setFacultyColor(name, color);
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

	public String getSpecialization() {
		return spec;
	}

	public double getWload() {
		return wload;
	}

	public int count() {
		return f.count();
	}

	public ArrayList<Faculty> facultyList() {
		return f.facultyList();
	}

	public Object[] toObjectArray() {
		return new Object[] { getID(), getName(), getGender(), getBday(), getPhone(), getAddress(), getSpecialization(),
				getWload() };
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

	public Object[] legendFacultyArray(){
		return new Object[] { getColor() ,getName()};
	}
}
