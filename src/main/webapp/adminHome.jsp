<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css">
<title>Admin Home</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/Images/fabicon.png">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/press.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/main.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dialog-mobile.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/mcx-dialog.js"></script>

<style type="text/css">
	.f1{
		width: 400px;
		height :300px;
	    padding: 40px;
	    padding-right:150px;
	    position: absolute;
	    top: 55%;
	    left: 50%;
	    transform: translate(-50%, -50%);
	    text-align: center;
	}
	.f1 form{
		width: 500px;
		padding:8px;
		position: relative;
	}
	
	.f1 input{
		margin-left: 5px;
		margin-left: 4px;
	}
	
	#logout{
		width: 200px;
	    padding: 10px;
	    position: absolute;
	    top:30%;
    	left: 50%;
    	transform: translate(-50%, 700%);
    	font-weight: bold;
    	font-family: sans-serif;
    	font-size: 15px;
    	align-content: center;
	}
	input[type=submit] {
   		height: 2.5em;
   		font-weight: bold;
    	font-family: sans-serif;
	}
	h2{
		transform: translate(60px, 10px);
	}
	</style>
</head>
<body>
		<%if(!(((String)request.getRequestURI()).contains("/admin/adminHome"))){
			throw new ServletException();
		}%>
		<a href="${pageContext.request.contextPath}/home.html">
		<img alt="" src="${pageContext.request.contextPath}/Images/banner.png" 
			style="position: absolute; border-radius: 20px; left: 24%; top: 2%; width :52%; height: 22%;
				margin-bottom:20px;"></a>
		
		<div class="f1">
		<h2>Hi, ${sessionScope.name}</h2>
		<h2>Welcome to the admin portal !</h2>
		
				<form action="tour" method="post">

						<input class="press press-blue press-round press-raised"
							type="submit" name="tour" value="Add Tour">
						<input class="press press-pink press-round press-raised" 
							type="submit" name="tour" value="Update Tour" >
						<input class="press press-teal press-round press-raised" 
							type="submit" name="tour" value="Delete Tour" >
						<input class="press press-lightgreen press-round press-raised" 
							type="submit" name="tour" value="View Tours" >
				</form><br>

				<form action="hotel" method="post">
						<input class="press press-orange press-round press-raised" 
							type="submit" name="hotel" value="Add Hotel" >
						<input class="press press-blue press-round press-raised" 
							type="submit" name="hotel" value="Update Hotel" >
						<input class="press press-pink press-round press-raised" 
							type="submit" name="hotel" value="Delete Hotel" >
						<input class="press press-teal press-round press-raised" 
							type="submit" name="hotel" value="View Hotels" >
				</form><br>
				<form action="misc" method="post">
						<input class="press press-orange press-round press-raised" 
							type="submit" name="misc" value="Show Bookings">
						<input class="press press-blue press-round press-raised" 
							type="submit" name="misc" value="Revoke Booking">
						<input class="press press-pink press-round press-raised" 
							type="submit" name="misc" value="Show Users">

				</form>
		
		<input type="button" class="press press-green press-pill press-ghost" 
			id="logout" style="color: black;" value="Logout" onclick="sendAlert();">
		</div>
</body>
</html>