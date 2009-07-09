import org.apache.commons.lang.time.DateUtils


class ReportControllerTests extends TestBase {
//    def myGetSlicesPerMonthOriginal = TimeSlice.metaClass.static.getSlicesByMonth
//
//    public void setUp(){
//      myGetSlicesPerMonthOriginal = TimeSlice.metaClass.static.getSlicesByMonth
//
//    }
//
//    public void tearDown(){
//        TimeSlice.metaClass.static.getSlicesByMonth = myGetSlicesPerMonthOriginal
//    }

    /**
     * Test Controllers list by Month function.
     * Prerequisites: controller get's called with a year and month value.
     * Expectation(in context):
     * * dailySliceLists: list of dailySliceLists for the given month
     * * allSlices: a SliceList of all slices
     */
    void testReportByMonth() {

        ReportController.metaClass.getParams = {-> [year: "2008", month: "09"] }
        def controller = new ReportController()
        def model = controller.monthlyReport()

        assertNotNull(model)
        assertNotNull(model.dailySliceLists)
        assertNotNull(model.allSlices)

        assertEquals(10, model.allSlices.size())

        assertEquals(DailySliceList, model.dailySliceLists[0].getClass())
        assertEquals(SliceList, model.allSlices.getClass())
        assertEquals(TimeSlice, model.allSlices.slices[0].getClass())

        assertNotNull(model.monthString)
    }

    /**
     * Test controllers list default without explicit year/month parameter.
     * Prerequisites: controller called with list
     * Expectation:
     * * list of dailySliceLists of the current month
     */
    void testDefaultReport() {
        def myGetSlicesPerMonthOriginal = TimeSlice.metaClass.static.getSlicesByMonth
        // FIXME: breaks after upgrade to grails 1.1
        TimeSlice.metaClass.static.getSlicesByMonth = {String monthYear ->
            assert monthYear == TestConstants.monthFormatter.format(new Date())
           // return [timeSliceOne, timeSliceTwo, timeSliceThree, timeSliceFour]
        }
        ReportController.metaClass.getParams = {-> [:] }

        // TODO: build controller and call list method!
        def controller = new ReportController()
        def model = controller.monthlyReport()

        assertNotNull(model)
        assertNotNull(model.allSlices)
        assertEquals(SliceList, model.allSlices.getClass())
        assertNotNull(model.year)
        assertNotNull(model.month)

        def todayDate = DateUtils.truncate(new Date(), Calendar.DATE)
        for (slice in model.allSlices.slices) {
            assertEquals(todayDate, DateUtils.truncate(slice.getStartTime(), Calendar.DATE))
        }
        TimeSlice.metaClass.static.getSlicesByMonth = myGetSlicesPerMonthOriginal
    }


    /**
     * Test the reporting of a specific project's data.
     *
     * Prerequisites:
     * <br/>some entries for a specific project that we want to report.
     * <br/>some entries for other projects in the same timeframe - to make sure filtering really works.
     * <br/>a full month/timeframe without any entries for this project
     *
     * Expectation:
     * <br/> summaries are correctly reported
     *
     */
    void testProjectReport() {
        // TODO: set the correct call parameters that we need! Project ID is missing!
        // TODO: where do i get my project from?
        ReportController.metaClass.getParams = {-> [year: "2009", month: "01", project:"...?!"] }

    }
}