<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="dashboard-heading fill-glossy-yellow">Question Bank</div>

<div class="ui-widget-content ui-corner-bottom ui-accordion-content-active" 
			style="padding: 7px 3px 5px 3px;">
			
	<div class="heading fill-glossy-aqua">
	<ul class="toolbar">
		<li><a href="<%=request.getContextPath()%>/question/bank/showAddForm.htm">Create Question Bank</a></li>
	</ul>		
	</div>
	<div class="ui-widget-content ui-corner-bottom ui-accordion-content-active" 
		style="padding: 7px 0px 5px 20px; ">
		<table width="350px" style="border: 1px dotted aqua;">
			<thead >
				<tr>
				<td>Name</td>
				<td>Created Date</td>
				<td colspan="2">Actions</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="qbank" items="${allQuestionBanks }">
				<tr>
					<td style="height: 21px;">
						<a href="<%=request.getContextPath()%>/question/bank/showDetails/<c:out value="${qbank.bankId }"/>.htm">
						<c:out value="${qbank.bankName }"/></a>
					</td>
					<td style="height: 21px;">
						<c:out value="${qbank.createdDate }"/>
					</td>
					<td><a href="<%=request.getContextPath()%>/question/bank/showEditForm/<c:out value="${qbank.bankId }"/>.htm">Edit</a></td>
					<td><a href="<%=request.getContextPath()%>/question/bank/<c:out value="${qbank.bankId }"/>/delete.htm">Delete</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>	
</div>
<div class="row"></div>