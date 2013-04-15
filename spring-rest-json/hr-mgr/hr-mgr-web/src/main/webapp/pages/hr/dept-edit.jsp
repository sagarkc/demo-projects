<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<spring:url value="/department/save.htm" var="submitUrl" scope="request"/>



<div>
<div id="form-wrapper">
<div id="section-header" class="ui-state-active ui-corner-all">
<fmt:message key="lbl.department.add"/>
</div>


<sf:form commandName="currentDepartment" action="${submitUrl}"
	method="POST">
	<div id="form-row">
		<div id="form-col-lbl"><fmt:message key="dept.form.dept.edit.label.id"/></div>
		<div id="form-col-input">
			<sf:label path="id" />
		</div>
	</div>
	<div id="form-row">
		<div id="form-col-lbl"><fmt:message key="dept.form.dept.edit.label.name"/></div>
		<div id="form-col-input">
			<sf:input path="name" maxlength="50"/>
		</div>
		<div id="form-col-err">
			<sf:errors path="name"/>
		</div>
	</div>
	
	<div id="form-row">
		<div id="form-col-lbl">
			<input name="reset" type="reset" value='<fmt:message key="reset.form.button.label" />' class="yellowButton"/>
		</div>
		<div id="form-col-input">
			<input type="submit" value='<fmt:message key="save.button.label" />' class="blueButton">
		</div>
	</div>
	
</sf:form>
</div>

</div>