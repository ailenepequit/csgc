package Graph.Models;

import java.util.ArrayList;

import DAO.SubjectDAO;

public class Subject {
	private int id, yr_lvl, class_size;
	private double lec_units, lab_units;
	private String course_code, description, sem, tag;
	private SubjectDAO s = new SubjectDAO();
	
	public Subject() {

	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public int getID(String name) {
		return s.getID(name);
	}
	
	public void setSemester(String sem) {
		this.sem = sem;
	}

	public String getSemester() {
		return sem;
	}

	public void setYrLvl(int yr_lvl) {
		this.yr_lvl = yr_lvl;
	}

	public int getYrLvl() {
		return yr_lvl;
	}
	
	public int getYrLvl(int id) {
		return s.getYrLvl(id);
	}
	
	public void setSubject(String course_code) {
		this.course_code = course_code;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}

	public String getSubject() {
		return course_code;
	}
	
	public void setLecUnits(double lec_units) {
		this.lec_units = lec_units;
	}
	
	public double getLecUnits() {
		return lec_units;
	}
	
	public void setLabUnits(double lab_units) {
		this.lab_units = lab_units;
	}
	public double getLabUnits() {
		return lab_units;
	}
	public void setClassSize(int class_size){
		this.class_size = class_size;
	}
	
	public int getClassSize(){
		return class_size;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag(){
		return tag;
	}
	
	public String getTag(int id){
		return s.getTag(id);
	}

	public Object[] toObjectArray() {
		return new Object[] { getID(), getSubject(), getLecUnits(), getLabUnits(), getYrLvl(), getSemester(), getTag() };
	}
	public int count() {
		return s.countSubjects();
	}

	public void addSubject(String subject, double units, int yr_lvl, Object semester) {
		s.addSubject(subject, units, yr_lvl, semester);
	}

	public void editSubject(int id, String subject, double units, int yr_lvl, Object semester) {
		s.editSubject(id, subject, units, yr_lvl, semester);
	}

	public void deleteSubject(int id) {
		s.deleteSubject(id);
	}

	public ArrayList<Subject> getLabSubjects() {
		return s.getLabSubjects();
	}
	
	public ArrayList<Subject> subjectList(String cond) {
		return s.listSubjects(cond, 1);
	}

	public boolean subjectHasLabHours(){
		if (this.getLabUnits() > 0 && this.getLecUnits() == 3.0)
			return true; 
		else
			return false;
	}
}
