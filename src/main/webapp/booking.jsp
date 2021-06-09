<%@page import="org.sqlite.SQLiteException"%>
<%@page import="java.sql.*,com.elanza48.TMS.model.DAO.*,java.util.*"%>
<%@ page language="java" contentType="text/html;"
	session="true" pageEncoding="UTF-8"%>
    <%! Statement statement=null;
    PreparedStatement statement2=null;
    	ResultSet resultSetFetch=null;
    	Connection connection=null;
    	List<Integer> bid= new ArrayList<>();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/press.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://ajax.aspnetcdn.com/ajax/jquery.ui/1.10.4/themes/hot-sneaks/jquery-ui.css">

<link href="${pageContext.request.contextPath}/date_dropper/datedropper.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/date_dropper/datedropper.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<title>Booking Portal</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/Images/fabicon.png">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/normalize.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gate.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/main.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dialog-mobile.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/mcx-dialog.js"></script>
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
	width: 800px;
	height:380px;
    padding: 30px;
    padding-right:20px;
    padding-left:60px;
    position: absolute;
    top: 55%;
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
	    top:32%;
    	left: 50%;
    	transform: translate(-50%, 900%);
    	font-weight: bold;
    	font-family: sans-serif;
    	font-size: 15px;
	}
	#btn{
		width: 120px;
		height : 40px;
	    padding: 5px;
	    margin:60px;
	    margin-left:20px;
		font-weight: bold;
    	font-family: sans-serif;
    	font-size: 16px;
	}
	.ui-datepicker-calendar {
    	display: none;
	}
	#bID_a.gate{
		text-indent: 50px;
	}
	#name.gate{
		text-indent: 90px;
	}
	#room.gate{
		width: 350px;
		text-indent: 110px;
	}
	#BID_d.gate, #BID_r.gate{
		width: 340px;
		text-indent: 110px;
	}
	#bID_a.gate{
		width: 300px;
		text-indent: 80px;
	}
	
	#bID_a.gate:focus ,#name.gate:focus, #room.gate:focus, #BID_d.gate:focus, #BID_r.gate:focus,
	#bID_a.gate:active ,#name.gate:active, #room.gate:active, #BID_d.gate:active, #BID_r.gate:active{
	  text-indent: 0;
	}
	</style>
</head>
<body>
		<%if(!(((String)request.getRequestURI()).contains("/user/booking"))){
			throw new ServletException();
		}
	
		connection=ConnectionFactory.getInstance().getConnection();
		statement=connection.createStatement();%>

			<a href="userHome">
			<img alt="" src="${pageContext.request.contextPath}/Images/banner.png" width="48%" height="20%" 
				style="position: absolute; border-radius: 20px; left: 26%; top: 2%;"></a>
			<h2 id="home">Hi, ${sessionScope.name}</h2>


