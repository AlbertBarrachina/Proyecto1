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

	//funcion para conectar a la base de datos
	//no pide nada y devuelde una conexion a la base de datos de la aplicacion
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
	
	public static void crearCliente(String dni, String nombre, String apellidos, int telefono, String correo, String password) {
		String sql = "INSERT INTO cliente value("+dni+","+nombre+","+apellidos+","+telefono+",,"+correo+","+password+",)";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
		} catch (SQLException e) {
			System.out.println(e);
		}

	}
	
	public static void editarCliente(String dni, String nombre, String apellidos, int telefono, String correo, String password, int creditos) {
		String sql = "UPDATE TABLE cliente SET dni = "+dni+", nombre = "+nombre+", apellidos = "+apellidos+", telefono = "+telefono+", , password = "+password+" WHERE dni = "+dni+";";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
		} catch (SQLException e) {
			System.out.println(e);
		}

	}
}
