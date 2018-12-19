<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logformat View</title>
    </head>
    
    <body>
        <h2>测试</h2>
        <form action="parse" method=get>
			Regex     <input type="text" name="regex" style="width:2000px" value=${regex}><br/>
			Logformat <input type="text" name="logformat" style="width:2000px" value=${logformat}><br/>
			Log       <input type="text" name="log" style="width:2000px" value=${log}><br/>
			<input type="submit">
		</form>

        <h2>Logformat Table:</h2>
        <table>
	    	<c:if test="${ empty lognormal}">
      			<tr>
      				<td>无记录</td>
      			</tr>  
           	</c:if>                 
       		<c:if test="${ not empty lognormal}">  
            	<tr>
           			<td>${lognormal}</td>
           		</tr>
          	</c:if>     
	    </table>
    </body>
</html>