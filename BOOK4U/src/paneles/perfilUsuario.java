package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.*;

public class perfilUsuario extends JPanel {
	public perfilUsuario() {
		// establece el display para que sea responsive
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		setBackground(new Color(173, 216, 230)); // Establecer el color de fondo deseado

		JLabel label = new JLabel("Perfil del Usuario");
		label.setHorizontalAlignment(JLabel.CENTER);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0;
		constraints.weighty = 0.05; // 10% of the vertical space
		add(label, constraints);

		JButton historialButton = new JButton("Historico de reservas");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0.0005;
		add(historialButton, constraints);
		historialButton.addActionListener(e -> {
			loader.cargarHistoricoReservas();
		});
		
		JButton creditosButton = new JButton("Comprar Creditos");
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0.0005;
		add(creditosButton, constraints);
		creditosButton.addActionListener(e -> {
			loader.cargarCreditos();
		});
		
		JButton backButton = new JButton("Volver");
		backButton.addActionListener(e -> {
			loader.cargarPrincipal();
		});
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.weightx = 1.0;
		constraints.weighty = 0.0005;
		add(backButton, constraints);

		
	}
}
