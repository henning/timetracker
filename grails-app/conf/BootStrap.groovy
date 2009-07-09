import grails.util.DomainBuilder
import grails.util.GrailsUtil 
import Worker

class BootStrap {
    def fixtureLoader
    def init = { servletContext ->
    
        println("run Bootstrap - evn: " + GrailsUtil.environment)
        
        switch(GrailsUtil.environment) {
            case "development":
                initDevelopment()
                fixtureLoader.load("sampleData") 
                break
            case "test":
                // initDevelopment()
                // println("loading sample data:")
                fixtureLoader.load("sampleData")
            break
        }     
    }
    
    
    def destroy = {
    
    }
    
    
    public void initDevelopment(){
        def builder = new DomainBuilder()
        def worker = builder.worker( 
                firstName: "Henning",
                lastName: "Sprang",
                email: "henning@sprang.de",
                holidaysPerMonth: 3,
                workHoursPerDay: 8,
        )
        worker.save()
        
      

        // some data for the projectreporting test:


        def reporttestproject1 = new Project(name:"reporttestproject1")
        reporttestproject1.save()

        def reporttestproject2=new Project(name:"reporttestproject2")
        reporttestproject2.save()

        def reporttestproject3=new Project(name:"reporttestproject3")
        reporttestproject3.save()


        // hours for 2009-01:
        //
        
        // sum project 2: 6

        // 2h
        new TimeSlice(
                startTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-01-02 10:30"),
                endTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-01-02 12:30"),
                project:reporttestproject2,
                description:"did some stuff"
        ).save()

        // 2,5 h
        new TimeSlice(
                startTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-01-03 11:30"),
                endTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-01-03 14:00"),
                project: reporttestproject2,
                description:"did some stuff"
        ).save()

        // 1, 5h
        new TimeSlice(
                startTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-01-06 22:30"),
                endTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-01-06 24:00"),
                project:reporttestproject2,
                description:"did some stuff"
        ).save()


        // sum of hours peoject 1: 3,5

        // 2h
        new TimeSlice(
                startTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-01-02 10:30"),
                endTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-01-02 12:30"),
                project:reporttestproject1,
                description:"did some stuff"
        ).save()


        // 1, 5h
        new TimeSlice(
                startTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-01-06 22:30"),
                endTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-01-06 24:00"),
                project:reporttestproject1
        ).save()


        // sum of hours project 3: 2,5

        // 2,5 h
        new TimeSlice(
                startTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-01-03 11:30"),
                endTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-01-03 14:00"),
                project: reporttestproject3,
                description:"did some stuff"
        ).save()


        // other months:

        // 2h
        new TimeSlice(
                startTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-02-02 10:30"),
                endTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-02-02 12:30"),
                project:reporttestproject2,
                description:"did some stuff"
        ).save()

        // 2,5 h
        new TimeSlice(
                startTime:TimeTrackerConstants.dateTimeFormatter.parse("2008-12-03 11:30"),
                endTime:TimeTrackerConstants.dateTimeFormatter.parse("2008-12-03 14:00"),
                project: reporttestproject2,
                description:"did some stuff"
        ).save()

        // 1, 5h
        new TimeSlice(
                startTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-02-06 22:30"),
                endTime:TimeTrackerConstants.dateTimeFormatter.parse("2009-02-06 24:00"),
                project:reporttestproject2,
                description:"did some stuff"
        ).save()

        

        //new TimeSlice(startDate:new Date(), endDate:new Date(), decsription:"done something").save()
        
        /*
        def worker = new Worker()
        worker.firstName = "John"
        worker.lastName = "Doe"
        worker.email = "john@example.com"
        worker.holidaysPerMonth = 2.5
        worker.workHoursPerDay = 8
        worker.save(flush:true) 
        if (worker.hasErrors()) {
            log.error(worker.errors)
        }
        */
         
        /*
        // http://docs.codehaus.org/display/GRAILS/DomainBuilder
        def builder = new DomainBuilder()
        
        
        worker = builder.worker( 
                firstName: "Heinz",
                lastName: "Becker",
                email: "heinz@becker.de",
                holidaysPerMonth: 2,
                workHoursPerDay: 8,
        )
         
        worker.save()
        
         
        //def fixtureLoader

        //def init = {
                //servletContext -> 
                 
                 //if (GrailsUtil.environment == GrailsApplication.ENV_DEVELOPMENT) { 
                        // fixtureLoader.load("somefixture") 
                 //    } 
                 //}
             
         //def destroy = {} 
         */
                      
    }
    
} 