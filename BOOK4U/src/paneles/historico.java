package paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;

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
					List<String[]> habitaciones = db.buscarHabitacion(Integer.parseInt(reserva[1]));
					String[] habitacion = habitaciones.get(0);
					String[] empresa = db.InfoEmpresa(Integer.parseInt(habitacion[1]));
					// intenta cargar la imgen
					try {
						File file = new File("src/assets/imagenes/" + habitacion[0] + ".jpg");
						Image image = ImageIO.read(file);
						Image resizedImage = image.getScaledInstance(170, 170, Image.SCALE_SMOOTH);

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
					String fecha1 = "Cargando...";
					String fecha2 = "Cargando...";
					SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
					SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date;
					try {
						date = inputFormat.parse(reserva[5]);
						fecha1 = outputFormat.format(date);
					} catch (ParseException e1) {
					}
					try {
						date = inputFormat.parse(reserva[5]);
						fecha2 = outputFormat.format(date);
					} catch (ParseException e1) {
					}
					textPane.setText("\rNombre: " + habitacion[7] + "\rDireccion: " + empresa[2] + "\rPrecio: "
							+ reserva[3] + " EcoBits\rEstado: " + reserva[4] + "\rFecha de entrada: " + fecha1
							+ "\rFecha de salida: " + fecha2);
				} else {
					textPane.setText("Invalid data");
				}
				Font readableFont = new Font("SansSerif", Font.PLAIN, 18);
				textPane.setFont(readableFont);
				panelTexto.add(textPane);
				textPanel.add(panelTexto);
			}
		}

		JScrollPane scrollPane = new JScrollPane(textPanel);

		scrollPane.getVerticalScrollBar().setUnitIncrement(8);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
//		scrollPane.setVisible(true);
//		scrollPane.getViewport().setViewPosition(new Point(0, 0));
		tabbedPane.addTab("Reservas", null, scrollPane, "Reservas");

		// segunda pestanya

		JPanel textPanel2 = new JPanel();
		textPanel2.setLayout(new BoxLayout(textPanel2, BoxLayout.Y_AXIS));

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
						File file = new File("src/assets/EcoBit.png");
						Image image = ImageIO.read(file);
						Image resizedImage = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);

						// Create an ImageIcon from the Image
						ImageIcon imageIcon = new ImageIcon(resizedImage);

						// Create a JLabel to display the image
						JLabel imageLabel = new JLabel(imageIcon);

						panelTexto.add(imageLabel);
					} catch (IOException e) {
						JLabel imageLabel = new JLabel("Imagen no encontrada.");
						panelTexto.add(imageLabel);
					}
					String fecha = "Cargando...";
					SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
					SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date;
					try {
						date = inputFormat.parse(compra[5]);
						fecha = outputFormat.format(date);
					} catch (ParseException e1) {
					}
					textPane.setText("\rCantidad: " + compra[2] + "\rPrecio de compra: " + compra[3]
							+ " €\rFecha compra" + fecha + "\r");
				} else {
					textPane.setText("Invalid data");
				}
				Font readableFont = new Font("SansSerif", Font.PLAIN, 18);
				textPane.setFont(readableFont);
				panelTexto.add(textPane);
				textPanel2.add(panelTexto);
			}
		}

		JScrollPane scrollPane2 = new JScrollPane(textPanel2);

		JScrollBar verticalScrollBar = scrollPane2.getVerticalScrollBar();

		// Set the scrollbar's value to the top
		verticalScrollBar.setValue(-1000);
		scrollPane2.getVerticalScrollBar().setUnitIncrement(8);
		scrollPane2.getHorizontalScrollBar().setUnitIncrement(16);

		tabbedPane.addTab("EcoBits", null, scrollPane2, "EcoBits");

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
