package backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class archivo {

	// lee el archivo introducido en la variable "ruta" y solo la linea "linea", las
	// lineas empiezan por 1 y la ruta para entrar en la carpeta config es
	// "./src/config/"
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
						lista[n] = (token.trim());
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

	
	//funcion para editar una linea especifica de un archivo (tipo txt o parecidos) requeire la ruta(string) la linea a editar(la primera es 0) y el texto que se desea insertar
	public static void editarTxt(String ruta, int linea, String textoNuevo) {

		 try {
		        List<String> lines = Files.readAllLines(Paths.get(ruta), StandardCharsets.UTF_8);

		        if (linea < 0) {
		            System.err.println("Invalid line number.");
		            return;
		        }

		        if (linea >= lines.size()) {
		            int linesToAdd = linea - lines.size();
		            List<String> emptyLines = Stream.generate(() -> "").limit(linesToAdd + 1).collect(Collectors.toList());
		            lines.addAll(emptyLines);
		        }

		        lines.set(linea, textoNuevo);
		        Files.write(Paths.get(ruta), lines, StandardCharsets.UTF_8);

		        System.out.println("Specific line modified or added successfully.");
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}
}