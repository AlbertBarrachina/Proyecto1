import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class main extends JFrame {

    public main() {
        // Crear el panel principal con BorderLayout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(800, 600);

        // Agregar un borde al panel
        Border panelBorder = new LineBorder(Color.BLACK);
        panel.setBorder(panelBorder);

        // Crear los componentes en la parte superior (título y búsqueda)
        Font font = new Font("Arial", Font.PLAIN, 12);
        JLabel menu = new JLabel("MENU");
        menu.setFont(font);    
        panel.add(menu, BorderLayout.WEST);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setSize(150, 25);
        Border topPanelBorder = new LineBorder(Color.BLACK);
        topPanel.setBorder(topPanelBorder);

        JLabel titulo = new JLabel("TITULO");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(font);
        topPanel.add(titulo, BorderLayout.CENTER);

        JTextField busqueda = new JTextField("Buscar...");
        busqueda.setFont(font);
        busqueda.setHorizontalAlignment(JTextField.CENTER);
        topPanel.add(busqueda, BorderLayout.SOUTH);
        panel.add(topPanel, BorderLayout.NORTH);

        // Crear el panel para contenidos centrados en tres columnas usando GridBagLayout
        JPanel contentPanel = new JPanel(new GridBagLayout());
        Border contentPanelBorder = new LineBorder(Color.BLACK);
        contentPanel.setBorder(contentPanelBorder);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(25, 40, 25, 40);

        int numContents = 9; // Número de contenidos a agregar

        for (int i = 0; i < numContents; i++) {
            JPanel contentItem = new JPanel();
            contentItem.setLayout(new BoxLayout(contentItem, BoxLayout.Y_AXIS));

            // Agregar una etiqueta para la imagen del contenido
            ImageIcon imageIcon = new ImageIcon("./src/descarga.jfif");
            JLabel imageLabel = new JLabel(imageIcon);
            contentItem.add(imageLabel);

            // Agregar dos etiquetas de descripción independientes para el contenido
            JLabel descriptionLabel1 = new JLabel("Descrin 1 - Contenido " + (i + 1));
            descriptionLabel1.setFont(font);
            contentItem.add(descriptionLabel1);

            JLabel descriptionLabel2 = new JLabel("Descripción 2 - Contenido " + (i + 1));
            descriptionLabel2.setFont(font);
            contentItem.add(descriptionLabel2);

            gbc.gridx = i % 3; // Columna
            gbc.gridy = i / 3; // Fila
            contentPanel.add(contentItem, gbc);
        }

        panel.add(contentPanel, BorderLayout.CENTER);

        // Ajustar el tamaño de la ventana
        this.add(panel);
        this.setTitle("BOOK4U");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new main();
    }
}
