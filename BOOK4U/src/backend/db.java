package backend;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.util.Base64;

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

	// encripacion de los textos necesatios
	
	//en progreso :'(
	
	// funciones de tabla cliente----------------------------------------------

	// ------------------------------------------------------------
	// crea el cliente el la base de datos (funcion de register)
	public static String crearCliente(String nombre, String apellidos, int telefono, String correo,
			String contrasenya) {
		String sql = "INSERT INTO CLIENTE values( NULL , ? , ? , ? , NULL , ? , ? ,0, 'S')";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setInt(3, telefono);
			pst.setString(4, correo);
			pst.setString(5, contrasenya);

			int rowsInserted = pst.executeUpdate();
			if (rowsInserted > 0) {
				return "Usuario creado correctamente.";
			} else {
				return "No se pudo crear el usuario.";
			}
		} catch (SQLException e) {
			return "Error: " + (e);
		}

	}

	// ------------------------------------------------------
	// recoje toda la informacion del cliente
	public static String[] mostrarInfoCliente(int idc) {
		String cliente[] = new String[5];
		String sql = "SELECT * from CLIENTE WHERE correo = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idc);

			ResultSet rs = pst.executeQuery();

			// si existe el usuario
			if (rs.next()) {
				cliente[0] = Integer.toString(rs.getInt("idc"));
				cliente[1] = rs.getString("nombre");
				cliente[2] = rs.getString("apellidos");
				cliente[3] = Integer.toString(rs.getInt("telefono"));
				cliente[2] = rs.getString("cuenta_bancaria");
				cliente[2] = rs.getString("correo");
				cliente[2] = rs.getString("contrasenya");
				cliente[3] = Integer.toString(rs.getInt("creditos"));
				cliente[2] = rs.getString("tipo");
			} else {
				System.out.println("No hay cliente.");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return cliente;
	}

	// ------------------------------------------------------------------------------------------
	// comprueba si los datos insertados coinciden con los datos en la base de
	// datos(correo y contraseña).

	public static String comprovarLoginCliente(String correo, String contrasenya) {
		String contrasenyaCorrecta;
		String sql = "SELECT contrasenya from CLIENTE WHERE correo = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, correo);

			ResultSet rs = pst.executeQuery();

			// si existe el usuario
			if (rs.next()) {
				contrasenyaCorrecta = rs.getString("contrasenya");
				if (contrasenyaCorrecta == contrasenya) {
					return "Sesión iniciada correctamente";
				} else {
					return "Contraseña incorrecta";
				}
			} else {
				return "No se ha encontrado el usuario.";
			}
		} catch (SQLException e) {
			return "No se ha podido hacer la operacion, error: " + (e) + ".";
		}
	}

	// -------------------------------------------------------------------------
	// cambia la info (nombre, apellidos, telefono y correo) de la cuenta.
	public static String editarInfoCliente(int idc, String nombre, String apellidos, int telefono, String correo) {
		String sql = "UPDATE CLIENTE SET nombre = ?, apellidos = ?, telefono = ?, correo = ? WHERE idc = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setInt(3, telefono);
			pst.setString(4, correo);
			pst.setInt(5, idc);

			int rowsUpdated = pst.executeUpdate();
			if (rowsUpdated > 0) {
				return "Usuario actualizado correctamente.";
			} else {
				return "No se ha encontrado el usuario.";
			}

		} catch (SQLException e) {
			return "Ha sucedido un error en la base de datos, vuelva a intentarlo en unos minutos.";
		}

	}

	// -----------------------------------------------------------------------------------!!!!!!!!!!!!!!!!!!!!!!!!
	// Cambia la contraseña de la cuenta. !!!!!!!!!!!!!!!!!!!(falta
	// encriptar)!!!!!!!!!!!!!!!!
	public static String editarContrasenyaCliente(int idc, String contrasenya) {
		String sql = "UPDATE CLIENTE SET contrasenya = ? WHERE idc = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, contrasenya);
			pst.setInt(2, idc);

			int rowsUpdated = pst.executeUpdate();
			if (rowsUpdated > 0) {
				return "La contraseña se ha cambiado correctamente.";
			} else {
				return "No se ha podido cambiar la contraseña.";
			}
		} catch (SQLException e) {
			return "Ha sucedido un error en la base de datos, vuelva a intentarlo en unos minutos.";
		}
	}

	// ------------------------------------------------------------------
	// edita cuantos creditos tiene una persona, comprueba si tiene suficientes para
	// gastar y si tiene demasiados (max 999) y los actualiza, manda una string para
	// que salga como notificacion por pantalla
	public static String editarCreditosCliente(int idc, int creditos) {

		String sql = "UPDATE CLIENTE SET creditos =  ? WHERE idc = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, creditos);
			pst.setInt(2, idc);

			int rowsUpdated = pst.executeUpdate();

			if (rowsUpdated > 0) {
				return "Operacion realizada con exito. Ahora tiene " + creditos + " creditos.";
			} else {
				return "No se ha encontrado el usuario.";
			}
		} catch (SQLException e) {
			return "No se ha podido hacer la operacion, error: " + (e) + ".";
		}
	}

	// -----------------------------------------------------------
	// obtiene cuantos creditos tiene el usuario indicado en su cuenta
	public static int getCreditosCliente(int idc) {
		int creditosActuales = 0;
		String sql = "SELECT creditos from CLIENTE WHERE IDC = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idc);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				creditosActuales = rs.getInt("creditos");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return creditosActuales;
	}

	// -------------------------------------------------------
	// elimina el cliente que se ha indicado en casos extremos.
	public static String eliminarCliente(int idc) {
		String sql = "DELETE FROM CLIENTE WHERE idc = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idc);

			int rowsDeleted = pst.executeUpdate();
			if (rowsDeleted > 0) {
				return "El cliente ha sido eliminado correctamente.";
			} else {
				return "No se ha podido eliminar el cliente.";
			}
		} catch (SQLException e) {
			return "Ha sucedido un error en la base de datos, vuelva a intentarlo en unos minutos.";
		}
	}

	// funciones de tabla compras

	// ---------------------------------------------------------------------------------
	// crea una factura de la compra de creditos y llama a la fucnion que modifica
	// los creditos
	// del usuario ( maximo operaciones de dos digitos por seguridad)
	public static String comprarCompras(int idc, int creditos, String metodo_pago) {
		String mensaje = "";

		int creditosActuales = getCreditosCliente(idc);
		int temp = creditosActuales + creditos;
		if (temp <= 999) {
			String sql = "INSERT INTO COMPRAS values( NULL , ? , ? , ? , ?)";
			try {
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, idc);
				pst.setInt(2, creditos);
				pst.setInt(3, creditos * 10);
				pst.setString(4, metodo_pago);

				int rowsInserted = pst.executeUpdate();
				if (rowsInserted > 0) {
					creditos = creditosActuales + creditos;
					mensaje = (editarCreditosCliente(idc, creditos));
				} else {
					mensaje = "No se pudo hacer la transaccion.";
				}
			} catch (SQLException e) {
				mensaje = "Error: " + (e);
			}

		} else {
			mensaje = "Has llegado a tu limite de creditos acumulados.";
		}
		return mensaje;
	}

	public static void historialCompras(int idc) {
		ArrayList<String[]> resultados = new ArrayList<>();
		String sql = "SELECT * from COMPRAS WHERE cliente = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idc);

			ResultSet rs = pst.executeQuery();

			// si existe el usuario
			while (rs.next()) {
				String[] row = new String[5];
				row[0] = Integer.toString(rs.getInt("id_compra"));
				row[1] = Integer.toString(rs.getInt("cliente"));
				row[2] = Integer.toString(rs.getInt("cantidad"));
				row[3] = Integer.toString(rs.getInt("precio"));
				row[4] = rs.getString("id_compra");

				resultados.add(row);

				System.out.println(row[0] + "   " + row[1] + "   " + row[2] + "   " + row[3] + "   " + row[4] + "   .");
			}
			if (resultados.isEmpty()) {
				System.out.println("No compraste nada.");
			}
		} catch (SQLException e) {
			System.out.println((e));
		}

	}

}
