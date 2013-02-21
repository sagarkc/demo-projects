<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="section-header" >
	<fmt:message key="add.customer.header"/>
</div>

<div>
	<table style="width: 400px; min-height: 300px; border-style: dotted;" border="0">
		<thead style="background: #C4DEFC;">
			<th><fmt:message key="customer.id"/></th>
			<th><fmt:message key="customer.name"/></th>
			<th><fmt:message key="customer.income"/></th>
			<th><fmt:message key="customer.address"/></th>
			<th></th>
		</thead>
		<tbody>
			<c:forEach items="${allCustomers }" var="cust">
			<tr style="min-height: 25px;">
				<td style="min-height: 25px;"><c:out value="${cust.id }" /></td>
				<td style="min-height: 25px;"><c:out value="${cust.name }" /></td>
				<td style="min-height: 25px;"><c:out value="${cust.income }" /></td>
				<td style="min-height: 25px;"><c:out value="${cust.address }" /></td>
				<td style="min-height: 25px;">
					<a href="cust/delete/<c:out value="${cust.id }" />.htm">Delete</a>
				</td>
			</tr>
			</c:forEach>
		
		</tbody>
		
	</table>
https://github.com/SpringSource/spring-data-solr 
http://wiki.apache.org/solr/Solrj
</div>