

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

@WebServlet("/GetDetails")
public class GetDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jai","root","");
			Statement st=con.createStatement();
			HttpSession session =request.getSession();
			String username=(String) session.getAttribute("name");
			String sql="select * from login where username='"+username+"';";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			JSONObject user=new JSONObject();
			user.put("fullname",rs.getString("fullname"));
			user.put("username",rs.getString("username"));
			user.put("password",rs.getString("password"));
			response.setContentType("application/json");
			out.print(user);
		}
		catch(Exception e){System.out.print(e);}
	}

}
