package Graph.Models;

import java.util.ArrayList;

public class Algorithm {
	private int N; // number of courses
	private int CM[][]; // conflict matrix
	private int D = 6; // number of class days
	private int d; // a class day
	private int T; // available timeslots per day
	private int t; // a timeslot
	private int R; // number of rooms
	private int r; // is timeslot in a room 1-R
	private int k; // number of colors; set k = 0
	private int loc; // number of courses per group
	private int CG[][]; // color matrix

//	Course course = new Course();
//	private ArrayList<Course>[] group = (ArrayList<Course>[]) new ArrayList[12];
//	private ArrayList<Course> clist = course.offeringsList();

//	public Algorithm(){
//	//sort vertices according to degree /number of edges
//	
//	for(int i =0; i < N; i++){
//		for(int j = 0; j < N; j++) //Step2
//		{
//			if(i != j){	//(a)
//				if(CM[i][j] == 0){ //not in conflict
//					if(clist.get(j).getAssignedColor() != -1){
//
//					}
//					for(int a = 0; a < 30 ; a++ ){ //check if course is not in conflict to group [i]
//						if(){
//							group[i].add(clist.get(j));
//						}
//					}
//					if(loc == R){
//						k++;
//					}
//				}
//			}
//			if(j <= N){ //(b)
//				//find not conflicted and not colored course
//				//go to step 2
//			} 
//			else
//				k++; //go to step 1
//		}
//	}
//
//	String[] days = {"MTh","TuF","W","Sa"};
//	String[] timeslot = {"07:30 AM", "09:00 AM", "10:30 AM", "12:00 PM", "01:30 PM", "03:00 PM", "04:30 PM"};
//	String[] timeslot_wsa = {"07:00 AM", "09:00 AM", "11:00 AM", "01:00 PM", "03:00 PM", "05:00 PM", "07:00 PM"};
//	String[] timeslot_u = {"06:00 PM", "07:30 PM"};
//	int[] group;
//	boolean found = false;
//	//assign courses to rooms
//	for (int d = 0; d < days.length; d++){
//		for (int t = 0; t < timeslot.length; t++){
//			for(int g = 0; g < group.length; g++){
//				if(t < 7){ //assume 7 slots per day
//					group[g].setTimeslot(timeslot[t]); //assigne courses to rooms
//					group.remove(group[g]);
//				}
//				else{
//					//find group with courses not conflicted with the conflicted coures of the selected group in day
//					if(group[g] == found){
//						group[g].setTimeslot(timeslot[t]);
//						//Assign grp to t
//						group.remove(group[g]);
//					}
//					else
//						break; //go to step 3
//					if(group[g] < 30)
//						//go to step 5
//					else
//						//go to step 4
//				}
//			}
//		}
//	}
//	}
	
	public void generateSchedule(){
		
	}
}
