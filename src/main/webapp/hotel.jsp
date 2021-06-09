<%@page import="java.sql.*,com.elanza48.TMS.model.DAO.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
    <%! Statement statement=null; 
    	ResultSet resultSet=null;
    	ResultSet resultSetHotel=null;
    	Connection connection=null;
    	List<Integer> hoteList = new ArrayList<>();%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css">
<title>Hotel portal</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/Images/fabicon.png">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/press.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gate.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/normalize.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/main.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dialog-mobile.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/mcx-dialog.js"></script>
<style type="text/css">
#home{
		width: 30%;
	    padding: 5%;
	    position: absolute;
	    top:30%;
    	left: 46%;
    	transform: translate(-20%, -75%);
	}
	.f1{
	width: 300px;
    padding: 40px;
    position: absolute;
    top: 55%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center;

	}
	h2 {
		align-content:center;
		transform: translate(45%, 90%);
	}
	#logout{
		width: 200px;
	    padding: 10px;
	    position: absolute;
	    top:30%;
    	left: 50%;
    	transform: translate(-50%, 900%);
    	font-weight: bold;
    	font-family: sans-serif;
    	font-size: 15px;
    	color: black;
	}
	#btn{
		width: 120px;
	    padding: 10px;
		font-weight: bold;
    	font-family: sans-serif;
    	font-size: 15px;
	}
	#id.gate{
		text-indent: 90px;
	}
	#name.gate{
		text-indent: 70px;
	}
	#place.gate{
		text-indent: 70px;
	}
	#hID_d.gate, #hID_u.gate{
		width: 280px;
		text-indent: 85px;
	}
	#id.gate:focus ,#name.gate:focus, #place.gate:focus, #hID_d.gate.gate:focus, #hID_u.gate.gate:focus,
	#id.gate:active ,#name.gate:active, #place.gate:active, #hID_d.gate.gate:active, #hID_u.gate.gate:active {
	  text-indent: 0;
	}
	</style>
</head>
<body>
		<%if(!(((String)request.getRequestURI()).contains("/admin/hotel"))){
			throw new ServletException();
		}

		connection=ConnectionFactory.getInstance().getConnection();
		statement=connection.createStatement();%>
		<a href="adminHome">
			<img alt="" src="${pageContext.request.contextPath}/Images/banner.png" width="48%" height="20%" 
				style="position: absolute; border-radius: 20px; left: 26%; top: 2%; min-width: 500px;"></a>
		<h2 id="home">Hi, ${sessionScope.name}</h2><br>

<% if(((String)request.getParameter("hotel")).equals("Add Hotel")){ %>
<!-- Add hotel -->
<form class="f1" action="HotelFunc" method="post" style="width: 30%; min-width: 400px;">
<table>
		<tr>
			<td><span>
				<input id="id" class="gate" type="number" name="hID_a" placeholder="Auto Generated">
				<label>Hotel ID:</label>
				</span>
			</td>
		</tr>
		<tr>		
			<td><span>
					<input id="name" class="gate" name="hName_a" type="text" required="required" placeholder="Hotel Name">
					<label>Name:</label>
				</span>
			</td>
		</tr>
		<tr>
			<td><span>
					<input id="place" class="gate" name="hPlace_a" type="text" required="required" placeholder="Tour Place">
					<label>Place:</label>
				</span>
			</td>
		</tr>
	</table>
	<br><input id="btn" class="press press-cyan press-teal press-raised press-round"
		type="submit" name="hotelFun" value="ADD">
</form>
<%}else if(((String)request.getParameter("hotel")).equals("Delete Hotel")){ 
	resultSetHotel=statement.executeQuery("SELECT * FROM hotelInfo");
	
	hoteList.clear();
		while(resultSetHotel.next()){
			hoteList.add(resultSetHotel.getInt(1));
		}resultSetHotel.close();%>
<!-- Delete hotel -->
<form action="HotelFunc" class="f1" method="post" style="width: 30%; min-width: 400px; ">
	<span>
	 	<SELECT class="gate" style="height: 40px;" id="hID_d" NAME="hID_d" required="required">
				<option value="Select-ID" disabled="disabled" selected="selected">Select ID</option>
				<%for(int i : hoteList){%>
					<option value="<%=i%>">h<%=i%></option>
				<%} %>
			</select>
			<label style="height: 40px;">Hotel Id :</label>
		</span>
			<br>
			<a href="#" 
				onclick="openPopup('overview',document.getElementById('hID_d').value,'HD');">overview</a><br><br>
	<input id="btn" class="press press-cyan press-teal press-raised press-round"
		type="submit" name="hotelFun" value="DELETE">
</form>
<%}else if(((String)request.getParameter("hotel")).equals("Update Hotel")){ 
	resultSetHotel=statement.executeQuery("SELECT * FROM hotelInfo");
	
	hoteList.clear();
		while(resultSetHotel.next()){
			hoteList.add(resultSetHotel.getInt(1));
		}resultSetHotel.close();%>
<!-- Update hotel -->
<form class="f1" action="HotelFunc" method="post" style="width: 30%; min-width: 400px;">
<table>
		<tr>
			<td><span><SELECT class="gate" style="height: 40px;" id="hID_u" NAME="hID_u" required="required">
				<option value="Select-ID" disabled="disabled" selected="selected">Select ID</option>
				<%for(int i : hoteList){%>
					<option value="<%=i%>">h<%=i%></option>
				<%} %>
			</select>
			<label style="height: 40px;">Hotel ID:</label>
			</span>
			<br>
			<a href="#" 
				onclick="openPopup('overview',document.getElementById('hID_u').value,'HU');">overview</a>
			</td>
			
		</tr>
		<tr>
			<td><span>
				<input id="name" class="gate" type="text" name="hName_u" required="required" placeholder="Hotel Name">
				<label>Name:</label>
			</span>
			</td>
		</tr>
		<tr>
			<td><span>
					<input id="place" class="gate" type="text" name="hPalce_u" required="required" placeholder="Tour Place">
					<label>Place:</label>
				</span>
			</td>
		</tr>
	</table>
	<br><input id="btn" class="press press-cyan press-teal press-raised press-round"
		type="submit" name="hotelFun" value="UPDATE">
</form>

<!-- View hotels -->
<%}else if(((String)request.getParameter("hotel")).equals("View Hotels")){
	resultSet=statement.executeQuery("SELECT * FROM hotelInfo");%>
	
	<div class="f1" style="overflow: auto;height: 45%; width: 30%;">
	<table id="t1">
			<tr>
				<th>H_ID</th>
				<th>H_NAME</th>
				<th>T_PLACE</th>
			</tr>
	
	<%while(resultSet.next()){%>
			<tr>
				<td><%=resultSet.getInt(1) %></td>
				<td><%=resultSet.getString(2) %></td>
				<td><%=resultSet.getString(3)%></td>
			</tr>
		<%} resultSet.close(); %>
		</table>
		</div>
<%}%>
<br><input id="logout" class="press press-green press-pill press-ghost"
	type="button" value="Logout" onclick="sendAlert();">

</body>
</html>