import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class main extends JFrame {
    private JPanel panelPrincipal;
    private JPanel perfilPanel;
    private List<Contenido> contenidos = new ArrayList<>();
    private JPanel contentPanel;

    public main() {
        String[] descriptions1 = {
            "Suite Deluxe", "Habitación con Vista al Mar", "Cabaña Acogedora", "Loft Urbano", "Habitación Familiar", "Bungalow de Montaña",
            "Habitación Temática Vintage", "Villa con Piscina Privada", "Habitación Loft", "Suite Romántica", "Habitación con Jacuzzi", "Cabaña junto al Río",
            "Habitación Eco-Friendly", "Alojamiento en Ático", "Habitación de Lujo", "Suite Junior", "Habitación con Terraza", "Cabaña de Campo",
            "Habitación con Chimenea", "Loft Industrial",
        };

        String[] descriptions2 = {
            "80 Créditos", "95 Créditos", "70 créditos", "85 créditos", "90 créditos", "75 créditos",
            "80 créditos", "99 créditos", "85 créditos", "95 créditos", "90 créditos", "80 créditos",
            "70 créditos", "85 créditos", "99 créditos", "75 créditos", "80 créditos", "70 créditos",
            "90 créditos", "85 créditos",
        };

        panelPrincipal = new JPanel(new BorderLayout());
        JPanel menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(100, 600));
        panelPrincipal.add(menuPanel, BorderLayout.WEST);

        JPanel topPanel = new JPanel(new BorderLayout());
        Font font = new Font("Arial", Font.PLAIN, 18);
        JLabel titulo = new JLabel("TITULO");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(font);
        titulo.setPreferredSize(new Dimension(300, 50));
        topPanel.add(titulo, BorderLayout.WEST);

        JTextField busqueda = new JTextField("Buscar...");
        busqueda.setFont(font);
        busqueda.setHorizontalAlignment(JTextField.CENTER);
        busqueda.setPreferredSize(new Dimension(300, 50));
        busqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscar(busqueda.getText().toLowerCase());
            }
        });
        topPanel.add(busqueda, BorderLayout.CENTER);

        JButton perfilButton = new JButton("Perfil");
        perfilButton.setFont(new Font("Arial", Font.PLAIN, 12));
        perfilButton.addActionListener(e -> {
            if (perfilPanel == null) {
                perfilPanel = new perfil_usuario(this, panelPrincipal);
            }
            setContentPane(perfilPanel);
            revalidate();
        });
        topPanel.add(perfilButton, BorderLayout.EAST);

        panelPrincipal.add(topPanel, BorderLayout.NORTH);

        contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);

        int numContents = 20;
        for (int i = 0; i < numContents; i++) {
            String imagePath = "src/imagenes/" + (i + 1) + ".jpg";
            String description1 = descriptions1[i];
            String description2 = descriptions2[i];
            Contenido contenido = new Contenido(imagePath, description1, description2, this, panelPrincipal);
            contenidos.add(contenido);
            gbc.gridx = i % 4;
            gbc.gridy = i / 4;
            contentPanel.add(contenido, gbc);
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        this.add(panelPrincipal);
        this.setTitle("BOOK4U");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void buscar(String texto) {
        contentPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        int count = 0;
        for (Contenido contenido : contenidos) {
            if (contenido.getDescription1().toLowerCase().contains(texto) || contenido.getDescription2().toLowerCase().contains(texto)) {
                gbc.gridx = count % 4;
                gbc.gridy = count / 4;
                contentPanel.add(contenido, gbc);
                count++;
            }
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new main());
    }
}