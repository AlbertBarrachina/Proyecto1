package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
import main.*;

public class detallesHabitacion extends JPanel {
	int[] dimensiones = main.getDimensiones();
	String[] cliente;
	private JLabel imageLabel;
	private JPanel imagePanel, detailsPanel, reserveButtonPanel, combinedPanel;
	private JButton backButton, reserveButton;
	private JDateChooser dateChooserInicio;
	private JDateChooser dateChooserFinal;

	public detallesHabitacion(ImageIcon imageIcon, String[] habitacion) {
		cliente = main.getSesion();
		main.setSesion(cliente[5], cliente[6]);
		cliente = main.getSesion();
		String[] empresa = db.InfoEmpresa(Integer.parseInt(habitacion[1]));
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

		// Panel de detalles con fuente más grande y mayor tamanyo
		detailsPanel = new JPanel();
		detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
		detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		detailsPanel.setPreferredSize(new Dimension(400, 300));

		JLabel nameLabel = new JLabel(habitacion[7]);
		nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
		nameLabel.setPreferredSize(new Dimension(500, 100));
		detailsPanel.add(nameLabel);

		JLabel directionLabel = new JLabel(empresa[2]);
		directionLabel.setFont(new Font("Arial", Font.BOLD, 16));
		directionLabel.setPreferredSize(new Dimension(500, 100));
		detailsPanel.add(directionLabel);

		JLabel priceLabel = new JLabel(habitacion[2] + " EcoBits");
		priceLabel.setFont(new Font("Arial", Font.BOLD, 17));
		priceLabel.setPreferredSize(new Dimension(500, 100));
		detailsPanel.add(priceLabel);

		JLabel descriptionLabel = new JLabel("Descripción: " + habitacion[8]);
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 15));
		descriptionLabel.setPreferredSize(new Dimension(500, 200));
		detailsPanel.add(descriptionLabel);

		///////////////////////////////////////////////////////////////////////////////////////////////////
		/////// ///////
		/////// UTILIZANDO LA LIBRERIA DE JCALENDAR ANYADIMOS EL CAMPO DE FECHA_INICIO Y
		/////////////////////////////////////////////////////////////////////////////////////////////////// FECHA_FINAL
		/////////////////////////////////////////////////////////////////////////////////////////////////// ///////
		/////// ///////
		///////////////////////////////////////////////////////////////////////////////////////////////////

		// fecha entrada
		dateChooserInicio = new JDateChooser();
		dateChooserInicio.setDateFormatString("yyyy/MM/dd");
		dateChooserInicio.getJCalendar().setMinSelectableDate(new Date());
		((JTextField) dateChooserInicio.getDateEditor().getUiComponent()).setEditable(false);

		detailsPanel.add(new JLabel("Fecha de Inicio:"));
		detailsPanel.add(dateChooserInicio);

		// fecha salida
		dateChooserFinal = new JDateChooser();
		dateChooserFinal.setDateFormatString("yyyy/MM/dd");
		dateChooserFinal.getJCalendar().setMinSelectableDate(new Date());
		((JTextField) dateChooserFinal.getDateEditor().getUiComponent()).setEditable(false);

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
			VentanaReserva(habitacion, empresa);
		});
		reserveButtonPanel.add(reserveButton);
		combinedPanel.add(reserveButtonPanel);

		// Anyadir el panel combinado al panel principal
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

	private void VentanaReserva(String[] habitacion, String[] empresa) {
		JDialog dialogReserva = new JDialog(main.getFrame(), "Detalles de la Reserva", true);
		dialogReserva.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialogReserva.setLayout(new GridLayout(0, 1));
		dialogReserva.setSize(400, 300);

// Obten las fechas seleccionadas
		Date fechaInicio = dateChooserInicio.getDate();
		Date fechaFinal = dateChooserFinal.getDate();

		LocalDate localFechaInicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate localFechaFinal = fechaFinal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		// Calculate the difference in days
		long daysDifference = ChronoUnit.DAYS.between(localFechaInicio, localFechaFinal);
		daysDifference += 1;

		String dias = Long.toString(daysDifference);
		// Agrega etiquetas y campos para los detalles de la reserva
		dialogReserva.add(new JLabel("Nombre de la habitación: " + habitacion[7]));
		dialogReserva.add(new JLabel("Direccion: " + empresa[2]));
		dialogReserva.add(new JLabel("Precio total: " + Integer.parseInt(habitacion[2]) * Integer.parseInt(dias)));
		dialogReserva.add(new JLabel("Descripción: " + habitacion[8]));

		if (fechaInicio != null && fechaFinal != null && fechaFinal.before(fechaInicio)) {
			JOptionPane.showMessageDialog(this, " La fecha final no puede ser anterior a la fecha de inicio.",
					"Error en las Fechas", JOptionPane.ERROR_MESSAGE);
			return;

		}

		// Formatea las fechas para mostrarlas
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String strFechaInicio = fechaInicio != null ? sdf.format(fechaInicio) : "N/A";
		String strFechaFinal = fechaFinal != null ? sdf.format(fechaFinal) : "N/A";

		dialogReserva.add(new JLabel("Fecha de inicio: " + strFechaInicio));
		dialogReserva.add(new JLabel("Fecha final: " + strFechaFinal));

		// Botón para confirmar la reserva
		JButton confirmButton = new JButton("Confirmar Reserva");
		confirmButton.addActionListener(e -> {
			if (Integer.parseInt(cliente[7]) < (Integer.parseInt(habitacion[2]) * Integer.parseInt(dias))) {
				JOptionPane.showMessageDialog(this,
						"No dispone de tantos EcoBits.\nActualmente tiene " + cliente[7] + " EcoBits.",
						"Insuficientes EcoBits!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (db.comprarReserva(Integer.parseInt(cliente[0]), Integer.parseInt(habitacion[0]),
					Integer.parseInt(habitacion[2]) * Integer.parseInt(dias), "P", strFechaInicio, strFechaFinal)
					&& db.editarCreditosCliente(Integer.parseInt(cliente[0]),
							-(Integer.parseInt(habitacion[2]) * Integer.parseInt(dias)))) {
				JOptionPane.showMessageDialog(dialogReserva, "Reserva realizada con éxito!\nFecha de Inicio: "
						+ strFechaInicio + "\nFecha Final: " + strFechaFinal);
				System.out.println(db.editarInfoHabitacion(Integer.parseInt(habitacion[0]), "N"));
				dialogReserva.dispose();
			} else {
				JOptionPane.showMessageDialog(dialogReserva,
						"No se pudo hacer la reserva, vuelva a intentarlo en unos minutos.");
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
