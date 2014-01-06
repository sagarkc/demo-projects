<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ul class="menu">
	<li><a href="#"><c:out value="{loggedinUserName}"/></a>

		<ul>
			<li><a href="<%=request.getContextPath()%>/question/bank.htm" 
				class="documents">My Questions</a></li>
			<li><a href="<%=request.getContextPath()%>/question/testPaper.htm" 
				class="messages">Profile</a></li>
			<li><a href="<%=request.getContextPath()%>/logout.htm" 
				class="messages">Log out</a></li>
		</ul>
	</li>
	
</ul>