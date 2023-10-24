package backend;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

	// funciones de cliente----------------------------------------------

	// crea el cliente el la base de datos (funcion de register)
	public static void crearCliente(String dni, String nombre, String apellidos, int telefono, String correo,
			String contrasenya) {
		String sql = "INSERT INTO CLIENTE values( ? , ? , ? , ? , NULL , ? , ? ,0, 'S')";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, dni);
			pst.setString(2, nombre);
			pst.setString(3, apellidos);
			pst.setInt(4, telefono);
			pst.setString(5, correo);
			pst.setString(6, contrasenya);
			System.out.println("SQL Statement: " + sql);
			int rowsInserted = pst.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Usuario creado correctamente.");
			} else {
				System.out.println("No se pudo crear el usuario.");
			}
		} catch (SQLException e) {
			System.out.println(e);
			;
		}

	}

	// cambia la info (dni, nombre, apellidos, telefono y correo) de la cuenta.
	public static void editarInfoCliente(String dni, String dniNuevo, String nombre, String apellidos, int telefono,
			String correo) {
		String sql = "UPDATE TABLE CLIENTE SET dni = " + dniNuevo + ", nombre = " + nombre + ", apellidos = "
				+ apellidos + ", telefono = " + telefono + " WHERE dni = " + dni;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	// comprueba que las dos contraseñas coincidan, si es asi, cambia la contraseña
	// de la cuenta. (falta encriptar)
	public static String editarContrasenyaCliente(String dni, String contrasenya) {
		String sql = "UPDATE TABLE CLIENTE SET contrasenya = " + contrasenya + " WHERE dni = " + dni;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			return "La contraseña se ha cambiado correctamente";

		} catch (SQLException e) {
			return "Ha sucedido un error en la base de datos, vuelva a intentarlo en unos minutos";
		}
	}

	// ------------------------------------------------------------------
	// edita cuantos creditos tiene una persona, comprueba si tiene suficientes para
	// gastar y si tiene demasiados (max 999) y los actualiza, manda una string para
	// que salga como notificacion por pantalla
	public static String editarCreditosCliente(String dni, int creditos) {
		int creditosActuales = 0;
		String sql = "SELECT creditos from CLIENTE WHERE dni = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, dni);

			ResultSet rs = pst.executeQuery();

			// si puede
			if (rs.next()) {
				creditosActuales = rs.getInt("creditos");
				int temp = 0;
				temp = creditosActuales + creditos;
				if (temp >= 0 && temp <= 999) {
					creditosActuales += creditos;
				} else {
					return "No tiene suficientes creditos";
				}
			} else {
				return "No se ha encontrado el dni: " + dni;
			}
		} catch (SQLException e) {
			return "No se ha podido hacer la operacion, error: " + (e) + ".";
		}

		sql = "UPDATE CLIENTE SET creditos = creditos + ? WHERE dni = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, creditos);
			pst.setString(2, dni);

			int rowsUpdated = pst.executeUpdate();

			if (rowsUpdated > 0) {
				return "Operacion realizada con exito. Ahora tiene " + creditosActuales + " creditos.";
			} else {
				return "No se ha encontrado el dni: " + dni;
			}
		} catch (SQLException e) {
			return "No se ha podido hacer la operacion, error: " + (e) + ".";
		}
	}

	// funciones de compras

}
