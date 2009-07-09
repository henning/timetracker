class XclassNameXTest extends grails.util.WebTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testXclassNameXListNewDelete()
        // add tests for more operations here
    }

    def testXclassNameXListNewDelete() {
        webtest('XclassNameX basic operations: view list, create new entry, view, edit, delete, view') {
            
            invoke      'XpropertyNameX'
            verifyText  'Home'

            verifyListSize 0

            clickLink   'New XclassNameX'
            verifyText  'Create XclassNameX'
            clickButton 'Create'
            verifyText  'Show XclassNameX', description:'Detail page'
            clickLink   'List', description:'Back to list view'

            verifyListSize 1

            group(description:'edit the one element') {
                showFirstElementDetails()
                clickButton 'Edit'
                verifyText  'Edit XclassNameX'
                clickButton 'Update'
                verifyText  'Show XclassNameX'
                clickLink   'List', description:'Back to list view'
            }

            verifyListSize 1

            group(description:'delete the only element') {
                showFirstElementDetails()
                clickButton 'Delete'
                verifyXPath xpath:  "//div[@class='message']",
                            text:   /.*XclassNameX.*deleted.*/,
                            regex:  true
            }

            verifyListSize 0
        }
    }

    String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

    def verifyListSize(int size) {
        ant.group(description:"verify XclassNameX list view with $size row(s)") {
            verifyText  'XclassNameX List'
            verifyXPath xpath:      ROW_COUNT_XPATH,
                        text:       size,
                        description:"$size row(s) of data expected"
        }
    }

    def showFirstElementDetails() {
        ant.clickLink   '1', description:'go to detail view'
    }
}