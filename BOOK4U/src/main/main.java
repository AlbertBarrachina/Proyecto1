package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
	private List<paneles.Contenido> contenidos = new ArrayList<>();
	private static String[] cliente = new String[10];
	private static int[]dimensiones = new int[2];
	// desde aqui se cmabia de pantalla
	public static void main(String[] args) {
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
		cliente = archivo.leerTxt("src/config/config_usuario.txt", 1);
		//establece el icono de la aplicacion
		ImageIcon Icon = new ImageIcon("src/assets/book4u.png");
		frame.setIconImage(Icon.getImage());

		// carga la pagina principal si el login es correcto
		if (db.comprobarLoginCliente(cliente[0], cliente[1]) == true) {
			setSesion(cliente[0], cliente[1]);
			ContenidoUI contenidoUI = new ContenidoUI(frame);
			frame.setVisible(true);
			JOptionPane.showMessageDialog(null, "login correcto.");
			// si no se puede hacer login carga la pantalla de login
		} else {
			loader.cargarLogin();
			frame.setVisible(true);
		}
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
	public static void setDimensiones(int alto,int ancho) {
		dimensiones[0] = alto;
		dimensiones[1] = ancho;
	}
	public static int[] getDimensiones() {
		return dimensiones;
	}
	public static JFrame getFrame() {
		return frame;
	}
}
