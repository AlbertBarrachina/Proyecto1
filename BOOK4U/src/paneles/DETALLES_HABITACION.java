package paneles;

import javax.swing.*;
import java.awt.*;

public class DETALLES_HABITACION extends JPanel {
    private JLabel imageLabel;
    private JPanel imagePanel, detailsPanel, reserveButtonPanel;
    private JButton backButton, reserveButton;
    private String nombreHabitacion;
    private String precioHabitacion;
    private String descripcionHabitacion;
    private JFrame frame;
    private JPanel mainPanel;

    public DETALLES_HABITACION(ImageIcon imageIcon, String nombreHabitacion, String precioHabitacion, String descripcionHabitacion, JFrame frame, JPanel mainPanel) {
        this.frame = frame;
        this.mainPanel = mainPanel;
        this.nombreHabitacion = nombreHabitacion;
        this.precioHabitacion = precioHabitacion;
        this.descripcionHabitacion = descripcionHabitacion;

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(173, 216, 230));

        // Panel de imagen con un tamaño preferido
        imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBorder(BorderFactory.createTitledBorder("Imagen de la Habitación"));
        imagePanel.setPreferredSize(new Dimension(600, 400)); 
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.WEST);

        // Panel de detalles con un tamaño preferido
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        detailsPanel.setPreferredSize(new Dimension(400, 200)); 
        detailsPanel.add(new JLabel("Nombre: " + nombreHabitacion));
        detailsPanel.add(new JLabel("Precio: " + precioHabitacion));
        detailsPanel.add(new JLabel("Descripción: " + descripcionHabitacion));
        add(detailsPanel, BorderLayout.CENTER);

        // Panel para el botón de reservar
        reserveButtonPanel = new JPanel();
        reserveButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        reserveButton = new JButton("Reservar");
        reserveButton.addActionListener(e -> {
            // Lógica de reserva
            JOptionPane.showMessageDialog(this, "Reserva realizada con éxito!");
        });
        reserveButtonPanel.add(reserveButton);
        add(reserveButtonPanel, BorderLayout.EAST);

        // Botón de regreso con un tamaño preferido
        backButton = new JButton("Volver");
        backButton.setPreferredSize(new Dimension(400, 50)); 
        backButton.addActionListener(e -> {
            frame.setContentPane(mainPanel);
            frame.revalidate();
        });
        add(backButton, BorderLayout.SOUTH);

        // Asegúrate de que el frame sea visible antes de llamar a este método
        SwingUtilities.invokeLater(() -> updateImageIcon(imageIcon));
    }

    // Este método escala la imagen al tamaño del imagePanel
    private void updateImageIcon(ImageIcon icon) {
        if (icon != null) {
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        }
    }
}
