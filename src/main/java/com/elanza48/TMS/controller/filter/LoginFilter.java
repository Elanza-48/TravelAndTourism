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
		
		if(session.getAttribute("name")!=null) {
			if((int)session.getAttribute("auth")==1) {

				request.setAttribute("customInfo.type", "warning");
				request.setAttribute("customInfo.msg", "You were not logged in!<br>Admin already logged in !");
				request.setAttribute("customInfo.back", "home.html");
				
				request.getRequestDispatcher("/customInfo").forward(request, response);

			}else {

				request.setAttribute("customInfo.type", "warning");
				request.setAttribute("customInfo.msg", "You were not logged in!<br>User already logged in !");
				request.setAttribute("customInfo.back", "home.html");
				
				request.getRequestDispatcher("/customInfo").forward(request, response);

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

							request.setAttribute("customInfo.type", "error");
							request.setAttribute("customInfo.msg", "The User is not permitted to access the Admin Portal !");
							request.setAttribute("customInfo.back", "adminLogin.html");
							
							request.getRequestDispatcher("/customInfo").forward(request, response);
							
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
							request.setAttribute("customInfo.type", "error");
							request.setAttribute("customInfo.msg", "Administrators are prohibited from accessing the User Portal !");
							request.setAttribute("customInfo.back", "userLogin.html");
							
							request.getRequestDispatcher("/customInfo").forward(request, response);
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

				request.setAttribute("customInfo.type", "error");
				request.setAttribute("customInfo.msg", "SORRY! Invalid Email/password please try again.");
				request.setAttribute("customInfo.back", "home.html");
				
				request.getRequestDispatcher("/customInfo").forward(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {}

}
