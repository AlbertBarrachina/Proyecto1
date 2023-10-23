package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class db {

	private static final String USER = "DW2_2324_BOOK4U_ASA";
	private static final String PWD = "AASA";
	// conexionn dentro de ilerna
	private static final String URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";
	// conexion fuera de ilerna
//	private static final String URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";

	private static final Connection con = conectarBD();

	// funcion para conectar a la base de datos
	// no pide nada y devuelde una conexion a la base de datos de la aplicacion
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

	// funciones de cliente

	public static void crearCliente(String dni, String nombre, String apellidos, int telefono, String cuneta_bancaria,
			String correo, String contrasenya, int creditos, String tipo) {
		String sql = "INSERT INTO CLIENTE values(" + dni + "," + nombre + "," + apellidos + "," + telefono + ",'',"
				+ correo + "," + contrasenya + ",0,'S');";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	// cambia la info (dni, nombre, apellidos, telefono y correo) de la cuenta.
	public static void editarInfoCliente(String dni, String dniNuevo, String nombre, String apellidos, int telefono,
			String correo) {
		String sql = "UPDATE TABLE CLIENTE SET dni = " + dniNuevo + ", nombre = " + nombre + ", apellidos = "
				+ apellidos + ", telefono = " + telefono + " WHERE dni = " + dni + ";";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	// comprueba que las dos contraseñas coincidan, si es asi, cambia la contraseña
	// de la cuenta. (falta encriptar)
	public static String editarContrasenyaCliente(String dni, String contrasenya, String contrasenya2) {
		if (contrasenya == contrasenya2) {
			String sql = "UPDATE TABLE CLIENTE SET contrasenya = " + contrasenya + " WHERE dni = " + dni + ";";
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				return "La contraseña se ha cambiado correctamente";

			} catch (SQLException e) {
				return "Ha sucedido un error en la base de datos, vuelva a intentarlo en unos minutos";
			}
		} else {
			return "La contraseña no coincide";
		}
	}

	// edita cuantos creditos tiene una persona (necesita areglar)
	public static void editarCreditosCliente(String dni, int creditos) {
		String sql = "SELECT creditos from CLIENTE WHERE dni = " + dni + ";";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			System.out.println(rs);

		} catch (SQLException e) {
			System.out.println(e);
		}

//		sql = "UPDATE TABLE CLIENTE SET creditos = creditos + " + creditos + " WHERE dni = " + dni + ";";
//		try {
//			Statement st = con.createStatement();
//			ResultSet rs = st.executeQuery(sql);
//
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
	}

	// funciones de compras

}
