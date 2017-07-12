import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//$Id$

public class JaiDB{

	public Connection connection;
	public Statement statement;
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			JaiDB a=new JaiDB();
			a.getStatement();
			
		} catch (Exception e) {
			System.out.print(e);
		}
		
	}
	public Statement getStatement(){
		try {
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/jai","root","");
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return statement;	
	}
//	@Override
//	public void contextDestroyed(ServletContextEvent event) {
//		try {
//			Connection con = (Connection) event.getServletContext().getAttribute("DBConnection");
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void contextInitialized(ServletContextEvent event) {
//		ServletContext context = event.getServletContext();
//		String dbClass = context.getInitParameter("dbClass");
//		String dbUser = context.getInitParameter("dbUser");
//		String dbPath = context.getInitParameter("dbPath");
//		
//		try {
//			Class.forName(dbClass);
//			connection = DriverManager.getConnection(dbPath,dbUser,"");
//			context.setAttribute("DBConnection", connection);
//			System.out.print("The DB COnnection Initialized !");
//		
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
}
