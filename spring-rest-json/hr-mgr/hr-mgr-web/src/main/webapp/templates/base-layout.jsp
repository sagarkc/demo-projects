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
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
    </head>
    <body >
        <div class="header">
            <tiles:insertAttribute name="header"/>
        </div>

        <div class="body">
        	<div class="gallery-details">
        		<div>
                    <div class="paging">
                    	<tiles:insertAttribute name="actions"/>
                        
                    </div>
                </div>
                <div>
            		<tiles:insertAttribute name="pageContent"/>
            	</div>
            </div>
        </div>

        <div class="footer">
            <tiles:insertAttribute name="footer"/>
        </div>
    </body>
</html>
