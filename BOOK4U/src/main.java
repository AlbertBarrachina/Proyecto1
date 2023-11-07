import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import backend.*;

public class main extends JFrame {
	private JPanel panelPrincipal;
	private JPanel perfilPanel;
	private List<Contenido> contenidos = new ArrayList<>();
	private JPanel contentPanel;
	private Color backgroundColor = new Color(173, 216, 230);

	public main() {

		String cliente[] = new String[10];

		cliente = archivo.leerTxt("./src/config/config_usuario.txt", 1);
		System.out.println(cliente[0]);
		System.out.println(cliente[1]);

		if (db.comprobarLoginCliente(cliente[0], cliente[1]) == true) {
			System.out.println("login correcto");

		} else {
			ContenidoUI contenidoUI = new ContenidoUI(this);
		}
	}

	// FUNCION MAIN PARA HACER QUE SE EJECUTE LA APLICACION
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new main());

	}

}