<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<jsp:include page="/templates/head.jsp" />
<title><tiles:insertAttribute name="title" ignore="true" />
</title>
</head>
<body>
	<div id="container">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div id="navigation" align="right" style="background: gray;">
			<tiles:insertAttribute name="actions" />
		</div>
		<div id="content">
			<tiles:insertAttribute name="pageContent" />
		</div>
		
	</div>
	<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
</body>
</html>
