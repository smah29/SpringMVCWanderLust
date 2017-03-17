
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="BlackHeader.jsp"></jsp:include>
</head>
<body>


<form action="./loginCheck">
User ID<input type="text" name="username" required/><br>
Password<input type="password" name="password" required/><br>
<input type="submit" value="Login"/>
</form>
</body>
</html>