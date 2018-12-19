<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket Update</title>
    </head>
    
    <body>
        <h2>更新</h2>
        <form action="update" method=get>
        	Id <input type="text" name="id"><br/>
        	User ID <input type="text" name="userid"><br/>
			Ticket Status <input name="status" type="radio" value="0" checked/>WaitingAutoRecover <input name="status" type="radio" value="1" />WaitingNotify <input name="status" type="radio" value="2" />WaitingReceive <input name="status" type="radio" value="3" />WaitingResolve <input name="status" type="radio" value="4" />Resolved <br/>
			GroupName <input type="text" name="groupname"><br/>
			Error <input type="text" name="error"><br/>
			ErrorReason <input type="text" name="errorreason"><br/>
			Remark <input type="text" name="remark"><br/>
			<input type="submit">
		</form>

        <h2>查询结果</h2>
        <table>
	        <tr>
	            <td>alarmlTicket:</td>
	            <td> ${alarmlTicket} </td>
	        </tr>
	    </table>
    </body>
</html>
