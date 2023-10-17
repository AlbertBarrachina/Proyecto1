package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class db {
	
	private static final String USER = "";
	private static final String PWD = "";
	//conexionn dentro de ilerna
	private static final String URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";
	//conexion fuera de ilerna
//	private static final String URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";

	private static final Connection con = conectarBD();

	public static Connection conectarBD() {
		Connection con = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PWD);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

		return con;

	}
}
