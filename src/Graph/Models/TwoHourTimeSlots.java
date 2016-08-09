package Graph.Models;

public enum TwoHourTimeSlots {
	HOUR_7_9,
	HOUR_9_11, 
	HOUR_11_13, 
	HOUR_13_15, 
	HOUR_15_17, 
	HOUR_17_19, 
	HOUR_19_21, 
	NULL_HOUR;
	    
	public String option() {
	      return this.name().toLowerCase();
	}
}
