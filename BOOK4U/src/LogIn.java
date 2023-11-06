
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogIn {
	  public static void main(String[] args) {
	        JFrame frame = new JFrame("Inicio de Sesi�n");
	        frame.setSize(300, 150);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        JPanel panel = new JPanel();
	        frame.add(panel);
//	        panel.setLayout(new GridLayout(3, 2));

	        JLabel userLabel = new JLabel("Usuario:");
	        panel.add(userLabel);
	        JTextField userText = new JTextField();
	        panel.add(userText);

	        JLabel passwordLabel = new JLabel("Contrase�a:");
	        panel.add(passwordLabel);
	        JPasswordField passwordText = new JPasswordField();
	        panel.add(passwordText);

	        JButton loginButton = new JButton("Iniciar Sesi�n");
	        panel.add(loginButton);

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

	        frame.setVisible(true);
	    }
}
