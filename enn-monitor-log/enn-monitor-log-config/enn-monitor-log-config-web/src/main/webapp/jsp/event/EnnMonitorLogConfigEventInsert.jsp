<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Event Insert</title>
    </head>
    
    <body>
        <h2>插入</h2>
        <form action="insert" method=get>
			用户ID <input type="text" name="userid"><br/>
			EventName <textarea style="width:2000px" name="eventName"></textarea><br/>
			<input type="submit">
		</form>

        <h2>查询结果</h2>
        <table>
	    	<c:if test="${ empty eventList}">
      			<tr>
      				<td>无记录</td>
      			</tr>  
           	</c:if>                 
       		<c:if test="${ not empty eventList}">  
               	<c:forEach  var="event" items="${eventList}">
               		<tr>
            			<td>${event}</td>
            		</tr>
              	</c:forEach>  
          	</c:if>     
	    </table>
    </body>
</html>