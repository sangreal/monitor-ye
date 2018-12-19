<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket Insert</title>
    </head>
    
    <body>
        <h2>插入</h2>
        <form action="insert" method=get>
			User ID <input type="text" name="userid"><br/>
			Ticket Status <input name="status" type="radio" value="0" checked/>WaitingAutoRecover <input name="status" type="radio" value="1" />WaitingNotify <input name="status" type="radio" value="2" />WaitingReceive <input name="status" type="radio" value="3" />WaitingResolve <input name="status" type="radio" value="4" />Resolved <br/>
			Ticket Level <input name="level" type="radio" value="0" checked/>Warning <input name="level" type="radio" value="1" />Critical <br/>
			Automation <input name="automation" type="radio" value="0" checked/>No <input name="automation" type="radio" value="1" />Yes <br/>
			GroupName <input type="text" name="groupname"><br/>
			ClusterName <input type="text" name="clustername"><br/>
			Ip <input type="text" name="ip"><br/>
			NameSpace <input type="text" name="namespace"><br/>
			PodName <input type="text" name="podname"><br/>
			AppName <input type="text" name="appname"><br/>
			Error <input type="text" name="error"><br/>
			ErrorReason <input type="text" name="errorreason"><br/>
			Remark <input type="text" name="remark"><br/>
			<input type="submit">
		</form>

        <h2>查询结果</h2>
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