package com.elanza48.TMS.controller.filter;

import java.io.IOException;

import java.sql.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elanza48.TMS.model.PasswordHasher;
import com.elanza48.TMS.model.DAO.ConnectionFactory;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(
	filterName="login_filter",
	urlPatterns="/validate"
)
public class LoginFilter implements Filter {
	
	boolean validResult=false;
	Connection connection=null;
	String QUERY= "SELECT U_ADMIN, U_NAME,U_EMAIL,U_MOB  FROM userAccount WHERE U_EMAIL=? AND U_PASS=?";
	PreparedStatement statement=null;
	ResultSet resultSet=null;
	HttpSession session=null;

    public LoginFilter() {
    	
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
		ServletException {
		session=(HttpSession) ((HttpServletRequest)request).getSession(true);
		String resource=session.getServletContext().getContextPath();
		
		if(session.getAttribute("name")!=null) {
			if((int)session.getAttribute("auth")==1) {
				response.getWriter().
				print("<html><head>\r\n" + 
					"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
					"<title>Not Permitted</title>\r\n" + 
					"<link rel=\"shortcut icon\" type=\"image/png\" href=\"Images/fabicon.png\">"+
					"</head>\r\n" + 
					"<body>"+
					"<P align=center><IMG SRC=\"Images/warning48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
					"<FONT COLOR=\"#fb8c00\" size=5 Face=\"verdana\">You were not logged in!<br>Admin already logged in !</FONT>\r\n" + 
					"<BR>\r\n" + 
					"<font Face=\"Comic Sans MS\" size=3><A HREF=\"home.html\">&lt;&lt; Back</A></font>\r\n" + 
					"<img alt=\"\" src=\""+resource+"/Images/banner.png\" width=\"60%\" height=\"30%\" \r\n" + 
					"		style=\"position: absolute; border-radius: 20px; left: 20%; top: 30%;\">"+
					"</P>"+
					"</body></html>");
			}else {
				response.getWriter().
				print("<html><head>\r\n" + 
					"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
					"<title>Not Permitted</title>\r\n" +
					"<link rel=\"shortcut icon\" type=\"image/png\" href=\"Images/fabicon.png\">"+
					"</head>\r\n" + 
					"<body>"+
					"<P align=center><IMG SRC=\"Images/warning48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
					"<FONT COLOR=\"#fb8c00\" size=5 Face=\"verdana\">You were not logged in!<br>User already logged in !</FONT>\r\n" + 
					"<BR>\r\n" + 
					"<font Face=\"Comic Sans MS\" size=3><A HREF=\"home.html\">&lt;&lt; Back</A></font>\r\n" + 
					"<img alt=\"\" src=\""+resource+"/Images/banner.png\" width=\"60%\" height=\"30%\" \r\n" + 
					"		style=\"position: absolute; border-radius: 20px; left: 20%; top: 30%;\">"+
					"</P>"+
					"</body></html>");
			}
		}else {
		
		try{
			connection=ConnectionFactory.getInstance().getConnection();

			statement= connection.prepareStatement(QUERY);
			statement.setString(1, (String)((HttpServletRequest)request).getParameter("lEmail"));
			statement.setString(2, PasswordHasher.hashNow(
					(String)((HttpServletRequest)request).getParameter("lPass")));
			resultSet=statement.executeQuery();
			
			if(resultSet.next() &&
					resultSet.getString(3).equals(((HttpServletRequest)request).getParameter("lEmail"))){
					validResult=true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}

		if(validResult){
			validResult=false;
			
				try {
					if(((String)((HttpServletRequest)request).getParameter("loginSubmit")).equals("Admin Login")){
						if(resultSet.getInt(1)==1) {
							
							session.setAttribute("auth", resultSet.getInt(1));
							session.setAttribute("name", resultSet.getString(2));
							session.setAttribute("email", resultSet.getString(3));
							session.setAttribute("mob", resultSet.getLong(4));
							System.out.println("\nAdmin Logged in : "+resultSet.getString(2)+"\n");
							
							((HttpServletResponse)response).sendRedirect("admin/adminHome");
						}else {
							((HttpServletResponse)response).getWriter().
							print("<html><head>\r\n" + 
									"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
									"<title>Not Permitted</title>\r\n" + 
									"<link rel=\"shortcut icon\" type=\"image/png\" href=\"Images/fabicon.png\">"+
									"</head>\r\n" + 
									"<body>"+
									"<P align=center><IMG SRC=\"Images/error48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
									"<FONT COLOR=\"Red\" size=5 Face=\"verdana\">The User is not permitted to access the Admin Portal !</FONT>\r\n" + 
									"<BR>\r\n" + 
									"<font Face=\"Comic Sans MS\" size=3><A HREF=\"adminLogin.html\">&lt;&lt; Back</A></font>\r\n" + 
									"<img alt=\"\" src=\""+resource+"/Images/banner.png\" width=\"60%\" height=\"30%\" \r\n" + 
									"		style=\"position: absolute; border-radius: 20px; left: 20%; top: 30%;\">"+
									"</P></body></html>");
						}
						
					}else if(((String)((HttpServletRequest)request).getParameter("loginSubmit")).equals("User Login")){ 
						if (resultSet.getInt(1)==0) {
							
							session.setAttribute("auth", resultSet.getInt(1));
							session.setAttribute("name", resultSet.getString(2));
							session.setAttribute("email", resultSet.getString(3));
							session.setAttribute("mob", resultSet.getLong(4));
							System.out.println("\nUser Logged in : "+resultSet.getString(2)+"\n");
								
							((HttpServletResponse)response).sendRedirect("user/userHome");
						}else {
							((HttpServletResponse)response).getWriter().
							print("<html><head>\r\n" + 
									"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
									"<title>Not Permitted</title>\r\n" + 
									"<link rel=\"shortcut icon\" type=\"image/png\" href=\"Images/fabicon.png\">"+
									"</head>\r\n" + 
									"<body>"+
									"<P align=center><IMG SRC=\"Images/error48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
									"<FONT COLOR=\"Red\" size=5 Face=\"verdana\">Administrators are prohibited from accessing the User Portal !</FONT>\r\n" + 
									"<BR>\r\n" + 
									"<font Face=\"Comic Sans MS\" size=3><A HREF=\"userLogin.html\">&lt;&lt; Back</A></font>\r\n" + 
									"<img alt=\"\" src=\""+resource+"/Images/banner.png\" width=\"60%\" height=\"30%\" \r\n" + 
									"		style=\"position: absolute; border-radius: 20px; left: 20%; top: 30%;\">"+
									"</P></body></html>");
						}
							
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					try {
						resultSet.close();
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
			}else {
				((HttpServletResponse)response).getWriter().
					print("<html><head>\r\n" + 
						"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
						"<title>Error Login</title>\r\n" +
						"<link rel=\"shortcut icon\" type=\"image/png\" href=\"Images/fabicon.png\">"+
						"</head>\r\n" + 
						"<body>"+
						"<P align=center>"+
						"<IMG SRC=\"Images/error48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
						"<FONT COLOR=\"Red\" size=5 Face=\"verdana\">SORRY! Invalid Email/password please try again</FONT>\r\n" + 
						"<BR>\r\n" + 
						"<font Face=\"Comic Sans MS\" size=3><A HREF=\"home.html\">&lt;&lt; Home</A></font>\r\n" +
						"<img alt=\"\" src=\""+resource+"/Images/banner.png\" width=\"60%\" height=\"30%\" \r\n" + 
						"		style=\"position: absolute; border-radius: 20px; left: 20%; top: 30%;\">"+
						"</P></body></html>");
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {}

}
