<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>

<META NAME="AUTHOR" CONTENT="Sabuj Das">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<meta http-equiv="pragma" content="no-cache">
<META NAME="KEYWORDS" CONTENT="project management, task management, project, task">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="<%=session.getMaxInactiveInterval()%>;url=<%=request.getContextPath() %>/index.htm" />


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/assets/css/one-column-liquid.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/assets/css/base-style.css" />


<%
String themeName = "dark-hive";
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/assets/css/<%=themeName%>/jquery-ui-1.9.2.css" />

<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/jquery-ui-1.9.2.<%=themeName%>.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/ptm-common.js"></script>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/assets/flexigrid/css/flexigrid.pack.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/flexigrid/js/flexigrid.pack.js"></script>

