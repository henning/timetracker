<g:each  var="sliceList" in="${dailySliceLists}">
  <g:render template="/dailySliceList" model="[sliceList:sliceList]"/>
</g:each>