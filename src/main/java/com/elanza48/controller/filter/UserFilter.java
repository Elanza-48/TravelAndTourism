package com.elanza48.controller.filter;

import java.io.IOException;

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

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter("/user/*")
public class UserFilter implements Filter {
	HttpSession session=null;


    public UserFilter() {}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			session=(HttpSession)((HttpServletRequest)request).getSession(false);
			String resource=session.getServletContext().getContextPath();

			if(session.getAttribute("name")==null || (int)session.getAttribute("auth")==1){
				((HttpServletResponse)response).getWriter().
				print("<html><head>\r\n" + 
						"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
						"<title>Not Permitted</title>\r\n" +
						"<title>Tour Func</title>\r\n" +
						"<link rel=\"shortcut icon\" type=\"image/png\" href=\"Images/fabicon.png\">"+
						"</head>\r\n" + 
						"<body>"+
						"<P align=center><IMG SRC=\"Images/error48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
						"<FONT COLOR=\"Red\" size=5 Face=\"verdana\">You are not permitted to Access the User Portal !</FONT>\r\n" + 
						"<BR>\r\n" + 
						"<font Face=\"Comic Sans MS\" size=3><A HREF=\"userLogin.html\">&lt;&lt; Back</A></font>\r\n" +
						"<img alt=\"\" src=\""+resource+"/Images/banner.png\" width=\"60%\" height=\"30%\" \r\n" + 
						"		style=\"position: absolute; border-radius: 20px; left: 20%; top: 30%;\">"+
						"</P></head></body>");
			}else {
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			((HttpServletResponse) response).sendRedirect("../error.html");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {}

}
