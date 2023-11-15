package paneles;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class perfil_usuario extends JPanel {
	public perfil_usuario() {
		setLayout(new BorderLayout());
		setBackground(new Color(173, 216, 230)); // Establecer el color de fondo deseado

		JLabel label = new JLabel("Perfil del Usuario");
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label, BorderLayout.CENTER);

		JButton backButton = new JButton("Volver");
		backButton.addActionListener(e -> {
			main.main.cargarPrincipal();
		});
		add(backButton, BorderLayout.SOUTH);
		JButton historialButton = new JButton("Historico de reservas");
		backButton.addActionListener(e -> {
			main.main.cargarHistoricoReservas();
		});
		add(historialButton, BorderLayout.EAST);
	}
}
