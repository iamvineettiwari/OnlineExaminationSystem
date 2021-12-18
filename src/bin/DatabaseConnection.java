package bin;

import java.sql.*;

public class DatabaseConnection {
	
	public Connection conn;
	public Statement stmt;
	public PreparedStatement pstmt;
	public ResultSet rst, rst2, rst3;
	
	public DatabaseConnection() {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineexam","root","");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
