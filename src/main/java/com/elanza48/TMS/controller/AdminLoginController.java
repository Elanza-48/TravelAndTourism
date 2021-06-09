package com.elanza48.TMS.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminLoginController
 */
@WebServlet("/AdminLoginController")
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpSession session=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session=request.getSession(true);
		String resource=session.getServletContext().getContextPath();
		
		if(session.getAttribute("name")!=null){
			if((int)session.getAttribute("auth")==1)
				response.sendRedirect("admin/adminHome");
			else{
				response.getWriter().
				print("<html><head>\r\n" + 
					"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
					"<title>Not Permitted</title>\r\n" +
					"<link rel=\"shortcut icon\" type=\"image/png\" href=\"Images/fabicon.png\">"+
					"</head>\r\n" + 
					"<body>"+
					"<P align=center><IMG SRC=\"Images/warning48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
					"<FONT COLOR=\"#fb8c00\" size=5 Face=\"verdana\">User already logged in !</FONT>\r\n" + 
					"<BR>\r\n" + 
					"<font Face=\"Comic Sans MS\" size=3><A HREF=\"home.html\">&lt;&lt; Back</A></font>\r\n" +
					"<img alt=\"\" src=\""+resource+"/Images/banner.png\" width=\"60%\" height=\"30%\" \r\n" + 
					"		style=\"position: absolute; border-radius: 20px; left: 20%; top: 30%;\">"+
					"</P>"+
					"</body></html>");
			}
		}else {
			response.sendRedirect("adminLogin.html");
		}
	}
}
