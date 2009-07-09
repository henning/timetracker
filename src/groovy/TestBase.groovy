//import grails.util.DomainBuilder
import Project

/**
 * @author henning
 * Some generically useable test data
 */
class TestBase extends GroovyTestCase {
    def testSlices = []
    def projectOne
    def projectTwo
    def workerOne
    def timeSliceOne
    def timeSliceTwo
    def timeSliceThree
    def timeSliceFour
    def timeSliceFive
    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    void setUp() throws Exception{
        projectOne = new Project(name:"one projekt")
        projectTwo = new Project(name:"project Two")
        
            
        workerOne = new Worker(
            firstName: "Bernd",
            lastName: "Becker",
            email: "bernd@example.com",
            holidaysPerMonth: 3,
            workHoursPerDay: 8
        )
            
        timeSliceOne = new TimeSlice(
            startTime: TimeTrackerConstants.dateTimeFormatter.parse("2008-09-01 12:00"),
            endTime: TimeTrackerConstants.dateTimeFormatter.parse("2008-09-01 13:00"),
            description: "was gemacht 1",  
            project: projectOne,
        )
                   
        testSlices.add(timeSliceOne)
            
        timeSliceTwo = new TimeSlice (
            startTime: TimeTrackerConstants.dateTimeFormatter.parse("2008-09-01 00:00"),
            endTime: TimeTrackerConstants.dateTimeFormatter.parse("2008-09-01 01:00"),
            description: "noch was gemacht 2",
            project: projectTwo,
        )
        testSlices.add(timeSliceTwo)
            
        timeSliceThree = new TimeSlice(
            startTime: TimeTrackerConstants.dateTimeFormatter.parse("2008-09-10 12:00"),
            endTime: TimeTrackerConstants.dateTimeFormatter.parse("2008-09-10 13:00"),
            description: "noch was gemacht 3",
            project: projectOne,
        )
        testSlices.add(timeSliceThree)
            
        timeSliceFour = new TimeSlice(
            startTime: TimeTrackerConstants.dateTimeFormatter.parse("2008-09-11 12:00"),
            endTime: TimeTrackerConstants.dateTimeFormatter.parse("2008-09-11 13:00"),
            description: "noch was gemacht 4",
        )
               
        testSlices.add(timeSliceFour)
            
        timeSliceFive = new TimeSlice(
            startTime: TimeTrackerConstants.dateTimeFormatter.parse("2008-09-11 14:00"),
            endTime: TimeTrackerConstants.dateTimeFormatter.parse("2008-09-11 15:23"),
            description: "noch was gemacht 5",
        ) 
        testSlices.add(timeSliceFive)
    
    }
    void testNothing(){
        assertTrue(true)
    }
}
