<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="jquery-datatable" uri="/net/sf/jquery/tags/datatable.tld" %>
<%@ taglib prefix="fg" uri="/net/sf/jquery/tags/flexigrid.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

    
<h2>
<fmt:message key="lbl.employee.list.header" />
</h2>

<fg:flexigrid id="employeeTable"
	title="" 
	url="employee/list.htm" dataType="json"
	sortname="id" sortorder="asc"
	usepager="true" singleSelect="true"
	useRowPerPage="true" rowPerPage="15"
	showTableToggleBtn="true"
	width="800" height="300" >


	<fg:column name="id" display="key:lbl.employee.list.id" width="50" sortable="true" align="center"/>
	<fg:column name="firstName" display="key:lbl.employee.list.fname" width="100" sortable="true" align="left"/>
	<fg:column name="lastName" display="key:lbl.employee.list.lname" width="100" sortable="true" align="left"/>
	<fg:column name="emailId" display="key:lbl.employee.list.email" width="100" sortable="true" align="left"/>
	<fg:column name="dateOfBirth" display="key:lbl.employee.list.dateOfBirth" width="100" sortable="true" align="left"/>
	<fg:column name="joiningDate" display="key:lbl.employee.list.joiningDate" width="100" sortable="true" align="left"/>
	<fg:column name="releavingDate" display="key:lbl.employee.list.releavingDate" width="100" sortable="true" align="left"/>
	
	
	<jquery-flexigrid:column name="link_a" display="" width="50" sortable="false" align="right"/>
	<jquery-flexigrid:column name="link_b" display="" width="50" sortable="false" align="right"/>
	<jquery-flexigrid:column name="link_c" display="" width="50" sortable="false" align="right"/>
	
	<fg:search name="firstName" display="key:lbl.employee.list.fname" isdefault="true" />
	<fg:search name="lastName" display="key:lbl.employee.list.lname" isdefault="true"/>
	<fg:search name="emailId" display="key:lbl.employee.list.email" isdefault="true" />
	<fg:search name="dateOfBirth" display="key:lbl.employee.list.dateOfBirth" isdefault="true"/>
	<fg:search name="joiningDate" display="key:lbl.employee.list.joiningDate" isdefault="true" />
	<fg:search name="releavingDate" display="key:lbl.employee.list.releavingDate" isdefault="true"/>
	
</fg:flexigrid> 


        
        
                