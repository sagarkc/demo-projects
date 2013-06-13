
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <sf:form method="post" action="/gwt-project-three/login.htm">
        <table width="480"><caption>Login</caption>
                <tr><td width="120">User Name: </td><td width="200"><input type="text" name="userName"></td></tr>
                <tr><td width="120">Password: </td><td width="200"><input type="password" name="password"></td></tr>
                <tr><td width="120"></td><td width="200"><input type="submit" value="Login"></td></tr>
        </table>
        </sf:form>
    </body>
</html>
