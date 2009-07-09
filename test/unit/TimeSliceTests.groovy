import java.text.SimpleDateFormat
import java.text.DateFormat

class TimeSliceTests extends GroovyTestCase {
    
    def dateString = "2008-11-02"
    def timeString = "10:22"

    def DateFormat timeFormatter = new SimpleDateFormat("hh:mm");
    def timeSlice = new TimeSlice()
    def endTimeString = "11:22"
    
    void setUp() {                
        this.timeSlice.setStartTimeFromStrings(this.dateString, this.timeString)
        this.timeSlice.setEndTimeFromStrings(this.dateString, this.endTimeString)
    }
    
    void testDateSetting() {        
        assertEquals(dateString, TestConstants.dateFormatter.format(timeSlice.getStartTime()))
        assertEquals(timeString, timeFormatter.format(timeSlice.getStartTime()))  
        
        assertEquals(dateString, TestConstants.dateFormatter.format(timeSlice.getEndTime()))
        assertEquals(endTimeString, timeFormatter.format(timeSlice.getEndTime()))
    }
    
    void testDurationCalculation(){
        assertEquals(60, timeSlice.getDurationInMinutes())
        assertEquals(1, timeSlice.getDurationInHours())
    }
    
    // TODO: test timeSlice sorting!!!
    
    
}
