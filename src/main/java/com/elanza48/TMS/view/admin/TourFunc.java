package com.elanza48.TMS.view.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elanza48.TMS.model.DAO.ConnectionFactory;

import java.sql.*;

/**
 * Servlet implementation class TourFunc
 */
@WebServlet("/admin/TourFunc")
public class TourFunc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PreparedStatement statement=null; 
	ResultSet resultSetFetch=null;
	Connection connection=null;
	int update=-1;
	String resource=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TourFunc() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		connection=ConnectionFactory.getInstance().getConnection();
		resource=request.getSession(false).getServletContext().getContextPath();
		
		if(((String)request.getParameter("tourFun")).equals("ADD")){
			String place_1= (String) request.getParameter("tPlace_a1");
			String place_2= (String) request.getParameter("tPlace_a2");
			String place_3= (String) request.getParameter("tPlace_a3");
			
			if(place_1.equals(place_2) || place_1.equals(place_3) || place_2.equals(place_3)){
				request.setAttribute("customInfo.type", "warning");
				request.setAttribute("customInfo.msg", "All three places must be unique !");
				request.setAttribute("customInfo.back", "adminHome");
				
				RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
				dispatcher.forward(request, response);

			}else if(Integer.parseInt(request.getParameter("tDays_a"))>10){
				request.setAttribute("customInfo.type", "warning");
				request.setAttribute("customInfo.msg", "More than 10 days not allowed !");
				request.setAttribute("customInfo.back", "adminHome");
				
				RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
				dispatcher.forward(request, response);

			}else if(Integer.parseInt(request.getParameter("tPrice_a"))<10000) {
				request.setAttribute("customInfo.type", "warning");
				request.setAttribute("customInfo.msg", "Minimum price of ₹10000 be charged !");
				request.setAttribute("customInfo.back", "adminHome");
				
				RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
				dispatcher.forward(request, response);
			}else {
				
				
				try{
					statement=connection.prepareStatement(
							"INSERT INTO tourInfo (T_NAME, T_PLACE_1, T_PLACE_2, T_PLACE_3, T_DAYS, T_PRICE) VALUES(?,?,?,?,?,?)");
					statement.setString(1, (String)request.getParameter("tName_a"));
					statement.setString(2, (String)request.getParameter("tPlace_a1"));
					statement.setString(3, (String)request.getParameter("tPlace_a2"));
					statement.setString(4, (String)request.getParameter("tPlace_a3"));
					statement.setInt(5, Integer.parseInt((String)request.getParameter("tDays_a")));
					statement.setInt(6, Integer.parseInt(request.getParameter("tPrice_a")));
					update= statement.executeUpdate();
					
					if(update>0){
						request.setAttribute("customInfo.type", "correct");
						request.setAttribute("customInfo.msg", "Tour Insertion Successful !");
						request.setAttribute("customInfo.back", "adminHome");
						
						RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
						dispatcher.forward(request, response);

					}else{
						request.setAttribute("customInfo.type", "error");
						request.setAttribute("customInfo.msg", "Tour Insertion Unsuccessful !");
						request.setAttribute("customInfo.back", "adminHome");
						
						RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
						dispatcher.forward(request, response);
					}
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
		}else if(((String)request.getParameter("tourFun")).equals("UPDATE")){
			
			String place_1= (String) request.getParameter("tPlace_u1");
			String place_2= (String) request.getParameter("tPlace_u2");
			String place_3= (String) request.getParameter("tPlace_u3");

			if(place_1.equals(place_2) || place_1.equals(place_3) || place_2.equals(place_3)){

				request.setAttribute("customInfo.type", "warning");
				request.setAttribute("customInfo.msg", "All three places must be unique !");
				request.setAttribute("customInfo.back", "adminHome");
				
				RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
				dispatcher.forward(request, response);
			}else if(Integer.parseInt(request.getParameter("tDays_u"))>10){

				request.setAttribute("customInfo.type", "warning");
				request.setAttribute("customInfo.msg", "More than 10 days not allowed !");
				request.setAttribute("customInfo.back", "adminHome");
				
				RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
				dispatcher.forward(request, response);
			}else if(Integer.parseInt(request.getParameter("tPrice_u"))<10000) {

				request.setAttribute("customInfo.type", "warning");
				request.setAttribute("customInfo.msg", "Minimum price of ₹10000 be charged !");
				request.setAttribute("customInfo.back", "adminHome");
				
				RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
				dispatcher.forward(request, response);
			}else {
				
				try{
					statement=connection.prepareStatement(
							"UPDATE tourInfo SET T_NAME=?, T_PLACE_1=?, T_PLACE_2=?, T_PLACE_3=?, T_DAYS=?,T_PRICE=? WHERE T_ID=?");
					statement.setString(1, (String)request.getParameter("tName_u"));
					statement.setString(2, (String)request.getParameter("tPlace_u1"));
					statement.setString(3, (String)request.getParameter("tPlace_u2"));
					statement.setString(4, (String)request.getParameter("tPlace_u3"));
					statement.setInt(5, Integer.parseInt((String)request.getParameter("tDays_u")));
					statement.setInt(6, Integer.parseInt(request.getParameter("tPrice_u")));
					statement.setInt(7, Integer.parseInt(request.getParameter("TID_U")));
					update= statement.executeUpdate();
					
					if(update>0){

						request.setAttribute("customInfo.type", "correct");
						request.setAttribute("customInfo.msg", "Tour Updation Successful !");
						request.setAttribute("customInfo.back", "adminHome");
						
						RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
						dispatcher.forward(request, response);
					}else{

						request.setAttribute("customInfo.type", "error");
						request.setAttribute("customInfo.msg", "Tour Updation Unsuccessful !");
						request.setAttribute("customInfo.back", "adminHome");
						
						RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
						dispatcher.forward(request, response);
					}
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
		}else if(((String)request.getParameter("tourFun")).equals("DELETE")){
			
			try{
				statement=connection.prepareStatement("DELETE FROM tourInfo WHERE T_ID=?");
				statement.setInt(1, Integer.parseInt(request.getParameter("TID_D")));
				update= statement.executeUpdate();
				
				if(update>0){
					
					request.setAttribute("customInfo.type", "correct");
					request.setAttribute("customInfo.msg", "Tour Deletion Successful !");
					request.setAttribute("customInfo.back", "adminHome");
					
					RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
					dispatcher.forward(request, response);
				}else{

					request.setAttribute("customInfo.type", "error");
					request.setAttribute("customInfo.msg", "Tour Deletion Unsuccessful !");
					request.setAttribute("customInfo.back", "adminHome");
					
					RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
					dispatcher.forward(request, response);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
