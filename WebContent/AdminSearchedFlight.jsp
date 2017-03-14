<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mmt.model.bean.Flight"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="AdminDashBoard.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Flights</title>
<style>
#book{
	border-style: solid;
    border-width: 1px;
    border-color: red;
    background-color: red;
    color: white;
    padding:5px;
    font: bold;
    text-align:center; }
table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    padding: 19px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}
th {
    background-color: black;
    color: white;
}

tr:hover{background-color:#f5f5f5}

</style>
</head>
<body>
<table border="1">
	<tr>
	<th>Flight Id</th>
	<th>Airline</th>
	<th>Depart</th>
	<th>Arrive</th>
	<th>Price</th>
	
	</tr>

<tr>
<c:set var="flight" scope="page" value="${flightSearched}"/>
<td><c:out value="${flight.getFlightId()}" /></td>
    <td>  <c:out value="${flight.getFlightCompanyName()}" /></td>
      <td> <c:out value="${flight.getFlightDepartureTime()}" /></td>
     <td>  <c:out value="${flight.getFlightArrivalTime()}" /></td>
      <td> <c:out value="${flight.getFlightTicketPrice()}" /></td>
      
  </tr>
 
</table>
</body>
</html>
