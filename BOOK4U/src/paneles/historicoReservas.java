package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import backend.db;

public class historicoReservas extends JPanel {
	{
		String[] cliente = main.main.getSesion();
		setLayout(new BorderLayout());
		setBackground(new Color(173, 216, 230)); // Establecer el color de fondo deseado
		setBounds(100, 100, 900, 1500);

		JLabel label = new JLabel("Historial de compras");
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label, BorderLayout.CENTER);

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
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			String[] reserva = reservas.get(i-1);
			if (reserva.length == 7) {
				textPane.setText("ID: "+reserva[0]+"|Habitacion: "+reserva[1]+"|Precio: "+reserva[3]+"Estado: "+reserva[4]+"|Fecha de entrada: "+reserva[5]+"|Fecha de salida: "+reserva[6]+"|");
			} else {
				textPane.setText("Invalid data");
			}
			textPanel.add(textPane);
		}

		JScrollPane scrollPane = new JScrollPane(textPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(32);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

//		scrollPane.setBounds(100, 100, 900, 1500);
		add(scrollPane);
	}
}
