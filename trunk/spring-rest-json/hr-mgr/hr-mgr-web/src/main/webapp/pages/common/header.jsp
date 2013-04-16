<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div id="leftcolumn">
	<div>
		<a href="<%=request.getContextPath()%>/index.htm" id="logo-image">
			<img src="<%=request.getContextPath()%>/assets/images/gsi-logo-48x48.png" alt="logo"> 
		</a> 
		<a href="<%=request.getContextPath()%>/index.htm" id="logo-text"> 
			<img src="<%=request.getContextPath()%>/assets/images/gsi-text-logo.png" alt="logo-text"> 
		</a>
	</div>
	<span style="text-transform: uppercase; margin-left: 0px;">
		Green Ideas of Invention 
	</span>
</div>
<div id="rightcolumn">
	<div id="navigation" align="right">
		<ul>
			<li><a href="<%=request.getContextPath()%>/index.htm">HOME</a></li>
		</ul>
		<ul>
			<li><a href="<%=request.getContextPath()%>/employee.htm">Employee</a></li>
		</ul>
		<ul>
			<li><a href="<%=request.getContextPath()%>/department.htm" style="border-right: none !important;">Department</a></li>
		</ul>
	</div>
	<div id="navigation">
		<ul>
			<li><a href="?countryCode=en">English</a></li>
		</ul>
		<ul>
			<li><a href="?countryCode=de">German</a></li>
		</ul>
		<ul>
			<li><a href="?countryCode=es" style="border-right: none !important;">Spanish</a></li>
		</ul>
	</div>
</div>

	
