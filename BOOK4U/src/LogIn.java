
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
				String usuario = userText.getText();
				String contraseña = new String(passwordText.getPassword());

				// Aquí puedes agregar la lógica de autenticación.
				if (usuario.equals("usuario") && contraseña.equals("contraseña")) {
					JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
				} else {
					JOptionPane.showMessageDialog(null, "Inicio de sesión fallido");
				}
			}
		});

		loginRegistroBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.cargarRegistro();
			}
		});
	}
}
