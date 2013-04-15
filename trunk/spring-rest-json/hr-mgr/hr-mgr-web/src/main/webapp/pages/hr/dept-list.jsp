<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="jquery-datatable" uri="/net/sf/jquery/tags/datatable.tld" %>
<%@ taglib prefix="jquery-flexigrid" uri="/net/sf/jquery/tags/flexigrid.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

    
<h2>
<fmt:message key="lbl.department.list.header" />
</h2>

<jquery-flexigrid:flexigrid id="departmentTable"
	title="" 
	url="department/list.htm" dataType="json"
	sortname="id" sortorder="asc"
	usepager="true" singleSelect="true"
	useRowPerPage="true" rowPerPage="15"
	showTableToggleBtn="true"
	width="600" height="300">


	<jquery-flexigrid:column name="id" display="key:lbl.department.list.id" width="50" sortable="true" align="center"/>
	<jquery-flexigrid:column name="name" display="key:lbl.department.list.name" width="100" sortable="true" align="left"/>
	<jquery-flexigrid:column name="links" display="key:lbl.department.list.actions" width="50" sortable="false" align="right"/>
	
	<jquery-flexigrid:search name="name" display="key:lbl.department.list.name" isdefault="true"/>

</jquery-flexigrid:flexigrid> 


        
        
                