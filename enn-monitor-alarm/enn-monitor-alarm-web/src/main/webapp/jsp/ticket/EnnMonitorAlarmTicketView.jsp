<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket View</title>
    </head>
    
    <body>
        <h2>查询条件</h2>
        <form action="search" method=get>
			Id <input type="text" name="id"><br/>
			Ticket Status <input name="status" type="radio" value="0" checked/>WaitingAutoRecover <input name="status" type="radio" value="1" />WaitingNotify <input name="status" type="radio" value="2" />WaitingReceive <input name="status" type="radio" value="3" />WaitingResolve <input name="status" type="radio" value="4" />Resolved <br/>
			GroupName <input type="text" name="groupname"><br/>
			<input type="submit">
		</form>

        <h2>Alarm Ticket:</h2>
        <table>
	    	<c:if test="${ empty alarmTicketList}">
      			<tr>
      				<td>无记录</td>
      			</tr>  
           	</c:if>                 
       		<c:if test="${ not empty alarmTicketList}">  
               	<c:forEach  var="alarmTicket" items="${alarmTicketList}">
               		<tr>
            			<td>${alarmTicket}</td>
            		</tr>
              	</c:forEach>  
          	</c:if>     
	    </table>
    </body>
</html>