<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket Op</title>
    </head>
    
    <body>
        <h2>Received</h2>
        <form action="received" method=get>
			TicketKey <input type="text" name="ticketKey"><br/>
			<input type="submit">
		</form>
		
		<h2>Resolved</h2>
        <form action="resolved" method=get>
			TicketKey <input type="text" name="ticketKey"><br/>
			<input type="submit">
		</form>

        <h2>Alarm Ticket:</h2>
        <table>
	        <tr>
	            <td>alarmlTicket:</td>
	            <td> ${alarmlTicket} </td>
	        </tr>
	    </table>
    </body>
</html>