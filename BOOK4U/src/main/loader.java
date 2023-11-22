package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class loader {
	// funcion para cargar la pantalla de historial de reservas
		public static void cargarHistoricoReservas() {
			JFrame frame = main.getFrame();
			frame.getContentPane().removeAll();
			frame.setTitle("BOOK4U reservas");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			paneles.historicoReservas panelHistorico = new paneles.historicoReservas();
			frame.getContentPane().add(panelHistorico, BorderLayout.CENTER);
			frame.revalidate();
			frame.repaint();
		}
		
		// funcion para cargar la pantalla de registro
		public static void cargarRegistro() {
			JFrame frame = main.getFrame();
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
			JFrame frame = main.getFrame();
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
			JFrame frame = main.getFrame();
			frame.getContentPane().removeAll();
			frame.setTitle("BOOK4U");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ContenidoUI panelPrincipal = new ContenidoUI(frame);
			frame.revalidate();
			frame.repaint();
		}
		//funcio para cargar el panel del perfil de usuario
		public static void cargarPerfil() {
			JFrame frame = main.getFrame();
			frame.getContentPane().removeAll();
			frame.setTitle("BOOK4U perfil");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			paneles.perfil_usuario panelPerfil = new paneles.perfil_usuario();
			frame.getContentPane().add(panelPerfil, BorderLayout.CENTER);
			frame.revalidate();
			frame.repaint();
		}
}
