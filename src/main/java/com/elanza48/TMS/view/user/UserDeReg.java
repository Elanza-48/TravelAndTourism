package com.elanza48.TMS.view.user;

import java.io.IOException;
import org.sqlite.SQLiteException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elanza48.TMS.model.DAO.ConnectionFactory;

/**
 * Servlet implementation class UserDeReg
 */
@WebServlet("/user/UserDeReg")
public class UserDeReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 PreparedStatement statement=null;
 	Connection connection=null;
 	int update=-1;
 	HttpSession session=null;
 	String resource=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeReg() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session=request.getSession(false);
		resource=session.getServletContext().getContextPath();
		
		if(session.getAttribute("name")==null || (int)session.getAttribute("auth")==1 || session.getAttribute("de-reg")==null){

			request.setAttribute("customInfo.type", "error");
			request.setAttribute("customInfo.msg", "You are not permitted to Access the User Portal !");
			request.setAttribute("customInfo.back", "userLogin.html");
			
			RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
			dispatcher.forward(request, response);
		}else{

			if(((String)session.getAttribute("de-reg")).equals("true")){
				session.removeAttribute("de-reg");
				connection=ConnectionFactory.getInstance().getConnection();
			
				String name= (String) session.getAttribute("name");
				
				try{
					statement=connection.prepareStatement("DELETE FROM userAccount WHERE U_EMAIL=? AND U_ADMIN=0");
					statement.setString(1, (String)session.getAttribute("email"));
					statement.executeUpdate();
					System.out.println("\nUser DEREGISTERED :"+name);
					
					session.invalidate();
					System.out.println("\nLogged out Successfully! [USER: "+name+"]\n");

					request.setAttribute("customInfo.type", "correct");
					request.setAttribute("customInfo.msg", "user De-registered !");
					request.setAttribute("customInfo.back", "home.html");
					
					RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
					dispatcher.forward(request, response);
					
				}catch(SQLiteException s){
					s.printStackTrace();

					request.setAttribute("customInfo.type", "error");
					request.setAttribute("customInfo.msg", "user De-registration unsuccessful !");
					request.setAttribute("customInfo.back", "userHome");
					
					RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
					dispatcher.forward(request, response);

				}catch(SQLException s){
					s.printStackTrace();

					request.setAttribute("customInfo.type", "error");
					request.setAttribute("customInfo.msg", "user De-registration unsuccessful !");
					request.setAttribute("customInfo.back", "userHome");
					
					RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
					dispatcher.forward(request, response);
				}
	}

}
	}}
