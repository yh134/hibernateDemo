package hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static String url="jdbc:sqlserver://localhost:1434;DatabaseName=db_03";
	
	static String name="sa";
	static String pwd="123";
	
	static {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static Connection getCon()
	{
		Connection con=null;
		try {
			con=DriverManager.getConnection(url,name,pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}