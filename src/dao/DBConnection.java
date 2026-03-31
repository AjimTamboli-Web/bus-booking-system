package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/bus_booking";
	private static final String USER = "root";
	private static final String PASS = "Ajim";
	
	public static Connection getConnection() {
	  
		Connection con = null;
	try {
		
	  Class.forName("com.mysql.cj.jdbc.Driver");   // this is optional after java 6
	
	  con = DriverManager.getConnection(URL, USER, PASS);
//	    System.out.println("Database Connection successful...");	
	    System.out.println("Connected DB: " + con.getCatalog());
	}
	catch(Exception ex) {
		System.out.println("connection failed.." + ex.getMessage());
	}
	return con;
	}
}
