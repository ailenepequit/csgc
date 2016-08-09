package Graph.Models;

public enum RegularTimeSlots {
	HOUR_730_9AM,
	HOUR_9_1030,
	HOUR_1030_12,
	HOUR_12_130,
	HOUR_130_3,
	HOUR_3_430,
	HOUR_430_6,
	HOUR_6_730,
	HOUR_730_9PM;
	
	public String option(){
		return this.name().toLowerCase();
	}
}
