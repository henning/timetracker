/**
 * A month-end report and summary of the working times  of a worker.
 * FIXME: do we really need this?!
 * We store many stuff here that can easily be calculated in realtime.
 * Storing that things here and now is rather premature optimization...
 * On the other hand - we do not have to store this thing in the Database, but
 * it's good to have a strcuture for it.
 */
class MonthlyWorkerReport {
    static belongsTo = [worker:Worker]
    
    /** The month this report is for */
    Integer Month

    /** The year of this report */
    Integer Year

    /** 
     * How many days should the worker have worked in this month.
     * This is the numer of day from MO-FR this month multiplied with the
     * number of hours the worker is supposed to work, minus the holidays,
     * sich days, and bank holidays.
     */
    Integer expectedWorkHours

    /** 
     * Indicator if this report is finished, and therfore not supposed to
     * change anymore.
     * Is this Monthreport finished? means, it should not be changed
     * anymore, or a change requires certain actions, like re-calculating all
     * following months!
     */
    Boolean isFinished

    /** 
     * How many hours did the worker actually work in the mont of this
     * report.
     * All timeslices of the user that fall into this month (at least partly, 
     * For overnight work, we need to calculate a bit. 
     */
    Integer workHourCount
    
    /** 
     * Overall overtime of the worker this report is for.
     * Last Months overtime plus this months overtime.
     *
     * TODO: field potentially interesting to store in the worker entity,
     * because it belongs to him!
     */
    Integer overallOverTime

    /** 
     * OverTime the worker did this month.
     * 
     */
    Integer thisMonthOverTime

    /** 
     * Overall Holidays for the worker. 
     *
     * TODO: field potentially interesting to store in the worker entity,
     * because it belongs to him!
    */
    Integer holidaysBalance

    /**
     * Holidays the worker did take this month.
     * Means: Project= holiday? Or do we need some extra sick and holidays
     * Entity?
     */
    Integer holidaysTaken

    /** 
     *Days the worker was sick this month.
     * Means: project=sick? or do we need an extra sickday entity?
     */
    Integer sickDays
    
    /** The worker this report is for */ 
    Worker worker
}
