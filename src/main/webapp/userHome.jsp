<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css">
<title>User Home</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/Images/fabicon.png">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/press.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/main.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dialog-mobile.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/mcx-dialog.js"></script>

<style type="text/css">
	.f1{
	width: 500px;
	height:240px;
	min-width: 500px;
	min-height:240px;
    padding: 30px;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center;
	}
	.f1 input{
		margin: 10px;
	}
	#logout{
		width: 200px;
	    padding: 10px;
	    position: absolute;
	    top:45%;
    	left: 50%;
    	transform: translate(-50%, 500%);
    	font-weight: bold;
    	font-family: sans-serif;
    	font-size: 15px;
    	margin-bottom: 10px;
	}
	input[type=submit], #dereg {
   		height: 2.5em;
   		font-weight: bold;
    	font-family: sans-serif;
	}

	</style>
</head>
<body>
		<%if(!(((String)request.getRequestURI()).contains("/user/userHome"))){
			throw new ServletException();
		}%>
		
		<a href="${pageContext.request.contextPath}/home.html">
		<img alt="" src="${pageContext.request.contextPath}/Images/banner.png"   
			style="position: absolute; border-radius: 20px; left: 24%; top: 2%; width: 52%; height:24%;
			margin-bottom:50px;"></a>
		
		
		
		<form class="f1" action="booking" method="post">
			<h2>Hi, ${sessionScope.name}</h2>
		<h2>Welcome to the User portal !</h2>
		
				<input class="press press-blue press-round press-raised "
					type="submit" name="book" value="Book now">
				<input class="press press-pink press-round press-raised "
					type="submit" name="book" value="Booking History">
				<input class="press press-lightgreen press-round press-raised "
					type="submit" name="book" value="Booking Report">
					<br>
					<input class="press press-lightgreen press-round press-raised "
					type="submit" name="book" value="View Tours">
				<input class="press press-blue press-round press-raised " 
					type="submit" name="book" value="Cancel Booking">
				<input id="dereg" class="press press-pink press-round press-raised"
					type="button" name="book" value="De-register" title="Delete your user account."
						onclick="mcxDialog.showBottom({
					         title: 'Are you sure you want to Deregister?',
					         btn: ['Deregister Now'],
					         btnColor: ['#d50000',],
					         cancelText: 'CANCEL',
					         btnClick: function(){
					        	 <%session.setAttribute("de-reg", "true"); %>
					        	 var form = document.createElement('form');
					 		    form.setAttribute('method', 'post');
					 		    form.setAttribute('action', 'UserDeReg');
					 	        document.body.appendChild(form);
					 		    form.submit();
					       }
					  });">
		</form>
		<input type="button" class="press press-green press-pill press-ghost"
			id="logout" style="color: black;" value="Logout" onclick="sendAlert();">
</body>
</html>