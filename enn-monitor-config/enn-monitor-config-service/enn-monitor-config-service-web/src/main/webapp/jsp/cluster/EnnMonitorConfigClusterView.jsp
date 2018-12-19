<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cluster View</title>
    </head>
    
    <body>
        <h2>查询</h2>
        <form action="search" method=get>
            Id <input type="text" name="id"><br/>
			ClusterName <input type="text" name="clusterName"><br/>
			ClusterStatus <input name="status" type="radio" value="0" checked/> Running <input name="status" type="radio" value="1" /> Deleting <br/>
			<input type="submit">
		</form>

        <h2>查询结果</h2>
        <table>
	    	<c:if test="${ empty clusterList}">
      			<tr>
      				<td>无记录</td>
      			</tr>  
           	</c:if>                 
       		<c:if test="${ not empty clusterList}">  
               	<c:forEach  var="cluster" items="${clusterList}">
               		<tr>
            			<td>${cluster}</td>
            		</tr>
              	</c:forEach>  
          	</c:if>     
	    </table>
    </body>
</html>