

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html;charset=UTF-8");
			RequestDispatcher rd;
			PrintWriter out=response.getWriter();
			try{
				ServletContext sc=getServletContext();
				String dbClass=sc.getInitParameter("dbClass");
				String dbPath=sc.getInitParameter("dbPath");
				String dbUser=sc.getInitParameter("dbUser");
				Class.forName(dbClass);
				Connection con=DriverManager.getConnection(dbPath,dbUser,"root");
				Statement st=con.createStatement();
				String sql="insert into login"+" values (?,?,?)";
				
				String newUser=request.getParameter("username");
				String newPass=request.getParameter("password");
				String newName=request.getParameter("fullname");
				PreparedStatement preparedStmt=con.prepareStatement(sql);
				preparedStmt.setString(1,newUser);
				preparedStmt.setString(2,newPass);
				preparedStmt.setString(3,newName);
				preparedStmt.execute();
				
				out.println("<font color='green'>Login Now !!</font>");
				rd=request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
				
				st.close();
				con.close();
			}
			catch(Exception e){
				e.printStackTrace();
				if(((SQLException)e).getErrorCode()==1062){
					out.println("<font color='RED'>USERNAME Already Exists</font><br>");
					rd=request.getRequestDispatcher("index.jsp");
					rd.include(request, response);
				}
				System.out.println(((SQLException)e).getErrorCode());
			}
	}

}
