<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

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
		<sf:form commandName="questionBank" action="../save.htm">
			<sf:input path="bankName"/>
			<input type="submit" name="saveQbankBtn" value="Save">
		</sf:form>
	</div>	
</div>
<div class="row"></div>