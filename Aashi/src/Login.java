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
	RequestDispatcher rd;
	Connection con;
	Statement st;
	
	public void socialLogin(HttpServletRequest request,HttpServletResponse response,String username,String fullName) {
		String socialCheck="select * from login where username='"+username+"';";
		ResultSet rsSocialLoginCheck;
		try {
			rsSocialLoginCheck = st.executeQuery(socialCheck);
			if(!rsSocialLoginCheck.isBeforeFirst()){
				String socialSql = "insert into login"+" values (?,?,?)";
				PreparedStatement pStatement = con.prepareStatement(socialSql);
				pStatement.setString(1, username);
				pStatement.setString(2, "Set New Password");
				pStatement.setString(3, fullName);
				pStatement.execute();
			}
			getValid(request, response, username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	public void getValid(HttpServletRequest req,HttpServletResponse res,String username) {
		HttpSession session=req.getSession();
		session.setAttribute("user","aashi_user");
		session.setAttribute("name", username);
		rd=req.getRequestDispatcher("welcome.jsp");
		try {
			rd.include(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		try{
		con=new JaiDB().getConnection();
		st=con.createStatement();
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String fbuser=request.getParameter("fbuser");
		String fbId = request.getParameter("fbId");
		String fbAccessKey = request.getParameter("fbAccessKey");
		boolean guser=false;
		String gmail = request.getParameter("gmail");
		String gname = request.getParameter("gname");
		
		boolean isFbUser = false;
		if(fbuser!=null && !fbuser.isEmpty()){
			isFbUser = true;
		}
		
		if(gmail!=null && !gmail.isEmpty()){
			guser = true;
		}
		
//		Normal User
		if(username!=null && password!=null && !username.isEmpty() && !password.isEmpty()) {
			String sql="select * from login where username='"+username+"' AND password='"+password+"';";
			ResultSet rs=st.executeQuery(sql);
			if(username.equals("king") && password.equals("king"))
			{
				response.sendRedirect("AdminLogin.html");
				ServletContext sc=getServletContext();
				sc.setAttribute("user", "Jai");
			}
			if(rs.isBeforeFirst()) {
				getValid(request,response,username);
			}
			else {
				out.println("<font color='red'>Sorry You Are Not In Our Database !</font>");
				rd=request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
				ServletConfig sc=getServletConfig();
				out.println("Any Problems in Login ---> "+sc.getInitParameter("Email"));
			}
		}
		
//		FB USer
		if(isFbUser){
			socialLogin(request, response, fbId ,fbuser);
		}
		else if(guser) {
			socialLogin(request, response, gmail, gname);
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
