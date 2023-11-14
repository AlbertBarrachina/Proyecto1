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
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class historicoReservas extends JPanel{
	public historicoReservas(JFrame frame, JPanel mainPanel) {
		setLayout(new BorderLayout());
		setBackground(new Color(173, 216, 230)); // Establecer el color de fondo deseado

		JLabel label = new JLabel("Historial de compras");
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label, BorderLayout.CENTER);

		JButton backButton = new JButton("Volver");
		backButton.addActionListener(e -> {
			frame.setContentPane(mainPanel);
			frame.revalidate();
		});
		add(backButton, BorderLayout.SOUTH);
		JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        int numberOfTexts = 10; // Set the desired number of text components

        for (int i = 1; i <= numberOfTexts; i++) {
            JTextPane textPane = new JTextPane();
            textPane.setEditable(false);
            textPane.setText("Text " + i);
            
            // Optional: Center-align the text in the JTextPane
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            textPane.getStyledDocument().setParagraphAttributes(0, textPane.getDocument().getLength(), center, false);

            textPanel.add(textPane);
        }

        JScrollPane scrollPane = new JScrollPane(textPanel);
        add(scrollPane);
	}
}
