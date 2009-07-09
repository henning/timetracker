<div class=sliceSummary>
  <g:if test="${summarySlices != null}">

    <h2>Summary for ${summaryTitle}</h2>
    <p>Total Worktime: <g:formatNumber number="${summarySlices.totalWorkTime/60}" format="###.##" /></p>

    <g:if test="${summarySlices.noProjectWorkTime>0}">
      <p>Times without Project: <g:formatNumber number="${summarySlices.noProjectWorkTime/60}" format="###.##" /> </p>
    </g:if>

    <g:if test="${ summarySlices.projectWorkTimes.size() != 0 }">
      <h3>Projects:</h3>
      <g:each in="${summarySlices.projectWorkTimes.keySet()}" var="project">
      <p>${project}: <g:formatNumber number="${summarySlices.projectWorkTimes[project]/60}" format="###.##" /></p>
      </g:each>
    </g:if>

    <br>
  </g:if>
  <g:else>
    Nothing to summarize  ${summarySlices}
  </g:else>
</div>