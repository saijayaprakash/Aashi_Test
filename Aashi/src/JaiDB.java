import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

//$Id$

public class JaiDB{
	public Statement statement;
	public Connection getConnection(){
		ApplicationContext context = new FileSystemXmlApplicationContext("C:/Users/Alienware/git/Aashi_Test/Aashi/WebContent/WEB-INF/applContext.xml");
		DbInit x = (DbInit) context.getBean("studentbean");
		Connection con= x.getConnection();
		return con;	
	}
}