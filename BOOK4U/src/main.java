import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class main extends JFrame {

    public main() {
        // Crear el panel con el BorderLayout y ajustar su tamaño
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(350, 100);

        // Agregar un borde al panel
        Border panelBorder = new LineBorder(Color.BLACK); // Puedes personalizar el color y el estilo del borde
        panel.setBorder(panelBorder);

        // Crear los componentes y ajustar su tamaño
        Font font = new Font("Arial", Font.PLAIN, 12);

        JLabel menu = new JLabel("MENU");
        menu.setFont(font);
        panel.add(menu, BorderLayout.WEST);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setSize(150, 25); // Reducir el tamaño del panel superior

        // Agregar un borde a topPanel
        Border topPanelBorder = new LineBorder(Color.BLACK); // Puedes personalizar el color y el estilo del borde
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

        // Crear un panel para el botón de perfil con forma circular en la parte superior derecha
        JPanel profileButtonPanel = new JPanel(new BorderLayout());
        profileButtonPanel.setSize(75, 75); // Tamaño del botón circular

        // Agregar un borde a profileButtonPanel
        Border profileButtonPanelBorder = new LineBorder(Color.BLACK); // Puedes personalizar el color y el estilo del borde
        profileButtonPanel.setBorder(profileButtonPanelBorder);

        JButton perfil = new JButton("Perfil") {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    super.paintComponent(g);
                    return;
                }

                super.paintComponent(g);

                int width = getWidth();
                int height = getHeight();
                g.setColor(getBackground());
                g.fillOval(0, 0, width, height);
            }
        };

        perfil.setFont(font);
        perfil.setForeground(Color.WHITE); // Color del texto en el botón
        perfil.setBackground(Color.BLUE); // Color de fondo del botón
        perfil.setOpaque(true);
        perfil.setBorderPainted(false);
        perfil.setSize(75, 75); // Tamaño del botón circular
        profileButtonPanel.add(perfil, BorderLayout.NORTH);
        panel.add(profileButtonPanel, BorderLayout.EAST);

        // Crear un panel para los contenidos centrados en tres columnas usando GridBagLayout
        JPanel contentPanel = new JPanel(new GridBagLayout());

        // Agregar un borde a contentPanel
        Border contentPanelBorder = new LineBorder(Color.BLACK); // Puedes personalizar el color y el estilo del borde
        contentPanel.setBorder(contentPanelBorder);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Aumentar el peso horizontal para desplazar hacia la derecha
        gbc.insets = new Insets(25, 40, 25, 40); // Espaciado entre componentes (más margen a la derecha)

        for (int i = 0; i < 6; i++) {
            JPanel contentItem = new JPanel();
            contentItem.setLayout(new BoxLayout(contentItem, BoxLayout.Y_AXIS));

            // Agregar una etiqueta para la imagen
            ImageIcon imageIcon = new ImageIcon("descarga.jfif"); // Reemplaza con la ruta de tu imagen
            JLabel imageLabel = new JLabel(imageIcon);
            contentItem.add(imageLabel);

            // Agregar dos etiquetas de descripción
            JLabel descriptionLabel1 = new JLabel("Descripción 1");
            descriptionLabel1.setFont(font);
            contentItem.add(descriptionLabel1);

            JLabel descriptionLabel2 = new JLabel("Descripción 2");
            descriptionLabel2.setFont(font);
            contentItem.add(descriptionLabel2);

            gbc.gridx = i % 3;
            gbc.gridy = i / 3;
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
