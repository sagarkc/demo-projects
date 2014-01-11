<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<!--
	Question Master
	Sabuj Das | sabuj.das@gmail.com
-->
<html>
	<head>
		<title><fmt:message key="application.title" /></title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="question, answer, test, examination" />
		<LINK REL="SHORTCUT ICON" HREF="<%=request.getContextPath()%>/assets/images/layout/qm-shortcut.png" />
		<link  rel="stylesheet" href="http://fonts.googleapis.com/css?family=Ubuntu+Condensed">
		<link  rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/qm-style.css">
		<link  rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/header-menu.css">
		<link  rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/toolbar-menu.css">
		<link  rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/framework/jquery-ui-1.10.3-green.css">
		<!--[if lte IE 9]><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/ie9.css" /><![endif]-->
		<!--[if lte IE 8]><script src="<%=request.getContextPath()%>/assets/js/framework/html5shiv.js"></script><![endif]-->
		
        <script src="<%=request.getContextPath()%>/assets/js/framework/jquery-1.9.1.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/framework/jquery-ui-1.10.3.js"></script>
        
        <script type="text/javascript">
            $(function() {
                $( "#globalSearchBtn" ).button();

                $( "#user-login-tabs" ).tabs();

                $(".dropdown dt a").click(function() {
                    $(".dropdown dd ul").toggle();
                });
                            
                $(".dropdown dd ul li a").click(function() {
                    var text = $(this).html();
                    $(".dropdown dt a span").html(text);
                    $(".dropdown dd ul").hide();
                    
                });
                            
                function getSelectedValue(id) {
                    return $("#" + id).find("dt a span.value").html();
                }

                $(document).bind('click', function(e) {
                    var $clicked = $(e.target);
                    if (! $clicked.parents().hasClass("dropdown"))
                        $(".dropdown dd ul").hide();
                });

				
                
            });
        </script>
	</head>

	<body>
        
        
        <div id="pageContainer">
            <div id="topBar" class="fill">
                <div id="header">
                    <div id="header-logo">
                        <a href="#">
                            <img alt="questionMaster"
                             src="<%=request.getContextPath()%>/assets/images/layout/qm-logo.png"/>
                        </a>
                    </div>
                    <div id="topbar-nav">
                    	<tiles:insertAttribute name="section_HeaderMenus" />
                    </div>
                    <div id="header-content">
                        <div id="header-links">
                            <tiles:insertAttribute name="section_HeaderLinks" />
                        </div>
                        <div id="header-user-links">
                            <tiles:insertAttribute name="section_UserProfileLinks" />
                        </div>
                        <div id="header-search-box">
                        <div class="form-row">
                            <form action="#" method="POST">
                                <input type="text" name="globalSearchKey" 
                                	value='<fmt:message key="search.field.default.text"/>'
                                	class="search-text-box search-text-aqua">
                                <input type="button" class="blueButton" 
                                       name="globalSearchBtn" value="Go">
                            </form>
                        </div>
                        </div>
                    </div>
                         
                </div>
            </div>
            <div id="mainContentWrapper">
            <div id="mainContent">
	        	<tiles:insertAttribute name="section_MainContent" />    
            </div>
            </div>
            <div id="footerContainer">Footer</div>
        </div>
        
        
	</body>
	
	
</html>