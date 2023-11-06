
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogIn {

	public static void login() {
		JFrame frame = new JFrame("Inicio de Sesi�n");
		frame.setSize(300, 175);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		JPanel panelLogin = new JPanel();
		panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS));
        frame.add(panelLogin);

		JLabel userLabel = new JLabel("Usuario:");
		panelLogin.add(userLabel);
		JTextField userText = new JTextField();
		panelLogin.add(userText);

		JLabel passwordLabel = new JLabel("Contrase�a:");
		panelLogin.add(passwordLabel);
		JPasswordField passwordText = new JPasswordField();
		panelLogin.add(passwordText);

		JButton loginButton = new JButton("Iniciar Sesi�n");
		panelLogin.add(loginButton);
		
		JButton loginRegistroBoton = new JButton("Crear usuario");
		panelLogin.add(loginRegistroBoton);

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
				//cambio de pantalla a la pantalla de registro
			}
		});

		frame.setVisible(true);
	}
}
