package paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import backend.*;
import main.*;

public class misReservas extends JPanel {
	int[] dimensiones = main.getDimensiones();
	String[] cliente = main.getSesion();

	public misReservas() {
		// establece el display para que sea responsive
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;

		setBackground(new Color(173, 216, 230));
		setBounds(0, 0, dimensiones[0], dimensiones[1]);

		// texto indicando que pagina es
		JLabel label = new JLabel("Reservas actuales");
		label.setHorizontalAlignment(JLabel.CENTER);

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0;
		constraints.weighty = 0.1; // 10% of the vertical space
		label.setBackground(new Color(255, 255, 255));
		add(label, constraints);

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		ArrayList<String[]> reservas = db.historialReservas(Integer.parseInt(cliente[0]), "P", "P", "P");
		// areglar!! no comprueba si hay habitaciones correctamente
		if (reservas.isEmpty()) {
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			textPane.setText("No se han encontrado reservas");
			textPanel.add(textPane);
		} else {

			int numberOfTexts = reservas.size();
			for (int i = 1; i <= numberOfTexts; i++) {
				// panel para cargar cada resultado
				JPanel panelTexto = new JPanel();
				// texto con la informacion de la busqueda
				JTextPane textPane = new JTextPane();
				textPane.setEditable(false);
				String[] reserva = reservas.get(i - 1);
				if (reserva.length == 7) {
					List<String[]> habitaciones = db.buscarHabitacion(0, 0, (float) 0.00, "", 0,
							Integer.parseInt(reserva[1]));
					String[] habitacion = habitaciones.get(0);
					String[] empresa = db.InfoEmpresa(Integer.parseInt(habitacion[1]));
					// intenta cargar la imgen
					try {
						File file = new File("src/assets/imagenes/" + habitacion[0] + ".jpg");
						Image image = ImageIO.read(file);
						Image resizedImage = image.getScaledInstance(190, 190, Image.SCALE_SMOOTH);

						// Create an ImageIcon from the Image
						ImageIcon imageIcon = new ImageIcon(resizedImage);

						// Create a JLabel to display the image
						JLabel imageLabel = new JLabel(imageIcon);

						panelTexto.add(imageLabel);
					} catch (IOException e) {
						JLabel imageLabel = new JLabel("Imagen no encontrada.");
						panelTexto.add(imageLabel);
					}
					// intenta traducir el estado en una palabra, si no puede indica que no se pudo
					// cargar
					try {
						if (reserva[4].equals("F")) {
							reserva[4] = "Finalizada";
						} else if (reserva[4].equals("P")) {
							reserva[4] = "Pagada";
						} else if (reserva[4].equals("C")) {
							reserva[4] = "Cancelada";
						} else if (reserva[4].equals("D")) {
							reserva[4] = "Denegada";
						}
					} catch (Exception e) {
						reserva[4] = "No se pudo cargar.";
					}
					textPane.setText("\rNombre: " + habitacion[7] + "\rDescripcion: " + habitacion[8] + "\rDireccion: "
							+ empresa[2] + "\rPrecio: " + reserva[3] + " EcoBits\rEstado: " + reserva[4]
							+ "\rFecha de entrada: " + reserva[5] + "\rFecha de salida: " + reserva[6] + " .");
				} else {
					textPane.setText("Invalid data");
				}
				panelTexto.add(textPane);
				JButton editarButton = new JButton("Editar reserva");
				editarButton.addActionListener(e -> {
					VentanaEditar(reserva);
				});
				Font readableFont = new Font("SansSerif", Font.PLAIN, 20);
				textPane.setFont(readableFont);
				panelTexto.add(editarButton);
				textPanel.add(panelTexto);
			}
		}

		JScrollPane scrollPane = new JScrollPane(textPanel);

		scrollPane.getVerticalScrollBar().setUnitIncrement(32);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 8; // 9/10 of the vertical space
		constraints.weightx = 1.0;
		constraints.weighty = 0.8; // 90% of the vertical space
		add(scrollPane, constraints);

		JButton backButton = new JButton("Volver");
		backButton.addActionListener(e -> {
			loader.cargarPerfil();
		});
		constraints.gridx = 0;
		constraints.gridy = 10;
		constraints.weightx = 1.0;
		constraints.weighty = 0.005; // 0.5% of the vertical space
		add(backButton, constraints);
	}

	private void VentanaEditar(String[] reserva) {
		List<String[]> habitaciones = db.buscarHabitacion(0, 0, (float) 0.00, "", 0, Integer.parseInt(reserva[1]));
		String[] habitacion = new String[9];
		if (!habitaciones.isEmpty()) {
			habitacion = Arrays.copyOf(habitaciones.get(0), 9);
		}
		JDialog dialogReserva = new JDialog(main.getFrame(), "Detalles de la Reserva", true);
		dialogReserva.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialogReserva.setLayout(new GridLayout(0, 1));
		dialogReserva.setSize(400, 300);

		// Agrega etiquetas y campos para los detalles de la reserva
		dialogReserva.add(new JLabel("Nombre de la habitación: " + habitacion[7]));
		dialogReserva.add(new JLabel("Precio: " + reserva[3]));
		dialogReserva.add(new JLabel("Descripción: " + habitacion[8]));

		// Botón para confirmar la reserva
		JButton confirmButton = new JButton("Cancelar reserva");
		confirmButton.addActionListener(e -> {
			if (db.editarInfoReserva(Integer.parseInt(reserva[0]))) {
				JOptionPane.showMessageDialog(dialogReserva, "Reserva Cancelada con exito.");
			} else {
				JOptionPane.showMessageDialog(dialogReserva, "ERROR");
			}
		});
		dialogReserva.add(confirmButton);
		dialogReserva.setVisible(true);
	}
}
