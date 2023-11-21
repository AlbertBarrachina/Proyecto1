package paneles;

import javax.swing.*;
import java.awt.*;

public class DETALLES_HABITACION extends JPanel {
    private JLabel imageLabel;
    private JPanel imagePanel, detailsPanel, reserveButtonPanel, combinedPanel;
    private JButton backButton, reserveButton;
    private String nombreHabitacion;
    private String precioHabitacion;
    private String descripcionHabitacion;
    private JFrame frame;
    private JPanel mainPanel;

    public DETALLES_HABITACION(ImageIcon imageIcon, String nombreHabitacion, String precioHabitacion, String descripcionHabitacion, JFrame frame2, JPanel mainPanel) {
        this.mainPanel = mainPanel;
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
        detailsPanel.setPreferredSize(new Dimension(400, 300)); // Tamaño aumentado

        JLabel nameLabel = new JLabel("Nombre: " + nombreHabitacion);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setPreferredSize(new Dimension(500 , 200)); // Tamaño de JLabel aumentado
        detailsPanel.add(nameLabel); 

        JLabel priceLabel = new JLabel("Precio: " + precioHabitacion);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceLabel.setPreferredSize(new Dimension(500 , 200)); // Tamaño de JLabel aumentado
        detailsPanel.add(priceLabel);

        JLabel descriptionLabel = new JLabel("Descripción: " + descripcionHabitacion);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        descriptionLabel.setPreferredSize(new Dimension(500 , 200)); // Tamaño de JLabel aumentado
        detailsPanel.add(descriptionLabel);

        combinedPanel.add(detailsPanel);
        combinedPanel.add(Box.createVerticalGlue()); // Espaciador para empujar hacia abajo

        // Panel para el botón de reservar
        reserveButtonPanel = new JPanel();
        reserveButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        reserveButton = new JButton("Reservar");
        reserveButton.setPreferredSize(new Dimension(200, 30)); // Tamaño del botón reducido
        reserveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Reserva realizada con éxito!");
        });
        reserveButtonPanel.add(reserveButton);
        combinedPanel.add(reserveButtonPanel);

        // Añadir el panel combinado al panel principal
        add(combinedPanel, BorderLayout.CENTER);

        // Botón de regreso
        backButton = new JButton("Volver");
        backButton.setPreferredSize(new Dimension(400, 50)); 
        backButton.addActionListener(e -> {
            frame.setContentPane(mainPanel);
            frame.revalidate();
        });
        add(backButton, BorderLayout.SOUTH);

        // Actualización de la imagen
        SwingUtilities.invokeLater(() -> updateImageIcon(imageIcon));
    }

    private void updateImageIcon(ImageIcon icon) {
        if (icon != null) {
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        }
    }
}
