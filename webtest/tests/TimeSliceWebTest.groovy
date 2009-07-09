//package webtest

import java.text.DateFormat
import java.text.SimpleDateFormat

class TimeSliceWebTest extends grails.util.WebTest {

  static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd")
  static DateFormat timeFormat = new SimpleDateFormat("HH");

  // Unlike unit tests, functional tests are often sequence dependent.
  // Specify that sequence here.
  void suite() {
    testTimeSliceBasicListNewDelete()
  }


  String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

  def verifyListSize(int size) {
    ant.group(description:"verify TimeSlice list view with $size row(s)") {
      verifyText  'TimeSlice List'
      verifyXPath xpath:      ROW_COUNT_XPATH,
              text:       size,
              description:"$size row(s) of data expected"
    }
  }

  def showMyElementDetails(int id) {
    ant.clickLink   id, description:'go to detail view'
  }

  def testTimeSliceBasicListNewDelete() {
    webtest('TimeSlice basic operations: view list, create new entry, view, edit, delete, view') {

      invoke      'timeSlice'
      verifyText  'Home'
      
      verifyListSize 8

      clickLink   'TimeSlice List'

      verifyListSize 8

      clickLink   'New TimeSlice'
      verifyText  'Create TimeSlice'

      // check that current date and time is entered as default!
      // TODO: some of thes actually test the controller's functionality...
      def date = new Date()

      verifyInputField(name: "startDateString", value: dateFormat.format(date))
      verifyInputField(name: "startTimeString", regex: true, value: timeFormat.format(date) + ":*")
      verifyInputField(name: "endDateString", value: dateFormat.format(date))
      verifyInputField(name: "endTimeString", regex: true, value: timeFormat.format(date) + ":*")

      setInputField(name: "startDateString", value: "2008-05-18")
      setInputField(htmlId: "startDateString", value: "2008-05-18")
      setInputField(name: "startTimeString", value: "10:15")
      setInputField(htmlId: "startTimeString", value: "10:15")
      setInputField(name: "endDateString", value: "2008-05-18")
      setInputField(htmlId: "endDateString", value: "2008-05-18")
      setInputField(name: "endTimeString", value: "11:15")
      setInputField(name: "description", value: "Kram gemacht")      

      clickButton 'Create'
      verifyText  'Show TimeSlice', description:'Detail page'

      // TODO: mehr Sachen pr?fen!
      verifyText(description: "Verify that text is contained in the page", "2008-05-18 10:15:00.0")
      verifyText(description: "Verify that text is contained in the page", "2008-05-18 11:15:00.0")
      verifyText(description: "Verify that text is contained in the page", "TimeSlice 9 saved")

      clickLink   'List', description:'Back to list view'

      verifyListSize 9

      clickLink   'New TimeSlice'
      clickButton 'Create'
      clickLink   'List', description:'Back to list view'

      verifyListSize 10


      group(description:'edit the one element') {
        //showMyElementDetails(9)
        clickLink 'Kram gemacht'
        clickButton 'Edit'
        verifyText  'Edit TimeSlice'

        verifyInputField(name: "startDateString", value: "2008-05-18")
        verifyInputField(name: "endDateString", value: "2008-05-18")
        verifyInputField(name: "startTimeString", value: "10:15")
        verifyInputField(name: "endTimeString", value: "11:15")

        setInputField(htmlId: "endDateString", value: "2008-05-19")
        setInputField(name: "endTimeString", value: "11:30")
        setInputField(name: "description", value: "lalala")

        clickButton 'Save'
        verifyText  'Show TimeSlice'

        verifyText(description: "Verify that text is contained in the page", "2008-05-18 10:15:00.0")
        verifyText(description: "Verify that text is contained in the page", "2008-05-19 11:30:00.0")
        verifyText(description: "Verify that text is contained in the page", "lalala")

        clickLink   'List', description:'Back to list view'
      }

      verifyListSize 10

      group(description:'delete the only element') {
        showMyElementDetails(9)
        clickButton 'Delete'
        //verifyXPath xpath:  "//div[@class='message']",
        //text:   /.*TimeSlice.*deleted.*/,
        //            regex:  true
        clickLink   'List', description:'Back to list view'
      }

      verifyListSize 9
    }
  }
}