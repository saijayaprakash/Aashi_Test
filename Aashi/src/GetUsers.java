

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.mysql.jdbc.ResultSet;

/**
 * Servlet implementation class GetUsers
 */
@WebServlet("/GetUsers")
public class GetUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("type/html");
		PrintWriter out=response.getWriter();
		try {
			Connection con = new JaiDB().getConnection();
			Statement st=con.createStatement();
			
			String sql="select username,password from login;";
			java.sql.ResultSet rs=st.executeQuery(sql);
			
			out.println("Username   | Password   |");
			out.println("-------------------------");
			while(rs.next()){
				String usr = rs.getString("username");
				String pass = rs.getString("password");
				out.println(usr+"  |  "+pass);
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
