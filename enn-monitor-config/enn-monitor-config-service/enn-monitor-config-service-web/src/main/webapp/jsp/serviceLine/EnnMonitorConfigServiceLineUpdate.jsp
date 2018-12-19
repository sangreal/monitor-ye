<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ServiceLine Update</title>
    </head>
    
    <body>
        <h2>更新</h2>
        <form action="update" method=get>
        	Id <input type="text" name="id"><br/>
        	ServiceLineName <input type="text" name="serviceLineName"><br/>
			BelongToCluster <input type="text" name="belongToCluster"><br/>
			LastUpdateUser <input type="text" name="lastUpdateUser"><br/>
			<input type="submit">
		</form>

        <h2>查询结果</h2>
        <table>
	        <tr>
	            <td>serviceLineList:</td>
	            <td> ${serviceLineList} </td>
	        </tr>
	    </table>
    </body>
</html>
