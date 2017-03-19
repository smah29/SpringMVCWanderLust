<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="BlackHeader.jsp"></jsp:include>
<SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</SCRIPT>
</head>
<body onload="noBack();"
    onpageshow="if (event.persisted) noBack();" onunload="">
Language : <a href="?lang=en">English</a>|<a href="?lang=de">German</a><br>

<form:form action="./loginCheck" method="post">
<!--  User&nbsp;&nbsp; ID&nbsp;&nbsp;<input type="text" name="username" required/><br>
Password&nbsp;<input type="password" name="password" required/><br>-->
<table>
    <tbody><tr>
        <td><spring:message code="label.username"></spring:message></td>
        <td><input type="text" name="username" required/></td> 
    </tr>
    <tr>
        <td><spring:message code="label.password"></spring:message></td>
        <td><input type="password" name="password" required/></td>
    </tr>
    </tbody></table>
<input type="submit" value="Login"/>
</form:form>
</body>
</html>