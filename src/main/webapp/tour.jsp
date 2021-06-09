
<%@page import="java.sql.*,com.elanza48.TMS.model.DAO.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
    <%! Statement statement=null;
    	Statement statement2=null;
    	ResultSet resultSetFetch=null;
    	ResultSet resultSetPlace=null;
    	ResultSet resultSetTID=null;
    	Connection connection=null;
    	List<String> places= new ArrayList<>();
    	List<Integer> tid= new ArrayList<>();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css">
<title>Tour Portal</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/Images/fabicon.png">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/press.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/main.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dialog-mobile.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/mcx-dialog.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/normalize.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gate.css">
<style type="text/css">
#home{
		width: 300px;
	    padding: 20px;
	    position: absolute;
	    top:22%;
    	left: 44%;
    	transform: translate(-20%, -60%);
    	align-items: center;
    	text-align: center;
	}
	.f1{
	width: 72%;
    padding: 10px;
    padding-bottom: 40px;
    padding-top: 40px;
    
    position: absolute;
    top: 55%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center;
	}
	#logout{
		width: 200px;
	    padding: 10px;
	    position: absolute;
	    top:90%;
    	left: 580px;
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
		text-indent: 80px;
	}
	#name.gate{
		text-indent: 110px;
	}
	#place.gate{
		width: 300px;
		text-indent: 75px;
	}
	#TID_d.gate, #TID_u.gate{
		width: 270px;
		text-indent: 80px;
	}
	#id.gate:focus ,#name.gate:focus, #place.gate:focus, #TID_u.gate:focus,
	#id.gate:active ,#name.gate:active, #place.gate:active, #TID_u.gate:active{
	  	text-indent: 0;
	}
	</style>
</head>
<body>
		<%if(!(((String)request.getRequestURI()).contains("/admin/tour"))){
			throw new ServletException();
		}
		
		connection=ConnectionFactory.getInstance().getConnection();
		statement=connection.createStatement();
		statement2=connection.createStatement();%>

			<a href="adminHome">
			<img alt="" src="${pageContext.request.contextPath}/Images/banner.png" 
				style="position: absolute; border-radius: 20px; left: 26%; top: 2%; width:48%; height:20%; min-width: 600px;"></a>
			<h2 id="home">Hi, ${sessionScope.name}</h2>
			
