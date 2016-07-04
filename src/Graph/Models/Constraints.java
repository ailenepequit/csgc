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
