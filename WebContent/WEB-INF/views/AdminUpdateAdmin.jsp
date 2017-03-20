<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <jsp:include page="AdminDashBoard.jsp"></jsp:include>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Admin</title>
<script>
  function phonenumber(inputtxt)  
  {  
    var phoneno = /^\d{10}$/;  
    if(inputtxt.value.match(phoneno))  
    {  
        return true;  
    }  
    else  
    {  
       alert("Enter 10 digit Phone Number");  
       return false;  
    }  
    }  
  </script>
</head>
<body>
<c:set var="option" scope="session" value="${option}"/>
<c:out value="${option}"></c:out>
<form  name="form1" action="./AdminServProfile">
<fieldset>
<legend>Fill Flight Details</legend><br><br>
<input type="hidden" name="option" value="update"/>
<table>
<tr><td>Old Admin ID</td><td><input type="text" name="oldadminId"  value=${admin.adminId } required/></td></tr>
<tr><td>New Admin ID</td><td><input type="text" name="adminId"  value=${admin.adminId } required/></td></tr>
<tr><td>New Admin Name</td><td><input type="text" name="adminName"  value=${admin.adminName } required/></td></tr>
<tr><td>New Admin Phone No</td><td><input type="tel"  name="adminPhoneNo"  value=${admin.adminPhoneNo } onblur="phonenumber(document.form1.adminPhoneNo)" required/></td></tr>
<tr><td>New Admin EmailId</td><td><input type="email" name="adminEmailId"  value=${admin.adminEmailId } required/></td></tr>
<tr><td>New Admin Address</td><td><input type="text" name="adminAddress"  value=${admin.adminAddress } required/></td></tr>
<tr><td>New Admin Password</td><td><input type="text" name="adminPassword"  value=${admin.adminPassword } required/></td></tr>

</table>
<input type="submit" value="Update"/>
</fieldset>
</form>
</body>
</html>