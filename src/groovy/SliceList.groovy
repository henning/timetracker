import org.apache.commons.lang.time.DateUtils
 * @author henning
 * A class representing a list of timeSLices, with tha ability to
 */
public class SliceList{
    public SliceList(List mySlices){
    }
    
    /**
     * All timeslices for that day
     */
    def slices
    
    /**
     * overall work time
     */
    def totalWorkTime
    
    /**
     * Minutes worked on stuff not belonging to a project
     */
    def noProjectWorkTime
    
    /**
     * A map with the project as the key and the hours worked on it as value.
     */
    def projectWorkTimes
    
    def size(){
         if (slices == null) {
             return 0
         }
         return slices.size()
    }
}