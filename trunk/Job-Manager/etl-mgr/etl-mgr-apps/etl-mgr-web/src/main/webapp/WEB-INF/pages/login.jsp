<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META NAME="AUTHOR" CONTENT="Sabuj Das">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<meta http-equiv="pragma" content="no-cache">
<META NAME="KEYWORDS"
	CONTENT="Batch, Job, management">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh"
	content="<%=session.getMaxInactiveInterval()%>;url=<%=request.getContextPath()%>/welcome.htm" />


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/EtlManager.css" />

<title><fmt:message key="applicationTitle" /></title>
</head>
<body>

	<c:if test="${hasSecurityError}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>

	<div id="login-wrapper">
		<form name="loginForm" id="loginForm" method="post"
			action="<c:url value='j_spring_security_check' />">
			<table width="320">
				<caption>Login</caption>
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
					<td width="200" align="right"><input type="submit"
						value="Login" class="blueButton"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>