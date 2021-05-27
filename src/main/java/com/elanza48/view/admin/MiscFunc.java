package com.elanza48.view.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elanza48.model.DAO.ConnectionFactory;

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
					response.getWriter().
					print("<html><head>\r\n" + 
							"<link rel=\"stylesheet\" type=\"text/css\" href=\""+resource+"/css/theme.css\">\r\n" + 
							"<title>Misc Func</title>"+
							"<link rel=\"shortcut icon\" type=\"image/png\" href=\""+resource+"/Images/fabicon.png\">"+
							"</head>\r\n" + 
							"<body>"+
							"<P align=center><IMG SRC=\""+resource+"/Images/correct48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
							"<FONT COLOR=\"Green\" size=5 Face=\"verdana\">Booking Revocation Successful !</FONT>\r\n" + 
							"<BR>\r\n" + 
							"<font Face=\"Comic Sans MS\" size=3><A HREF=\"adminHome\">&lt;&lt; Back</A></font>\r\n" + 
							"<img alt=\"\" src=\""+resource+"/Images/banner.png\" width=\"60%\" height=\"30%\" \r\n" + 
							"		style=\"position: absolute; border-radius: 20px; left: 20%; top: 30%;\">"+
							"</P>"+
							"</body></html>");
				}else{
					response.getWriter().
					print("<html><head>\r\n" + 
							"<link rel=\"stylesheet\" type=\"text/css\" href=\""+resource+"/css/theme.css\">\r\n" + 
							"<title>Misc Func</title>"+
							"<link rel=\"shortcut icon\" type=\"image/png\" href=\""+resource+"/Images/fabicon.png\">"+
							"</head>\r\n" + 
							"<body>"+
							"<P align=center><IMG SRC=\""+resource+"/Images/error48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
							"<FONT COLOR=\"Red\" size=5 Face=\"verdana\">Booking Revocation Unsuccessful !</FONT>\r\n" + 
							"<BR>\r\n" + 
							"<font Face=\"Comic Sans MS\" size=3><A HREF=\"adminHome\">&lt;&lt; Try again</A></font>\r\n" + 
							"<img alt=\"\" src=\""+resource+"/Images/banner.png\" width=\"60%\" height=\"30%\" \r\n" + 
							"		style=\"position: absolute; border-radius: 20px; left: 20%; top: 30%;\">"+
							"</P>"+
							"</body></html>");
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
