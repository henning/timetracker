//package webtest

import java.text.SimpleDateFormat

class ReportWebTest extends grails.util.WebTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        //testReportListNewDelete()
        testMonthlyList()
        // add tests for more operations here
    }

//
//    def testReportListNewDelete() {
//        webtest('Report basic operations: view list, create new entry, view, edit, delete, view') {
//
//            invoke      'report'
//            verifyText  'Home'
//
//            verifyListSize 0
//
//            clickLink   'New Report'
//            verifyText  'Create Report'
//            clickButton 'Create'
//            verifyText  'Show Report', description:'Detail page'
//            clickLink   'List', description:'Back to list view'
//
//            verifyListSize 1
//
//            group(description:'edit the one element') {
//                showFirstElementDetails()
//                clickButton 'Edit'
//                verifyText  'Edit Report'
//                clickButton 'Update'
//                verifyText  'Show Report'
//                clickLink   'List', description:'Back to list view'
//            }
//
//            verifyListSize 1
//
//            group(description:'delete the only element') {
//                showFirstElementDetails()
//                clickButton 'Delete'
//                verifyXPath xpath:  "//div[@class='message']",
//                            text:   /.*Report.*deleted.*/,
//                            regex:  true
//            }
//
//            verifyListSize 0
//        }
//    }

    String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

    def verifyListSize(int size) {
        ant.group(description:"verify Report list view with $size row(s)") {
            verifyText  'Report'
            verifyXPath xpath:      ROW_COUNT_XPATH,
                        text:       size,
                        description:"$size row(s) of data expected"
        }
    }

    def showFirstElementDetails() {
        ant.clickLink   '1', description:'go to detail view'
    }


    /**
     * TODO: better adjust to monthly reports! check number of days!
     * mabye add another one that can check for number of slices for a given day!
     */
    def verifyMonthReportSize(int size) {
        ant.group(description:"verify TimeSlice list view with $size row(s)") {
            verifyText  'Monthly Report'
            verifyXPath xpath:      "count(//div[@class='list'])",
                        text:       size,
                        description:"$size row(s) of data expected"
        }
    }



    def testMonthlyList(){
        webtest( Thread.currentThread().getStackTrace()[2].getMethodName() + 'Report list/reporting operations: list default, list a given month') {
            invoke      'report'

            def dateString = new SimpleDateFormat("yyyy-MM").format(new Date())

            verifyText  'Summary for ' + dateString

            verifyMonthReportSize 0

            // shouldn't even be there! this is timeSlice stuff!
            
            clickLink   'TimeSlice List', description:'Back to list view'
            verifyListSize 7
            clickLink '3'
            clickButton 'Edit'
            setSelectField(name: "project.id", text: "one projekt")
            clickButton 'Save'

            // create an entry for today and test new list size/structure

            clickLink   'New TimeSlice'
            clickButton 'Create'

            clickLink 'Report', description:'Back to monthReport'

            verifyMonthReportSize 1

            // TODO: better check for monthly and daily summary!

            // check that fiels are filled with current dates:
            verifyInputField(name:"year", value: new SimpleDateFormat("yyyy").format(new Date()))
            verifyInputField(name:"month", value: new SimpleDateFormat("MM").format(new Date()))

            setInputField(name:"year", value: "2008")
            setInputField(name:"month", value: "09")

            clickButton 'Show'

            verifyText  'Summary for 2008-09'
            verifyText 'Total Worktime: 2'
            verifyText 'Times without Project: 1'

            // TODO: doesn#t work because fixtures do not load one to one correctly!
            // report fixtures bug, use "normal build" fixtures!


            //TimeSlice.get(3).setProject(Project.get(1)).save()

            //verifyText 'Projects:'
            //verifyText 'one projekt: 1'

            verifyMonthReportSize 4



            // TODO: check correct report data for given month

            // TODO; check year/month fileds contain the currently reported month

            // TODO: create another entry, at an other day of this month and test new list size and all the summaries

            // TODO: check for "next/previous" month navigation buttons

        }
    }

  def testProjectReport(){
        webtest( Thread.currentThread().getStackTrace()[2].getMethodName() + ' - single Project report') {

           invoke      'report'

           clickLink 'by Project'

           verifyText 'select Project'

           // TODO: select project "projectReportTestProject"


           // TODO: check if we have 3 entries and alltogether 6 hours

          
        }
  }
}