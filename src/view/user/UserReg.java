package view.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import model.*;
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
			
			if(!(request.getParameter("pass").equals(request.getParameter("cPass")))){
				response.getWriter()
					.print(
						"<html>\r\n" + 
						"<head>\r\n" + 
						"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
						"<title>UserReg</title>\r\n" + 
						"</head>\r\n" + 
						"<body>"+
						"<P align=center><IMG SRC=\"Images/error48.png\" WIDTH=\"17\" HEIGHT=\"17\" BORDER=\"0\" ALT=\"\">\r\n" + 
						"<FONT COLOR=\"Red\" Face=\"Georgia\">Password Mismatch !</FONT>\r\n" + 
						"<BR>\r\n" + 
						"<A HREF=\"userReg.html\">&lt;&lt;Retry Again</A>\r\n" + 
						"</P></body></html>");
			}else{
				statement.setString(1, (String)request.getParameter("pass"));
			}
			
			statement.setString(2, (String)request.getParameter("name"));
			statement.setString(3, (String)request.getParameter("email"));
			statement.setLong(4, Long.parseLong((String)request.getParameter("phone")));
			updatecode=statement.executeUpdate();
			
			if(updatecode>0){
				System.out.println("Registration Successful ! ["+(String)request.getParameter("name")+"]");
				response.getWriter()
				.print(
						"<html>\r\n" + 
						"<head>\r\n" + 
						"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
						"<title>UserReg</title>\r\n" + 
						"</head>\r\n" + 
						"<body>"+
						"<P align=center><IMG SRC=\"Images/correct48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
						"<FONT COLOR=\"Green\" size=5 Face=\"verdana\">User Registration Successful !</FONT>\r\n" + 
						"<BR>\r\n" + 
						"<font Face=\"Comic Sans MS\" size=3><A HREF=\"userLogin.html\">&lt;&lt; Login Now</A></font>\r\n" + 
						"</P>\r\n" + 
						"</body></html>");
			}else {
				response.getWriter()
				.print(
						"<html>\r\n" + 
						"<head>\r\n" + 
						"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
						"<title>UserReg</title>\r\n" + 
						"</head>\r\n" + 
						"<body>"+
						"<P align=center><IMG SRC=\"Images/error48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
						"<FONT COLOR=\"Red\" size=5 Face=\"verdana\">User Registration Unsuccessful ! <br> Retry again.</FONT>\r\n" + 
						"<BR>\r\n" + 
						"<font Face=\"Comic Sans MS\" size=3><A HREF=\"userReg.html\">&lt;&lt; Back</A></font>\r\n" + 
						"</P></body></html>");
			}
		}catch(SQLIntegrityConstraintViolationException e){
			e.printStackTrace();
			response.getWriter()
			.print(
					"<html>\r\n" + 
					"<head>\r\n" + 
					"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
					"<title>UserReg</title>\r\n" + 
					"</head>\r\n" + 
					"<body>"+
					"<P align=center><IMG SRC=\"Images/error48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
					"<FONT COLOR=\"Red\" size=5 Face=\"verdana\">Either Email or Mobile number has already been registered !</FONT>\r\n" + 
					"<BR>\r\n" + 
					"<font Face=\"Comic Sans MS\" size=3><A HREF=\"userReg.html\">&lt;&lt; Back</A></font>\r\n" + 
					"</P></body></html>");
		}catch(SQLiteException e){
			e.printStackTrace();
			response.getWriter()
			.print(
					"<html>\r\n" + 
					"<head>\r\n" + 
					"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
					"<title>UserReg</title>\r\n" + 
					"</head>\r\n" + 
					"<body>"+
					"<P align=center><IMG SRC=\"Images/error48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
					"<FONT COLOR=\"Red\" size=5 Face=\"verdana\">Either Email or Mobile number has already been registered !</FONT>\r\n" + 
					"<BR>\r\n" + 
					"<font Face=\"Comic Sans MS\" size=3><A HREF=\"userReg.html\">&lt;&lt; Back</A></font>\r\n" + 
					"</P></body></html>");
		}catch(SQLException c){
			c.printStackTrace();
		}
	}
}