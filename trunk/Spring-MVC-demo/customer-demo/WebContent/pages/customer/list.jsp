<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="section-header" >
	<fmt:message key="add.customer.header"/>
</div>

<div>
	<table style="width: 400px; min-height: 300px; border-style: dotted;" border="1">
		<thead>
			<th><fmt:message key="customer.id"/></th>
			<th><fmt:message key="customer.name"/></th>
			<th><fmt:message key="customer.income"/></th>
			<th><fmt:message key="customer.address"/></th>
			<th></th>
		</thead>
		<tbody>
			<c:forEach items="${allCustomers }" var="cust">
			<tr>
				<td><c:out value="${cust.id }" /></td>
				<td><c:out value="${cust.name }" /></td>
				<td><c:out value="${cust.income }" /></td>
				<td><c:out value="${cust.address }" /></td>
				<td>
					<a href="cust/delete/<c:out value="${cust.id }" />.htm">Delete</a>
				</td>
			</tr>
			</c:forEach>
		
		</tbody>
		
	</table>

</div>