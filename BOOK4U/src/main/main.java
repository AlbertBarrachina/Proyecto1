package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import backend.archivo;
import backend.db;

public class main {
	// frame de la aplicacion, se usara este para todo
	private static JFrame frame = new JFrame("Book4U Loading...");
	private JPanel panelPrincipal;
	private JPanel perfilPanel;
	private List<paneles.Contenido> contenidos = new ArrayList<>();
	private JPanel contentPanel;
	private Color backgroundColor = new Color(173, 216, 230);
	private static String cliente[] = new String[10];
	// desde aqui se cmabia de pantalla
	public static void main(String[] args) {
		cliente = archivo.leerTxt("src/config/config_usuario.txt", 1);
		//establece el icono de la aplicacion
		ImageIcon Icon = new ImageIcon("src/assets/book4u.png");
		frame.setIconImage(Icon.getImage());

		// carga la pagina principal si el login es correcto
		if (db.comprobarLoginCliente(cliente[0], cliente[1]) == true) {
			cliente=backend.db.mostrarInfoCliente(cliente[0], cliente[1]);
			ContenidoUI contenidoUI = new ContenidoUI();
			frame.setVisible(true);
			JOptionPane.showMessageDialog(null, "login correcto.");
			// si no se puede hacer login carga la pantalla de login
		} else {
			cargarLogin();
			frame.setVisible(true);
		}
	}

	// funcion para cargar la pantalla de registro
	public static void cargarRegistro() {
		frame.getContentPane().removeAll();
		frame.setTitle("Pantalla de Registro");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setResizable(false);
		paneles.Registro panelRegistro = new paneles.Registro();
		frame.getContentPane().add(panelRegistro, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
	}

	// funcion para cargar la pantalla de login
	public static void cargarLogin() {
		frame.getContentPane().removeAll();
		frame.setTitle("Pantalla de Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 175);
		frame.setResizable(false);
		paneles.LogIn panelLogin = new paneles.LogIn();
		frame.getContentPane().add(panelLogin, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
	}

	// funcion para cargar la pantalla principal
	public static void cargarPrincipal() {
		 frame.getContentPane().removeAll();
		    frame.setTitle("BOOK4U habitaciones");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    ContenidoUI contenidoUI = new ContenidoUI(); // Aquí ContenidoUI es un JPanel
		    frame.add(contenidoUI); // Agregar al JFrame
		    frame.pack(); // Ajustar tamaño del JFrame basado en el contenido
		    frame.setVisible(true);
		
	}
	// guarda los datos en la array de cliente para tenerlos en toda la aplicacion
	// cada vez que se abre esta
	public static void setSesion(String correo, String contrasenya) {
		cliente = db.mostrarInfoCliente(correo, contrasenya);
	}
	//devuelve los datos del usuario logeado actualmente
	public static String[] getSesion() {
		return cliente;
	}
}
