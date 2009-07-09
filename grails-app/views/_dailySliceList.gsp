<h2>${sliceList.dateString}</h2>
                 <div class="list">
	                <table>
	                    <thead>
	                        <tr>
	                   	        <g:sortableColumn property="startTime" title="Start Time" />

	                   	        <g:sortableColumn property="endTime" title="End Time" />

	                            <g:sortableColumn property="project" title="Project" />

                     	        <g:sortableColumn property="description" title="Description" />

	                   	        <g:sortableColumn property="duration" title="Duration"/>

	                        </tr>
	                    </thead>
	                    <tbody>
	                    <g:each in="${sliceList.slices}" status="i" var="timeSlice">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

	                            <td><g:link action="show" controller="timeSlice" id="${timeSlice.id}"><g:formatDate format="HH:mm" date="${timeSlice.getStartTime()}"/></g:link></td>

	                            <td><g:link action="show" controller="timeSlice" id="${timeSlice.id}"><g:formatDate format="HH:mm" date="${timeSlice.getEndTime()}"/></g:link></td>

	                            <td>
                                  <g:if test="${timeSlice.project != null}">
                                    <g:link action="show" id="${timeSlice.project.id}" controller="project">${fieldValue(bean:timeSlice, field:'project')}</g:link>
                                  </g:if>
                                  </td>

	                            <td><g:link action="show" controller="timeSlice" id="${timeSlice.id}">${fieldValue(bean:timeSlice, field:'description')}</g:link></td>

	                            <td><g:link action="show" controller="timeSlice" id="${timeSlice.id}"><g:formatNumber number="${timeSlice.getDurationInHours()}" format="###.##" /></g:link></td>

	                        </tr>
	                   </g:each>
	                   </tbody>
	                </table>
	            </div>
                <g:render template="/sliceSummary" model="[summarySlices:sliceList, summaryTitle:'day']" />