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
import java.nio.file.StandardCopyOption;
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
		try {
			File archivo = new File(ruta);

			FileReader fileReader = new FileReader(archivo);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String textoLinea;
			while ((textoLinea = bufferedReader.readLine()) != null) {
				i++;
				if (i == linea) {
					lista=separarLinea(textoLinea);
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

	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//falta corregir errores al sobreescribir lineas
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//funcion para editar una linea especifica de un archivo (tipo txt o parecidos) requeire la ruta(string) la linea a editar(la primera es 0) y el texto que se desea insertar
	public static void editarTxt(String ruta, int linea, String textoNuevo) {

		 try {
		        List<String> lines = Files.readAllLines(Paths.get(ruta));

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
		        Files.write(Paths.get(ruta), lines);

		        System.out.println("Specific line modified or added successfully.");
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}
	
	//funcion que junta los campos enviados separandolos por un punto y coma

	public static String juntarLinea(String[] array) {
	        String joinedString = String.join(";", array);

	        return joinedString;
	}
	//funcion que separa la linea donde hay puntos y coma devolviendo una array de strings de esa linea
	public static String[] separarLinea(String textoLinea){
		String[] tokens = textoLinea.split(";");
		int n = 0;
		for (String token : tokens) {
			tokens[n] = (token.trim());
			n++;
		}
		return tokens;
	}
	
	//copia un archivo y le cambia el nombre
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//no va bien solo lo renombra
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public static void copiarArchivo(String nombreNuevo,String rutaNueva, String nombreOriginal, String rutaOrigen) {
		Path destino = Path.of(rutaNueva, nombreNuevo+".png");
		Path origen = Path.of(rutaOrigen, nombreOriginal+".png");
		try {
			Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}