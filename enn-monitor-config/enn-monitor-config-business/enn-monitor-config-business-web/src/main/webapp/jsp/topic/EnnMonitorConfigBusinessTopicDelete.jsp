<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Topic Delete</title>
    </head>
    
    <body>
        <h2>Topic删除</h2>
        <form action="delete" method=get>
			ID <input type="text" name="id"><br/>
			<input type="submit">
		</form>

        <h2>查询结果</h2>
        <table>
	    	<c:if test="${ empty response}">
      			<tr>
      				<td>无记录</td>
      			</tr>  
           	</c:if>                 
       		<c:if test="${ not empty response}">  
               	<tr>
           			<td>${response}</td>
           		</tr>
          	</c:if>     
	    </table>
    </body>
</html>