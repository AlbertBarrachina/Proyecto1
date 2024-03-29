package paneles;

import javax.swing.*;


import main.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Contenido extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel imageLabel;
    private ImageIcon imageIcon;
    private Color backgroundColor = new Color(173, 216, 230);  // Define el color de fondo
    int[] dimensiones = main.getDimensiones();
    
    
    public Contenido(String[] habitacion, String imagePath) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(backgroundColor);  // Establece el color de fondo del panel

        try {
        	imageIcon = createImageIcon(imagePath);
		} catch (Exception e) {
			imageIcon = createImageIcon("src/assets/imagenes/1.jpg");
		}
        if (imageIcon != null) {
            Image image = imageIcon.getImage();
            //establece tamanyo de la imaen con respecto al tama�o del frame
            Image newImg = image.getScaledInstance(350, 300, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newImg);
            imageLabel = new JLabel(imageIcon);
            add(imageLabel);
        }

        JLabel descriptionLabel1 = new JLabel(habitacion[7]);
        descriptionLabel1.setFont(new Font("Arial", Font.PLAIN, 18));
        descriptionLabel1.setOpaque(true);  // Hace que el fondo de la etiqueta sea opaco
        descriptionLabel1.setBackground(backgroundColor);  // Establece el color de fondo de la etiqueta
        add(descriptionLabel1);

        JLabel descriptionLabel2 = new JLabel(habitacion[2]+ " EcoBits");
        descriptionLabel2.setFont(new Font("Arial", Font.PLAIN, 18));
        descriptionLabel2.setOpaque(true);  // Hace que el fondo de la etiqueta sea opaco
        descriptionLabel2.setBackground(backgroundColor);  // Establece el color de fondo de la etiqueta
        add(descriptionLabel2);
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loader.cargardetallesHabitacion(imageIcon, habitacion);
            }
        });
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
}