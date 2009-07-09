import org.apache.commons.lang.time.DateUtils
/**
 * Class to keep slices for a single day.
 */
public class DailySliceListTests extends TestBase{
     
     /**
      * Test sorting of a daily SliceList.
      * TODO: better make a more simple test of the comparison methods!
      */
     void testSliceListSorting(){
         TimeSlice timeSliceOlder = new TimeSlice(
                 startTime: TimeTrackerConstants.dateTimeFormatter.parse("2008-09-03 12:00"),
                 endTime: TimeTrackerConstants.dateTimeFormatter.parse("2008-09-03 13:00"),
                 description: "noch was gemacht 3",
                 project: projectOne,
             )
         testSlices.add(timeSliceOlder)
         
         TimeSlice timeSliceNewer = new TimeSlice(
                 startTime: TimeTrackerConstants.dateTimeFormatter.parse("2008-09-06 12:00"),
                 endTime: TimeTrackerConstants.dateTimeFormatter.parse("2008-09-08 13:00"),
                 description: "noch was gemacht 3",
                 project: projectOne,
             )
         testSlices.add(timeSliceNewer)
         
         List dailySlices = DailySliceList.buildDayLists(testSlices) 
         
         Collections.sort(dailySlices)
         
         assertEquals(DateUtils.truncate(timeSliceTwo.startTime, Calendar.DATE), dailySlices[0].date)
         assertEquals(DateUtils.truncate(timeSliceFive.startTime, Calendar.DATE), dailySlices.last().date )

     }
     
     /**
      * Test the static method that creates a list of DailyTimeSliceLists from a 
      * list of timeslices.
      * TODO
      * Prerequisites: a list of TimeSLices
      * Expectation: a list of DailySliceList instances
      * 
      */ 
     void testMultiDailyListBuilder(){
         List myDailyLists = DailySliceList.buildDayLists(testSlices)
         
         Date date = new Date()
         
         assertEquals(3, myDailyLists.size())
     }
     
     
     /**
      * Test the constructor that takes a list of timeslices.
      * Prerequisistes: a list of timeslices
      * Expectation: 
      * * sets the date to the date of the first slice in the list
      * * adds all the slices and calls calculate
      * 
      */
     void testSingleSliceConstructor(){
         def myDailyList = new DailySliceList(timeSliceOne)
         
         // TODO: check if the correct date is set! - a new date with only the year, 
         // month, day componets of the timeslice date!

         Date date =  TestConstants.dateFormatter.parse("2008-09-01")
         assertTrue(DateUtils.isSameDay(date, myDailyList.getDate()))
         assertEquals(date, myDailyList.date)
         assertEquals("2008-09-01", myDailyList.dateString)
         
         assertNotNull(myDailyList.getSlices())
     }
     
     /**
      * Test adding slices.
      * Cases:
      * * A slice that matches the current day is added - true returned
      * * A slice that doesn't match or already exists is discarded - false returned
      * * 
      */
     void testAddSlice(){
         def timeSliceSameAsOne = new TimeSlice(
                 startTime: TimeTrackerConstants.dateTimeFormatter.parse("2008-09-01 13:00"),
                 endTime: TimeTrackerConstants.dateTimeFormatter.parse("2008-09-01 14:00"),
                 description: "was gemacht 1",  
                 project: projectOne,
             )
         def myDailyList = new DailySliceList(timeSliceOne)
         assertEquals(1, myDailyList.size())
         
         assertTrue(myDailyList.addSlice(timeSliceSameAsOne))
         assertEquals(2, myDailyList.size())
         
         assertFalse(myDailyList.addSlice(timeSliceThree))
         assertEquals(2, myDailyList.size())
         
         assertFalse(myDailyList.addSlice(timeSliceOne))
         assertEquals(2, myDailyList.size())
     }
     
     /**
      * Test the constructor with date
      * TODO
      
     void testAddManySlices(){
         
     }
     */
     
     
     
}