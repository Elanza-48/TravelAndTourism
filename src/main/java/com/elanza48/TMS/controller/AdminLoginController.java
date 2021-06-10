package com.elanza48.TMS.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
		
		if(session.getAttribute("name")!=null){
			if((int)session.getAttribute("auth")==1)
				response.sendRedirect("admin/adminHome");
			else{
				request.setAttribute("customInfo.type", "warning");
				request.setAttribute("customInfo.msg", "User already logged in !");
				request.setAttribute("customInfo.back", "home.html");
				
				RequestDispatcher  dispatcher = getServletContext().getRequestDispatcher("/customInfo");
				dispatcher.forward(request, response);
				
			}
		}else {
			response.sendRedirect("adminLogin.html");
		}
	}
}
