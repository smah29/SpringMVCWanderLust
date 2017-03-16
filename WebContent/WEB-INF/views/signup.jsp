<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	<title>SignUp</title>
</head>
<body>
<jsp:include page="BlackHeader.jsp"></jsp:include>

<form:form action="./userRegister" modelAttribute="user" id="myform">

	<table>
	<tr>
		<td><form:label path="userId">User ID</form:label></td>
		<td><form:input path="userId" /></td> 
		<td><form:errors path="userId"></form:errors></td>
	</tr>
	<tr>
		<td><form:label path="userName">User Name</form:label></td>
		<td><form:input path="userName" /></td>
		<td><form:errors path="userName"></form:errors></td>
	</tr>
	<tr>
		<td><form:label path="userPhoneNo">User Phone No</form:label></td>
		<td><form:input path="userPhoneNo" /></td>
		<td><form:errors path="userPhoneNo"></form:errors></td>
	</tr>
	<tr>
		<td><form:label path="userEmailId">Email</form:label></td>
		<td><form:input path="userEmailId" /></td>
		<td><form:errors path="userEmailId"></form:errors></td>
	</tr>
	<tr>
		<td><form:label path="userAddress">Address</form:label></td>
		<td><form:input path="userAddress" /></td>
		<td><form:errors path="userAddress"></form:errors></td>
	</tr>
	<tr>
		<td><form:label path="userPassword">Password</form:label></td>
		<td><form:input path="userPassword" /></td>
		<td><form:errors path="userPassword"></form:errors></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="Register"/>
		</td>
	</tr>
</table>	
	
</form:form>
</body>
</html>
