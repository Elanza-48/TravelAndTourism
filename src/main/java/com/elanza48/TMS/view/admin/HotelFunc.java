package com.elanza48.TMS.view.admin;

import java.io.IOException;
import org.sqlite.SQLiteException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elanza48.TMS.model.DAO.ConnectionFactory;

import java.sql.*;
/**
 * Servlet implementation class HotelFunc
 */
@WebServlet("/admin/HotelFunc")
public class HotelFunc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PreparedStatement statement=null; 
	ResultSet resultSetFetch=null;
	Connection connection=null;
	int update=-1;
	String resource=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotelFunc() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		connection=ConnectionFactory.getInstance().getConnection();
		resource=request.getSession(false).getServletContext().getContextPath();
		
		if(((String)request.getParameter("hotelFun")).equals("ADD")){

			try{
				statement=connection.prepareStatement("INSERT INTO hotelInfo (H_NAME, T_PLACE) VALUES (?,?)");
				statement.setString(1, (String)request.getParameter("hName_a"));
				statement.setString(2, (String)request.getParameter("hPlace_a"));
				update= statement.executeUpdate();
				
				if(update>0){

					request.setAttribute("customInfo.type", "correct");
					request.setAttribute("customInfo.msg", "Hotel Insertion Successful !");
					request.setAttribute("customInfo.back", "admin/adminHome");
					
					RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
					dispatcher.forward(request, response);

				}else{

					request.setAttribute("customInfo.type", "error");
					request.setAttribute("customInfo.msg", "Hotel Insertion Unsuccessful !");
					request.setAttribute("customInfo.back", "admin/adminHome");
					
					RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
					dispatcher.forward(request, response);

				}
			}catch(SQLiteException s){
				s.printStackTrace();

				request.setAttribute("customInfo.type", "warning");
				request.setAttribute("customInfo.msg", "Tour Place already exists !");
				request.setAttribute("customInfo.back", "admin/adminHome");
				
				RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
				dispatcher.forward(request, response);
				
			}catch(SQLException e){
				e.printStackTrace();
			}
	}else if(((String)request.getParameter("hotelFun")).equals("UPDATE")){
		
			try{
				statement=connection.prepareStatement("UPDATE hotelInfo SET H_NAME=? ,T_PLACE=? WHERE H_ID=?");
				
				statement.setString(1, (String)request.getParameter("hName_u"));
				statement.setString(2, (String)request.getParameter("hPalce_u"));
				statement.setInt(3, Integer.parseInt((String)request.getParameter("hID_u")));
				update= statement.executeUpdate();
				
				if(update>0){

					request.setAttribute("customInfo.type", "correct");
					request.setAttribute("customInfo.msg", "Hotel Updation Successful !");
					request.setAttribute("customInfo.back", "admin/adminHome");
					
					RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
					dispatcher.forward(request, response);
					
				}else {
					request.setAttribute("customInfo.type", "error");
					request.setAttribute("customInfo.msg", "Hotel Updation Unsuccessful !");
					request.setAttribute("customInfo.back", "admin/adminHome");
					
					RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
					dispatcher.forward(request, response);
				}
			}catch(SQLiteException s){
				s.printStackTrace();

				request.setAttribute("customInfo.type", "warning");
				request.setAttribute("customInfo.msg", "Tour Place already exists !");
				request.setAttribute("customInfo.back", "admin/adminHome");
				
				RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
				dispatcher.forward(request, response);

			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}else if(((String)request.getParameter("hotelFun")).equals("DELETE")){
			
			try{
				statement=connection.prepareStatement("DELETE FROM hotelInfo WHERE H_ID=?");
				statement.setInt(1, Integer.parseInt((String)request.getParameter("hID_d")));
				update= statement.executeUpdate();
				
				if(update>0){

					request.setAttribute("customInfo.type", "correct");
					request.setAttribute("customInfo.msg", "Hotel Deletion Successful !");
					request.setAttribute("customInfo.back", "admin/adminHome");
					
					RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
					dispatcher.forward(request, response);
				}else{

					request.setAttribute("customInfo.type", "error");
					request.setAttribute("customInfo.msg", "Hotel Deletion Unsuccessful !");
					request.setAttribute("customInfo.back", "admin/adminHome");
					
					RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
					dispatcher.forward(request, response);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}