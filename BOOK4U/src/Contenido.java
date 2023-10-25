import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Contenido extends JPanel {
    private static ImageIcon[] imageIcons;
    private static String[] descriptions1;
    private static String[] descriptions2;

    private String[] imagePaths; // Agregar un array de rutas de imágenes específico para esta instancia de Contenido

    
    static {
        // Inicializa las imágenes y descripciones aquí
        imageIcons = new ImageIcon[] {
        	 new ImageIcon("src/imagenes/descarga.jfif"),
        	 new ImageIcon("src/imagenes/1.jfif"),            
        	 new ImageIcon("src/imagenes/2.jfif"), 
        	 new ImageIcon("src/imagenes/3.jfif"),
        	 new ImageIcon("src/imagenes/4.jfif"),
        	 new ImageIcon("src/imagenes/5.jfif"),
        	 new ImageIcon("src/imagenes/6.jfif"),
        	 new ImageIcon("src/imagenes/7.jfif"),
        	 new ImageIcon("src/imagenes/8.jfif"),
        	 new ImageIcon("src/imagenes/9.jfif"),
        	 new ImageIcon("src/imagenes/10.jfif"),
        	 new ImageIcon("src/imagenes/11.jfif"),
        	 new ImageIcon("src/imagenes/12.jfif"),
        	 new ImageIcon("src/imagenes/13.jfif"),
        	 new ImageIcon("src/imagenes/14.jfif"),
        	 new ImageIcon("src/imagenes/15.jfif"),
        	 new ImageIcon("src/imagenes/16.jfif"),
        	 new ImageIcon("src/imagenes/17.jfif"),
        	 new ImageIcon("src/imagenes/18.jfif"),
        	 new ImageIcon("src/imagenes/19.jfif"),
            
        };

        descriptions1 = new String[] {
            "Descripción 1 - Contenido 1",
            "Descripción 1 - Contenido 2",
            "Descripción 1 - Contenido 3",
            "Descripción 1 - Contenido 4",
            "Descripción 1 - Contenido 5",
            "Descripción 1 - Contenido 6",
            "Descripción 1 - Contenido 7",
            "Descripción 1 - Contenido 8",
            "Descripción 1 - Contenido 9",
            "Descripción 1 - Contenido 10",
            "Descripción 1 - Contenido 11",
            "Descripción 1 - Contenido 12",
            "Descripción 1 - Contenido 13",
            "Descripción 1 - Contenido 14",
            "Descripción 1 - Contenido 15",
            "Descripción 1 - Contenido 16",
            "Descripción 1 - Contenido 17",
            "Descripción 1 - Contenido 18",
            "Descripción 1 - Contenido 19",
            "Descripción 1 - Contenido 20",
        };

        descriptions2 = new String[] {
            "Descripción 2 - Contenido 1",
            "Descripción 2 - Contenido 2",
            "Descripción 2 - Contenido 3",
            "Descripción 2 - Contenido 4",
            "Descripción 2 - Contenido 5",
            "Descripción 2 - Contenido 6",
            "Descripción 2 - Contenido 7",
            "Descripción 2 - Contenido 8",
            "Descripción 2 - Contenido 9",
            "Descripción 2 - Contenido 10",
            "Descripción 2 - Contenido 11",
            "Descripción 2 - Contenido 12",
            "Descripción 2 - Contenido 13",
            "Descripción 2 - Contenido 14",
            "Descripción 2 - Contenido 15",
            "Descripción 2 - Contenido 16",
            "Descripción 2 - Contenido 17",
            "Descripción 2 - Contenido 18",
            "Descripción 2 - Contenido 19",
            "Descripción 2 - Contenido 20",
        };
    }
    
 // Función para cargar una imagen con manejo de excepciones
    private static ImageIcon createImageIcon(String imagePath) {
        URL imageURL = Contenido.class.getResource(imagePath);
        if (imageURL != null) {
            return new ImageIcon(imageURL);
        } else {
            System.err.println("No se pudo cargar la imagen: " + imagePath);
            return null;
        }
    }

    public Contenido(String[] imagePaths, String description1, String description2) {
        this.imagePaths = imagePaths; // Inicializar el array de rutas de imágenes para esta instancia
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Usar una de las rutas de imágenes de este conjunto
        ImageIcon imageIcon = createImageIcon(imagePaths[0]);
        if (imageIcon != null) {
            JLabel imageLabel = new JLabel(imageIcon);
            add(imageLabel);
        }

        JLabel descriptionLabel1 = new JLabel(description1);
        descriptionLabel1.setFont(new Font("Arial", Font.PLAIN, 18));
        add(descriptionLabel1);

        JLabel descriptionLabel2 = new JLabel(description2);
        descriptionLabel2.setFont(new Font("Arial", Font.PLAIN, 18));
        add(descriptionLabel2);
    }

}
