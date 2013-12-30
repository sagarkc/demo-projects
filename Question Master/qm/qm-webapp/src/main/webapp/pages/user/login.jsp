<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>

	
<c:if test="${hasSecurityError}">
	<div class="errorblock">
		Your login attempt was not successful, try again.<br /> Caused :
		${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
	</div>
</c:if>
<div  class="login-caption">
	<span>Login</h1>
</div>
<form name="loginForm" id="loginForm" method="post"
		action="<c:url value='j_spring_security_check' />">
<div id="login-wrapper" style="height: auto;">
		
		<table width="320">
			<tr>
				<td width="60">User Name:</td>
				<td width="200" align="right"><input type="text"
					name="j_username" style="width: 180px;" maxlength="50"
					class="text-box text-aqua"></td>
			</tr>
			<tr>
				<td width="60">Password:</td>
				<td width="200" align="right"><input type="password"
					name="j_password" style="width: 180px;" maxlength="50"
					class="text-box text-aqua"></td>
			</tr>
			<tr>
				<td width="60"></td>
				<td width="200" align="right">
				</td>
			</tr>
			<tr>
				<td width="60"></td>
				<td width="200" align="right"><input type="submit"
					value="Login" class="blueButton"></td>
			</tr>
		</table>
	
</div>

<div style="width: 350px; height: auto; margin: 0 auto; padding: 10px;">
	<input type="checkbox" 
					name="rememberMe" style="width: 13px; height: 13px;" maxlength="50"
					class="text-aqua"><span style="padding-left: 10px;">Remember me on this system.</span>
</div>
</form>
<div style="width: 350px; height: auto; margin: 0 auto; padding: 10px;">
	<span><a href="#">Forgot Password?</a></span>
	<span>|</span>
	<span><a href="showRegister.htm">New User?</a></span>
</div>



</html>