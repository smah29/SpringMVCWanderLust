<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<style >
h2 span{
color: dark grey; 
   font: bold 24px/45px Helvetica, Sans-Serif; 
   font-size:100px;
   letter-spacing: -1px;  
   padding: 10px;
}
body {
background-image: url(images/img.jpg); /*You will specify your image path here.*/

-moz-background-size: cover;
-webkit-background-size: cover;
background-size: cover;
background-position: top center !important;
background-repeat: no-repeat; !important;
background-attachment: fixed;
}
</style>
  <title>Bootstrap Case</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<br>
<br>
<h1><span><center>WanderLust</center></span></h1>
<br>
<br>
<br>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    
    <ul class="nav navbar-nav">
    
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Flight <span class="caret"></span></a>
        <ul class="dropdown-menu">
         <li><a href="./AdminDisplayFlight?option=display">Display All</a></li>
          <!--  <li><a href="AdminInsertFlight.jsp?option=insert">Insert</a></li>
         
          <li><a href="AdminDeleteFlight.jsp?option=delete">Delete</a></li>-->
           <li><a href="./AdminUpdateDisplayFlight?option=update">Update</a></li>
           <!--   <li><a href="AdminSearchFlight.jsp?option=search">Search</a></li>-->
        </ul>
      </li>
      
       <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Hotel <span class="caret"></span></a>
        <ul class="dropdown-menu">
 
         <li><a href="./AdminDisplayHotel?option=display">Display All</a></li>
          <!--  <li><a href="AdminInsertHotel.jsp?option=insert">Insert</a></li>-->
         
       <!--   <li><a href="AdminDeleteHotel.jsp?option=delete">Delete</a></li>-->
         <!--   <li><a href="AdminUpdateHotel.jsp?option=update">Update</a></li>-->
            <!--  <li><a href="AdminSearchHotel.jsp?option=search">Search</a></li>-->

         
        </ul>
      </li>
      
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Promotion <span class="caret"></span></a>
        <ul class="dropdown-menu">
         <li><a href="./adminDisplayPromotion">Display All</a></li>
          <!--  <li><a href="AdminInsertPromotion.jsp?option=insert">Insert</a></li>
         
          <li><a href="AdminDeletePromotion.jsp?option=delete">Delete</a></li>
           <li><a href="AdminUpdatePromotion.jsp?option=update">Update</a></li>
            <li><a href="AdminSearchPromotion.jsp?option=search">Search</a></li>--> 
        </ul>
      </li>
      
     <!--    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">User <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="AdminSearchUser.jsp?option=search">Search</a></li>
         
         
        </ul>
      </li>-->
      
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="./AdminUpdateAdmin">Update Profile</a></li>
          <!-- <li><a href="AdminInsertAdmin.jsp?option=insert">Add Another</a></li>
          <li><a href="AdminDeleteAdmin.jsp?option=delete">Remove Another</a></li>-->
        </ul>
      </li>
      
     
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="./AdminProfile"><span class="glyphicon glyphicon-user"></span>Profile</a></li>
      <li><a href="./logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
    </ul>
  </div>
</nav>
  


</body>
</html>
