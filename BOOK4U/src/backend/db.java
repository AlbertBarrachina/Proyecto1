package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class db {

	private static final String USER = "DW2_2324_BOOK4U_ASA";
	private static final String PWD = "AASA";
	// conexionn dentro de ilerna
//	private static final String URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";
	// conexion fuera de ilerna
//	private static final String URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";

	private static final Connection con = conectarBD();

	// funcion para conectar a la base de datos
	// no pide nada y devuelde una conexion a la base de datos de la aplicacion
	public static Connection conectarBD() {
		Connection con = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			try {
				con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.ilerna.com:1521:xe", USER, PWD);
			} catch (Exception e) {
				con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.3.26:1521:xe", USER, PWD);
			}

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

		return con;
	}

	// encripacion de los textos necesatios

	// en progreso :'(

	///////////////////////////////////////////////////////////
	// funciones tabla cliente ///
	///////////////////////////////////////////////////////////

	public static String comprobarCorreoTelefonoCliente(int telefono, String correo) {
		String sql = "SELECT telefono, correo FROM CLIENTE WHERE (correo = ? OR telefono = ?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, correo);
			pst.setInt(2, telefono);

			ResultSet rs = pst.executeQuery();

			// si existe el usuario
			if (rs.next()) {
				return "El correo o el telefono ya estan en uso";
			} else {

				return ".";
			}
		} catch (SQLException e) {
			return "Ha sucedido un error en la base de datos, vuelva a intentarlo en unos minutos.";
		}

	}

	// crea el cliente el la base de datos (funcion de register)
	public static String crearCliente(String nombre, String apellidos, int telefono, String correo,
			String contrasenya) {
		String sql = "SELECT telefono, correo from CLIENTE WHERE correo = ? OR telefono = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, correo);
			pst.setInt(2, telefono);

			ResultSet rs = pst.executeQuery();

			// si existe el usuario
			if (rs.next()) {
				return "El correo o el telefono ya estan en uso";
			} else {
				sql = "INSERT INTO CLIENTE values( NULL , ? , ? , ? , NULL , ? , ? ,0)";
				try {
					pst = con.prepareStatement(sql);
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
					return "No se pudo crear el usuario.";
				}
			}
		} catch (SQLException e) {
			return "No se pudo crear el usuario.";
		}
	}

	// ------------------------------------------------------
	// recoje toda la informacion del cliente
	public static String[] mostrarInfoCliente(String correo, String contrasenya) {
		String cliente[] = new String[8];
		String sql = "SELECT * from CLIENTE WHERE correo = ? AND contrasenya = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, correo);
			pst.setString(2, contrasenya);

			ResultSet rs = pst.executeQuery();

			// si existe el usuario
			if (rs.next()) {
				cliente[0] = Integer.toString(rs.getInt("idc"));
				cliente[1] = rs.getString("nombre");
				cliente[2] = rs.getString("apellidos");
				cliente[3] = Integer.toString(rs.getInt("telefono"));
				cliente[4] = rs.getString("cuenta_bancaria");
				cliente[5] = rs.getString("correo");
				cliente[6] = rs.getString("contrasenya");
				cliente[7] = Integer.toString(rs.getInt("creditos"));
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
	// datos(correo y contrase�a).

	public static boolean comprobarLoginCliente(String correo, String contrasenya) {
		String contrasenyaCorrecta;
		String sql = "SELECT contrasenya FROM CLIENTE WHERE correo = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, correo);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				contrasenyaCorrecta = rs.getString("contrasenya");
				if (contrasenyaCorrecta.equals(contrasenya)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}

	// -------------------------------------------------------------------------
	// cambia la info (nombre, apellidos, telefono y correo) de la
	// cuenta.!!!!!!!falta comprobar y areglar el primer select para que solo mire
	// los otros campos!!!!!!!!
	public static String editarInfoCliente(int idc, String nombre, String apellidos, int telefono, String correo) {
		String sql = "SELECT telefono, correo FROM CLIENTE WHERE (correo = ? OR telefono = ?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, correo);
			pst.setInt(2, telefono);

			ResultSet rs = pst.executeQuery();

			// si existe el usuario
			if (rs.next()) {
				return "El correo o el telefono ya estan en uso";
			} else {

				sql = "UPDATE CLIENTE SET nombre = ?, apellidos = ?, telefono = ?, correo = ? WHERE idc = ?";
				try {
					pst = con.prepareStatement(sql);
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
		} catch (SQLException e) {
			return "Ha sucedido un error en la base de datos, vuelva a intentarlo en unos minutos.";
		}

	}

	// -----------------------------------------------------------------------------------!!!!!!!!!!!!!!!!!!!!!!!!
	// Cambia la contrase�a de la cuenta. !!!!!!!!!!!!!!!!!!!(falta
	// encriptar)!!!!!!!!!!!!!!!!
	public static String editarContrasenyaCliente(int idc, String contrasenya) {
		String sql = "UPDATE CLIENTE SET contrasenya = ? WHERE idc = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, contrasenya);
			pst.setInt(2, idc);

			int rowsUpdated = pst.executeUpdate();
			if (rowsUpdated > 0) {
				return "La contrase�a se ha cambiado correctamente.";
			} else {
				return "No se ha podido cambiar la contrase�a.";
			}
		} catch (SQLException e) {
			return "Ha sucedido un error en la base de datos, vuelva a intentarlo en unos minutos.";
		}
	}

	// ------------------------------------------------------------------
	// edita cuantos creditos tiene una persona, comprueba si tiene suficientes para
	// gastar y si tiene demasiados (max 999) y los actualiza, manda una string para
	// que salga como notificacion por pantalla
	public static Boolean editarCreditosCliente(int idc, int creditos) {

		String sql = "UPDATE CLIENTE SET creditos =  creditos + ? WHERE idc = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, creditos);
			pst.setInt(2, idc);

			int rowsUpdated = pst.executeUpdate();

			if (rowsUpdated > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			return false;
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

	///////////////////////////////////////////////////////////
	// funciones tabla compras ///
	///////////////////////////////////////////////////////////

	// ---------------------------------------------------------------------------------
	// crea una factura de la compra de creditos y llama a la fucnion que modifica
	// los creditos
	// del usuario ( maximo operaciones de dos digitos por seguridad)
	public static String comprarCompras(int idc, int creditos, String metodo_pago) {
		String mensaje = "";

		int creditosActuales = getCreditosCliente(idc);
		int temp = creditosActuales + creditos;
		if (temp <= 999) {
			String sql = "INSERT INTO COMPRAS values( NULL , ? , ? , ? , ?, ?)";
			try {
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, idc);
				pst.setInt(2, creditos);
				pst.setInt(3, creditos * 10);
				pst.setString(4, metodo_pago);
				java.util.Date currentDate = new java.util.Date();
				java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
				pst.setDate(5, sqlDate);
				int rowsInserted = pst.executeUpdate();
				if (rowsInserted > 0) {
					if (editarCreditosCliente(idc, creditos)) {
						mensaje = "Operacion realizada con exito. Ahora tiene " + (creditos + creditosActuales)
								+ " creditos.";
					} else {
						mensaje = "No se pudo hacer la transaccion.1";
					}
				} else {
					mensaje = "No se pudo hacer la transaccion.2";
				}
			} catch (SQLException e) {
				mensaje = "Error: " + (e);
			}

		} else {
			mensaje = "Has llegado a tu limite de creditos acumulados.";
		}
		return mensaje;
	}

	public static ArrayList<String[]> historialCompras(int idc) {
		ArrayList<String[]> resultados = new ArrayList<>();
		String sql = "SELECT * FROM COMPRAS WHERE cliente = ? ORDER BY fecha DESC";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idc);

			ResultSet rs = pst.executeQuery();
			// formato para convertir fechas en strings
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:MM'h'");
			// si existe el usuario
			while (rs.next()) {
				String[] row = new String[6];
				row[0] = Integer.toString(rs.getInt("id_compra"));
				row[1] = Integer.toString(rs.getInt("cliente"));
				row[2] = Integer.toString(rs.getInt("cantidad"));
				row[3] = Integer.toString(rs.getInt("precio"));
				row[4] = rs.getString("id_compra");
				row[5] = dateFormat.format(rs.getDate("fecha"));

				resultados.add(row);
			}
		} catch (SQLException e) {
			String[] row = new String[6];
			row[0] = "" + (e);
			resultados.add(row);
		}
		return resultados;

	}

	///////////////////////////////////////////////////////////
	// funciones tabla reserva ///
	///////////////////////////////////////////////////////////

	// permite crear reservas
	public static Boolean comprarReserva(int idc, int id_habitacion, int precio, String estado, String strEntrada,
			String strSalida) {
		String sql = "INSERT INTO RESERVA values(0, ?, ?, ?, ?, ?, ?)";

		SimpleDateFormat DateFormat = new SimpleDateFormat("dd-MM-yyyy");

		try {
			Date Inicial = DateFormat.parse(strEntrada);
			Date Final = DateFormat.parse(strSalida);
			Timestamp Entrada = new Timestamp(Inicial.getTime());
			Timestamp Salida = new Timestamp(Final.getTime());
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id_habitacion);
			pst.setInt(2, idc);
			pst.setInt(3, precio);
			pst.setString(4, estado);
			pst.setTimestamp(5, Entrada);
			pst.setTimestamp(6, Salida);

			int rowsInserted = pst.executeUpdate();
			if (rowsInserted > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace(); // Print the exception details for debugging
			System.out.println("error fatal");
			return false;
		}

	}

	// permite cancelar reservas.
	public static boolean editarInfoReserva(int id_reserva) {

		String sql = "UPDATE RESERVA SET estado = 'C' WHERE id_reserva = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id_reserva);

			int rowsUpdated = pst.executeUpdate();

			if (rowsUpdated > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}

	// historico de las reservas, puede mostrar las reservas acaadas canceladas o
	// fenegadas o las reservas pagadas
	public static ArrayList<String[]> historialReservas(int idc, String estado, String estado2, String estado3) {
		ArrayList<String[]> resultados = new ArrayList<>();
		String sql = "SELECT * FROM RESERVA WHERE cliente = ? AND (estado = ? OR estado = ? OR estado = ?) ORDER BY fecha_entrada DESC";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idc);
			pst.setString(2, estado);
			pst.setString(3, estado2);
			pst.setString(4, estado3);
			ResultSet rs = pst.executeQuery();
			// formato para convertir fechas en strings
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:MM'h'");
			// si existe el usuario
			while (rs.next()) {
				String[] row = new String[7];
				row[0] = Integer.toString(rs.getInt("id_reserva"));
				row[1] = Integer.toString(rs.getInt("id_habitacion"));
				row[2] = Integer.toString(rs.getInt("cliente"));
				row[3] = Integer.toString(rs.getInt("precio"));
				row[4] = rs.getString("estado");
				row[5] = dateFormat.format(rs.getDate("fecha_entrada"));
				row[6] = dateFormat.format(rs.getDate("fecha_salida"));

				resultados.add(row);

			}
		} catch (SQLException e) {
			String[] row = new String[7];
			row[0] = "" + (e);
			resultados.add(row);
		}
		return resultados;

	}
	
	// busca todas las fechas donde las habitaciones estan reservadas en este momento
		public static ArrayList<String[]> calendarioReservas(int idh) {
			ArrayList<String[]> resultados = new ArrayList<>();
			String sql = "SELECT * FROM RESERVA WHERE estado = 'P' AND ID_HABITACION = ? ORDER BY fecha_entrada DESC";
			try {
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, idh);
				ResultSet rs = pst.executeQuery();
				// formato para convertir fechas en strings
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				// si existe el usuario
				while (rs.next()) {
					String[] row = new String[2	];
					row[0] = dateFormat.format(rs.getDate("fecha_entrada"));
					row[1] = dateFormat.format(rs.getDate("fecha_salida"));

					resultados.add(row);

				}
			} catch (SQLException e) {
				System.out.println(e);
			}
			return resultados;

		}

	///////////////////////////////////////////////////////////
	// ------------funciones tabla empresa----------------- ///
	///////////////////////////////////////////////////////////

	// obtiene la info de la empresa especificada
	public static String[] InfoEmpresa(int ide) {
		String[] resultados = new String[3];
		String sql = "SELECT * FROM EMPRESA WHERE IDE = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, ide);

			ResultSet rs = pst.executeQuery();
			rs.next();
			resultados[0] = Integer.toString(rs.getInt("ide"));
			resultados[1] = rs.getString("nombre");
			resultados[2] = rs.getString("direccion");
		} catch (SQLException e) {
			resultados[0] = "1";
			resultados[1] = "BOOK4U";
			resultados[2] = "No se pudo cargar la direccion";
		}
		return resultados;
	}

	///////////////////////////////////////////////////////////
	// ----- funciones tabla habitacion ---------------------///
	///////////////////////////////////////////////////////////

	// ----------------------------------------------------------------
	// cambia el estado de la habitacion.
	public static String editarInfoHabitacion(int id_habitacion, String estado) {

		String sql = "UPDATE HABITACION SET disponibilidad = ? WHERE id_habitacion = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, estado);
			pst.setInt(2, id_habitacion);

			int rowsUpdated = pst.executeUpdate();

			if (rowsUpdated > 0) {
				return "Infomracion cambiada correctamente";
			} else {
				return "No se ha encontrado la habitacion.";
			}
		} catch (SQLException e) {
			return "No se ha podido hacer la operacion, error: " + (e) + ".";
		}
	}

	// busca toda la informacion de las habitaciones que entren dentro de las
	// categorias de busqueda introducidas, si la categoria es null no se incluye en
	// el select
	public static List<String[]> buscarHabitacion(int idh) {
		List<String[]> resultados = new ArrayList<>();

		StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM HABITACION WHERE 1 = 1");
		List<Object> params = new ArrayList<>();

		if (idh > 0) {
			sqlBuilder.append(" AND id_habitacion = ?");
			params.add(idh);
		} else {
			sqlBuilder.append(" AND DISPONIBILIDAD = 'S'");
		}

		String sql = sqlBuilder.toString();
		try (PreparedStatement pst = con.prepareStatement(sql)) {
			// Set parameters
			for (int i = 0; i < params.size(); i++) {
				pst.setObject(i + 1, params.get(i));
			}

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					String[] row = new String[9];
					row[0] = Integer.toString(rs.getInt("ID_HABITACION"));
					row[1] = Integer.toString(rs.getInt("EMPRESA"));
					row[2] = Integer.toString(rs.getInt("PRECIO"));
					row[3] = Integer.toString(rs.getInt("DESCUENTO"));
					row[4] = rs.getString("DISPONIBILIDAD");
					row[5] = rs.getString("TIPO");
					row[6] = Integer.toString(rs.getInt("CAMAS"));
					row[7] = rs.getString("NOMBRE");
					row[8] = rs.getString("DESCRIPCION");
					resultados.add(row);
				}
			} catch (Exception e) {
				if (resultados.isEmpty()) {
					String[] row = new String[9];
					row[0] = "No se han encontrado habitaciones. :(";
					resultados.add(row);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Log the exception or handle it as needed
		}

		return resultados;
	}

}
