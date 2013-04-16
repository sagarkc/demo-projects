
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div style="max-width: 500px; right: 0px;">
	<ul>
		<li><a href="<%=request.getContextPath()%>/department/list.htm">
				<fmt:message key="lbl.department.list.header"/> </a>
		</li>
		<li><a href="<%=request.getContextPath()%>/department/add.htm">
				<fmt:message key="lbl.department.add"/> </a>
		</li>
	</ul>
</div>
