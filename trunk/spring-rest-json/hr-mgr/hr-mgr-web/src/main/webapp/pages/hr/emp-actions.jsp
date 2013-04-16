
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div style="max-width: 500px; right: 0px;">

<ul>
	<li><a href="<%=request.getContextPath()%>/employee.htm">
			<fmt:message key="lbl.employee.list.all"/> </a></li>
	<li><a href="<%=request.getContextPath()%>/employee/add.htm">
			<fmt:message key="lbl.employee.add"/> </a></li>
</ul>

</div>