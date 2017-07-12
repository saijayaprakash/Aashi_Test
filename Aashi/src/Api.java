

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Api
 */
@WebServlet("/Api")
public class Api extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("type/html");
		try{
				PrintWriter out=response.getWriter();
				String user=request.getParameter("user");
				HttpSession session=request.getSession(false);
				String key=(String) session.getAttribute("user");
				if(user.equals("jai"))
				{
					out.print("Hello Jai ..This is the Mission Info !!");
					
				}
				else
					response.sendRedirect("index.jsp");
		}finally{
			
		}
	}
	public String getDetails(String username)
	{
		return null;
	}

}
