package paneles;

import javax.swing.*;

import main.*;

import java.awt.*;
import java.awt.event.*;

public class LogIn extends JPanel {
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel userLabel = new JLabel("Usuario:");
		this.add(userLabel);
		JTextField userText = new JTextField();
		this.add(userText);

		JLabel passwordLabel = new JLabel("Contraseña:");
		this.add(passwordLabel);
		JPasswordField passwordText = new JPasswordField();
		this.add(passwordText);

		JButton loginButton = new JButton("Iniciar Sesión");
		this.add(loginButton);

		JButton loginRegistroBoton = new JButton("Crear usuario");
		this.add(loginRegistroBoton);

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String correo = userText.getText();
				String contrasenya = new String(passwordText.getPassword());

				if (backend.db.comprobarLoginCliente(correo, contrasenya)) {
					String[] array = new String[2];
					array[0] = correo;
					array[1] = contrasenya;
					main.setSesion(array[0], array[1]);
					Dimension screenSize = main.getDimensionesPantalla();
					main.setDimensiones((int)Math.round(screenSize.height*0.8),(int)Math.round(screenSize.width*0.8));
					loader.cargarPrincipal();
					String linea = backend.archivo.juntarLinea(array);
					backend.archivo.editarTxt("src/config/config_usuario.txt", 0, linea);
					JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
				} else {
					JOptionPane.showMessageDialog(null, "Inicio de sesión fallido");
				}
			}
		});

		loginRegistroBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loader.cargarRegistro();
			}
		});
	}
}
