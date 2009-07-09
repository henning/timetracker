/** 
 * Represents a day off because of sickness or holidays. 
 */
class DayOff {
    static belongsTo = [ worker : Worker ]
    
    /** When was or will be the day off. */
    Date day

    /** States if the day is a holday. Must not be true if isSickDay is true. */
    Boolean isHoliday
    
    /** 
     * True if this day is a sick day called in. Must not be true if isHoliday 
     * is true.
     * 
     * FIXME: would that be better done with an enumeration?! But then we'd have 
     * the possible values encoded in tha class, not in the db!
     */
    Boolean isSickDay 
    
    /** Remarks about that day off. */
    String comment
}
