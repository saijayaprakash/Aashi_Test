import java.sql.DriverManager;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbInit {
	private static String className,dbUrl,dbName,dbUser,dbPassword;
	public DbInit(){
		
	}
	public DbInit(String className,String dbUrl,String dbName,String dbUser,String dbPassword){
		this.className = className;
		this.dbUrl = dbUrl;
		this.dbName = dbName;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
	}
	public static Connection getConnection(){
		Statement statement = null;
		Connection connection = null;
		try {
			Class.forName(className);
			connection=DriverManager.getConnection(dbUrl+dbName,dbUser,dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
//	public static void main(String a[]){		
//		ApplicationContext context = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applContext.xml");
//		DbInit x = (DbInit) context.getBean("studentbean");
//		
//		Statement st=null;
//		Connection con= x.getConnection();
//		try {
//			st = con.createStatement();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		
//		String sql="select username,password from login;";
//		java.sql.ResultSet rs;
//		try {
//			rs = st.executeQuery(sql);
//		System.out.println("Username   | Password   |");
//		while(rs.next()){
//			String usr = rs.getString("username");
//			String pass = rs.getString("password");
//			System.out.println(usr+"  |  "+pass);
//		}
//		st.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}