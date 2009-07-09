

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>TimeSlice List</title>
    </head>
    <body>
        <g:render template="timeSliceNav" />
        <div class="body">
            <h1>TimeSlice List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>

            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	   <g:sortableColumn defaultOrder="desc" property="startTime" title="Start Time" />
                        
                   	   <g:sortableColumn defaultOrder="desc" property="endTime" title="End Time" />

                           <g:sortableColumn property="project" title="Project" />

                   	   <g:sortableColumn property="description" title="Description" />

                           <g:sortableColumn property="duration" title="Duration" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${timeSliceList}" status="i" var="timeSlice">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${timeSlice.id}"><g:formatDate format="yyyy-MM-dd HH:mm" date="${timeSlice.startTime}"/></g:link></td>
                        
                            <td><g:link action="show" id="${timeSlice.id}"><g:formatDate format="yyyy-MM-dd HH:mm" date="${timeSlice.endTime}"/></g:link></td>

                            <td><g:link action="show" id="${timeSlice.id}">${fieldValue(bean:timeSlice, field:'project')}</g:link></td>

                            <td><g:link action="show" id="${timeSlice.id}">${fieldValue(bean:timeSlice, field:'description')}</g:link></td>
                        
	                    <td><g:link action="show" controller="timeSlice" id="${timeSlice.id}"><g:formatNumber number="${timeSlice.getDurationInHours()}" format="###,##0" /></g:link></td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${TimeSlice.count()}" />
            </div>
        </div>
    </body>
</html>
