package paneles;

import javax.swing.*;
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
					//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					//falta hacer que guarde los datos depndiendo de que se ponga en los campos
					//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					backend.archivo.editarTxt("src/config/config_usuario.txt", 0, "perez@gmail.com;cowontraseña");
					JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
				} else {
					JOptionPane.showMessageDialog(null, "Inicio de sesión fallido");
				}
			}
		});

		loginRegistroBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.main.cargarRegistro();
			}
		});
	}
}
