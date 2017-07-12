

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionDetails
 */
@WebServlet("/SessionDetails")
public class SessionDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("type/html");
		PrintWriter out=response.getWriter();
		try{
			HttpSession session=request.getSession(false);
			out.println("Your Previous Session Id="+session.getId());
			out.println("The Previous Creation Time :"+new Date(session.getCreationTime()));
			out.println("The Last Accessed Time :"+new Date(session.getLastAccessedTime()));
			out.println("Session can be active for "+session.getMaxInactiveInterval()+" Seconds only !!");
		}finally{
			
		}
	}

}
