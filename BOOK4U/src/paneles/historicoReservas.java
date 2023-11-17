package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.print.attribute.AttributeSet;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import backend.db;

public class historicoReservas extends JPanel {
	{
		int[] dimensiones = main.main.getdimensiones();
		String[] cliente = main.main.getSesion();
		setLayout(new BorderLayout());
		setBackground(new Color(173, 216, 230));
		setBounds(100, 100, dimensiones[0], dimensiones[1]);
		
//		JLabel label = new JLabel("Historial de compras");
//		label.setBounds(0,0,1920,500);
//		label.setBackground(new Color(255,255,255));
//		add(label);

		JButton backButton = new JButton("Volver");
		backButton.addActionListener(e -> {
			main.main.cargarPrincipal();
		});
		add(backButton, BorderLayout.SOUTH);
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

		ArrayList<String[]> reservas = db.historialReservas(Integer.parseInt(cliente[0]));
		int numberOfTexts = reservas.size();
		for (int i = 1; i <= numberOfTexts; i++) {
			// panel para cargar cada resultado
			JPanel panelTexto = new JPanel();
			// texto con la informacion de la busqueda
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			String[] reserva = reservas.get(i - 1);
			if (reserva.length == 7) {
				//intenta cargar la imgen
				try {
					File file = new File("src/assets/imagenes/1.jpg");
					Image image = ImageIO.read(file);
					Image resizedImage = image.getScaledInstance(100, 100,
							Image.SCALE_SMOOTH);

					// Create an ImageIcon from the Image
					ImageIcon imageIcon = new ImageIcon(resizedImage);
					
					// Create a JLabel to display the image
					JLabel imageLabel = new JLabel(imageIcon);

					panelTexto.add(imageLabel);
				} catch (IOException e) {
					JLabel imageLabel = new JLabel("Imagen no encontrada.");
					panelTexto.add(imageLabel);
				}
				if(reserva[4].equals("F")) {
					reserva[4]="Finalizada";
				}else if(reserva[4].equals("P")) {
					reserva[4]="Pagada";
				}else if(reserva[4].equals("C")) {
					reserva[4]="Cancelada";
				}else if(reserva[4].equals("D")) {
					reserva[4]="Denegada";
				}
				textPane.setText("\rNº Habitacion: " + reserva[1] + ".\rPrecio: " + reserva[3]
						+ " creditos.\rEstado: " + reserva[4] + ".\rFecha de entrada: " + reserva[5] + ".\rFecha de salida: "
						+ reserva[6]+".");
			} else {
				textPane.setText("Invalid data");
			}
			panelTexto.add(textPane);
			textPanel.add(panelTexto);
		}

		JScrollPane scrollPane = new JScrollPane(textPanel);
		
		scrollPane.getVerticalScrollBar().setUnitIncrement(32);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

//		scrollPane.setBounds(0, 0, 3000, 2000);
		add(scrollPane);
	}
}
