package paneles;

import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.swing.*;

import backend.archivo;
import backend.db;
import main.*;

public class perfilUsuario extends JPanel {
	public perfilUsuario() {
		int[] dimensiones = main.getDimensiones();
		String[] usuario = main.getSesion();
		main.setSesion(usuario[5], usuario[6]);
		usuario = main.getSesion();
		// Establece el layout para que sea responsive
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		setBackground(new Color(173, 216, 230)); // Establecer el color de fondo deseado

		// Componente para la foto del usuario
		ImageIcon userPhoto = new ImageIcon("src/assets/perfiles/default.png");
		try {
			userPhoto = new ImageIcon("src/assets/perfiles/" + usuario[0] + ".png");
		} catch (Exception e) {
			userPhoto = new ImageIcon("src/assets/perfiles/default.png");
		}
		// Asegúrate de cambiar "path/to/user/photo" a la ruta real de la imagen
		JLabel photoLabel = new JLabel(userPhoto);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 4; // La imagen se extiende sobre 4 filas hasta el botón "Volver"
		constraints.weightx = 0.5;
		constraints.weighty = 1.0; // La imagen ocupa el total de la altura disponible hasta el botón "Volver"
		constraints.insets = new Insets(10, 10, 10, 10); // Margen para la etiqueta
		add(photoLabel, constraints);

		// Panel para los datos del usuario
		JPanel userDataPanel = new JPanel();
		userDataPanel.setLayout(new BoxLayout(userDataPanel, BoxLayout.Y_AXIS));
		// nombre del usuario
		JLabel nombreUsuario = new JLabel("Nombre: " + usuario[1]);
		Font readableFont = new Font("SansSerif", Font.PLAIN, 18);
		nombreUsuario.setFont(readableFont);
		userDataPanel.add(nombreUsuario);
		// creditos del usuario
		JLabel creditosUsuario = new JLabel("EcoBits: " + usuario[7]);
		readableFont = new Font("SansSerif", Font.PLAIN, 18);
		nombreUsuario.setFont(readableFont);
		userDataPanel.add(creditosUsuario);
		userDataPanel.setBackground(new Color(173, 216, 230)); // Establecer el color de fondo igual al del panel
																// principal
		// constraints
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1; // El panel de datos ocupa solo 1 fila
		constraints.weightx = 0.5;
		constraints.weighty = 0.1;
		add(userDataPanel, constraints);

		// boton para cerrar sesion
		JButton logoutButton = new JButton("Cerrar sesión");
		logoutButton.addActionListener(e -> {
			JDialog dialogLogOut = new JDialog(main.getFrame(), "Confirmacion de ciere de sesión!", true);
			dialogLogOut.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			dialogLogOut.setSize(300, 100);

			dialogLogOut.add(new JLabel("Esta seguro de que quiere cerrar sesión?"));

			// Botón para confirmar la reserva
			JButton confirmButton = new JButton("Cerrar sesion");
			confirmButton.addActionListener(ee -> {
				archivo.editarTxt("src/config/config_usuario.txt", 0, "");
				loader.cargarLogin();
				dialogLogOut.dispose();
				JOptionPane.showMessageDialog(dialogLogOut, "Sesion cerrada");
			});
			dialogLogOut.add(confirmButton);

			dialogLogOut.setVisible(true);
		});
		add(logoutButton);
		// Botones como en tu código original
		JButton reservasButton = new JButton("Reservas actuales");
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0.1;
		add(reservasButton, constraints);
		reservasButton.addActionListener(e -> {
			loader.cargarMisReservas();
		});

		JButton historialButton = new JButton("Histórico de reservas y compras");
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.weightx = 0.5;
		constraints.weighty = 0.1;
		add(historialButton, constraints);
		historialButton.addActionListener(e -> {
			loader.cargarHistoricoReservas();
		});

		JButton creditosButton = new JButton("Comprar EcoBits");
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.weightx = 0.5;
		constraints.weighty = 0.1;
		add(creditosButton, constraints);
		creditosButton.addActionListener(e -> {
			loader.cargarCreditos();
		});

		JButton backButton = new JButton("Volver");
		backButton.addActionListener(e -> {
			loader.cargarPrincipal();
		});
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 2; // El botón de volver se extiende sobre 2 columnas
		constraints.weightx = 1.0;
		constraints.weighty = 0.05;
		add(backButton, constraints);
	}
}
