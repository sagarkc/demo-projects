<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="heading fill-glossy-aqua">
	Question Bank : <c:out value="${questionBank.bankId }"/> | <c:out value="${questionBank.bankName }"/>
</div>

<div class="ui-widget-content ui-corner-bottom ui-accordion-content-active" 
			style="padding: 7px 3px 5px 3px;">

	<div class="ui-widget-content ui-corner-bottom ui-accordion-content-active" 
		style="padding: 7px 0px 5px 20px; ">
		Details...
	</div>	
</div>
<div class="row"></div>