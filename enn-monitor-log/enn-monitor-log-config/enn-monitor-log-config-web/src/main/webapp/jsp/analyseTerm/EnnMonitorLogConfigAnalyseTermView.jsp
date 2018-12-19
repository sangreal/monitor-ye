<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AnalyseTerm View</title>
    </head>
    
    <body>
        <h2>查询条件</h2>
        <form action="search" method=get>
			ID <input type="text" name="id"><br/>
			belongToServiceId <input type="text" name="belongToServiceId"><br/>
			<input type="submit">
		</form>

        <h2>AnalyseTerm Table:</h2>
        <table>
	    	<c:if test="${ empty analyseTermTableList}">
      			<tr>
      				<td>无记录</td>
      			</tr>  
           	</c:if>                    
       		<c:if test="${ not empty analyseTermTableList}">  
               	<c:forEach  var="analyseTerm" items="${analyseTermTableList}">
               		<tr>
            			<td>${analyseTerm}</td>
            		</tr>
              	</c:forEach>  
          	</c:if>      
	    </table>
    </body>
</html>