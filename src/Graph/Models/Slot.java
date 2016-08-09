package Graph.Models;

public class Slot {

	int roomID, slotID;
	private String day;
	RoomType roomType;
	private String time;
	private int color;

	public Slot(int roomID, String day, String time, int color) {
		this.roomID = roomID;
		this.day = day;
		this.time = time;
		this.color = color;
	}
	
	public int getID(){
		return roomID;
	}
	
	public String getDay(){
		return day;
	}

	public String getTime() {
		return time;
	}
	
	public int getColor() {
		return color;
	}

	@Override
	public String toString() {
		return getDay() + " " + getTime();
	}
	
	public void setRoomId(int roomID) {
		this.roomID = roomID;
	}

	public int getRoomId() {
		return this.roomID;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomSize(RoomType roomType) {
		this.roomType = roomType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + color;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) // Null references are not equal to this instance.
			return false;
		if (!obj.getClass().equals(Slot.class)) // Neither are they instances of different classes
			return false;
		return ((Slot) obj).getDay() == this.day && ((Slot) obj).getTime() == this.time;
	}
	
	  /** Day constants */
    public final static int MONDAY = 0;
    public final static int TUESDAY = 1;
    public final static int WEDNESDAY = 2;
    public final static int THURSDAY = 3;
    public final static int FRIDAY = 4;
    public final static int SATURDAY = 5;
    public final static int NULL_DAY = -1;
    /** Time constants */
    public final static int HOUR_730_9AM = 0;
    public final static int HOUR_9_1030 = 1;
    public final static int HOUR_1030_12 = 2;
    public final static int HOUR_12_130 = 3;
    public final static int HOUR_130_3 = 4;
    public final static int HOUR_3_430 = 5;
    public final static int HOUR_430_6 = 6;
    public final static int HOUR_6_730 = 7;
    public final static int HOUR_730_9PM = 8;
    public final static int HOUR_7_9 = 9;
    public final static int HOUR_9_11 = 10;
    public final static int HOUR_11_13 = 11;
    public final static int HOUR_13_15 = 12;
    public final static int HOUR_15_17 = 13;
    public final static int HOUR_17_19 = 14;
    public final static int HOUR_19_21 = 15;
    public final static int NULL_HOUR = -2;

    /** Returns the constant corresponding to the given string/day.
     * @param day The string that represents the day.
     * @return  
     */
    public static int getDay(String day) {
        if (day.equalsIgnoreCase("Monday")) return MONDAY;
        if (day.equalsIgnoreCase("Tuesday")) return TUESDAY;
        if (day.equalsIgnoreCase("Wednesday")) return WEDNESDAY;
        if (day.equalsIgnoreCase("Thursday")) return THURSDAY;
        if (day.equalsIgnoreCase("Friday")) return FRIDAY;
        if (day.equalsIgnoreCase("Saturday")) return SATURDAY;
        return NULL_DAY;
    }

    /** Returns the constant corresponding to the given string/time.
     * @param start_time The string that represents the starting time of a course.
     * @return  
     */
    public static int getTimeSlot(String start_time){
    	if (start_time.equalsIgnoreCase("7:30 AM")) return HOUR_730_9AM;
        if (start_time.equalsIgnoreCase("9:00")) return HOUR_9_1030;
        if (start_time.equalsIgnoreCase("10:30")) return HOUR_1030_12;
        if (start_time.equalsIgnoreCase("12:00")) return HOUR_12_130;
        if (start_time.equalsIgnoreCase("1:30")) return HOUR_130_3;
        if (start_time.equalsIgnoreCase("3:00")) return HOUR_3_430;
        if (start_time.equalsIgnoreCase("4:30")) return HOUR_430_6;
        if (start_time.equalsIgnoreCase("6:00")) return HOUR_6_730;
        if (start_time.equalsIgnoreCase("7:30 PM")) return HOUR_730_9PM;
    	if (start_time.equalsIgnoreCase("7")) return HOUR_7_9;
        if (start_time.equalsIgnoreCase("9")) return HOUR_9_11;
        if (start_time.equalsIgnoreCase("11")) return HOUR_11_13;
        if (start_time.equalsIgnoreCase("13")) return HOUR_13_15;
        if (start_time.equalsIgnoreCase("15")) return HOUR_15_17;
        if (start_time.equalsIgnoreCase("17")) return HOUR_17_19;
        if (start_time.equalsIgnoreCase("19")) return HOUR_19_21;
        return NULL_HOUR;
    }

    /** Returns the constant corresponding to the given string/day.
     * @param day The constant that represents the day.
     * @return  
     */
    public static String getDay(int day) {
        if (day == Slot.MONDAY) return "Monday";
        if (day == Slot.TUESDAY) return "Tuesday";
        if (day == Slot.WEDNESDAY) return "Wednesday";
        if (day == Slot.THURSDAY) return "Thursday";
        if (day == Slot.FRIDAY) return "Friday";
        if (day == Slot.SATURDAY) return "Saturday";
        return Component.NULL_NAME;
    }

    /** Returns the constant corresponding to the given string/time.
     * @param start_time The constant that represents the starting time of a course.
     * @return  
     */
    public static String getTimeSlot(int start_time){
    	if (start_time == Slot.HOUR_730_9AM) return "7:30-9:00 AM";
        if (start_time == Slot.HOUR_9_1030) return "9:00-10:30";
        if (start_time == Slot.HOUR_1030_12) return "10:30-12:00";
        if (start_time == Slot.HOUR_12_130) return "12:00-1:30";
        if (start_time == Slot.HOUR_130_3) return "1:30-3:00";
        if (start_time == Slot.HOUR_3_430) return "3:00-4:30";
        if (start_time == Slot.HOUR_430_6) return "4:30-6:00";
        if (start_time == Slot.HOUR_6_730) return "6:00-7:30";
        if (start_time == Slot.HOUR_730_9PM) return "7:30-9:00 PM";
        if (start_time == Slot.HOUR_7_9) return "7:00-9:00 AM";
        if (start_time == Slot.HOUR_9_11) return "9:00-11:00";
        if (start_time == Slot.HOUR_11_13) return "11:00-1:00";
        if (start_time == Slot.HOUR_13_15) return "1:00-3:00";
        if (start_time == Slot.HOUR_15_17) return "3:00-5:00";
        if (start_time == Slot.HOUR_17_19) return "5:00-7:00";
        if (start_time == Slot.HOUR_19_21) return "7:00-9:00 PM";
        return Component.NULL_NAME;
    }

    /**Returns true if the day given in the argument is a valid schedule day.
     * @param day
     * @return  
     */
    public static boolean isValidDay(int day){ return (day>=Slot.MONDAY && day<= Slot.SATURDAY ); }

    /**Returns true if the hour given in the argument is a valid schedule hour(time slot).
     * @param time
     * @return  
     */
    public static boolean isValidTimeSlot(int time){ return (time>=Slot.HOUR_730_9AM && time<=Slot.HOUR_19_21); }
}
