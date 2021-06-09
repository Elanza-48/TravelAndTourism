<%@page import="java.sql.*,com.elanza48.TMS.model.DAO.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
    <%! Statement statement=null;
    	PreparedStatement statement2=null;
    	ResultSet resultSetFetch=null;
    	Connection connection=null;
    	List<Integer> bid= new ArrayList<>();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css">
<title>Miscellaneous Tasks</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/Images/fabicon.png">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/press.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gate.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/normalize.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/main.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dialog-mobile.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/mcx-dialog.js"></script>
<style type="text/css">
#home{
		width: 300px;
	    padding: 20px;
	    position: absolute;
	    top:160px;
    	left: 44%;
    	transform: translate(-20%, -60%);
    	align-items: center;
    	text-align: center;
	}
	.f1{
	width: 300px;
    padding: 40px;
    position: absolute;
    top: 58%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center;
	}
	h2 {
		align-content:center;
	}
	#logout{
		width: 200px;
	    padding: 10px;
	    position: absolute;
	    top:84%;
    	left: 50%;
    	transform: translate(-50%, 100%);
    	font-weight: bold;
    	font-family: sans-serif;
    	font-size: 15px;
    	color: black;
	}
	#btn{
		width: 100px;
	    padding: 5px;
		font-weight: bold;
    	font-family: sans-serif;
    	font-size: 15px;
	}#reg{
		width: 150px;
    	padding: 10px;
    	font-weight: bold;
    	font-family: sans-serif;
    	font-size: 15px;
	}
	#MB_r.gate{
		width: 340px;
		text-indent: 110px;
	}
	#MB_r.gate:focus ,#MB_r.gate:active{
		text-indent: 0;
	}
	</style>
</head>
<body>
		<%if(!(((String)request.getRequestURI()).contains("/admin/misc"))){
			throw new ServletException();
		}
		
		
		connection=ConnectionFactory.getInstance().getConnection();
		statement=connection.createStatement();%>
		
			<a href="adminHome">
		<img alt="" src="${pageContext.request.contextPath}/Images/banner.png" width="48%" height="20%" 
				style="position: absolute; border-radius: 20px; left: 26%; top: 2%;"></a>
			<h2 id="home">Hi, ${sessionScope.name}</h2>
		
		<!-- Show Bookings -->
		<% if(((String)request.getParameter("misc")).equals("Show Bookings")){ 
			resultSetFetch=statement.executeQuery("SELECT * FROM bookingInfo");%>
		
		<div class="f1" style="overflow: auto;height: 55%; width: 73%;">
		<table id="t1">
				<tr>
					<th>B_ID</th>
					<th>B_DATE</th>
					<th>T_ID</th>
					<th>T_DATE</th>
					<th>U_EMAIL</th>
					<th>PERSONS</th>
					<th>R_TYPE</th>
					<th>T_AMOUNT</th>
					<th>STATUS</th>
				</tr>
		
		<%while(resultSetFetch.next()){%>
				<tr>
					<td><%=resultSetFetch.getInt(1) %></td>
					<td><%=resultSetFetch.getString(2) %></td>
					<td><a href="#" 
						onclick="openPopup('overview',<%=resultSetFetch.getInt(3)%>,'MSB');">
						<%=resultSetFetch.getInt(3)%></a></td>
					<td><%=resultSetFetch.getString(4)%></td>
					<td><%=resultSetFetch.getString(5)%></td>
					<td><%=resultSetFetch.getInt(6) %></td>
					
					<%if(resultSetFetch.getString(7).equals("DE")) {%>
						<td>DELUXE</td>
					<%}else if(resultSetFetch.getString(7).equals("SU")){ %>
						<td>SUPERIOR</td>
					<%} else if(resultSetFetch.getString(7).equals("ST")){ %>
						<td>STANDARD</td>
					<%} %>
					<td><%=resultSetFetch.getInt(8)%></td>
					
					<%if(resultSetFetch.getInt(9)==1) {%>
						<td>AVAILABLE</td>
					<%}else if(resultSetFetch.getInt(9)==0){ %>
						<td>CANCELLED</td>
					<%} %>
				</tr>
			<%} resultSetFetch.close(); %>
			</table>
			</div>
		
		<%}else  if(((String)request.getParameter("misc")).equals("Revoke Booking")){
			resultSetFetch=statement.executeQuery("SELECT B_ID FROM bookingInfo WHERE STATUS=1");
			bid.clear();
				while(resultSetFetch.next()){
					bid.add(resultSetFetch.getInt(1));
				}%>
		<!-- Revoke Bookings -->
			<form id="revoke" action="MiscFunc" class="f1" method="post" style="width: 30%; min-width: 420px;">
			<span>
				 <SELECT class="gate" style="height: 40px;" id="MB_r" NAME="MB_r" required="required">
				<option value="Select-ID" disabled="disabled" selected="selected">Select ID</option>
					<%for (int i2 : bid){%>
						<option value="<%=i2%>">b<%=i2%></option>
					<%} %>
					</select>
					<label style="height: 40px;">Booking Id :</label>
				</span>
					<br>
				<a href="#" 
					onclick="openPopup('overview',document.getElementById('MB_r').value,'MR');">overview</a>
				<br><br>
				<input id="btn" class="press press-cyan press-teal press-raised press-round"
					type="submit" name="miscFun" value="REVOKE">
			</form>

		<!-- Show Users. -->
		<%}else if(((String)request.getParameter("misc")).equals("Show Users")){
		
		resultSetFetch=statement.executeQuery("SELECT U_ID, U_NAME, U_EMAIL, U_MOB, U_ADMIN FROM userAccount");%>
		
		<div class="f1" style="overflow: auto;height: 49%; width: 49%;">
		<table id="t1">
				<tr>
					<th>U_ID</th>
					<th>U_NAME</th>
					<th>U_EMAIL</th>
					<th>U_MOB</th>
					<th>U_ADMIN</th>
				</tr>
		
		<%while(resultSetFetch.next()){%>
				<tr>
					<td><%=resultSetFetch.getInt(1) %></td>
					<td><%=resultSetFetch.getString(2) %></td>
					<td><%=resultSetFetch.getString(3)%></td>
					<td><%=resultSetFetch.getLong(4)%></td>
					<%if(resultSetFetch.getInt(5)==1) {%>
						<td>ADMIN</td>
					<%}else if(resultSetFetch.getInt(5)==0){ %>
						<td>USER</td>
					<%} %>
				</tr>
			<%} resultSetFetch.close(); %>
			</table>
			</div>
			<p style="font-size: 12px; height:15px;  width: 550px;  position: absolute; text-align:center;
				bottom:12%; left: 30%;font-family: cursive; background-color:#eeff41;" >
				<b>*NOTE:</b>	Administrators are prohibited form accessing the passwords due to privacy concern.
			</p>
		<%} %>
		<br><input id="logout" class="press press-green press-pill press-ghost"
			type="button" value="Logout" onclick="sendAlert();">
	
</body>
</html>