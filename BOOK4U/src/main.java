import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import backend.archivo;
import backend.db;

public class main {
	//frame de la aplicacion, se usara este para todo
	private static JFrame frame = new JFrame("Book4U Loading...");
	
	//desde aqui se cmabia de pantalla
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String cliente[] = new String[10];
		cliente = archivo.leerTxt("./src/config/config_usuario.txt", 1);
		try {
	         Image iconImage = ImageIO.read(new File("/assets/book4u.ico")); // falta cambiar la direccion de la imagen
	         frame.setIconImage(iconImage);
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
		
		//carga la pagina principal si el login es correcto
		if (db.comprobarLoginCliente(cliente[0], cliente[1]) == true) {
			cargarRegistro();
			frame.setVisible(true);
			JOptionPane.showMessageDialog(null, "login correcto.");
			//si no se puede hacer login carga la pantalla de login
		} else {
			cargarLogin();
			frame.setVisible(true);
		}
	}
	//funcion para cargar la pantalla de registro
	public static void cargarRegistro() {
		frame.getContentPane().removeAll();
		frame.setTitle("Pantalla de Registro");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setResizable(false);
		Registro panelRegistro = new Registro();
        frame.getContentPane().add(panelRegistro, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
	}
	//funcion para cargar la pantalla de login
	public static void cargarLogin() {
		frame.getContentPane().removeAll();
		frame.setTitle("Pantalla de Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 175);
		frame.setResizable(false);
		LogIn panelLogin = new LogIn();
		frame.getContentPane().add(panelLogin, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
	}

}
