package Graph.Models;

/**
 * Represents one time slot, during which a course meets.
 */
public class Timeslot {

	/**
	 * Indicates the day of the week, using the familiar correspondence of
	 * letters to days: MTWRF.
	 */
	private String day;

	/** The starting hour, as an integer from 0 to 23. */
	private String start_time;
	private String end_time;
	
	private int color;

	/** The number of hours in the time slot, as an integer from 1 to 3. */
	private int length;

	public Timeslot(String day, String start_time, String end_time, int color) {
		this.day = day;
		this.start_time = start_time;
		this.end_time = end_time;
		this.color = color;
	}

	/** @return the starting hour of this time slot. */
	public String getStart() {
		return start_time;
	}
	
	public String getEnd(){
		return end_time;
	}

	/** @return the hour at which this time slot ends. */
	public int getColor() {
		return color;
	}

	@Override
	public String toString() {
		return day + " " + getStart() + "-" + getEnd();
	}

}
