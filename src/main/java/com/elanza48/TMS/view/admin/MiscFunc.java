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
 * Servlet implementation class Misc
 */
@WebServlet("/admin/MiscFunc")
public class MiscFunc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PreparedStatement statement=null; 
	ResultSet resultSetFetch=null;
	Connection connection=null;
	int update=-1;
	String resource=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MiscFunc() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		connection=ConnectionFactory.getInstance().getConnection();
		resource=request.getSession(false).getServletContext().getContextPath();
		try{
			if(((String)request.getParameter("miscFun")).equals("REVOKE")){
				statement=connection.prepareStatement("UPDATE bookingInfo SET STATUS=0 WHERE B_ID=?;");
				statement.setInt(1, Integer.parseInt((String)request.getParameter("MB_r")));
				update=statement.executeUpdate();
				
				if(update>0){
					request.setAttribute("customInfo.type", "correct");
					request.setAttribute("customInfo.msg", "Booking Revocation Successful !");
					request.setAttribute("customInfo.back", "admin/adminHome");
					
					RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
					dispatcher.forward(request, response);
				}else{
					request.setAttribute("customInfo.type", "error");
					request.setAttribute("customInfo.msg", "Booking Revocation Unsuccessful !");
					request.setAttribute("customInfo.back", "admin/adminHome");
					
					RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
					dispatcher.forward(request, response);
				}
			}
		}catch(SQLException s){
			s.printStackTrace();
		}finally{
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
