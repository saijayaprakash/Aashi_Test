

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Reset
 */
@WebServlet("/Reset")
public class Reset extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher rd;
			try{	
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jai","root","root");
				Statement st=con.createStatement();
				
				HttpSession sesssion = request.getSession();
				String username=(String) sesssion.getAttribute("name");
				
				String param=request.getParameter("reset");
				if(param.equalsIgnoreCase("reset"))
				{
					String newpass=request.getParameter("rpass");
					String sql="update login set password='"+newpass+"' where username='"+username+"'";
					st.executeUpdate(sql);
					rd=request.getRequestDispatcher("welcome.jsp");
					rd.include(request, response);
				}
				if(param.equalsIgnoreCase("change"))
				{
					String sql="update login set fullname='default' where username='"+username+"'";
					st.executeUpdate(sql);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
			}
	}

}
