<%@ taglib prefix="g" uri="/web-app/WEB-INF/tld/grails.tld" %>
<div class="dialog">
<table>
    <tbody>
    
        <tr class="prop">
            <td valign="top" class="name">
                <label for="startTime">Start Time:</label>
            </td>
            <td valign="top" class="value ${hasErrors(bean:timeSlice,field:'startTime','errors')}">
                <%
                // TODO: enable things like "today", "yesterday", etc.
                // TODO: automatically set endDate to the same value!
                // <g:datePicker name="startTime" value="${timeSlice?.startTime}" ></g:datePicker> 
                %>
                
                <%
                   //<g:textField name="startDateString" value="${start?.startDateString}" onchange="magicDate('actionDate_yyyy-mm-dd');" onfocus="if (this.className != 'error') this.select()"/> 
                   //<g:textField name="startTimeString" value="${start?.startTimeString}" onchange="magicDate('actionDate_yyyy-mm-dd');" onfocus="if (this.className != 'error') this.select()"/>
                %>
                <g:textField name="startDateString" value="${start?.startDateString}"/>
                <g:textField name="startTimeString" value="${start?.startTimeString}"/>
            </td>
        </tr> 
    
        <tr class="prop">
            <td valign="top" class="name">
                <label for="endTime">End Time:</label>
            </td>
            <td valign="top" class="value ${hasErrors(bean:timeSlice,field:'endTime','errors')}">
                <% 
                //<g:datePicker name="endTime" value="${timeSlice?.endTime}" ></g:datePicker>  
                //<g:textField name="endDateString" value="${end?.endDateString}" onchange="magicDate('actionDate_yyyy-mm-dd');" onfocus="if (this.className != 'error') this.select()"/>
                //<g:textField name="endTimeString" value="${end?.endTimeString}" onchange="magicDate('actionDate_yyyy-mm-dd');" onfocus="if (this.className != 'error') this.select()"/>                                    
                %>
                <g:textField name="endDateString" value="${end?.endDateString}"/>
                <g:textField name="endTimeString" value="${end?.endTimeString}"/>
                
            </td>
        </tr> 
    
        <tr class="prop">
            <td valign="top" class="name">
                <label for="description">Description:</label>
            </td>
            <td valign="top" class="value ${hasErrors(bean:timeSlice,field:'description','errors')}">
                <input type="text" id="description" name="description" value="${fieldValue(bean:timeSlice,field:'description')}"/>
            </td>
        </tr> 
    
        <tr class="prop">
            <td valign="top" class="name">
                <label for="project">Project:</label>
            </td>
            <td valign="top" class="value ${hasErrors(bean:timeSlice,field:'project','errors')}">
                <g:select optionKey="id" from="${Project.list()}" name="project.id" value="${timeSlice?.project?.id}" noSelection="['null':'']"></g:select>
            </td>
        </tr> 
    
        <tr class="prop">
            <td valign="top" class="name">
                <label for="notes">Notes:</label>
            </td>
            <td valign="top" class="value ${hasErrors(bean:timeSlice,field:'notes','errors')}">
                <textarea rows="5" cols="40" name="notes">${fieldValue(bean:timeSlice, field:'notes')}</textarea>
            </td>
        </tr> 
    
        <tr class="prop">
            <td valign="top" class="name">
                <label for="worker">Worker:</label>
            </td>
            <td valign="top" class="value ${hasErrors(bean:timeSlice,field:'worker','errors')}">
                <g:select optionKey="id" from="${Worker.list()}" name="worker.id" value="${timeSlice?.worker?.id}" noSelection="['null':'']"></g:select>
            </td>
        </tr>

        <tr class="prop">
            <td valign="top" class="name">
                <label for="tags">Tags:</label>
            </td>
            <td valign="top" class="value ${hasErrors(bean:timeSlice,field:'tags','errors')}">
                                    
							<ul>
							<g:each var="t" in="${timeSlice?.tags?}">
							    <li><g:link controller="tag" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
							</g:each>
							</ul>
							<g:link controller="tag" params="['timeSlice.id':timeSlice?.id]" action="create">Add Tag</g:link>

            </td>
        </tr> 
    
    </tbody>
</table>
</div>