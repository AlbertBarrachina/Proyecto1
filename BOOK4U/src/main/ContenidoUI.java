package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import components.ImagenPerfil;

public class ContenidoUI extends JPanel{
	private JPanel panelPrincipal;
	private Color backgroundColor;
	private List<paneles.Contenido> contenidos;
	private JFrame frame;
	private JPanel contentPanel;
	private JPanel perfilPanel;

	public ContenidoUI(JFrame frame) {
		this.frame = frame;
		this.contenidos = new ArrayList<>();
		this.backgroundColor = new Color(173, 216, 230);
		construirUI();
	}

	private void construirUI() {

		this.contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setBackground(backgroundColor);

		//// STRING CON LOS NOMBRES DE LAS HABITACIONES/CASAS RURALES////
		String[] descriptions1 = { "Suite Deluxe", "Habitacion con Vista al Mar", "Cabaña Acogedora", "Loft Urbano",
				"Habitacion Familiar", "Bungalow de Montaña", "Habitacion Tematica Vintage",
				"Villa con Piscina Privada", "Habitacion Loft", "Suite Romantica", "Habitacion con Jacuzzi",
				"Cabaï¿½a junto al Rio", "Habitacion Eco-Friendly", "Alojamiento en atico", "Habitacion de Lujo",
				"Suite Junior", "Habitacion con Terraza", "CabaÃ±a de Campo", "Habitacion con Chimenea",
				"Loft Industrial", };

		//// STRING CON LOS PRECIOS DE LAS HABITACIONES/CASAS RURALES////
		String[] descriptions2 = { "80 creditos", "95 creditos", "70 creditos", "85 creditos", "90 creditos",
				"75 creditos", "80 creditos", "99 creditos", "85 creditos", "95 creditos", "90 creditos", "80 creditos",
				"70 creditos", "85 creditos", "99 creditos", "75 creditos", "80 creditos", "70 creditos", "90 creditos",
				"85 creditos", };

		// Colores para los diferentes paneles
		Color baseColor1 = new Color(255, 228, 196);
		Color menuColor = baseColor1.darker();
		Color titleColor = baseColor1;

		Color baseColor2 = new Color(245, 245, 220);
		Color searchColor = baseColor2;
		Color profileColor = baseColor2;

		//// PANEL PRINCIPAL DONDE SE Aï¿½ADIRAN EL RESTO DE PANELES////
		panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(backgroundColor);

		// PANEL LATERAL DONDE IRAN ALGUNO DE LOS APARTADOS DEL MENU
		JPanel menuPanel = new JPanel();
		menuPanel.setPreferredSize(new Dimension(100, 600));
		menuPanel.setBackground(menuColor); // Establecer el color de fondo
		panelPrincipal.add(menuPanel, BorderLayout.WEST);

		//// PANEL SUPERIOR DONDE IRAN LOS PANELES DE BUSQUEDA, PERFIL Y TITULO////
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(backgroundColor);
		topPanel.setBackground(new Color(173, 216, 230));

		//// CREAMOS EL PANEL PARA EL TITULO////
		JPanel tituloPanel = new JPanel(new BorderLayout());
		tituloPanel.setBackground(titleColor);
		Font font = new Font("Arial", Font.PLAIN, 18);
		JLabel titulo = new JLabel("TITULO");
		titulo.setHorizontalAlignment(JLabel.CENTER);
		titulo.setFont(font);
		tituloPanel.add(titulo, BorderLayout.CENTER);
		tituloPanel.setPreferredSize(new Dimension(100, 30));
		topPanel.add(tituloPanel, BorderLayout.WEST);

		//// CREAMOS EL PANEL PARA EL CAMPO DE BUSQUEDA////
		JTextField busqueda = new JTextField("");
		busqueda.setFont(font);
		busqueda.setHorizontalAlignment(JTextField.CENTER);

		// CREAMOS EL BOTON DE BUSQUEDA
		components.CircularButton buscarButton = new components.CircularButton("", null);
		buscarButton.setPreferredSize(new Dimension(30, 30));
		buscarButton.setContentAreaFilled(false);
		buscarButton.setBorderPainted(true);
		buscarButton.setFocusPainted(false);
		buscarButton.setOpaque(false);

		try {
			Image img = ImageIO.read(new File("src/assets/imagenes/lupa.png")); // Reemplaza con la ruta a tu imagen
			Image resizedImg = img.getScaledInstance(buscarButton.getPreferredSize().width,
					buscarButton.getPreferredSize().height, Image.SCALE_SMOOTH);
			buscarButton.setIcon(new ImageIcon(resizedImg));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		buscarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar(busqueda.getText().toLowerCase());
			}
		});

		JPanel busquedaPanel = new JPanel(new BorderLayout());
		busquedaPanel.setBackground(searchColor);
		busquedaPanel.add(busqueda, BorderLayout.CENTER);
		busquedaPanel.add(buscarButton, BorderLayout.EAST);

		topPanel.add(busquedaPanel, BorderLayout.CENTER);

		//// CREAMOS EL BOTON QUE SERVIRA PARA ACCEDER AL PERFIL DE USUARIO////
		ImagenPerfil botonPerfil = new components.ImagenPerfil();
		botonPerfil.setPreferredSize(new Dimension(60, 60));
		
		botonPerfil.addActionListener(e -> {
			main.cargarHistoricoReservas();
		});

		// Aï¿½ADIMOS EL BOTON A UN PANEL PERSONALIZADO//
		JPanel perfilPanelContainer = new JPanel();
		perfilPanelContainer.setBackground(profileColor);
		perfilPanelContainer.add(botonPerfil);
		topPanel.add(perfilPanelContainer, BorderLayout.EAST);

		//// Aï¿½ADIMOS EL PANEL SUPERIOR AL PANEL PRINCIPAL////
		panelPrincipal.add(topPanel, BorderLayout.NORTH);

		//// CREAMOS EL PANEL DONDE IRAN ALOJADOS CADA UNO DE LOS CONTENIDOS////
		contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setBackground(backgroundColor);
		contentPanel.setBackground(new Color(173, 216, 230));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(75, 75, 75, 75);

		//// MARCAMOS CUANTOS CONTENIDOS QUEREMOS QUE TENGA EL PANEL////
		int numContents = 20;

		/// INICIAMOS UN BUCLE PARA QUE CARGUE LA IMAGENES Y DESCRIPCIONES
		/// CORRESPONDIENTES A CADA UNO DE LOS CONTENIDOS///
		/// A SU VEZ SE MARCA COMO SE QUIERE QUE ESTOS CONTENIDOS ESTEN DISTRIBUIDOS///
		for (int i = 0; i < numContents; i++) {
			String imagePath = "src/assets/imagenes/" + (i + 1) + ".jpg";
			String description1 = descriptions1[i];
			String description2 = descriptions2[i];
			paneles.Contenido contenido = new paneles.Contenido(imagePath, description1, description2, frame,
					panelPrincipal);

			contenidos.add(contenido);
			gbc.gridx = i % 4;
			gbc.gridy = i / 4;
			contentPanel.add(contenido, gbc);
		}

		/// Aï¿½ADIMOS UN SCROLLPANE PARA PODER DESPLAZARSE
		/// MANUALMENTE POR LOS CONTENIDOS QUE HAY EN LA APLICACION
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Ajusta este valor segÃºn sea necesario
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
		panelPrincipal.add(scrollPane, BorderLayout.CENTER);

		/// Aï¿½ADIMOS EL PANEL PRINCIPAL AL FRAME///
		frame.add(panelPrincipal);
		frame.setTitle("BOOK4U");
		frame.setSize(900, 450);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void buscar(String texto) {
		contentPanel.removeAll();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(75, 75, 75, 75);

		int count = 0;
		for (paneles.Contenido contenido : contenidos) {
			if (contenido.getDescription1().toLowerCase().contains(texto)
					|| contenido.getDescription2().toLowerCase().contains(texto)) {
				gbc.gridx = count % 4;
				gbc.gridy = count / 4;
				contentPanel.add(contenido, gbc);
				count++;
			}
		}
		contentPanel.revalidate();
		contentPanel.repaint();
	}

	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}

}
