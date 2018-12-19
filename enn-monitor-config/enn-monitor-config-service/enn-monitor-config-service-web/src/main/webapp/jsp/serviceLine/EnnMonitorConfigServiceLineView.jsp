<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ServiceLine View</title>
    </head>
    
    <body>
        <h2>查询</h2>
        <form action="search" method=get>
        	Id <input type="text" name="id"><br/>
        	Status <input name="status" type="radio" value="0" checked/> Default <input name="status" type="radio" value="1"/> Running <input name="status" type="radio" value="2" /> Deleting <br/>
			ServiceLineName <input type="text" name="serviceLineName"><br/>
			BelongToCluster <input type="text" name="belongToCluster"><br/>
			<input type="submit">
		</form>

        <h2>查询结果</h2>
        <table>
	    	<c:if test="${ empty serviceLineList}">
      			<tr>
      				<td>无记录</td>
      			</tr>  
           	</c:if>                 
       		<c:if test="${ not empty serviceLineList}">  
               	<c:forEach  var="serviceLine" items="${serviceLineList}">
               		<tr>
            			<td>${serviceLine}</td>
            		</tr>
              	</c:forEach>  
          	</c:if>     
	    </table>
    </body>
</html>