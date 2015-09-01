package module.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * �������ݿ����ӡ��û���Ĭ��root ����tiancai
 * @author Administrator
 *
 */
public class DBConnect {
	private static Connection conn = null;
	
	private DBConnect(){
		super();
	}
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}						
	}
	
	public static void init(String sname,String password) throws SQLException {
		conn =DriverManager.getConnection("jdbc:mysql://localhost/"+DBConstants.DATABASE_NAME+"?useUnicode=true&characterEncoding=gbk", sname, password);
		System.out.println("Database connected");
	}
	
	public static Connection getConnection() throws SQLException{
		if (conn == null){
			init("root", "");			
		}else{
			return conn;
		}
		return conn;
		
		
	}
}
