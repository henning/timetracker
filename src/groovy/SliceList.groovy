import org.apache.commons.lang.time.DateUtils/**
 * @author henning
 * A class representing a list of timeSLices, with tha ability to * calculate sums of worktimes. *  *  TODO: why don't we just extend ArrayList here?!!
 */
public class SliceList{
    public SliceList(List mySlices){        this.slices = mySlices        calculateTimes()
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
    }        def calculateTimes(){        this.totalWorkTime = 0        this.noProjectWorkTime = 0        this.projectWorkTimes = [:]        for ( slice in getSlices()){            def sliceDuration = slice.getDurationInMinutes()            totalWorkTime += sliceDuration                        def project = slice.getProject()                        if(project == null) {                noProjectWorkTime += sliceDuration                continue            }                        if (projectWorkTimes[project] == null){                projectWorkTimes[project] = 0            }            this.projectWorkTimes[project] += sliceDuration                }    }    
}
