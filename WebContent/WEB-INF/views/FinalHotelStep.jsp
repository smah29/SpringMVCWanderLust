<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <jsp:include page="UserDashBoard.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booking done</title>
</head>
<body>
<form action="UserLogin.jsp">
	<!--<c:set var="hotelBooking" scope="session" value="${hotelBooking}"/>-->
	Booking Confirmed!!<br>
	Booking ID: ${hotelBooking.hotelBookingId()}<br><br>
	<input type="submit" value="Okay">
	</form>
</body>
</html>