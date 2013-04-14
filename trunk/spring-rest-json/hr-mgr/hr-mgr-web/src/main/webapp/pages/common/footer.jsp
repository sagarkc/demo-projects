<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<div style="min-height: 190px;">

</div>
<div>
    <ul>
			<li><a href="<%=request.getContextPath()%>/index.htm">
					HOME </a></li>
			<li><a href="<%=request.getContextPath()%>/search.htm">
					Search </a></li>
			<li><a href="<%=request.getContextPath()%>/employee.htm">
					Employee </a></li>
			<li><a href="<%=request.getContextPath()%>/department.htm">
					Department </a></li>
			<li><span style="color: white;">Language: </span> <a
				href="<%=request.getContextPath()%>/index.htm?countryCode=en">En</a>
				| <a href="<%=request.getContextPath()%>/index.htm?countryCode=es">Es</a>
			</li>		
		</ul>
    <p>
        &#169; 2013 GreenSource Innovation. All Rights Reserved
    </p>
</div>