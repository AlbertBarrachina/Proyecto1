package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class archivo {

	public static String[] leerTxt(String ruta, int linea) {
		String[] lista = new String[10];
		int i = 0;
		int n = 0;
		try {
			File archivo = new File(ruta);

			FileReader fileReader = new FileReader(archivo);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String textolinea;
			while ((textolinea = bufferedReader.readLine()) != null) {
				i++;
				if (i == linea) {
					String[] tokens = textolinea.split(";");

					for (String token : tokens) {
						lista[n]=(token.trim());
						n++;
					}
					break;
				}
			}

			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lista;
	}
}
