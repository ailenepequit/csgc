package Graph.Models;

import java.util.ArrayList;

import DAO.SubjectDAO;

public class Subject {
	private int id, yr_lvl, class_size;
	private double units;
	private String subject, sem;
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
	
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setUnits(double units) {
		this.units = units;
	}

	public double getUnits() {
		return units;
	}
	
	public void setClassSize(int class_size){
		this.class_size = class_size;
	}
	
	public int getClassSize(){
		return class_size;
	}
	
	public Object[] toObjectArray() {
		return new Object[] { getID(), getSubject(), getUnits(), getYrLvl(), getSemester() };
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
		return s.listSubjects(cond);
	}
}
