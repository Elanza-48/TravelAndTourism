<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logged out</title>
<link rel="stylesheet" type="text/css" href="css/theme.css">
</head>
<body>
	<%
	if(session.getAttribute("name")!=null){
		session.invalidate();
		System.out.println("\nLogged out Successfully!\n");
	}else{
		System.out.println("\nNothing to log out!\n");
	}

	response.sendRedirect("home.html");%>
</body>
</html>