package paneles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import backend.db;

public class Registro extends JPanel {
	{

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel nombreLabel = new JLabel("Nombre:");
		add(nombreLabel);
		JTextField nombreField = new JTextField(20);
		add(nombreField);

		JLabel apellidoLabel = new JLabel("Apellido:");
		add(apellidoLabel);
		JTextField apellidoField = new JTextField(20);
		add(apellidoField);

		JLabel telefonoLabel = new JLabel("Telefono:");
		add(telefonoLabel);
		JTextField telefonoField = new JTextField(20);
		add(telefonoField);

		JLabel correoLabel = new JLabel("Correo:");
		add(correoLabel);
		JTextField correoField = new JTextField(20);
		add(correoField);

		JLabel contrase�aLabel = new JLabel("Contrase�a:");
		add(contrase�aLabel);
		JPasswordField contrase�aField = new JPasswordField(20);
		add(contrase�aField);

		JLabel contrase�a2Label = new JLabel("Repita la contrase�a:");
		add(contrase�a2Label);
		JPasswordField contrase�a2Field = new JPasswordField(20);
		add(contrase�a2Field);

		JButton registrarButton = new JButton("Registrar");
		add(registrarButton);

		registrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] passwd1 = contrase�aField.getPassword();
				char[] passwd2 = contrase�a2Field.getPassword();

				String password1 = new String(passwd1);
				String password2 = new String(passwd2);
				if (password1.equals(password2)) {
					String mensaje = "Error";

					if (telefonoField.getText().length() == 9) {
						mensaje = db.comprobarCorreoTelefonoCliente(Integer.parseInt(telefonoField.getText()),
								correoField.getText());
						if (mensaje.equals(".")) {
							mensaje = db.crearCliente(nombreField.getText(), apellidoField.getText(),
									Integer.parseInt(telefonoField.getText()), correoField.getText(), password1);
							if (mensaje.equals("Usuario creado correctamente.")) {
								main.main.cargarLogin();
								JOptionPane.showMessageDialog(null, mensaje);
							} else {
								JOptionPane.showMessageDialog(null, mensaje);
							}
						} else {
							JOptionPane.showMessageDialog(null, mensaje);
						}
					} else {
						mensaje = "Introduzca un telefono valido";
						JOptionPane.showMessageDialog(null, mensaje);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Las contrase�as no coinciden.");
				}

			}
		});
	}
}
