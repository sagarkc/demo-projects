
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="assets/css/common.css"/>
        <style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
    </head>
    <body>
        
        <c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
        
        <div id="login-wrapper">
            <form name="loginForm" id="loginForm" method="post" 
                  action="<c:url value='j_spring_security_check' />">
                <table width="320"><caption>Login</caption>
                    <tr>
                        <td width="60">User Name: </td>
                        <td width="200" align="right">
                            <input type="text" name="j_username" style="width:180px;" maxlength="50" class="text-box text-aqua">
                        </td>
                    </tr>
                    <tr>
                        <td width="60">Password: </td>
                        <td width="200" align="right">
                            <input type="password" name="j_password" style="width:180px;" maxlength="50" class="text-box text-aqua">
                        </td>
                    </tr>
                    <tr>
                        <td width="60"></td>
                        <td width="200" align="right">
                            <input type="submit" value="Login" class="button aqua">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
