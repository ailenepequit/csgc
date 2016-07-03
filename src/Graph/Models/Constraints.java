package Graph.Models;

public class Constraints {

	
		public Constraints()
		{

		}

		public boolean CourseConflict()
		{
			return false;
			//no same course in the same room at the same time
			//data needed : name of courses.
			//add edge if course[i].timeslot !
		}
		
		public boolean TimeConflict()
		{
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

		public boolean RoomConflict()
		{
			return false;
			//no courses in the same room w/ same timeslot
			//graph coloring
		}

		public boolean RoomCapacityConflict()
		{
			return false;
			//do not assign a room where room capacity < class size
			//data needed : class size, room size
			//if room.size >= class size -> return true
			//else false
		}
	}


/*
	//find rooms for each course

	i = 1; 
	j = 1;
	for(int i = 0; i>c.size(); i++){
	for(int j = 0; j>r.size(); j++){
		if(c.get(i).getClassSize() <= r.get(j).	getCapacity()){
		c.setRoom(r.get(j).getID());	
		}

		k = j;
		
	if(c.get(i).getClassSize() >= r.get(k).getCapacity()){
		for(int k = j; k > r.size(); k++){
			
		}
	}
	}

	if(start_time < faculty.getAvailStartTime() || start_time < faculty.getAvailEndTime() && end_time > faculty.getAvailEndTime){
		
	}

	if(c.getTimeRoom() == 0)
		t.setTimeSlot();
		t.setRoom();
	}*/
