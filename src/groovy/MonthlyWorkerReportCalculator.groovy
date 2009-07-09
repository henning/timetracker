/**
 * This class is responsible for calculating a Monthly report according to our
 * calculating rules.
 * 
 * That means:
 * 
 * - given a worker, and a month(in a specific year), get all timeslices  for which
 *   either the start or the end, or both are in this month
 * - count all hours worked in these timeslices together - for the slices which 
 *   have start or end outside the month, only count the hours ín the month, not
 *   the others
 * - count workdays in the month:
 *   - number of monday to friday days, minus (bank) holidays, minus sick days, 
 *     minus     
 */
class MonthlyWorkerReportCalculator {
	static void main(args) {
		
	}
}