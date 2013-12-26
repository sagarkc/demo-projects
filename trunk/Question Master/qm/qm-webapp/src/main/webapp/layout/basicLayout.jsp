<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE HTML>
<!--
	Question Master
	Sabuj Das | sabuj.das@gmail.com
-->
<html>
	<head>
		<title>Question Master | Practice yourself</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="question, answer, test, examination" />
		<LINK REL="SHORTCUT ICON" HREF="assets/images/layout/qm-shortcut.png" />
		<link  rel="stylesheet" href="http://fonts.googleapis.com/css?family=Ubuntu+Condensed">
		<link  rel="stylesheet" href="assets/css/qm-style.css">
		<link  rel="stylesheet" href="assets/css/framework/jquery-ui-1.10.3-green.css">
		<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
		<!--[if lte IE 8]><script src="assets/js/framework/html5shiv.js"></script><![endif]-->
		
        <script src="assets/js/framework/jquery-1.9.1.js"></script>
		<script src="assets/js/framework/jquery-ui-1.10.3.js"></script>
        
        <script type="text/javascript">
            $(function() {
                $( "#globalSearchBtn" ).button();
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
                             src="assets/images/layout/qm-logo.png"/>
                        </a>
                    </div>
                    <div id="header-content">
                        <div id="header-links">
                            <tiles:insertAttribute name="section_HeaderLinks" />
                        </div>
                        <div id="header-user-links">
                            <tiles:insertAttribute name="section_UserHeaderLinks" />
                        </div>
                        <div id="header-search-box">
                            <form action="#" method="POST">
                                <input type="text" name="globalSearchKey" value="Search">
                                <input type="button" class="blueButton" 
                                       name="globalSearchBtn" value="Go">
                            </form>
                        </div>
                    </div>
                         
                </div>
            </div>
            <div id="mainContent">
	        	<tiles:insertAttribute name="section_MainContent" />    
            </div>
            <div id="footerContainer">Footer</div>
        </div>
        
        
	</body>
	
	
</html>