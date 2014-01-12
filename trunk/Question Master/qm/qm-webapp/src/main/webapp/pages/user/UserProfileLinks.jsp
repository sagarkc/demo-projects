<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ul class="menu">
	<li><a href="#"><c:out value="${loggedInUserName}"/></a>

		<ul>
			<li><a href="<%=request.getContextPath()%>/user/course.htm"  class="cources">Courses</a></li>
			<li><a href="<%=request.getContextPath()%>/user/course.htm"  >Tests</a></li>
			<li><a href="<%=request.getContextPath()%>/user/profile.htm" >Exams</a></li>
			
			<li><a href="<%=request.getContextPath()%>/user/profile.htm"  class="profile">Profile</a></li>
			
			<li><a href="<%=request.getContextPath()%>/user/settings.htm"  class="settings">Settings</a></li>
			<li><a href="<%=request.getContextPath()%>/logout.htm"  class="signout">Sign out</a></li>
		</ul>
	</li>
	
</ul>