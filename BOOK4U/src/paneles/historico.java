package paneles;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import main.*;
import backend.db;

public class historico extends JPanel {
	int[] dimensiones = main.getDimensiones();
	String[] cliente = main.getSesion();

	public historico() {
		// establece el display para que sea responsive
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		JTabbedPane tabbedPane = new JTabbedPane();

		setBackground(new Color(173, 216, 230));
		setBounds(0, 0, dimensiones[0], dimensiones[1]);

		// texto indicando que pagina es
		JLabel label = new JLabel("Historial de compras y reservas");
		label.setHorizontalAlignment(JLabel.CENTER);

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0;
		constraints.weighty = 0.1; // 10% of the vertical space
		label.setBackground(new Color(255, 255, 255));
		add(label, constraints);

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

		ArrayList<String[]> reservas = db.historialReservas(Integer.parseInt(cliente[0]), "F", "C", "D");
		if (reservas.size() < 1) {
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
					// intenta cargar la imgen
					try {
						File file = new File("src/assets/imagenes/1.jpg");
						Image image = ImageIO.read(file);
						Image resizedImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

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
					textPane.setText("\rNº Habitacion: " + reserva[1] + ".\rPrecio: " + reserva[3]
							+ " creditos.\rEstado: " + reserva[4] + ".\rFecha de entrada: " + reserva[5]
							+ ".\rFecha de salida: " + reserva[6] + ".");
				} else {
					textPane.setText("Invalid data");
				}
				panelTexto.add(textPane);
				textPanel.add(panelTexto);
			}
		}

		JScrollPane scrollPane = new JScrollPane(textPanel);

		scrollPane.getVerticalScrollBar().setUnitIncrement(32);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

		JPanel panel1 = new JPanel();
		panel1.add(scrollPane);
		tabbedPane.addTab("Reservas", null, panel1, "Reservas");

		// segunda pestanya

		JPanel textPanel2 = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

		ArrayList<String[]> compras = db.historialCompras(Integer.parseInt(cliente[0]));
		if (compras.size() < 1) {
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			textPane.setText("No se han encontrado compras");
			textPanel2.add(textPane);
		} else {
			int numberOfTexts = compras.size();
			for (int i = 1; i <= numberOfTexts; i++) {
				// panel para cargar cada resultado
				JPanel panelTexto = new JPanel();
				// texto con la informacion de la busqueda
				JTextPane textPane = new JTextPane();
				textPane.setEditable(false);
				String[] compra = compras.get(i - 1);
				if (compra.length == 6) {
					// intenta cargar la imgen
					try {
						File file = new File("src/assets/book4u.png");
						Image image = ImageIO.read(file);
						Image resizedImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

						// Create an ImageIcon from the Image
						ImageIcon imageIcon = new ImageIcon(resizedImage);

						// Create a JLabel to display the image
						JLabel imageLabel = new JLabel(imageIcon);

						panelTexto.add(imageLabel);
					} catch (IOException e) {
						JLabel imageLabel = new JLabel("Imagen no encontrada.");
						panelTexto.add(imageLabel);
					}
					textPane.setText("\rCantidad: " + compra[2] + ".");
				} else {
					textPane.setText("Invalid data");
				}
				panelTexto.add(textPane);
				textPanel2.add(panelTexto);
			}
		}

		JScrollPane scrollPane2 = new JScrollPane(textPanel2);

		scrollPane2.getVerticalScrollBar().setUnitIncrement(32);
		scrollPane2.getHorizontalScrollBar().setUnitIncrement(16);

		JPanel panel2 = new JPanel();
		panel2.add(scrollPane2);
		tabbedPane.addTab("EcoBits", null, panel2, "EcoBits");

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 8;
		constraints.weightx = 1.0;
		constraints.weighty = 0.8;
		add(tabbedPane, constraints);

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
}
