<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/templates/head.jsp" />
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>

<body>
	<div id="page-header">
		<tiles:insertAttribute name="header"/>
	</div>
	<div id="page-wrap">
		<div id="inside">
			<div id="top-panel">
				<tiles:insertAttribute name="baseToolbar"/>
			</div>
			
			<div id="left-sidebar">
				<tiles:insertAttribute name="leftNavigator"/>
			</div>
			
			<div id="main-content">
				<div id="home-content-wrapper">
				<tiles:insertAttribute name="pageContent"/>
				</div>
			</div>
			
		</div>
		<div style="clear: both;"></div>
	</div>
	<div id="footer-content">
		<tiles:insertAttribute name="footer"/>
	</div>
</body>
</html>