// FIXME: find out how to import the grails classes from the default package,
// or put all the others into correct packages, too, so we can import them
// 
//package de.sprang.timetracker.logic

import Worker

/** 
 * Test class for the MonthlyWorkerReportCalculator.
 * Check if business logic is applied correctly to availably data
 * 
 */
class MonthlyWorkerReportCalculatorTests extends GroovyTestCase {
    Worker testWorker
    List projects
    
    /**
     * Set up some sample data for the test.
     * 
     * TODO: maybe write a parser for our real application data, exported to csv
     */
    void setUp(){
        // TODO: create test data - worker, project and timeclices
        
        /**
        def worker = new Worker()
        
        worker.setFirstName("henning")
        worker.setLastName("sprang")
        worker.setHolidaysPerMonth(25/12)
        worker.setWorkHoursPerDays(8)
        
        // Needed? worker.save()
        testWorker = worker
        
        // TODO: create some projects and timeslices
        def project = new Project(name:"MIE scc-358")    
        def timeSlice = new TimeSlice()
        timeSlice.setWorker(worker)
        
        projects.add(project)

        projects.add(new Project(name:"MIE scc"))
        projects.add(new Project(name:"MIE scc-360"))
        projects.add(new Project(name:"MIE scc-363"))
        projects.add(new Project(name:"HWC Vortrag"))
        projects.add(new Project(name:"Picture Frame Remoting"))
        
        // TODO: create some timeslices for the worker and the projects
        */
    }
    
	/**
	 * Test the calculation of the monthly report.
	 * 
	 * Prerequisites:
	 * - need a worker, a project and some timeslices to calculate stuff
	 * Expected results:
	 * - ...
	 */
	void testCalculation(){
        // TODO: create an instance of the calulator class and set worker and 
        
        // TODO: 
        
        // TODO: run the calculation
        
        // TODO: check the results
        
        println TimeSlice.list()
        println Worker.list()
    	    
	}
}