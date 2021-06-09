package com.elanza48.TMS.view.user;

import java.io.IOException;
import org.sqlite.SQLiteException;
import java.sql.*;

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
			response.getWriter()
			.print(
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<link rel=\"stylesheet\" type=\"text/css\" href=\""+resource+"/css/theme.css\">\r\n" + 
				"<title>UserDeReg</title>\r\n" + 
				"<link rel=\"shortcut icon\" type=\"image/png\" href=\""+resource+"/Images/fabicon.png\">"+
				"</head>\r\n" + 
				"<body>"+
				"<P align=center><IMG SRC=\""+resource+"/Images/error48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
				"<FONT COLOR=\"Red\" size=5 Face=\"verdana\">You are not permitted to Access the User Portal !</FONT>\r\n" + 
				"<BR>\r\n" + 
				"<font Face=\"Comic Sans MS\" size=3><A HREF=\"userLogin.html\">&lt;&lt; Back</A></font>\r\n" + 
				"<img alt=\"\" src=\""+resource+"/Images/banner.png\" width=\"60%\" height=\"30%\" \r\n" + 
				"		style=\"position: absolute; border-radius: 20px; left: 20%; top: 30%;\">"+
				"</P></body></html>");
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
					
					response.getWriter()
					.print(
						"<html>\r\n" + 
						"<head>\r\n" + 
						"<link rel=\"stylesheet\" type=\"text/css\" href=\""+resource+"/css/theme.css\">\r\n" + 
						"<title>UserDeReg</title>\r\n" + 
						"<link rel=\"shortcut icon\" type=\"image/png\" href=\""+resource+"/Images/fabicon.png\">"+
						"</head>\r\n" + 
						"<body>"+
						"<P align=center><IMG SRC=\""+resource+"/Images/correct48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
						"<FONT COLOR=\"Green\" size=5 Face=\"verdana\">user De-registered !</FONT>\r\n" + 
						"<BR>\r\n" + 
						"<font Face=\"Comic Sans MS\" size=3><A HREF=\"../home.html\">&lt;&lt; home</A></font>\r\n" + 
						"<img alt=\"\" src=\""+resource+"/Images/banner.png\" width=\"60%\" height=\"30%\" \r\n" + 
						"		style=\"position: absolute; border-radius: 20px; left: 20%; top: 30%;\">"+
						"</P>"+
						"</body></html>");
				}catch(SQLiteException s){
					s.printStackTrace();
					response.getWriter()
					.print(
						"<html>\r\n" + 
						"<head>\r\n" + 
						"<link rel=\"stylesheet\" type=\"text/css\" href=\""+resource+"/css/theme.css\">\r\n" + 
						"<title>UserDeReg</title>\r\n" + 
						"<link rel=\"shortcut icon\" type=\"image/png\" href=\""+resource+"/Images/fabicon.png\">"+
						"</head>\r\n" + 
						"<body>"+
						"<P align=center><IMG SRC=\""+resource+"/Images/error48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
						"<FONT COLOR=\"Red\" size=5 Face=\"verdana\">user De-registration unsuccessful !</FONT>\r\n" + 
						"<BR>\r\n" + 
						"<font Face=\"Comic Sans MS\" size=3><A HREF=\"userHome\">&lt;&lt; home</A></font>\r\n" + 
						"<img alt=\"\" src=\""+resource+"/Images/banner.png\" width=\"60%\" height=\"30%\" \r\n" + 
						"		style=\"position: absolute; border-radius: 20px; left: 20%; top: 30%;\">"+
						"</P>"+
						"</body></html>");
				}catch(SQLException s){
					s.printStackTrace();
					response.getWriter()
					.print(
						"<html>\r\n" + 
						"<head>\r\n" + 
						"<link rel=\"stylesheet\" type=\"text/css\" href=\""+resource+"/css/theme.css\">\r\n" + 
						"<title>UserDeReg</title>\r\n" + 
						"<link rel=\"shortcut icon\" type=\"image/png\" href=\""+resource+"/Images/fabicon.png\">"+
						"</head>\r\n" + 
						"<body>"+
						"<P align=center><IMG SRC=\""+resource+"/Images/error48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
						"<FONT COLOR=\"Red\" size=5 Face=\"verdana\">user De-registration unsuccessful !</FONT>\r\n" + 
						"<BR>\r\n" + 
						"<font Face=\"Comic Sans MS\" size=3><A HREF=\"userHome\">&lt;&lt; home</A></font>\r\n" + 
						"<img alt=\"\" src=\""+resource+"/Images/banner.png\" width=\"60%\" height=\"30%\" \r\n" + 
						"		style=\"position: absolute; border-radius: 20px; left: 20%; top: 30%;\">"+
						"</P>"+
						"</body></html>");
				}
	}

}
	}}
