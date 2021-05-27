<%@page import="java.sql.*,com.elanza48.model.DAO.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%! PreparedStatement statement=null;
    	ResultSet resultSetFetch=null;
    	Connection connection=null;
    	String key=null;
    	int val=-1;%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Overview</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/Images/fabicon.png">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/press.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/main.js"></script>
<style type="text/css">
	html {
	    position: relative;
	    min-width: 260px;
	    min-height: auto;
	    resize: none;
	    width: 260px;
	    height: auto;   
	}
	#logout{
		width: 100px;
	    padding: 5px;
	    position: absolute;
	    top:20%;
    	left: 50%;
    	transform: translate(-50%, 700%);
    	font-weight: bold;
    	font-family: sans-serif;
    	font-size: 15px;
    	color: #b2ff59;
    	border-color: #558b2f;
	}
</style>
</head>
<body style="background-color: #880e4f; background-image: none;">
	
<%
	if(!(((String)request.getRequestURI()).contains("/overview"))){
		throw new ServletException();
	}

	if(session.getAttribute("name")==null){%>
		<P align=center><IMG SRC="Images/error48.png" WIDTH="48" HEIGHT="48" BORDER="0" ALT=""><br>
			<FONT COLOR="Red" size=5 Face="verdana">You are not permitted to Access the Admin Portal !</FONT>
		</P>
	<%}else{
		connection=ConnectionFactory.getInstance().getConnection();
		key=(String)request.getParameter("key");
		
		try{
			val=Integer.parseInt((String)request.getParameter("val"));
		
		
		if(	(key.equals("TU") && (int)session.getAttribute("auth")==1) || 
				(key.equals("TD") && (int)session.getAttribute("auth")==1) ||
				(key.equals("MSB") && (int)session.getAttribute("auth")==1) ||
				(key.equals("AB") && (int)session.getAttribute("auth")==0)	||
				(key.equals("UCB") && (int)session.getAttribute("auth")==0)){
		statement=connection.prepareStatement("SELECT * FROM tourInfo WHERE T_ID=?");
		statement.setInt(1, val);
		resultSetFetch=statement.executeQuery();
		%>
	<table id="t1" style="font-size: 14px; font-family: sans-serif; padding: 10%">
			<tr>
				<th>T_ID</th>
				<td><%=resultSetFetch.getInt(1) %></td>
			</tr>
			<tr>
				<th>T_NAME</th>
				<td><%=resultSetFetch.getString(2) %></td>
			</tr>
			<tr>
				<th>T_PLACE_1</th>
				<td><%=resultSetFetch.getString(3)%></td>
			</tr>
			<tr>
				<th>T_PLACE_2</th>
				<td><%=resultSetFetch.getString(4)%></td>
			</tr>
			<tr>
				<th>T_PLACE_3</th>
				<td><%=resultSetFetch.getString(5)%></td>
			</tr>
			<tr>
				<th>T_DAYS</th>
				<td><%=resultSetFetch.getInt(6)%></td>
			</tr>
			<tr>
				<th>T_PRICE</th>
				<td><%=resultSetFetch.getInt(7)%></td>
			</tr>
		</table>
		<button id="logout" class="press press-blue press-round press-ghost"
			type="button" onclick="closePopup();">close</button>
		
		<%}else if( (key.equals("HU") && (int)session.getAttribute("auth")==1) || 
				(key.equals("HD") && (int)session.getAttribute("auth")==1)){
			statement=connection.prepareStatement("SELECT * FROM hotelInfo WHERE H_ID=?");
			statement.setInt(1, val);
			resultSetFetch=statement.executeQuery();
			%>
		<table id="t1" style="font-size: 14px; font-family: sans-serif; padding: 10%">
				<tr>
					<th>H_ID</th>
					<td><%=resultSetFetch.getInt(1) %></td>
				</tr>
				<tr>
					<th>H_NAME</th>
					<td><%=resultSetFetch.getString(2) %></td>
				</tr>
				<tr>
					<th>T_PLACE</th>
					<td><%=resultSetFetch.getString(3)%></td>
				</tr>
			</table>
			<button id="logout" class="press press-blue press-round press-ghost"
				style="top: -10%;" type="button" onclick="closePopup();">close</button>
			
			<%}else if( (key.equals("HU") && (int)session.getAttribute("auth")==1) || 
				(key.equals("MR") && (int)session.getAttribute("auth")==1) ||
				(key.equals("CB") && (int)session.getAttribute("auth")==0)){
				
				statement=connection.prepareStatement("SELECT B_ID, B_DATE, T_ID, T_DATE, U_EMAIL,"+
						" PERSONS, R_TYPE,T_AMOUNT FROM bookingInfo WHERE B_ID=?");
				statement.setInt(1, val);
				resultSetFetch=statement.executeQuery();
					%>
					<table id="t1" style="font-size: 14px; font-family: sans-serif; padding: 10%">
						<tr>
							<th>B_ID</th>
							<td><%=resultSetFetch.getInt(1) %></td>
						</tr>
						<tr>
							<th>B_DATE</th>
							<td><%=resultSetFetch.getString(2) %></td>
						</tr>
						<tr>
							<th>T_ID</th>
							<%if((int)session.getAttribute("auth")==1) {%>
							<td><a href="#" 
								onclick="openPopupSelf('overview',<%=resultSetFetch.getInt(3)%>,'MSB');"><%=resultSetFetch.getInt(3)%></a></td>
									
								<%}else if((int)session.getAttribute("auth")==0){ %>
								<td><a href="#" 
								onclick="openPopupSelf('overview',<%=resultSetFetch.getInt(3)%>,'UCB');"><%=resultSetFetch.getInt(3)%></a></td>
								<%} %>
						</tr>
						<tr>
							<th>T_DATE</th>
							<td><%=resultSetFetch.getString(4)%></td>
						</tr>
						<tr>
							<th>U_EMAIL</th>
							<td><%=resultSetFetch.getString(5)%></td>
						</tr>
						<tr>
							<th>PERSONS</th>
							<td><%=resultSetFetch.getInt(6) %></td>
						</tr>
						<tr>
							<th>R_TYPE</th>
							<%if(resultSetFetch.getString(7).equals("DE")) {%>
								<td>DELUXE</td>
							<%}else if(resultSetFetch.getString(7).equals("SU")){ %>
								<td>SUPERIOR</td>
							<%} else if(resultSetFetch.getString(7).equals("ST")){ %>
								<td>STANDARD</td>
							<%} %>
						</tr>
						<tr>
							<th>T_AMOUNT</th>
							<td><%=resultSetFetch.getInt(8)%></td>
						</tr>
					</table>
					<button id="logout" class="press press-blue press-round press-ghost"
						style="top: 30%;" type="button" onclick="closePopup();">close</button>
				
			
			<%}else{
				%>
					<P align=center><IMG SRC="Images/warning48.png" WIDTH="48" HEIGHT="48" BORDER="0" ALT=""><br>
						<FONT COLOR="#eeff41" size=5 Face="verdana">Not Allowed !</FONT>
					</P>
				<%
			}
		
		}catch(NumberFormatException e){
			%>
			<P align=center>
				<IMG SRC="Images/warning48.png" WIDTH="48" HEIGHT="48" BORDER="0" ALT=""><br>
				<FONT size=5 Face="verdana">Select the tour correctly !</FONT>
			</P>
			<button id="logout" class="press press-blue press-round press-ghost"
			type="button" style="top: 5%;" onclick="closePopup();">close</button>
		<%}
	}%>

</body>
</html>