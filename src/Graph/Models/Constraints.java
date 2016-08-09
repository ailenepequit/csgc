package Graph.Models;

import java.util.ArrayList;

public class Constraints {

	
		public Constraints()
		{

		}
		
		//Course Conflict : Block Subject have different timeslots
		public boolean isSameBlock(ArrayList<Subject> subject, int yr_lvl)
		{
			for(int i = 0; i < subject.size(); i++){
				if(subject.get(i).getYrLvl() == yr_lvl){
					return false;
				}	
			}
			//check if courses have the same daycode
			//no overlapping of timeslot
			//data needed : timeslots of each course
			return true;
		}

		public boolean checkDay()
		{
			//find courses with the same daycode
			//
			return true;
		}

		//Timeslot-Room Conflict
		public boolean isTimeRoomAvailable(ArrayList<Offering> offering, String timeslot)
		{
			for(int i = 0; i < offering.size(); i++){
				if(offering.get(i).getTimeslot().equals(timeslot)){
					return false;
				}	
			}
			return true;
			//no courses in the same room w/ same timeslot
			//graph coloring
		}

		//Room Capacity Conflict
		public boolean roomCanHoldClass(Room room, Subject subject)
		{
			if(room.getCapacity() >= subject.getClassSize()){
				return true;
			}
			else
				return false;
		}
	}
