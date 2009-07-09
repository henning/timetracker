<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Monthly Report</title>
    </head>
    <body>
        
        <div class="body">
            <h1>Report</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>


          <div id="criteriaSelector">
          <g:form method="post">

            Project:${selectedProject?.id}
                <g:select
                  name='projectSelection'
                  value="${selectedProject?.id}"
                  noSelection="${['':'Any']}"
                  from='${projectList}'
                  optionKey="id"
                  optionValue="name">
                </g:select>

              Year: <g:textField name="year" value="${year}" />
              Month:  <g:textField name="month" value="${month}" />

              <!-- TODO: add next/previous month buttons -->

              <!-- TODO: other choic of time range: -->
                <br>
                From Date: <b>TODO</b>
                <br>
                Until Date: <b>TODO</b>
                <br>
                Worker: <b>TODO</b>
              <g:actionSubmit action="monthlyReport" value="Show"/>

            </g:form>
            </div>



            <g:render template="/sliceSummary" model="[summarySlices:allSlices, summaryTitle:monthString]" />

            <g:render template="/multiDailySliceList" model="[dailySliceLists:dailySliceLists]"/>

        </div>
    </body>
</html>
