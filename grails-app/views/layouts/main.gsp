<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />				
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>	
        <div class="logo"><h1><a href="${createLinkTo(dir:'')}">TIMETRACKER</a></h1></div>        
        
        <div class="nav">
          <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
          <span class="menuButton"><g:link class="list" action="list" controller="timeSlice">TimeSlice List</g:link></span>
          <span class="menuButton"><g:link class="create" action="create" controller="timeSlice">New TimeSlice</g:link></span>
          <span class="menuButton"><g:link controller="report">Report</g:link></span>        
        </div>
        <g:layoutBody />
    </body>	
</html>