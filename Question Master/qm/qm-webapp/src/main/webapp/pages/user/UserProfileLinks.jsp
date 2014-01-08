<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ul class="menu">
	<li><a href="#"><c:out value="${loggedInUserName}"/></a>

		<ul>
			<li><a href="<%=request.getContextPath()%>/user/profile.htm" 
				class="documents">Profile</a></li>
			<li><a href="<%=request.getContextPath()%>/user/settings.htm" 
				class="messages">Settings</a></li>
			<li><a href="<%=request.getContextPath()%>/logout.htm" 
				class="messages">Log out</a></li>
		</ul>
	</li>
	
</ul>