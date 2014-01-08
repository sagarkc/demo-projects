<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<script type="text/javascript">
<!--
	function showHideUserName(){
		var chkBox = document.getElementById("emailIsUserName");
		var txtBoxUserName = document.getElementById("userName");
		var txtBoxEmail = document.getElementById("email");
		alert(chkBox.checked);
		if (chkBox.checked){
			txtBoxUserName.value = txtBoxEmail.value;
			txtBoxUserName.readonly = "readonly";
		} else {
			txtBoxUserName.readonly = "";
		}
	}
-->
</script>

<div  class="login-caption">
	<span>Register User</h1>
</div>
	
		<div id="login-wrapper" style="height: auto; width: 370px;">
			<sf:form commandName="registrationDto" action="register.htm" method="post">
					<sf:errors path="*" cssClass="ui-state-error ui-corner-all" element="div"
						cssStyle="width: 620px;" />
					
				
				
				<table width="365">
					
					<tr>
						<td width="180">User Name:</td> <td width="180"></td> 
					</tr>
					<tr>
						<td width="180" align="right">
							<sf:input path="userName" maxlength="100" id="userName"
								cssClass="text-box text-aqua" 
								cssStyle="width: 180px; "/>
						</td>
						<td width="180" align="right">
							<span><sf:errors path="userName"></sf:errors></span>
						</td>
					</tr>
					<tr>
						<td width="180">Email:</td> <td width="180"></td> 
					</tr>
					<tr>
						<td width="180" align="right">
							<sf:input path="email" maxlength="100" id="email"
								cssClass="text-box text-aqua"
								cssStyle="width: 180px;"/>
						</td>
						<td width="180" align="right">
							<sf:errors path="email"></sf:errors>
						</td>
						
					</tr>
					<%-- <tr>
						
						<td width="180"><sf:checkbox id="emailIsUserName" path="emailIsUserName" label="Use Email as User Name"
								cssStyle="width: 13px; height: 13px; " cssClass="text-aqua" 
								onclick="showHideUserName();"/></td>
						<td width="180"></td>
					</tr> --%>
					
					
					<tr>
						<td width="180">Password:</td> <td width="180">Confirm Password:</td> 
					</tr>
					<tr>
						<td width="180" align="right">
							<sf:password path="password" maxlength="100"
								cssClass="text-box text-aqua"
								cssStyle="width: 180px;"/>
						</td>
						<td width="180" align="right">
							<input type="password" name="confirmPassword" 
							Class="text-box text-aqua" Style="width: 180px; ">
						</td>
					</tr>
					<tr>
						<td width="180"><sf:errors path="password"></sf:errors></td> 
						<td width="180"><span id="errConfPwd" name="errConfPwd"></span></td> 
					</tr>
					
					<tr>
						<td width="180">First Name:</td> <td width="180">Last Name:</td> 
					</tr>
					
					<tr>
						<td width="180" align="right">
							<sf:input path="firstName" maxlength="100"
								cssClass="text-box text-aqua" 
								cssStyle="width: 180px;"/>
						</td>
						<td width="180" align="right">
							<sf:input path="lastName" maxlength="100"
								cssClass="text-box text-aqua" 
								cssStyle="width: 180px;"/>
						</td>
					</tr>
					<tr>
						<td width="180"><sf:errors path="firstName"></sf:errors></td> 
						<td width="180"><sf:errors path="lastName"></sf:errors></span></td> 
					</tr>
					
					<tr>
						<td width="180"></td>
						<td width="180" align="right" >
							<input type="submit"
							value="Register" class="blueButton"></td>
					</tr>
				</table>
			
			</sf:form>
			
			
		</div>
	</div>




</html>