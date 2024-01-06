package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import backend.db;
import components.ImagenPerfil;
import main.*;

public class ContenidoUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private Color backgroundColor;
	private List<paneles.Contenido> contenidos;
	private JPanel contentPanel;

	public ContenidoUI() {
		this.contenidos = new ArrayList<>();
		this.backgroundColor = new Color(173, 216, 230);

		this.contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setBackground(backgroundColor);

		// Colores para los diferentes paneles
		Color baseColor1 = new Color(255, 228, 196);
		Color titleColor = baseColor1;

		Color baseColor2 = new Color(245, 245, 220);
		Color searchColor = baseColor2;
		Color profileColor = baseColor2;

		//// PANEL PRINCIPAL DONDE SE A�ADIRAN EL RESTO DE PANELES////
		panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(backgroundColor);

		//// PANEL SUPERIOR DONDE IRAN LOS PANELES DE BUSQUEDA, PERFIL Y TITULO////
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(backgroundColor);
		topPanel.setBackground(new Color(173, 216, 230));

		//// CREAMOS EL PANEL PARA EL TITULO////
		JPanel tituloPanel = new JPanel(new BorderLayout());
		tituloPanel.setBackground(titleColor);
		Font font = new Font("Arial", Font.PLAIN, 18);
		JLabel titulo = new JLabel("");
		titulo.setHorizontalAlignment(JLabel.CENTER);
		titulo.setFont(font);

		// Cargar y a�adir la imagen al panel tituloPanel
		try {
			Image img = ImageIO.read(new File("src/assets/book4u.png")); // Reemplaza con la ruta a tu imagen
			Image resizedImg = img.getScaledInstance(100, 70, Image.SCALE_SMOOTH); // Ajusta el tamano segun sea
																					// necesario

			ImageIcon imageIcon = new ImageIcon(resizedImg);
			JLabel imageLabel = new JLabel(imageIcon);

			tituloPanel.add(imageLabel, BorderLayout.WEST);
			tituloPanel.add(titulo, BorderLayout.CENTER);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		tituloPanel.setPreferredSize(new Dimension(100, 30));
		topPanel.add(tituloPanel, BorderLayout.WEST);

		JPanel busquedaPanel = new JPanel(new BorderLayout());
		busquedaPanel.setBackground(searchColor);
		JLabel tituloLabel = new JLabel("Habitaciones");
		tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
		tituloLabel.setHorizontalAlignment(JLabel.CENTER);
		tituloLabel.setVerticalAlignment(JLabel.CENTER);
		busquedaPanel.add(tituloLabel, BorderLayout.CENTER);

		topPanel.add(busquedaPanel, BorderLayout.CENTER);

		//// CREAMOS EL BOTON QUE SERVIRA PARA ACCEDER AL PERFIL DE USUARIO////
		ImagenPerfil botonPerfil = new components.ImagenPerfil();
		botonPerfil.setPreferredSize(new Dimension(60, 60));

		botonPerfil.addActionListener(e -> {
			loader.cargarPerfil();
		});

		// A�ADIMOS EL BOTON A UN PANEL PERSONALIZADO//
		JPanel perfilPanelContainer = new JPanel();
		perfilPanelContainer.setBackground(profileColor);
		perfilPanelContainer.add(botonPerfil);
		topPanel.add(perfilPanelContainer, BorderLayout.EAST);

		//// A�ADIMOS EL PANEL SUPERIOR AL PANEL PRINCIPAL////
		panelPrincipal.add(topPanel, BorderLayout.NORTH);

		//// CREAMOS EL PANEL DONDE IRAN ALOJADOS CADA UNO DE LOS CONTENIDOS////
		contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setBackground(backgroundColor);
		contentPanel.setBackground(new Color(173, 216, 230));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(75, 75, 75, 75);

		/// INICIAMOS UN BUCLE PARA QUE CARGUE LA IMAGENES Y DESCRIPCIONES
		/// CORRESPONDIENTES A CADA UNO DE LOS CONTENIDOS///
		/// A SU VEZ SE MARCA COMO SE QUIERE QUE ESTOS CONTENIDOS ESTEN DISTRIBUIDOS///
		List<String[]> habitaciones = db.buscarHabitacion(0);
		for (int i = 0; i < habitaciones.size(); i++) {
			String[] habitacion = habitaciones.get(i);
			String imagePath = "src/assets/imagenes/" + (habitacion[0]) + ".jpg";

			paneles.Contenido contenido = new paneles.Contenido(habitacion, imagePath);

			contenidos.add(contenido);
			gbc.gridy = i / 3;
			contentPanel.add(contenido, gbc);
		}

		/// A�ADIMOS UN SCROLLPANE PARA PODER DESPLAZARSE
		/// MANUALMENTE POR LOS CONTENIDOS QUE HAY EN LA APLICACION
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Ajusta este valor según sea necesario
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
		panelPrincipal.add(scrollPane, BorderLayout.CENTER);

		/// A�ADIMOS EL PANEL PRINCIPAL AL FRAME///
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		add(panelPrincipal, constraints);
	}

}
