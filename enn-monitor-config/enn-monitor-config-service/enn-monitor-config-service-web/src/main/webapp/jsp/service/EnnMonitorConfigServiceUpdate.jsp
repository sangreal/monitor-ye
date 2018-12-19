<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Service Update</title>
    </head>
    
    <body>
        <h2>更新</h2>
        <form action="update" method=get>
        	Id <input type="text" name="id"><br/>
        	ServiceName <input type="text" name="serviceName"><br/>
        	BelongToServiceLine <input type="text" name="belongToServiceLine"><br/>
			Status <input name="status" type="radio" value="0" /> Default <input name="status" type="radio" value="1" checked/> Running <input name="status" type="radio" value="2" /> Stop <br/>
			Token <input type="text" name="token"><br/>
			Owner <input type="text" name="owner"><br/>
			Guest <input type="text" name="guest"><br/>
			<input type="submit">
		</form>

        <h2>查询结果</h2>
        <table>
	        <tr>
	            <td>serviceList:</td>
	            <td> ${serviceList} </td>
	        </tr>
	    </table>
    </body>
</html>
