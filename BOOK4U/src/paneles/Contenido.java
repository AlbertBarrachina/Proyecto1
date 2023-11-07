package paneles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Contenido extends JPanel {
    private String description1;
    private String description2;
    private Color backgroundColor = new Color(173, 216, 230);  // Define el color de fondo
    
    public Contenido(String imagePath, String description1, String description2, JFrame frame, JPanel mainPanel) {
        this.description1 = description1;
        this.description2 = description2;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(backgroundColor);  // Establece el color de fondo del panel

        ImageIcon imageIcon = createImageIcon(imagePath);
        if (imageIcon != null) {
            Image image = imageIcon.getImage();
            Image newImg = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newImg);
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.setContentPane(new paneles.DETALLES_HABITACION(description1 + ": " + description2, frame, mainPanel));
                    frame.revalidate();
                }
            });
            add(imageLabel);
        }

        JLabel descriptionLabel1 = new JLabel(description1);
        descriptionLabel1.setFont(new Font("Arial", Font.PLAIN, 18));
        descriptionLabel1.setOpaque(true);  // Hace que el fondo de la etiqueta sea opaco
        descriptionLabel1.setBackground(backgroundColor);  // Establece el color de fondo de la etiqueta
        add(descriptionLabel1);

        JLabel descriptionLabel2 = new JLabel(description2);
        descriptionLabel2.setFont(new Font("Arial", Font.PLAIN, 18));
        descriptionLabel2.setOpaque(true);  // Hace que el fondo de la etiqueta sea opaco
        descriptionLabel2.setBackground(backgroundColor);  // Establece el color de fondo de la etiqueta
        add(descriptionLabel2);
    }

    private static ImageIcon createImageIcon(String imagePath) {
        File file = new File(imagePath);
        if (file.exists()) {
            return new ImageIcon(imagePath);
        } else {
            System.err.println("No se pudo cargar la imagen: " + imagePath);
            return null;
        }
    }

    
    public String getDescription1() {
        return description1;
    }

    public String getDescription2() {
        return description2;
    }
}