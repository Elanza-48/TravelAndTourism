package com.elanza48.TMS.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	int auth=-1;
	String name=null;
	Connection connection=null;
	PreparedStatement statement=null;
	HttpSession session=null;
	
    public Logout() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			session=request.getSession(false);
			
			if(session.getAttribute("name")!=null){
				auth= (int)session.getAttribute("auth");
				name= (String)session.getAttribute("name");
				session.invalidate();

				if(auth==0)
					System.out.println("\nLogged out Successfully! [USER: "+name+"]\n");
				else if(auth==1)
					System.out.println("\nLogged out Successfully! [ADMIN: "+name+"]\n");
				else
					System.out.println("\nNothing to log out!\n");
				response.sendRedirect("home.html");
			}
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
		
	}

}
