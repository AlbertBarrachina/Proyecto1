
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

		JLabel passwordLabel = new JLabel("Contrase�a:");
		this.add(passwordLabel);
		JPasswordField passwordText = new JPasswordField();
		this.add(passwordText);

		JButton loginButton = new JButton("Iniciar Sesi�n");
		this.add(loginButton);

		JButton loginRegistroBoton = new JButton("Crear usuario");
		this.add(loginRegistroBoton);

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = userText.getText();
				String contrase�a = new String(passwordText.getPassword());

				// Aqu� puedes agregar la l�gica de autenticaci�n.
				if (usuario.equals("usuario") && contrase�a.equals("contrase�a")) {
					JOptionPane.showMessageDialog(null, "Inicio de sesi�n exitoso");
				} else {
					JOptionPane.showMessageDialog(null, "Inicio de sesi�n fallido");
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
