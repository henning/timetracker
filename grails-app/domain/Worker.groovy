/** 
 * Somebody who spends time, represented by timeslices, 
 * working on projects.
 */
class Worker {
    
    static hasMany = [ 
                       // the timeslices this worker did
                       timeslices:TimeSlice, 
                       // the days ths worker did not work 
                       offDays:DayOff 
                     ]
    
    /** first name of the worker */
    String firstName
    /** Last name of the worker */
    String lastName
    /** Email of the worker */
    String email
    /** Free days this worker gets per Month */
    Integer holidaysPerMonth 
    /** Hours of work per day this worker is contracted for */
    Integer workHoursPerDay
    
    String toString(){
        def myName = firstName + " " + lastName
        return myName
    }
}
