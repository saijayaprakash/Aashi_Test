

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		try{
		String usr=request.getParameter("adminUser");
		String pass=request.getParameter("adminPass");
		ServletContext sc=getServletContext();
		String code=(String) sc.getAttribute("user");
		if(usr.equals("jai") && code.equals("Jai"))
		{
			sc.removeAttribute("user");
			HttpSession session=request.getSession();
			session.setAttribute("user","Kingmaker");
			RequestDispatcher rd=request.getRequestDispatcher("Admin.jsp");
			rd.include(request, response);
		}
		else {
			response.sendRedirect("index.jsp");
		}
		}finally{
			
		}
	}

}
