package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;

import backend.db;
import main.loader;
import main.main;

public class detallesHabitacion extends JPanel {
	int[] dimensiones = main.getDimensiones();
	String[] cliente = main.getSesion();
	int id_habitacion = 1;
	private JLabel imageLabel;
	private JPanel imagePanel, detailsPanel, reserveButtonPanel, combinedPanel;
	private JButton backButton, reserveButton;
	private String nombreHabitacion;
	private String precioHabitacion;
	private String descripcionHabitacion;
	private JDateChooser dateChooserInicio;
	private JDateChooser dateChooserFinal;

	public detallesHabitacion(ImageIcon imageIcon, String[] habitacion) {
		this.nombreHabitacion = nombreHabitacion;
		this.precioHabitacion = precioHabitacion;
		this.descripcionHabitacion = descripcionHabitacion;

		setLayout(new BorderLayout(10, 10));
		setBackground(new Color(173, 216, 230));

		// Panel de imagen
		imagePanel = new JPanel(new BorderLayout());
		imagePanel.setBorder(BorderFactory.createTitledBorder(""));
		imagePanel.setPreferredSize(new Dimension(600, 400));
		imageLabel = new JLabel();
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		imagePanel.add(imageLabel, BorderLayout.CENTER);
		add(imagePanel, BorderLayout.WEST);

		// Panel combinado para detalles y botón
		combinedPanel = new JPanel();
		combinedPanel.setLayout(new BoxLayout(combinedPanel, BoxLayout.Y_AXIS));

		// Panel de detalles con fuente más grande y mayor tamaño
		detailsPanel = new JPanel();
		detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
		detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		detailsPanel.setPreferredSize(new Dimension(400, 300));

		JLabel nameLabel = new JLabel(nombreHabitacion);
		nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
		nameLabel.setPreferredSize(new Dimension(500, 200));
		detailsPanel.add(nameLabel);

		JLabel priceLabel = new JLabel(precioHabitacion);
		priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
		priceLabel.setPreferredSize(new Dimension(500, 200));
		detailsPanel.add(priceLabel);

		JLabel descriptionLabel = new JLabel("Descripción: " + descripcionHabitacion);
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 16));
		descriptionLabel.setPreferredSize(new Dimension(500, 200));
		detailsPanel.add(descriptionLabel);

		///////////////////////////////////////////////////////////////////////////////////////////////////
		/////// ///////
		/////// UTILIZANDO LA LIBRERIA DE JCALENDAR AÑADIMOS EL CAMPO DE FECHA_INICIO Y
		/////////////////////////////////////////////////////////////////////////////////////////////////// FECHA_FINAL
		/////////////////////////////////////////////////////////////////////////////////////////////////// ///////
		/////// ///////
		///////////////////////////////////////////////////////////////////////////////////////////////////
		dateChooserInicio = new JDateChooser();
		dateChooserFinal = new JDateChooser();

		dateChooserInicio = new JDateChooser();
		dateChooserInicio.getJCalendar().setMinSelectableDate(new Date());
		((JTextField) dateChooserInicio.getDateEditor().getUiComponent()).setEditable(false);

		dateChooserFinal = new JDateChooser();
		dateChooserFinal.getJCalendar().setMinSelectableDate(new Date());
		((JTextField) dateChooserFinal.getDateEditor().getUiComponent()).setEditable(false);

		detailsPanel.add(new JLabel("Fecha de Inicio:"));
		detailsPanel.add(dateChooserInicio);

		detailsPanel.add(new JLabel("Fecha Final:"));
		detailsPanel.add(dateChooserFinal);

		combinedPanel.add(detailsPanel);
		combinedPanel.add(Box.createVerticalGlue());

		// Panel para el botón de reservar
		reserveButtonPanel = new JPanel();
		reserveButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		reserveButton = new JButton("Reservar");
		reserveButton.setPreferredSize(new Dimension(200, 80));
		reserveButton.addActionListener(e -> {
			VentanaReserva();
		});
		reserveButtonPanel.add(reserveButton);
		combinedPanel.add(reserveButtonPanel);

		// Añadir el panel combinado al panel principal
		add(combinedPanel, BorderLayout.CENTER);

		// Botón de regreso
		backButton = new JButton("Volver");
		backButton.setPreferredSize(new Dimension(400, 55));
		backButton.addActionListener(e -> {
			loader.cargarPrincipal();
		});
		add(backButton, BorderLayout.SOUTH);

		// Actualización de la imagen
		SwingUtilities.invokeLater(() -> updateImageIcon(imageIcon));
	}

	private void VentanaReserva() {
		JDialog dialogReserva = new JDialog(main.getFrame(), "Detalles de la Reserva", true);
		dialogReserva.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialogReserva.setLayout(new GridLayout(0, 1));
		dialogReserva.setSize(400, 300);

		// Agrega etiquetas y campos para los detalles de la reserva
		dialogReserva.add(new JLabel("Nombre de la habitación: " + nombreHabitacion));
		dialogReserva.add(new JLabel("Precio: " + precioHabitacion));
		dialogReserva.add(new JLabel("Descripción: " + descripcionHabitacion));

		// Obten las fechas seleccionadas
		Date fechaInicio = dateChooserInicio.getDate();
		Date fechaFinal = dateChooserFinal.getDate();

		if (fechaInicio != null && fechaFinal != null && fechaFinal.before(fechaInicio)) {
			JOptionPane.showMessageDialog(this, " La fecha final no puede ser anterior a la fecha de inicio.",
					"Error en las Fechas", JOptionPane.ERROR_MESSAGE);
			return;

		}

		// Formatea las fechas para mostrarlas
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String strFechaInicio = fechaInicio != null ? sdf.format(fechaInicio) : "N/A";
		String strFechaFinal = fechaFinal != null ? sdf.format(fechaFinal) : "N/A";

		dialogReserva.add(new JLabel("Fecha de inicio: " + strFechaInicio));
		dialogReserva.add(new JLabel("Fecha final: " + strFechaFinal));

		// Botón para confirmar la reserva
		JButton confirmButton = new JButton("Confirmar Reserva");
		confirmButton.addActionListener(e -> {
			if (db.comprarReserva(Integer.parseInt(cliente[0]), id_habitacion, 10, "P", strFechaInicio,
					strFechaFinal)) {
JOptionPane.showMessageDialog(dialogReserva, "Reserva realizada con éxito!\nFecha de Inicio: "
					+ strFechaInicio + "\nFecha Final: " + strFechaFinal);
			}else {
				JOptionPane.showMessageDialog(dialogReserva, "No se pudo hacer la reserva, vuelva a intentarlo en unos minutos.");
			}
			
		});
		dialogReserva.add(confirmButton);

		dialogReserva.setVisible(true);
	}

	private void updateImageIcon(ImageIcon icon) {
		if (icon != null) {
			Image image = icon.getImage();
			Image scaledImage = image.getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(),
					Image.SCALE_SMOOTH);
			imageLabel.setIcon(new ImageIcon(scaledImage));
		}
	}
}
