package paneles;

import java.awt.BorderLayout;
import java.awt.Color;

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

public class historicoReservas extends JPanel {
	{
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

		int numberOfTexts = 100;
//		String[] reservas = ;
		for (int i = 1; i <= numberOfTexts; i++) {
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			textPane.setText("");

			// Optional: Center-align the text in the JTextPane
			SimpleAttributeSet center = new SimpleAttributeSet();
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			textPane.getStyledDocument().setParagraphAttributes(0, textPane.getDocument().getLength(), center, false);

			textPanel.add(textPane);
		}

		JScrollPane scrollPane = new JScrollPane(textPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(32);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
		
//		scrollPane.setBounds(100, 100, 900, 1500);
		add(scrollPane);
	}
}
