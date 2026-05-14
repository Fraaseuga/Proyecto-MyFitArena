package dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Conexion {
	private static final String nombredb = "MyFitArena";
	private static final String puerto = "5432";
	private static final String url = "jdbc:postgresql://localhost:"+puerto+"/"+nombredb;
	private static final String user = "postgres";
	private static final String password = "1234";
	
	public static Connection getConexion() {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url,user,password);

		}catch(SQLException e) {
			System.out.println("");
		}
		return con;
	}
}