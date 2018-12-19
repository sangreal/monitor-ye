<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Template View</title>
    </head>
    
    <body>
        <h2>查询条件</h2>
        <form action="search" method=get>
			ID <input type="text" name="id"><br/>
			BelongToParentTemplate <input type="text" name="belongToParentTemplate"><br/>
			BelongToServiceId <input type="text" name="belongToServiceId"><br/>
			firstEventId <input type="text" name="firstEventId"><br/>
			<input type="submit">
		</form>

        <h2>Template Table:</h2>
        <table>
	    	<c:if test="${ empty templateTableList}">
      			<tr>
      				<td>无记录</td>
      			</tr>  
           	</c:if>                    
       		<c:if test="${ not empty templateTableList}">  
               	<c:forEach  var="template" items="${templateTableList}">
               		<tr>
            			<td>${template}</td>
            		</tr>
              	</c:forEach>  
          	</c:if>      
	    </table>
    </body>
</html>