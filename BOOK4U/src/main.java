import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import backend.archivo;
import backend.db;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String cliente[] = new String[10];
//		System.out.println(db.crearCliente("Alex", "Perezzzzzzz", 444522636, "perezzz@gmail.con", "cowontraseña"));
//		cliente = db.mostrarInfoCliente(1);
//		System.out.println(cliente[0]+cliente[1]+cliente[2]+cliente[3]+cliente[4]+cliente[5]+cliente[6]+cliente[7]+cliente[8]);
//		if(db.comprobarLoginCliente("perez@gmail.con", "cowontraseña") == true) {
//			System.out.println("acerstaste.");
//		}else {
//			System.out.println("wawaa");
//		}
//		System.out.println(db.editarInfoCliente(2, "NOT Alex", "Prieto", 669696976, "correogenrico@gmail.com"));
		ArrayList<String[]> resultados = db.buscarHabitacion(0, 0, 0, null, 0);
		 for (String[] item : resultados) {
	            System.out.println(item[0]);
	        }
		
	}

}
