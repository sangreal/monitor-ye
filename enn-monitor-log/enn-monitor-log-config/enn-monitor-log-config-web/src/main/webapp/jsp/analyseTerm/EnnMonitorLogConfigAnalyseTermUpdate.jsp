<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AnalyseTerm Update</title>
    </head>
    
    <body>
        <h2>更新</h2>
        <form action="update" method=get>
        	ID <input type="text" name="id"><br/>
			AddTerm <input type="text" style="width:2000px" name="addTerm"><br/>
			FilterTerm <input type="text" style="width:2000px" name="filterTerm"><br/>
			BelongToServiceId <input type="text" name="belongToServiceId" /><br/>
			LastUpdateUser <input type="text" name="lastUpdateUser"><br/>
			<input type="submit">
		</form>

        <h2>查询结果</h2>
        <table>
	        <tr>
	            <td>用户AnalyseTerm:</td>
	            <td> ${AnalyseTerm} </td>
	        </tr>
	    </table>
    </body>
</html>
