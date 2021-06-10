package com.elanza48.TMS.controller.filter;

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
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/admin/*")
public class AdminFilter implements Filter {

	HttpSession session=null;

    public AdminFilter() {}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		session=(HttpSession)((HttpServletRequest)request).getSession(false);
	
		try {
			if(session.getAttribute("name")==null || (int)session.getAttribute("auth")==0){
				
				request.setAttribute("customInfo.type", "error");
				request.setAttribute("customInfo.msg", "You are not permitted to Access the Admin Portal !");
				request.setAttribute("customInfo.back", "adminLogin.html");
				
				request.getRequestDispatcher("/customInfo").forward(request, response);
				
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
