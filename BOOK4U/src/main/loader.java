package main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class loader {
	private static int[] dimensiones;
	private static Dimension screenSize;

	public loader() {
		dimensiones = main.getDimensiones();
		screenSize = main.getDimensionesPantalla();
	}

	// funcion para cargar la pantalla de historial de reservas
	public static void cargarHistoricoReservas() {
		JFrame frame = main.getFrame();
		frame.getContentPane().removeAll();
		frame.setTitle("BOOK4U - Historico reservas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		paneles.historico panelHistorico = new paneles.historico();
		frame.getContentPane().add(panelHistorico, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
	}

	// funcion para cargar la pantalla de reservas actuales
	public static void cargarMisReservas() {
		JFrame frame = main.getFrame();
		frame.getContentPane().removeAll();
		frame.setTitle("BOOK4U - reservas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		paneles.misReservas panelReservas = new paneles.misReservas();
		frame.getContentPane().add(panelReservas, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
	}

	// funcion para cargar la pantalla de registro
	public static void cargarRegistro() {
		screenSize = main.getDimensionesPantalla();
		JFrame frame = main.getFrame();
		frame.getContentPane().removeAll();
		frame.setTitle("BOOK4U - Registro");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize((int) Math.round(screenSize.width * 0.2), (int) Math.round(screenSize.height * 0.35));
		frame.setResizable(false);
		paneles.Registro panelRegistro = new paneles.Registro();
		frame.getContentPane().add(panelRegistro, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
	}

	// funcion para cargar la pantalla de login
	public static void cargarLogin() {
		screenSize = main.getDimensionesPantalla();
		JFrame frame = main.getFrame();
		frame.getContentPane().removeAll();
		frame.setTitle("BOOK4u - Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize((int) Math.round(screenSize.width * 0.15), (int) Math.round(screenSize.height * 0.15));
		frame.setResizable(false);
		paneles.LogIn panelLogin = new paneles.LogIn();
		frame.getContentPane().add(panelLogin, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
	}

	// funcion para cargar la pantalla principal
	public static void cargarPrincipal() {
		JFrame frame = main.getFrame();
		frame.getContentPane().removeAll();
		frame.setTitle("BOOK4U");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setSize((int) Math.round(screenSize.width * 0.8), (int) Math.round(screenSize.height * 0.8));
		paneles.ContenidoUI panelPrincipal = new paneles.ContenidoUI();
		frame.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
	}

	// funcio para cargar el panel del perfil de usuario
	public static void cargarPerfil() {
		JFrame frame = main.getFrame();
		frame.getContentPane().removeAll();
		frame.setTitle("BOOK4U - perfil");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		paneles.perfilUsuario panelPerfil = new paneles.perfilUsuario();
		frame.getContentPane().add(panelPerfil, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
	}

	// funcio para cargar el panel de detalles habitacion
	public static void cargardetallesHabitacion(ImageIcon icono, String[] habitacion) {
		JFrame frame = main.getFrame();
		frame.getContentPane().removeAll();
		frame.setTitle("BOOK4U - habitacion");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		paneles.detallesHabitacion panelPerfil = new paneles.detallesHabitacion(icono, habitacion);
		frame.getContentPane().add(panelPerfil, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
	}

	// funcion para cargar la pantalla principal
	public static void cargarCreditos() {
		JFrame frame = main.getFrame();
		frame.getContentPane().removeAll();
		frame.setTitle("BOOK4U comprar creditos");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		paneles.creditos panelCreditos = new paneles.creditos();
		frame.getContentPane().add(panelCreditos, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
	}
}
