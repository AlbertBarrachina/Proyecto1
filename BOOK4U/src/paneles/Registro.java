package paneles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import main.*;
import backend.db;

public class Registro extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

		JLabel contrasenaLabel = new JLabel("Contrase�a:");
		add(contrasenaLabel);
		JPasswordField contrasenaField = new JPasswordField(20);
		add(contrasenaField);

		JLabel contrasena2Label = new JLabel("Repita la contrase�a:");
		add(contrasena2Label);
		JPasswordField contrasena2Field = new JPasswordField(20);
		add(contrasena2Field);

		JButton registrarButton = new JButton("Registrar");
		add(registrarButton);

		registrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] passwd1 = contrasenaField.getPassword();
				char[] passwd2 = contrasena2Field.getPassword();

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
								String[]usuario= db.mostrarInfoCliente(correoField.getText(), password1);
								backend.archivo.copiarArchivo(usuario[0],"src/assets/perfiles/","default","src/assets/perfiles/");
								loader.cargarLogin();
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
