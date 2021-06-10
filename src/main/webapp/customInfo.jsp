<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
 <%! final String[] INFO_ATTRIBUTES= {
 			"customInfo.type",
 			"customInfo.msg",
 			"customInfo.back"
 	 };
 %>   
    
 <%
	String type = (String) request.getAttribute(INFO_ATTRIBUTES[0]);
 	String msg = (String) request.getAttribute(INFO_ATTRIBUTES[1]);
 	String back = (String) request.getAttribute(INFO_ATTRIBUTES[2]);
 	
 	String txt_color;
 	String img;
 	
 	switch(type){
 		case "correct" : 
 			txt_color="green";
 			img="correct48.png";
 			break;
 		
 		case "warning" :
 			txt_color="#fb8c00";
 			img="warning48.png";
 			break;
 			
 		default :
 			txt_color="red";
 			img="error48.png";
 			break;
 	}
 %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css">
		<title>Information</title>
		<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/Images/fabicon.png">
	</head>
	<body>
		<P align=center>
			<img SRC="${pageContext.request.contextPath}/Images/<%=img%>" WIDTH="48" HEIGHT="48" border="0" alt=""><br>
			<span style="color:<%=txt_color%>;  font-size:25px; font-family:verdana"><%=msg%></span><br>
			<span style=" font-family:'Comic Sans MS'; font-size:16px">
				<a href=${pageContext.request.contextPath}/<%=back%>>&lt;&lt; Back</a>
			</span>
			<img alt="" src="${pageContext.request.contextPath}/Images/banner.png" width="60%" height="30%" 
			style="position: absolute; border-radius: 20px; left: 20%; top: 30%;">
		</P>
	</body>
</html>