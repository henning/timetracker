//import grails.util.DomainBuilder
import Project

/**
 * @author henning
 *
 */
public class SliceListTests extends TestBase{

    void testConstructor(){
        def myNewSliceList = new SliceList(testSlices)
        
        assertEquals(5, myNewSliceList.size())
        assertEquals(323, myNewSliceList.getTotalWorkTime())
        assertEquals(143, myNewSliceList.getNoProjectWorkTime())
        
        def projectWorkTimes = myNewSliceList.getProjectWorkTimes()
        assertEquals(2, projectWorkTimes.size())
        
        assertEquals(Project, projectWorkTimes.keySet().asList().get(1).getClass())
    }
    
}
