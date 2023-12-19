package paneles;

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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import backend.FiltradoHabitaciones;
import backend.archivo;
import backend.db;
import components.ImagenPerfil;
import main.*;

public class ContenidoUI extends JPanel {
	private JPanel panelPrincipal;
	private Color backgroundColor;
	private List<paneles.Contenido> contenidos;
	private JPanel contentPanel;

	public ContenidoUI() {
		this.contenidos = new ArrayList<>();
		this.backgroundColor = new Color(173, 216, 230);

		int[] dimensiones = main.getDimensiones();
		this.contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setBackground(backgroundColor);

		// Colores para los diferentes paneles
		Color baseColor1 = new Color(255, 228, 196);
		Color menuColor = baseColor1.darker();
		Color titleColor = baseColor1;

		Color baseColor2 = new Color(245, 245, 220);
		Color searchColor = baseColor2;
		Color profileColor = baseColor2;

		//// PANEL PRINCIPAL DONDE SE A�ADIRAN EL RESTO DE PANELES////
		panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(backgroundColor);

		// PANEL LATERAL DONDE IRAN ALGUNO DE LOS APARTADOS DEL MENU
		JPanel menuPanel = new JPanel();
		menuPanel.setPreferredSize(new Dimension(200, 600));
		menuPanel.setBackground(menuColor); // Establecer el color de fondo
		panelPrincipal.add(menuPanel, BorderLayout.WEST);

		menuPanel.setLayout(new GridBagLayout()); // Usando GridBagLayout para un mejor control
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.gridwidth = GridBagConstraints.REMAINDER;
		gbc1.fill = GridBagConstraints.HORIZONTAL;

		// Crear una instancia de la clase FiltradorDeContenido
		FiltradoHabitaciones filtrador = new FiltradoHabitaciones(contenidos, contentPanel); // Aseg�rate de que
																								// contenidos y
																								// contentPanel est�n
																								// definidos

		// Componentes de filtrado
		JComboBox<String> filtroPrecio = new JComboBox<>(new String[] { "1 - 10", "11 - 20", "21 - 30", "31 - 40",
				"41 - 50", "51 - 60", "61 - 70", "71 - 80", "81 - 90", "91 - 99" });
		JCheckBox filtroDescuento = new JCheckBox("Con descuento");
		JComboBox<String> filtroTipoHabitacion = new JComboBox<>(new String[] { "Hotel", "Casa Rural", "ApartHotel" });
		JComboBox<Integer> filtroNumCamas = new JComboBox<>(new Integer[] { 1, 2, 3, 4 });

		// Listeners para los componentes de filtrado

		ActionListener filtroListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrador.filtrarContenidos((String) filtroPrecio.getSelectedItem(), filtroDescuento.isSelected(),
						(String) filtroTipoHabitacion.getSelectedItem(), (Integer) filtroNumCamas.getSelectedItem());
			}
		};

		filtroPrecio.addActionListener(filtroListener);
		filtroDescuento.addActionListener(filtroListener);
		filtroTipoHabitacion.addActionListener(filtroListener);
		filtroNumCamas.addActionListener(filtroListener);

		Dimension maxComponentSize = new Dimension(Integer.MAX_VALUE, filtroPrecio.getPreferredSize().height);
		filtroPrecio.setMaximumSize(maxComponentSize);
		filtroDescuento.setMaximumSize(maxComponentSize);
		filtroTipoHabitacion.setMaximumSize(maxComponentSize);
		filtroNumCamas.setMaximumSize(maxComponentSize);

		// Agregar componentes de filtrado al panel de men�
		menuPanel.add(new JLabel("Filtrar por Precio:"), gbc1);
		menuPanel.add(filtroPrecio, gbc1);
		menuPanel.add(new JLabel("Descuento:"), gbc1);
		menuPanel.add(filtroDescuento, gbc1);
		menuPanel.add(new JLabel("Tipo de Habitacion:"), gbc1);
		menuPanel.add(filtroTipoHabitacion, gbc1);
		menuPanel.add(new JLabel("Numero de Camas:"), gbc1);
		menuPanel.add(filtroNumCamas, gbc1);

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
		List<String[]> habitaciones = db.buscarHabitacion(0, 0, (float) 1.00, "", 0, 0);
		for (int i = 0; i < habitaciones.size(); i++) {
			String[] habitacion = habitaciones.get(i);
			String imagePath = "src/assets/imagenes/" + (habitacion[0]) + ".jpg";

			paneles.Contenido contenido = new paneles.Contenido(habitacion, imagePath);

			contenidos.add(contenido);
			gbc.gridx = i % 4;
			gbc.gridy = i / 4;
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

	private void buscar(String texto) {
		contentPanel.removeAll();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(75, 75, 75, 75);

		int count = 0;
		for (paneles.Contenido contenido : contenidos) {
			if (1 == 1) {
				gbc.gridx = count % 4;
				gbc.gridy = count / 4;
				contentPanel.add(contenido, gbc);
				count++;
			}
		}
		contentPanel.revalidate();
		contentPanel.repaint();
	}

}
