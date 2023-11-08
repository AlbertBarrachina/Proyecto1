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

	// desde aqui se cmabia de pantalla
	public static void main(String[] args) {
		String cliente[] = new String[10];
		cliente = archivo.leerTxt("src/config/config_usuario.txt", 1);
		// icono de la aplicacion!
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!no funciona
		// correctamente!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		ImageIcon originalIcon = new ImageIcon("/src/assets/book4u.ico");
		int iconWidth = 32;
		int iconHeight = 32;
		Image resizedIcon = originalIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
		ImageIcon finalIcon = new ImageIcon(resizedIcon);
		frame.setIconImage(finalIcon.getImage());

		// carga la pagina principal si el login es correcto
		if (db.comprobarLoginCliente(cliente[0], cliente[1]) == true) {
			ContenidoUI contenidoUI = new ContenidoUI(frame);
			frame.setSize(1500, 1000);
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
			frame.setSize(300, 175);
			frame.setResizable(false);
			ContenidoUI panelPrincipal = new ContenidoUI(frame);
			frame.revalidate();
			frame.repaint();
		}

}