<% if(((String)request.getParameter("book")).equals("Book now")){
	resultSetFetch=statement.executeQuery("SELECT T_ID FROM tourInfo");

	bid.clear();
		while(resultSetFetch.next()){
			bid.add(resultSetFetch.getInt(1));
		}%>
<!-- Book Now -->
<form class="f1" action="BookingFunc" method="post" style="width: 880px; top: 57%; padding-left: 0px;">
<table style=" width: 750px; height: 200px;">
	<tr>
		<td>
	
	<table style="text-align: left;">
		<tr>
			<td>
			<a href="#" 
					onclick="openPopup('overview',document.getElementById('bID_a').value,'AB');">overview</a>
					<br>
			<span>
				<select class="gate" style="height: 40px;" id="bID_a" NAME="bID_a" required="required">
					<option value="Select-tour" disabled="disabled" selected="selected">Select Tour</option>
					<%for (int t1 : bid){%>
						<option value="<%=t1%>">t<%=t1%></option>
					<%} %>
				</select>
				<label style="height: 40px;">Tour ID:</label>
			</span>
				
			</td>
			
		</tr>
		<tr>
			<td><span>
					<input class="gate" style="width: 100%" type="text" id="datepicker"
						placeholder="YYYY-MM-DD" name="tDate" required="required" data-large-mode="true" data-lock="from"
	           			data-translate-mode="false" data-theme="leaf" data-format="Y-m-d"
	            		data-fx-mobile="false" data-large="true" data-max-year="2025"
	            		data-min-year="2010" data-autofill="false">
					<label>Date:</label>
					<script>
            $('#datepicker').dateDropper();
        </script>
				</span>
			</td>
		</tr>
		<tr>
			<td ><span>
			<input id="name" class="gate" style="width: 100%" type="number" pattern="[1-8]" title="Must be between 1 to 10."
					name="pNo" required="required" placeholder="Total Persons">
					<label>Persons:</label>
				</span>
				<div class="tooltip" >
					<img alt="" style="margin-bottom: -4px" 
					src="${pageContext.request.contextPath}/Images/info.png">
					<span>₹ 12000/person<br>(Above 3 years)<br>Upto 7 persons.</span>
				</div>
			</td>
		</tr>
		<tr>
			<td>
			<span>
				<select id="room" class="gate" style="height: 40px;" NAME="rType" required="required">
					<option value="Select-type" disabled="disabled" selected="selected">Select Type</option>
					<option value="ST">STANDARD</option>
					<option value="SU">SUPERIOR</option>
					<option value="DE">DELUXE</option>
				</select>
				<label style="height: 40px;">Room type:</label>
			</span>
			<div class="tooltip" >
					<img alt="" style="margin-bottom: -4px" 
					src="${pageContext.request.contextPath}/Images/info.png" >
						<span>STANDARD: &nbsp; ₹4000<br>SUPERIOR: &nbsp; ₹7000<br>DELUXE: &nbsp; ₹10000</span>
				</div>
			</td>
		</tr>
	</table>
	</td>
	<td>
	<table style="text-align: left;">
			<tr>
				<td><span>
						<input id="name" class="gate" type="text" name="cName" placeholder="Card Holder">
						<label>Name :</label>
					</span>
				</td>
			</tr>
			<tr>
				<td><span>
						<input id="name" class="gate" type="number" name="cNo"
							placeholder="0000 0000 0000 0000" style="width: 145%;">
						<label>Card No:</label>
					</span>
				</td>
			</tr>
			<tr>
				<td><span>
						<input  class="gate" style="width: 100%" type="text"  id="expiryDate" name="cExp" placeholder="MM/YY">
						<label>ExpD:</label>
					</span>
				</td>
			</tr>
			<tr>
				<td><span>
						<input class="gate" style="width: 80%" type="number" pattern="{3}" name="cvv" placeholder="000">
						<label>CVV:</label>
					</span>
				</td>
			</tr>
		</table>
		</td>
		</tr>
		</table>
	<br>
	<input id="btn" class="press press-cyan press-teal press-raised press-round"
		style="margin-top: 0px" type="submit" name="uBooking" value="Book Now">
	</form>
	<!-- Booking History -->
	<%}else  if(((String)request.getParameter("book")).equals("Booking History")){
		statement2=connection.prepareStatement("SELECT * FROM bookingInfo WHERE U_EMAIL=?");
		statement2.setString(1, (String)session.getAttribute("email"));
		resultSetFetch=statement2.executeQuery();%>
		
		<div class="f1" style="overflow: auto; height: 55%; width: 75%; padding-left: 20px;">
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
					<td><a href="#" onclick="openPopup('overview',<%=resultSetFetch.getInt(3)%>,'UCB');">
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
	
	<!-- Cancel Booking -->
	<%}else  if(((String)request.getParameter("book")).equals("Cancel Booking")){
		statement2=connection.prepareStatement("SELECT B_ID FROM bookingInfo WHERE U_EMAIL=? AND STATUS=1");
		statement2.setString(1, (String)session.getAttribute("email"));
		resultSetFetch=statement2.executeQuery();

		bid.clear();
		while(resultSetFetch.next()){
			bid.add(resultSetFetch.getInt(1));
		}%>
		<form action="BookingFunc" class="f1" method="post" style="width: 35%; height: 30%; min-width: 460px; padding-right: 5%;">
			<span>
		 <SELECT class="gate" style="height: 40px;" id="BID_d" NAME="BID_d" required="required">
				<option value="Select-ID" disabled="disabled" selected="selected">Select ID</option>
				<%for (int i2 : bid){%>
					<option value="<%=i2%>">t<%=i2%></option>
				<%} %>
			</select>
			<label style="height: 40px;">Booking Id :</label>
			</span>
			<br>
			<a href="#" 
				onclick="openPopup('overview',document.getElementById('BID_d').value,'CB');">overview</a>
				<br><br>
			<input id="btn" class="press press-cyan press-teal press-raised press-round"
				type="submit" style="margin-top: 20px; margin-left: 65px;" name="uBooking" value="CANCEL">
		</form>
	
	<!-- Booking Report -->
	<%}else if(((String)request.getParameter("book")).equals("Booking Report")){
	
		statement2=connection.prepareStatement("SELECT B_ID FROM bookingInfo WHERE U_EMAIL=? AND STATUS=1");
		statement2.setString(1, (String)session.getAttribute("email"));
		resultSetFetch=statement2.executeQuery();

		bid.clear();
		while(resultSetFetch.next()){
			bid.add(resultSetFetch.getInt(1));
		}%>
		<form action="BookingFunc" class="f1" style="width: 35%; height: 30%; padding-right: 5%; min-width: 460px;" method="post">
		<span>
		 <SELECT class="gate" style="height: 40px;" id="BID_r" NAME="BID_r" required="required">
				<option value="Select-ID" disabled="disabled" selected="selected">Select ID</option>
				<%for (int i2 : bid){%>
					<option value="<%=i2%>">t<%=i2%></option>
				<%} %>
			</select>
			<label style="height: 40px;">Booking Id :</label>
		</span>
				<br>
			<input id="btn" class="press press-cyan press-teal press-raised press-round"
				style="width: 150px; margin-left: 12%;" type="submit" name="uBooking" value="VIEW REPORT">
		</form>
	
	<!-- View tours -->
	<%}else if(((String)request.getParameter("book")).equals("View Tours")){ 
	resultSetFetch=statement.executeQuery("SELECT * FROM tourInfo");%>
	
	<div class="f1" style="overflow: auto;height: 55%; width: 64%; padding-left: 30px;">
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
	<br><input class="press press-green press-pill press-ghost"
		id="logout" type="button" style="color: black;" value="Logout" onclick="sendAlert();">
</body>
</html>