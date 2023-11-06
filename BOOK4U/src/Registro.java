import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import backend.db;

public class Registro {

	public static void registro() {
		JFrame frame = new JFrame("Pantalla de Registro");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setResizable(false);

		JPanel panelRegistro = new JPanel();
		panelRegistro.setLayout(new BoxLayout(panelRegistro, BoxLayout.Y_AXIS));
		frame.add(panelRegistro);

		JLabel nombreLabel = new JLabel("Nombre:");
		panelRegistro.add(nombreLabel);
		JTextField nombreField = new JTextField(20);
		panelRegistro.add(nombreField);

		JLabel apellidoLabel = new JLabel("Apellido:");
		panelRegistro.add(apellidoLabel);
		JTextField apellidoField = new JTextField(20);
		panelRegistro.add(apellidoField);

		JLabel telefonoLabel = new JLabel("Telefono:");
		panelRegistro.add(telefonoLabel);
		JTextField telefonoField = new JTextField(20);
		panelRegistro.add(telefonoField);

		JLabel correoLabel = new JLabel("Correo:");
		panelRegistro.add(correoLabel);
		JTextField correoField = new JTextField(20);
		panelRegistro.add(correoField);

		JLabel contrase�aLabel = new JLabel("Contrase�a:");
		panelRegistro.add(contrase�aLabel);
		JPasswordField contrase�aField = new JPasswordField(20);
		panelRegistro.add(contrase�aField);

		JLabel contrase�a2Label = new JLabel("Repita la contrase�a:");
		panelRegistro.add(contrase�a2Label);
		JPasswordField contrase�a2Field = new JPasswordField(20);
		panelRegistro.add(contrase�a2Field);

		JButton registrarButton = new JButton("Registrar");
		panelRegistro.add(registrarButton);

		frame.setVisible(true);

		registrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] passwd1 = contrase�aField.getPassword();
				char[] passwd2 = contrase�a2Field.getPassword();

				String password1 = new String(passwd1);
				String password2 = new String(passwd2);
				if (password1.equals(password2)) {
					String mensaje = "Error";
					mensaje = db.crearCliente(nombreField.getText(), apellidoField.getText(),
							Integer.parseInt(telefonoField.getText()), correoField.getText(), password1);
					if (mensaje.equals("Usuario creado correctamente.")) {
						// aqui abajo lo del testo de abajo

						// cambio de pantalla a la pantalla de login para iniciar sesion
						// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
						JOptionPane.showMessageDialog(null, mensaje);
					}
					else {
						JOptionPane.showMessageDialog(null, mensaje);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Las contrase�as no coinciden.");
				}

			}
		});
	}
}
