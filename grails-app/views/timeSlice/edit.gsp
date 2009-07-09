<%@ taglib prefix="g" uri="/web-app/WEB-INF/tld/grails.tld" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit TimeSlice</title>
    </head>
    <body>
        <g:render template="timeSliceNav" />
        <div class="body">
            <h1>Edit TimeSlice</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${timeSlice}">
            <div class="errors">
                <g:renderErrors bean="${timeSlice}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${timeSlice?.id}" />
                <g:render template="dataEntry" />
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Save" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
