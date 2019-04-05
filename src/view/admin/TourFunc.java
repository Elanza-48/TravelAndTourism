package view.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import model.*;

/**
 * Servlet implementation class TourFunc
 */
@WebServlet("/admin/TourFunc")
public class TourFunc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PreparedStatement statement=null; 
	ResultSet resultSetFetch=null;
	Connection connection=null;
	int update=-1;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TourFunc() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
connection=ConnectionFactory.getInstance().getConnection();
		
		if(((String)request.getParameter("tourFun")).equals("ADD")){
			String place_1= (String) request.getParameter("tPlace_a1");
			String place_2= (String) request.getParameter("tPlace_a2");
			String place_3= (String) request.getParameter("tPlace_a3");
			
			if(place_1.equals(place_2) || place_1.equals(place_3) || place_2.equals(place_3)){
				response.getWriter().
				print("<html><head>\r\n" + 
					"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
					"<title>Not Permitted</title>\r\n" + 
					"</head>\r\n" + 
					"<body>"+
					"<P align=center><IMG SRC=\"Images/warning48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
					"<FONT COLOR=\"#fb8c00\" size=5 Face=\"verdana\">All three places must be unique !</FONT>\r\n" + 
					"<BR>\r\n" + 
					"<font Face=\"Comic Sans MS\" size=3><A HREF=\"adminHome\">&lt;&lt; Back</A></font>\r\n" + 
					"</P>"+
					"</body></html>");
			}else{
				
				
				try{
					statement=connection.prepareStatement(
							"INSERT INTO tourInfo (T_NAME, T_PLACE_1, T_PLACE_2, T_PLACE_3, T_DAYS, T_PRICE) VALUES(?,?,?,?,?,?)");
					statement.setString(1, (String)request.getParameter("tName_a"));
					statement.setString(2, (String)request.getParameter("tPlace_a1"));
					statement.setString(3, (String)request.getParameter("tPlace_a2"));
					statement.setString(4, (String)request.getParameter("tPlace_a3"));
					statement.setInt(5, Integer.parseInt((String)request.getParameter("tDays_a")));
					statement.setInt(6, Integer.parseInt(request.getParameter("tPrice_a")));
					update= statement.executeUpdate();
					
					if(update>0){
						response.getWriter().
						print("<html><head>\r\n" + 
							"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
							"<title>Not Permitted</title>\r\n" + 
							"</head>\r\n" + 
							"<body>"+
							"<P align=center><IMG SRC=\"Images/correct48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
							"<FONT COLOR=\"Green\" size=5 Face=\"verdana\">Tour Insertion Successful !</FONT>\r\n" + 
							"<BR>\r\n" + 
							"<font Face=\"Comic Sans MS\" size=3><A HREF=\"adminHome\">&lt;&lt; Back</A></font>\r\n" + 
							"</P>"+
							"</body></html>");
					}else{
						response.getWriter().
						print("<html><head>\r\n" + 
							"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
							"<title>Not Permitted</title>\r\n" + 
							"</head>\r\n" + 
							"<body>"+
							"<P align=center><IMG SRC=\"Images/error48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
							"<FONT COLOR=\"Red\" size=5 Face=\"verdana\">Tour Insertion Unsuccessful !</FONT>\r\n" + 
							"<BR>\r\n" + 
							"<font Face=\"Comic Sans MS\" size=3><A HREF=\"adminHome\">&lt;&lt; Try again</A></font>\r\n" + 
							"</P>"+
							"</body></html>");
					}
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
		}else if(((String)request.getParameter("tourFun")).equals("UPDATE")){
			
			String place_1= (String) request.getParameter("tPlace_u1");
			String place_2= (String) request.getParameter("tPlace_u2");
			String place_3= (String) request.getParameter("tPlace_u3");

			if(place_1.equals(place_2) || place_1.equals(place_3) || place_2.equals(place_3)){
				response.getWriter().
				print("<html><head>\r\n" + 
					"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
					"<title>Not Permitted</title>\r\n" + 
					"</head>\r\n" + 
					"<body>"+
					"<P align=center><IMG SRC=\"Images/warning48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
					"<FONT COLOR=\"#fb8c00\" size=5 Face=\"verdana\">All three places must be unique !</FONT>\r\n" + 
					"<BR>\r\n" + 
					"<font Face=\"Comic Sans MS\" size=3><A HREF=\"adminHome\">&lt;&lt; Back</A></font>\r\n" + 
					"</P>"+
					"</body></html>");
			}else{
				
				try{
					statement=connection.prepareStatement(
							"UPDATE tourInfo SET T_NAME=?, T_PLACE_1=?, T_PLACE_2=?, T_PLACE_3=?, T_DAYS=?,T_PRICE=? WHERE T_ID=?");
					statement.setString(1, (String)request.getParameter("tName_u"));
					statement.setString(2, (String)request.getParameter("tPlace_u1"));
					statement.setString(3, (String)request.getParameter("tPlace_u2"));
					statement.setString(4, (String)request.getParameter("tPlace_u3"));
					statement.setInt(5, Integer.parseInt((String)request.getParameter("tDays_u")));
					statement.setInt(6, Integer.parseInt(request.getParameter("tPrice_u")));
					statement.setInt(7, Integer.parseInt(request.getParameter("TID_U")));
					update= statement.executeUpdate();
					
					if(update>0){
						response.getWriter().
						print("<html><head>\r\n" + 
							"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
							"<title>Not Permitted</title>\r\n" + 
							"</head>\r\n" + 
							"<body>"+
							"<P align=center><IMG SRC=\"Images/correct48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
							"<FONT COLOR=\"Green\" size=5 Face=\"verdana\">Tour Updation Successful !</FONT>\r\n" + 
							"<BR>\r\n" + 
							"<font Face=\"Comic Sans MS\" size=3><A HREF=\"adminHome\">&lt;&lt; Back</A></font>\r\n" + 
							"</P>"+
							"</body></html>");
					}else{
						response.getWriter().
						print("<html><head>\r\n" + 
							"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
							"<title>Not Permitted</title>\r\n" + 
							"</head>\r\n" + 
							"<body>"+
							"<P align=center><IMG SRC=\"Images/error48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
							"<FONT COLOR=\"Red\" size=5 Face=\"verdana\">Tour Updation Unsuccessful !</FONT>\r\n" + 
							"<BR>\r\n" + 
							"<font Face=\"Comic Sans MS\" size=3><A HREF=\"adminHome\">&lt;&lt; Back</A></font>\r\n" + 
							"</P>"+
							"</body></html>");
					}
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
		}else if(((String)request.getParameter("tourFun")).equals("DELETE")){
			
			try{
				statement=connection.prepareStatement("DELETE FROM tourInfo WHERE T_ID=?");
				statement.setInt(1, Integer.parseInt(request.getParameter("TID_D")));
				update= statement.executeUpdate();
				
				if(update>0){
					response.getWriter().
					print("<html><head>\r\n" + 
						"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
						"<title>Not Permitted</title>\r\n" + 
						"</head>\r\n" + 
						"<body>"+
						"<P align=center><IMG SRC=\"Images/correct48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
						"<FONT COLOR=\"Green\" size=5 Face=\"verdana\">Tour Deletion Successful !</FONT>\r\n" + 
						"<BR>\r\n" + 
						"<font Face=\"Comic Sans MS\" size=3><A HREF=\"adminHome\">&lt;&lt; Back</A></font>\r\n" + 
						"</P>"+
						"</body></html>");
				}else{
					response.getWriter().
					print("<html><head>\r\n" + 
						"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/theme.css\">\r\n" + 
						"<title>Not Permitted</title>\r\n" + 
						"</head>\r\n" + 
						"<body>"+
						"<P align=center><IMG SRC=\"Images/error48.png\" WIDTH=\"48\" HEIGHT=\"48\" BORDER=\"0\" ALT=\"\"><br>\r\n" + 
						"<FONT COLOR=\"Red\" size=5 Face=\"verdana\">Tour Deletion Unsuccessful !</FONT>\r\n" + 
						"<BR>\r\n" + 
						"<font Face=\"Comic Sans MS\" size=3><A HREF=\"adminHome\">&lt;&lt; Back</A></font>\r\n" + 
						"</P>"+
						"</body></html>");
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
