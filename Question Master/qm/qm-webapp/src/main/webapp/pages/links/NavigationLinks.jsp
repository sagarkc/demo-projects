<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ul class="menu">
	<li><a href="<%=request.getContextPath()%>/course/dashboard.htm">Courses</a></li>
	
	<li><a href="<%=request.getContextPath()%>/question/dashboard.htm">Questions</a>
		<ul>
			<li><a href="<%=request.getContextPath()%>/question/bank.htm"  >Question Bank</a></li>
		</ul>
	</li>
	
	
	<li><a >Examination</a>
		<ul>
			<li><a href="<%=request.getContextPath()%>/test/dashboard.htm">Self-Test</a></li>
			<li><a href="<%=request.getContextPath()%>/exam/dashboard.htm">Examination</a></li>
		</ul>
	</li>
	
	
	<li><a >Control</a>
		<ul>
			<li><a href="<%=request.getContextPath()%>/examiner/dashboard.htm">Examiner</a></li>
			<li><a href="<%=request.getContextPath()%>/admin/dashboard.htm">Admin</a></li>
		</ul>
	</li>
	<li><a >Documents</a>
		<ul>
			<li><a href="<%=request.getContextPath()%>/info/spech/tech.htm">TS</a></li>
			<li><a href="<%=request.getContextPath()%>/info/spech/func.htm">FS</a></li>
		</ul>
	</li>
	<li><a >About</a>
		<ul>
			<li><a href="<%=request.getContextPath()%>/info/contact.htm">Contact</a></li>
			<li><a href="<%=request.getContextPath()%>/info/about.htm">About Us</a></li>
		</ul>
	</li>

</ul>