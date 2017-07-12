import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jai","root","");
		Statement st=con.createStatement();
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String fbuser=request.getParameter("fbuser");
		String fbId = request.getParameter("fbId");
		String fbAccessKey = request.getParameter("fbAccessKey");
		
		System.out.println("fb user"+fbuser);
		
		boolean isFbUser = false;
		if(fbuser!=null && !fbuser.isEmpty()){
			isFbUser = true;
		}
		
		String sql="select * from login where username='"+username+"' AND password='"+password+"';";
		ResultSet rs=st.executeQuery(sql);
		
		RequestDispatcher rd;
		if(username!=null && password!=null && username.equals("king") && password.equals("king"))
		{
			response.sendRedirect("AdminLogin.html");
			ServletContext sc=getServletContext();
			sc.setAttribute("user", "Jai");
		}
		
		if(rs.isBeforeFirst() || isFbUser)
		{	
			if(isFbUser){
				username = fbId;
				System.out.println("Fb Access Key of "+fbuser+" is "+fbAccessKey);
				String fbSqlLoginCheck="select * from login where username='"+username+"';";
				ResultSet rsFbLoginCheck=st.executeQuery(fbSqlLoginCheck);
				if(!rsFbLoginCheck.isBeforeFirst()){
					String fbSql = "insert into login"+" values (?,?,?)";
					PreparedStatement pStatement = con.prepareStatement(fbSql);
					pStatement.setString(1, fbId);
					pStatement.setString(2, "Set New Password");
					pStatement.setString(3, fbuser);
					pStatement.execute();
				}
			}
			HttpSession session=request.getSession();
			session.setAttribute("user","aashi_user");
			session.setAttribute("name", username);
			rd=request.getRequestDispatcher("welcome.jsp");
			rd.include(request, response);
		}
		else
		{
			out.println("<font color='red'>Sorry You Are Not In Our Database !</font>");
			rd=request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
			ServletConfig sc=getServletConfig();
			out.println("Any Problems in Login ---> "+sc.getInitParameter("Email"));
		}
		st.close();
		con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		out.close();
	}

}
}
