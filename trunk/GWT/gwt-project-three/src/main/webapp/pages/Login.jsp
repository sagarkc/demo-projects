<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GWT Spring Sample</title>
</head>
<body>
<form:form method="post" action="/GwtProjectThree/login.htm">
<table width="480"><caption>Login</caption>
	<tr><td width="120">User Name: </td><td width="200"><input type="text" name="userName"></td></tr>
	<tr><td width="120">Password: </td><td width="200"><input type="password" name="password"></td></tr>
	<tr><td width="120"></td><td width="200"><input type="submit" value="Login"></td></tr>
</table>
</form:form>

</body>
</html>