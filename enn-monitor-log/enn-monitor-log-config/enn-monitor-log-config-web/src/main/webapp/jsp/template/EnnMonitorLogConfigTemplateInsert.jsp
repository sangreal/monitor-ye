<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Template Insert</title>
    </head>
    
    <body>
        <h2>插入</h2>
        <form action="insert" method=get>
			用户ID <input type="text" name="userid"><br/>
			TemplateKey <input type="text" style="width:2000px" name="templateKey"><br/>
			BelongToParentTemplate <input type="text" style="width:2000px" name="belongToParentTemplate"><br/>
			BelongToServiceId <input type="text" name="belongToServiceId" /><br/>
			FirstEventId <input type="text" name="firstEventId" /><br/>
			SecondEvent <input type="text" name="secondEvent" /><br/>
			<input type="submit">
		</form>

        <h2>查询结果</h2>
        <table>
	    	<c:if test="${ empty templateList}">
      			<tr>
      				<td>无记录</td>
      			</tr>  
           	</c:if>                 
       		<c:if test="${ not empty templateList}">  
               	<c:forEach  var="template" items="${templateList}">
               		<tr>
            			<td>${template}</td>
            		</tr>
              	</c:forEach>  
          	</c:if>     
	    </table>
    </body>
</html>