
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="assets/css/common.css"/>
    </head>
    <body>
        <div id="login-wrapper">
        <sf:form method="post" action="/gwt-project-three/login.htm">
        <table width="320"><caption>Login</caption>
            <tr><td width="60">User Name: </td><td width="200" align="right"><input type="text" name="userName" style="width:180px;" maxlength="50" class="text-box text-aqua"></td></tr>
                <tr><td width="60">Password: </td><td width="200" align="right"><input type="password" name="password" style="width:180px;" maxlength="50" class="text-box text-aqua"></td></tr>
                <tr><td width="60"></td><td width="200" align="right"><input type="submit" value="Login" class="button aqua"></td></tr>
        </table>
        </sf:form>
        </div>
    </body>
</html>
