<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ul class="menu">

	<li><a href="<%=request.getContextPath()%>/question/listall.htm">Question</a>

		<ul>
			<li><a href="<%=request.getContextPath()%>/question/bank.htm" 
				class="documents">Question Bank</a></li>
			<li><a href="<%=request.getContextPath()%>/question/testPaper.htm" 
				class="messages">Test Paper</a></li>
		</ul>
	</li>
	<li><a href="<%=request.getContextPath()%>/examiner/dashboard.htm">Examiner</a></li>
	<li><a href="<%=request.getContextPath()%>/admin/dashboard.htm">Admin</a></li>

</ul>