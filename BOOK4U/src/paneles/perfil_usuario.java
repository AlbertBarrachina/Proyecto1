package paneles;

import javax.swing.*;

import main.ContenidoUI;

import java.awt.*;

public class perfil_usuario extends JPanel {
	public perfil_usuario(ContenidoUI contenidoUI, JPanel mainPanel) {
		setLayout(new BorderLayout());
		setBackground(new Color(173, 216, 230)); // Establecer el color de fondo deseado

		JLabel label = new JLabel("Perfil del Usuario");
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label, BorderLayout.CENTER);

		JButton backButton = new JButton("Volver");
		backButton.addActionListener(e -> {
		    JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		    mainFrame.setContentPane(mainPanel);
		    mainFrame.revalidate();
		});

		add(backButton, BorderLayout.SOUTH);
	}
}
