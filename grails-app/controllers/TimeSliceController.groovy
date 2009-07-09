import java.text.DateFormat
import java.text.SimpleDateFormat

class TimeSliceController {

    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd")
    static DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    
    def index = {
            redirect(action:list)
    }

    // the delete, save and update actions only accept POST requests
    static def allowedMethods = [delete:'POST', save:'POST']


    /**
     * Basic slice listing.
     */
    def list = {
      if(!params.max) params.max = 20
      if(params.sort == null | params.sort == ""){
          params.sort = "startTime"
      }
      if(params.order == "" || params.order == null){
          params.order = "desc"
      }

      [ timeSliceList: TimeSlice.list( params) ]
    }
    

    /**
     * Show a single slice as given by param id
     */
    def show = {
        def timeSlice = TimeSlice.get( params.id )

        if(!timeSlice) {
            flash.message = "TimeSlice not found with id ${params.id}"
            redirect(action:list)
        }
        
        return [ timeSlice : timeSlice ] 
        
    }

    /**
     * Delete a slice.
     */
    def delete = {
        def timeSlice = TimeSlice.get( params.id )
        
        if(!timeSlice) {
            flash.message = "TimeSlice not found with id ${params.id}"
            redirect(action:index)
        }

        timeSlice.delete()
        flash.message = "TimeSlice ${params.id} deleted"
        redirect(action:index)                
    }
    
    
    /**
     * Start editing a slice.
     */
    def edit = {
        def timeSlice = TimeSlice.get( params.id )

        if(!timeSlice) {
            flash.message = "TimeSlice not found with id ${params.id}"
            redirect(action:index)
        }
        
        StartCommand start = new StartCommand()
        start.setStartDateString(dateFormat.format(timeSlice.getStartTime()))
        start.setStartTimeString(timeFormat.format(timeSlice.getStartTime()))
        
        EndCommand end = new EndCommand()
        end.setEndDateString(dateFormat.format(timeSlice.getEndTime()))
        end.setEndTimeString(timeFormat.format(timeSlice.getEndTime()))
        
        return ['timeSlice':timeSlice, 'start':start, 'end':end]
        
    }

    def create = {
        def timeSlice = new TimeSlice()
        timeSlice.properties = params
        
        StartCommand start = new StartCommand()
        start.setStartDateString(dateFormat.format(new Date()))
        start.setStartTimeString(timeFormat.format(new Date()))
        
        EndCommand end = new EndCommand()
        end.setEndDateString(dateFormat.format(new Date()))
        end.setEndTimeString(timeFormat.format(new Date()))
        
        return ['timeSlice':timeSlice, 'start':start, 'end':end]
    }

    def save = { StartCommand start, EndCommand end ->
        def timeSlice = null
        if ( params.id != null) {
            timeSlice = TimeSlice.get(params.id )
            if(timeSlice == null ){
                flash.message = "TimeSlice not found with id ${params.id}"
                redirect(action:edit,id:params.id)
            }
            timeSlice.properties = params
        } else {
            timeSlice = new TimeSlice(params)
        }

        assert(timeSlice != null)
        timeSlice.setStartTimeFromStrings(start.startDateString,start.startTimeString)
        timeSlice.setEndTimeFromStrings(end.endDateString, end.endTimeString)
        
        if(!timeSlice.hasErrors() && timeSlice.save()) {
            flash.message = "TimeSlice ${timeSlice.id} saved"
            redirect(action:show,id:timeSlice.id)
        }
        else {
            render(view:'create',model:[timeSlice:timeSlice, start:start, end:end])
        }
    }
    
}

/**
 * A class that defines the date/time inputs that need to become parsed
 * TODO: start using this instead of StartCommand/EndCommand!
 * NOT YET USED!!!
 */
class DateTimeEntriesCommand{
    static constraints = {
        // TODO: date and times must be parseable
        // TODO: endTime must be after startTime
    }
    
    String date
    String startTime
    String endTime
}


// TODO: maybe only one command for start and end?!
class StartCommand {
    static constraints = {
        // TODO: must define stuff! regex for date/time patterns!
    }
    String startDateString
    String startTimeString
}

class EndCommand {
    static constraints = {
        // TODO: must define stuff!
    }
    String endDateString
    String endTimeString
}
