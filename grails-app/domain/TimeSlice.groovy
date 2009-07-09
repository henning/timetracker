

/**
 * Slice of time spent by a worker doing some work on a project.
 * TODO: find out how this could be used with and as taggable!
 * like: "class TimeSlice implements Taggable {"...
 * 
 * TODO: have some method getDate() to return a trincated date of start/end date
 * or an exception if start and end are not the same day!
 * 
 * replace this method in the place where we currently use the startDate as 
 * generic date!
 *
 */
class TimeSlice {
    
     // TODO: more criteria!
     // * startTime _after_ endTime!
     // * date of start and end must be the same day!
     //   (also change this in the web-GUI! will make many things easier!)
     
    static constraints = {
        startTime()
        endTime()
        description()
        project(nullable:true)
        notes(maxSize:10000, nullable: true)
        worker(nullable:true)        
    }
    
    /** tags for this slice. */
    static hasMany = [ tags : Tag ]
    
    /** The Project on which the work has been done */
    static belongsTo = [ project : Project , worker : Worker ]
    
    /** The project this timeslice has been spent on doing stuff */
    Project project
    
    /** The worker who did the work */
    Worker worker

    /** When did the work start */
    Date startTime

    /** When did the work end */
    Date endTime     

    /** short descripion of the work */
    String description
    
    /** additional longer notes */
    String notes
   
    String toString(){
        return description
    }
    
    /**
     * Set start Time from a date-String and a time-String
     */
    def setStartTimeFromStrings(String date, String time){
        this.setStartTime(TimeTrackerConstants.dateTimeFormatter.parse(date + " " + time))        
    }
    
    /**
     * Set end time from a date-String and a time-String
     */
    def setEndTimeFromStrings(String date, String time){
        this.setEndTime(TimeTrackerConstants.dateTimeFormatter.parse(date + " " + time))        
    }
    
    def getDurationInMinutes(){
        def seconds = getEndTime().getTime()/1000 - getStartTime().getTime()/1000
        return seconds/60
    }
    
    def getDurationInHours(){
        // TODO: format to 2 fields after point!
        return getDurationInMinutes()/60
    }

    /**
     * Get slices by Strings representing month, year and a project.
     * Accept null values and react with nice defaults.
     *
     * FIXME: probably, this ratther belongs to a service?!
     * 
     */
    static getSlicesByMonthAndProject(String year, String month, String projectId){

        def ourDate
        if(year != null && year != "" && month != ""){
            def monthYear = year + "-" + month
            ourDate = TimeTrackerConstants.monthYearFormatter.parse(monthYear)
        } else {
            ourDate = new Date()
        }

        Calendar minCal = Calendar.getInstance()
        minCal.setTime(ourDate)
        minCal.set(Calendar.DAY_OF_MONTH, 1)

        def maxCal = Calendar.getInstance()
        maxCal.setTime(ourDate)
        maxCal.add(Calendar.MONTH, 1)

        def criteria = TimeSlice.createCriteria()


        def slices = criteria.list {
            and {
                if(projectId != null && projectId != "") {
                    // FIXME: quite ugly way to handle the search for no-project
                    // entries?! make it bette rsomehow?!
                    if(projectId == "***none***") {
                       println("non projects search!!!")
                       isNull("project")
                    } else {
                    
                        project {
                            eq("id", Long.parseLong(projectId))
                        }
                    
                    }
                }

                ge("startTime", minCal.getTime())
                le("endTime", maxCal.getTime())
            }
            maxResults(100)
            order("startTime", "desc")

        }

        return slices
    
    }
}
