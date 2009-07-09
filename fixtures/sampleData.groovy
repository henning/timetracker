// doc: http://www.grails.org/Fixtures+Plugin
import java.text.SimpleDateFormat
import java.text.DateFormat

def DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

fixture {
    projectOne(Project) {
        name = "one projekt"
    }
    projectTwo(Project) {
        name = "project Two"
    }
    projectThree(Project) {
        name = "Another project"
        description = "bla"
    }
    
    workerOne(Worker) { 
            firstName = "Bernd"
            lastName = "Becker"
            email = "bernd@example.com"
            holidaysPerMonth = 3
            workHoursPerDay = 8
    }
    
    timeSliceOne(TimeSlice) {
        startTime = dateFormatter.parse("2008-08-31 23:00")
        endTime = dateFormatter.parse("2008-08-31 24:00")
        description = "was gemacht 1"        
    }
    
    timeSliceTwo(TimeSlice){
        startTime = dateFormatter.parse("2008-09-01 00:00")
        endTime = dateFormatter.parse("2008-09-01 01:00")
        description = "noch was gemacht 2"
    }
    
    timeSliceThree(TimeSlice){
        startTime = dateFormatter.parse("2008-09-10 12:00")
        endTime = dateFormatter.parse("2008-09-10 13:00")
        description = "noch was gemacht 3"
    }
    
    timeSliceFour(TimeSlice){
        startTime = dateFormatter.parse("2008-09-11 12:00")
        endTime = dateFormatter.parse("2008-09-11 13:00")
        description = "noch was gemacht 4"
    }
    
    timeSliceFive(TimeSlice){
        startTime = dateFormatter.parse("2008-09-11 13:00")
        endTime = dateFormatter.parse("2008-09-11 14:00")
        description = "An Projekt 5 gearbeitet"
        // FIXME/BUG: seems not to work!!!
        project = Project.get(1)
        project = projectOne
    }
    
    timeSliceSix(TimeSlice){
        startTime = dateFormatter.parse("2008-09-30 23:00")
        endTime = dateFormatter.parse("2008-09-30 24:00")
        description = "noch was gemacht 6"
    }

    timeSliceSeven(TimeSlice){
        startTime = dateFormatter.parse("2008-10-01 00:00")
        endTime = dateFormatter.parse("2008-10-01 01:00")
        description = "noch was gemacht 6"
    }
}