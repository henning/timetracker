class ReportController {
    def index = {redirect(action:monthlyReport) }

    /**
    * Builds a report by month, sorted by days, with summaries.
    *
    */
    def monthlyReport = {

        def monthString
        def year
        def month
        //TODO: better checks for month/year!

        def date = new Date()
        if (params.year == null ){
          year = TimeTrackerConstants.yearFormatter.format(date)
        } else {
          year = params.year
        }

        if (params.month == null){
          month = TimeTrackerConstants.monthFormatter.format(date)
        } else {
          month = params.month
        }

        monthString = year + "-" + month

        List<TimeSlice> monthSlices = TimeSlice.getSlicesByMonthAndProject(
            params.year, params.month, params["projectSelection"])

        SliceList allSlices = new SliceList(monthSlices)
        List<DailySliceList> dailySliceLists = DailySliceList
            .buildDayLists(allSlices.slices)

        return [
                allSlices:allSlices,
                dailySliceLists:dailySliceLists,
                monthString:monthString,
                year:year,
                projectList:Project.list(),
                selectedProject:Project.get(params["projectSelection"]),
                month:month]
      }
}
