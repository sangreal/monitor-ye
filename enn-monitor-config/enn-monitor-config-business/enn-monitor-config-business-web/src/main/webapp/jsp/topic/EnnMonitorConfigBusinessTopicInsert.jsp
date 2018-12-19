<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Topic Insert</title>
    </head>
    
    <body>
        <h2>插入</h2>
        <form action="insert" method=get>
        	UserId <input type="text" name="userid"><br/>
			Source <input type="text" name="source"><br/>
			Topic <input type="text" name="topic"><br/>
			<input type="submit">
		</form>

        <h2>查询结果</h2>
        <table>
	    	<c:if test="${ empty topicList}">
      			<tr>
      				<td>无记录</td>
      			</tr>  
           	</c:if>                 
       		<c:if test="${ not empty topicList}">  
               	<c:forEach  var="topic" items="${topicList}">
               		<tr>
            			<td>${topic}</td>
            		</tr>
              	</c:forEach>  
          	</c:if>     
	    </table>
    </body>
</html>