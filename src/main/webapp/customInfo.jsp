<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css">
		<title>Not Permitted</title>
		<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/Images/fabicon.png">
	</head>
	<body>
		<P align=center>
			<img SRC="${pageContext.request.contextPath}Images/warning48.png" WIDTH="48" HEIGHT="48" border="0" alt="">
			<br>
			<font color="#fb8c00" size=5 Face="verdana">User already logged in !</font>
			<br>
			<font face="Comic Sans MS" size=3>
				<a href="home.html">&lt;&lt; Back</a>
			</font>
			<img alt="" src="${pageContext.request.contextPath}/Images/banner.png" width="60%" height="30%" 
			style="position: absolute; border-radius: 20px; left: 20%; top: 30%;">
		</P>
	</body>
</html>