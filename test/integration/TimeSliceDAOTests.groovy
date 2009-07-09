import java.text.SimpleDateFormat
import java.text.DateFormat

class TimeSliceDAOTests extends GroovyTestCase {
    def DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM");

    List testProjects = new ArrayList()

    Project testProject

    /**
     * Create some test entries according to prerequisites of our tests!
     */
    void setUp() {

        // test month: 06/2009
        testProject = new Project(name:"timeslice testproject")
        testProject.save()
        testProjects.add(testProject)

        Project anotherProject = new Project(name:"whatever project")
        anotherProject.save()

        // This one should NOT appear in report for 06!
        new TimeSlice(
            startTime:TimeTrackerConstants.dateTimeFormatter
                .parse("2009-05-31 22:00"),
            endTime:TimeTrackerConstants.dateTimeFormatter
                .parse("2009-05-31 24:00"),
            project:anotherProject,
            description:"did some stuff in another month"
        ).save()


        new TimeSlice(
            startTime:TimeTrackerConstants.dateTimeFormatter
                .parse("2009-06-01 00:00"),
            endTime:TimeTrackerConstants.dateTimeFormatter
                .parse("2009-06-01 02:30"),
            project:testProject,
            description:"did some stuff"
        ).save()


        new TimeSlice(
            startTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-06-01 01:00"),
            endTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-06-01 02:30"),
            description:"did some stuff"
        ).save()

        new TimeSlice(
            startTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-06-12 22:00"),
            endTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-06-12 23:00"),
            project:testProject,
            description:"did some stuff"
        ).save()

        new TimeSlice(
            startTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-06-30 22:00"),
            endTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-06-30 24:00"),
            project:testProject,
            description:"did some stuff"
        ).save()

        // should NOT appear in 06 report!
        new TimeSlice(
            startTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-07-01 00:00"),
            endTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-07-1 02:00"),
            description:"did some stuff"
        ).save()

        TimeSlice searchTestSlice = new TimeSlice()
        searchTestSlice.save()

    }

    /**
     * Test getting lists by month and project.
     *
     * Prerequisites:
     * <ul>
     * <li> Some projects in the database for a specific month
     * <li> multiple different projects
     * <li> without project
     * <li> border cases:
     * <ul>
     *   <li>start at 00:00 of the first day
     *   <li>end 24:00 of the last day of month,
     *   <li>start at 00:00 of the first day of the next month
     *   <li> end 24:00 of the last day previous month
     * </ul>
     *
     * Expectations:
     * <ul>
     * <li> return the "correct" slices according givne month, year, project
     * <li> When given "" or null as Project, return everything regardless project
     * <li> when given ***none*** as project return only entries without project
     * <li> slices sorted ascending by start time
     * </ul>
     */
    void testGetSlicesByMonthAndProject(){

        //assertEquals(0, TimeSlice.list())

        List slices =  TimeSlice.getSlicesByMonthAndProject(null, null, null);

        // TODO: check that we have the slices of the current month!

        slices = TimeSlice.getSlicesByMonthAndProject("2009", "06", "");

        assertEquals( 4, slices.size())

        slices = TimeSlice.getSlicesByMonthAndProject("2009", "06",
            String.valueOf(testProject.getId()));

        assertEquals(3, slices.size())

        slices = TimeSlice.getSlicesByMonthAndProject("2009", "06", "***none***")

        assertEquals(1, slices.size())
        
    }
    
}