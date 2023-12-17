package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import backend.*;
import paneles.*;
import main.loader;

public class main {
	// frame de la aplicacion, se usara este para todo
	private static JFrame frame = new JFrame("Book4U Loading...");
	private static String[] cliente = new String[10];
	private static int[] dimensiones = new int[2];
	private static Dimension screenSize;
	public static void main(String[] args) {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDimensiones((int)Math.round(screenSize.height*0.8),(int)Math.round(screenSize.width*0.8));
		frame.setBounds((int)Math.round(dimensiones[0]*0.05),(int)Math.round(dimensiones[1]*0.05),dimensiones[0], dimensiones[1]);
		//actualiza las dimensiones cada vez que se modifica el tamaño del frame
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				// Get the new size of the window
				Dimension newSize = e.getComponent().getSize();

				int altura = newSize.height;
				int ancho = newSize.width;
				setDimensiones(altura, ancho);
				updateDimensions(frame.getSize());
			}

			private void updateDimensions(Dimension newSize) {
				dimensiones[0] = (int) newSize.getWidth();
				dimensiones[1] = (int) newSize.getHeight();
			}
		});
		//lee el archivo de config_usuario.txt para ves si puede iniciar sesion con esos dato
		try {
			cliente = archivo.leerTxt("src/config/config_usuario.txt", 1);
			// establece el icono de la aplicacion
		ImageIcon Icon = new ImageIcon("src/assets/book4u.png");
		frame.setIconImage(Icon.getImage());

		// carga la pagina principal si el login es correcto
		if (db.comprobarLoginCliente(cliente[0], cliente[1])) {
			setSesion(cliente[0], cliente[1]);
			loader.cargarPrincipal();
			frame.setExtendedState(frame.MAXIMIZED_BOTH);
			frame.setVisible(true);
			JOptionPane.showMessageDialog(null, "login correcto.");
			// si no se puede hacer login carga la pantalla de login
		} else {
			loader.cargarLogin();
			frame.setVisible(true);
		}
		} catch (Exception e) {
			loader.cargarLogin();
			frame.setVisible(true);
		}
		
	}

	// guarda los datos en la array de cliente para tenerlos en toda la aplicacion
	// cada vez que se abre esta
	public static void setSesion(String correo, String contrasenya) {
		cliente = db.mostrarInfoCliente(correo, contrasenya);
	}

	// devuelve los datos del usuario logeado actualmente
	public static String[] getSesion() {
		return cliente;
	}

	public static void setDimensiones(int alto, int ancho) {
		dimensiones[0] = ancho;
		dimensiones[1] = alto;
	}

	public static int[] getDimensiones() {
		return dimensiones;
	}
	public static Dimension getDimensionesPantalla() {
		return screenSize;
	}

	public static JFrame getFrame() {
		return frame;
	}
}
