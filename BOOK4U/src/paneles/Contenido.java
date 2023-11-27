package paneles;

import javax.swing.*;

import main.ContenidoUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Contenido extends JPanel {
    private ImageIcon imageIcon;
    private String description1;
    private String description2;
    private Color backgroundColor = new Color(173, 216, 230);

    public Contenido(String imagePath, String description1, String description2, ContenidoUI contenidoUI, JPanel mainPanel) {
        this.description1 = description1;
        this.description2 = description2;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(backgroundColor);

        this.imageIcon = createImageIcon(imagePath);
        if (this.imageIcon != null) {
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    contenidoUI.setContentPane(new DETALLES_HABITACION(imageIcon, description1, description2, description2, contenidoUI, mainPanel));
                    contenidoUI.revalidate();
                }
            });
            add(imageLabel);
        }

        JLabel descriptionLabel1 = new JLabel(description1);
        descriptionLabel1.setFont(new Font("Arial", Font.PLAIN, 18));
        descriptionLabel1.setOpaque(true);
        descriptionLabel1.setBackground(backgroundColor);
        add(descriptionLabel1);

        JLabel descriptionLabel2 = new JLabel(description2);
        descriptionLabel2.setFont(new Font("Arial", Font.PLAIN, 18));
        descriptionLabel2.setOpaque(true);
        descriptionLabel2.setBackground(backgroundColor);
        add(descriptionLabel2);
    }


    private ImageIcon createImageIcon(String imagePath) {
        File file = new File(imagePath);
        if (file.exists()) {
            ImageIcon icon = new ImageIcon(imagePath);
            Image image = icon.getImage();
            Image newImg = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            return new ImageIcon(newImg);
        } else {
            System.err.println("No se pudo cargar la imagen: " + imagePath);
            return null;
        }
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }
    public String getDescription1() {
        return description1;
    }

    public String getDescription2() {
        return description2;
    }
}