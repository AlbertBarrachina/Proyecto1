
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogIn {
	  public static void main(String[] args) {
	        JFrame frame = new JFrame("Inicio de Sesión");
	        frame.setSize(300, 150);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        JPanel panel = new JPanel();
	        frame.add(panel);
//	        panel.setLayout(new GridLayout(3, 2));

	        JLabel userLabel = new JLabel("Usuario:");
	        panel.add(userLabel);
	        JTextField userText = new JTextField();
	        panel.add(userText);

	        JLabel passwordLabel = new JLabel("Contraseña:");
	        panel.add(passwordLabel);
	        JPasswordField passwordText = new JPasswordField();
	        panel.add(passwordText);

	        JButton loginButton = new JButton("Iniciar Sesión");
	        panel.add(loginButton);

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

	        frame.setVisible(true);
	    }
}
