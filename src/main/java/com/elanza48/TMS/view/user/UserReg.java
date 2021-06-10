package com.elanza48.TMS.view.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elanza48.TMS.model.PasswordHasher;
import com.elanza48.TMS.model.DAO.ConnectionFactory;

import java.sql.*;

import org.sqlite.SQLiteException;

/**
 * Servlet implementation class UserReg
 */
@WebServlet("/UserReg")
public class UserReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String QUERY= "INSERT INTO userAccount (U_PASS,U_NAME,U_EMAIL,U_MOB) VALUES(?,?,?,?)";
	PreparedStatement statement=null;
	boolean validUser=false;
	Connection connection=null;
	int updatecode=-1;
	String resource=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserReg() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			connection=ConnectionFactory.getInstance().getConnection();
			statement= connection.prepareStatement(QUERY);
			resource=request.getSession(false).getServletContext().getContextPath();
			
			if(!(request.getParameter("pass").equals(request.getParameter("cPass")))){
				response.getWriter()
					.print(
						"<html>\r\n" + 
						"<head>\r\n" + 
						"<link rel=\"stylesheet\" type=\"text/css\" href=\""+resource+"/css/theme.css\">\r\n" +
						"<title>UserReg</title>\r\n" + 
						"<link rel=\"shortcut icon\" type=\"image/png\" href=\""+resource+"/Images/fabicon.png\">"+
						"</head>\r\n" + 
						"<body>"+
						"<P align=center><IMG SRC=\""+resource+"/Images/error48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
						"<FONT COLOR=\"Red\" size=5 Face=\"verdana\">Password Mismatch !</FONT>\r\n" +  
						"<BR>\r\n" + 
						"<font Face=\"Comic Sans MS\" size=3><A HREF=\"userReg.html\">&lt;&lt; Retry Again</A></font>\r\n" +
						"<img alt=\"\" src=\""+resource+"/Images/banner.png\" width=\"60%\" height=\"30%\" \r\n" + 
						"		style=\"position: absolute; border-radius: 20px; left: 20%; top: 30%;\">"+
						"</P></body></html>");
			}else{
				statement.setString(1, PasswordHasher.hashNow(request.getParameter("pass")));
			}
			
			statement.setString(2, (String)request.getParameter("name"));
			statement.setString(3, (String)request.getParameter("email"));
			statement.setLong(4, Long.parseLong((String)request.getParameter("phone")));
			updatecode=statement.executeUpdate();
			
			if(updatecode>0){
				System.out.println("Registration Successful ! ["+(String)request.getParameter("name")+"]");

				request.setAttribute("customInfo.type", "correct");
				request.setAttribute("customInfo.msg", "User Registration Successful !");
				request.setAttribute("customInfo.back", "userLogin.html");
				
				RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
				dispatcher.forward(request, response);

			}else {

				request.setAttribute("customInfo.type", "error");
				request.setAttribute("customInfo.msg", "User Registration Unsuccessful !");
				request.setAttribute("customInfo.back", "userReg.html");
				
				RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
				dispatcher.forward(request, response);

			}
		}catch(SQLIntegrityConstraintViolationException e){
			e.printStackTrace();

			request.setAttribute("customInfo.type", "error");
			request.setAttribute("customInfo.msg", "Either Email or Mobile number has already been registered !");
			request.setAttribute("customInfo.back", "userReg.html");
			
			RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
			dispatcher.forward(request, response);

		}catch(SQLiteException e){
			e.printStackTrace();

			request.setAttribute("customInfo.type", "error");
			request.setAttribute("customInfo.msg", "Either Email or Mobile number has already been registered !");
			request.setAttribute("customInfo.back", "userReg.html");
			
			RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
			dispatcher.forward(request, response);

		}catch(SQLException c){
			c.printStackTrace();
		}
	}
}