<% if(((String)request.getParameter("tour")).equals("Add Tour")){ 
	resultSetPlace=statement.executeQuery("SELECT T_PLACE FROM hotelInfo");
	
	places.clear();
		while(resultSetPlace.next()){
			places.add(resultSetPlace.getString(1));
		}resultSetPlace.close();%>
<!-- Add tour -->
<form class="f1" action="TourFunc" method="post" style="width: 62%; top: 57%;  min-width: 950px;
	padding-top: 40px; padding-bottom: 40px;">
<table style=" width: 100%; height: 200px;">
	<tr>
		<td>
<table style="text-align: left;">
		<tr>
			<td><span>
				<input id="id" class="gate" NAME="tID_a" type="number" placeholder="Auto generated">
				<label>Tour ID:</label>
			</span>
			</td>
			</tr>
			<tr>
			<td><span>
					<input id="name" class="gate" NAME="tName_a" type="text" required="required" placeholder="Tour Name">
					<label>Tour Name:</label>
				</span>
			</td>
		</tr>
</table>
</td>
<td>
<table style="text-align: left;">
		<tr>
			<td><span>
			<SELECT id="place" class="gate" style="height:40px;" NAME="tPlace_a1" required="required" title="Must be different from 2 and 3">
				<option value="Select Place" disabled="disabled" selected="selected">Select Place</option>
				<%for (String s1 : places){%>
					<option value="<%=s1%>"><%=s1%></option>
				<%} %>
			</select>
			<label style="height:40px;">Place 1:</label>
			</span>
			</td>
		</tr>
		<tr>
			<td>
			<span>
			<SELECT id="place" class="gate" style="height:40px;" NAME="tPlace_a2" required="required" title="Must be different from 1 and 3">
				<option value="Select Place" disabled="disabled" selected="selected">Select Place</option>
				<option value="NULL">None</option>
				<%for (String s2 : places){%>
					<option value="<%=s2%>"><%=s2%></option>
				<%} %>
			</select>
			<label style="height:40px;">Place 2:</label>
			</span>
			</td>
		</tr>
		<tr>
			<td>
			<span>
			<SELECT id="place" class="gate" style="height:40px;" NAME="tPlace_a3" required="required" title="Must be different from 1 and 2">
				<option value="NULL">None</option>
				<option value="Select Place" disabled="disabled" selected="selected">Select Place</option>
				<%for (String s3 : places){%>
					<option value="<%=s3%>"><%=s3%></option>
				<%} %>
			</select>
			<label style="height:40px;">Place 3:</label>
			</span>
			</td>
		</tr>
		</table>
		</td>
		<td>
		<table style="text-align: left;">
		<tr>
			<td><span>
				<input class="gate" NAME="tDays_a" type="number" required="required" style="width: 100%"
					placeholder="Total days">
				<label>Days:</label>
			</span>
			<div  style="padding: 0px" class="tooltip" >
				<img alt="" style="margin-bottom: -4px;" 
					src="${pageContext.request.contextPath}/Images/info.png" >
						<span style="width: 160px">Not more than 10 days</span>
			</div>
			</td>
		</tr>
		<tr>
			<td><span>
				<input class="gate" NAME="tPrice_a" type="number" required="required" 
					 style="width: 100%" placeholder="Pakage price">
					 <label>Price:</label>
				</span>
				<div class="tooltip" >
				<img alt="" style="margin-bottom: -4px;" 
					src="${pageContext.request.contextPath}/Images/info.png" >
						<span>minimum ₹10000</span>
			</div>
				</td>
		</tr>
	</table>
	</td>
	</tr>
	</table>
	<br><input id="btn" class="press press-cyan press-teal press-raised press-round"
		type="submit" name="tourFun" value="ADD">
</form>
<%}else  if(((String)request.getParameter("tour")).equals("Delete Tour")){
resultSetTID=statement.executeQuery("SELECT T_ID FROM tourInfo");
	
	tid.clear();
		while(resultSetTID.next()){
			tid.add(resultSetTID.getInt(1));
		}resultSetTID.close();%>
<!-- Delete tour -->
<form action="TourFunc" class="f1" method="post" style="width: 400px">
		<span>
	 	<SELECT class="gate" style="height: 40px;" id="TID_d" NAME="TID_D" required="required">
				<option value="Select-ID" disabled="disabled" selected="selected">Select ID</option>
				<%for (int i2 : tid){%>
					<option value="<%=i2%>">t<%=i2%></option>
				<%} %>
			</select>
			<label style="height: 40px;">Tour Id :</label>
			</span>
			<br>
			<a href="#" 
				onclick="openPopup('overview',document.getElementById('TID_d').value,'TD');">overview</a>
				<br><br>
	<input id="btn" class="press press-cyan press-teal press-raised press-round"
		type="submit" name="tourFun" value="DELETE">
</form>
<%}else if(((String)request.getParameter("tour")).equals("Update Tour")){
	resultSetTID=statement2.executeQuery("SELECT T_ID FROM tourInfo");
	resultSetPlace=statement.executeQuery("SELECT T_PLACE FROM hotelInfo");
	
		places.clear();
		while(resultSetPlace.next()){
			places.add(resultSetPlace.getString(1));
		}
		resultSetPlace.close();
	
		tid.clear();
		while(resultSetTID.next()){
			tid.add(resultSetTID.getInt(1));
		}
		resultSetTID.close();%>
<!-- Update tour -->
<form class="f1" action="TourFunc" method="post" style="width: 72%; top: 57%;  min-width: 1000px;
	  padding-top: 40px; padding-bottom: 40px;">
<table style=" width: 100%; height: 200px;">
	<tr>
		<td>
<table style="text-align: left;">
		<tr>
			<td>
			<a href="#" 
				onclick="openPopup('overview',document.getElementById('TID_u').value,'TU');">overview</a><br>
			<span>
			<select class="gate" style="height: 40px;" id="TID_u" NAME="TID_U" required="required">
				<option value="Select-ID" disabled="disabled" selected="selected">Select ID</option>
				<%for (int i4 : tid){%>
					<option value="<%=i4%>">t<%=i4%></option>
				<%} %>
			</select>
			<label style="height: 40px;">Tour ID:</label>
			</span>

			</td>
		</tr>
		<tr>
			<td><span>
				<input id="name" class="gate" NAME="tName_u" type="text" required="required"
					placeholder="Tour Name">
					<label>Tour Name:</label>
			</span>
			</td>
		</tr>
</table>
</td>
<td>
<table style="text-align: left;">
		<tr>
			<td>
			<span>
			<SELECT id="place" class="gate" style="height:40px;" NAME="tPlace_u1" title="Must be different from 2 and 3" required="required">
				<option value="Select Place" disabled="disabled" selected="selected" >Select Place</option>
				<%for (String s1 : places){%>
					<option value="<%=s1%>"><%=s1%></option>
				<%} %>
			</select>
			<label style="height:40px;">Place 1:</label>
			</span>
			</td>
		</tr>
		<tr>	
			<td>
			<span>
			<SELECT id="place" class="gate" style="height:40px;" NAME="tPlace_u2" title="Must be different from 1 and 3" required="required">
			<option value="null">None</option>
				<option value="Select Place" disabled="disabled" selected="selected">Select Place</option>
				<%for (String s2 : places){%>
					<option value="<%=s2%>"><%=s2%></option>
				<%} %>
			</select>
			<label style="height:40px;">Place 2:</label>
			</span>
			</td>
		</tr>
		<tr>	
			<td><span>
			<SELECT id="place" class="gate" style="height:40px;" NAME="tPlace_u3" title="Must be different from 1 and 2" required="required">
				<option value="Select Place" disabled="disabled" selected="selected">Select Place</option>
				<option value="null">None</option>
				<%for (String s3 : places){%>
					<option value="<%=s3%>"><%=s3%></option>
				<%} %>
			</select>
			<label style="height:40px;">Place 3:</label>
			</span>
			</td>
		</tr>
</table>
</td>
<td>
<table style="text-align: left;">
		<tr>
			<td><span>
				<input class="gate" type="number" name="tDays_u" required="required" style="width: 100%"
					placeholder="Total days">
				<label>Days:</label>
			</span>
			<div class="tooltip" >
				<img alt="" style="margin-bottom: -4px" 
					src="${pageContext.request.contextPath}/Images/info.png" >
						<span style="width: 160px">Not more than 10 days</span>
			</div>
			</td>
		</tr>
		<tr>
			<td><span>
				<input class="gate" type="number" NAME="tPrice_u" required="required" style="width: 100%"
					placeholder="Total price">
				<label>Price:</label>
			</span>
			<div class="tooltip" >
				<img alt="" style="margin-bottom: -4px" 
					src="${pageContext.request.contextPath}/Images/info.png" >
						<span>minimun ₹10000</span>
			</div>
			</td>
		</tr>
</table>
</td>
</tr>
</table>
	<br><input id="btn" class="press press-cyan press-teal press-raised press-round"
		type="submit" name="tourFun" value="UPDATE">
</form>

<!-- View tours -->
<%}else if(((String)request.getParameter("tour")).equals("View Tours")){ 
	resultSetFetch=statement.executeQuery("SELECT * FROM tourInfo");%>
	
	<div class="f1" style="overflow: auto;height: 58%; width: 58%; padding-left: 25px; padding-right: 25px;">
	<table id="t1">
			<tr>
				<th>T_ID</th>
				<th>T_NAME</th>
				<th>T_PLACE_1</th>
				<th>T_PLACE_2</th>
				<th>T_PLACE_3</th>
				<th>T_DAYS</th>
				<th>T_PRICE</th>
			</tr>
	
	<%while(resultSetFetch.next()){%>
			<tr>
				<td><%=resultSetFetch.getInt(1) %></td>
				<td><%=resultSetFetch.getString(2) %></td>
				<td><%=resultSetFetch.getString(3)%></td>
				<td><%=resultSetFetch.getString(4)%></td>
				<td><%=resultSetFetch.getString(5)%></td>
				<td><%=resultSetFetch.getInt(6)%></td>
				<td><%=resultSetFetch.getInt(7)%></td>
			</tr>
		<%} resultSetFetch.close(); %>
		</table>
		</div>
		
	<%}%>
	<br><input id="logout" class="press press-green press-pill press-ghost"
		type="button" value="Logout" onclick="sendAlert();">
	</body>
</html>