<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<spring:url value="/cust/save.htm" var="submitUrl" scope="request"/>

<div id="form-wrapper">
<div id="section-header" >
	<fmt:message key="add.customer.header"/>
</div>

<sf:form commandName="customer" action="${submitUrl}" method="POST">
	<div id="form-row">
		<div id="form-col-lbl"><fmt:message key="customer.name"/></div>
		<div id="form-col-input">
			<sf:input path="name" maxlength="150"/>
		</div>
		<div id="form-col-err">
			<sf:errors path="name"/>
		</div>
	</div>
	
	<div id="form-row">
		<div id="form-col-lbl"><fmt:message key="customer.income"/></div>
		<div id="form-col-input">
			<sf:input path="income" />
		</div>
		<div id="form-col-err">
			<sf:errors path="income"/>
		</div>
	</div>
	
	<div id="form-row">
		<div id="form-col-lbl"><fmt:message key="customer.address"/></div>
		<div id="form-col-input">
			<sf:textarea path="address" maxlength="400" rows="5" cols="15"/>
		</div>
		<div id="form-col-err">
			<sf:errors path="address"/>
		</div>
	</div>
	
	
	
	<div id="form-row">
		<div id="form-col-lbl">
			<input name="reset" type="reset" class="yellowButton" value="<fmt:message key="reset.form.button.label" />">
		</div>
		<div id="form-col-input">
			<input type="submit" value="<fmt:message key="save.button.label" />" class="blueButton">
		</div>
	</div>
	
</sf:form>


</div>