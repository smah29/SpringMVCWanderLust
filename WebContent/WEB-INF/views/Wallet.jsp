<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="UserDashBoard.jsp"></jsp:include>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wallet</title>
</head>
<body>
<form>



<button type="submit" formaction="./addMoney">Add Money</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button type="submit" formaction="./DisplayWallet">Display Wallet Balance</button>
<br>
<br>

<div id="displayBalance">
Wallet Status&nbsp; :&nbsp;${balance}<br>
${msgMoney}
<c:remove var="msgMoney" scope="session" />
</div>
<c:if test="${not empty messageFlight}">
<c:redirect url="./confirmBookingFlight"/>
    
</c:if>
<c:if test="${not empty messageHotel}">
<c:redirect url="./confirmBookingHotel"/>
   
   
    
</c:if>

</form>
</body>
</html>