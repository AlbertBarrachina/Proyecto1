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
            "Suite Deluxe", "Habitaci�n con Vista al Mar", "Caba�a Acogedora", "Loft Urbano", "Habitaci�n Familiar", "Bungalow de Monta�a",
            "Habitaci�n Tem�tica Vintage", "Villa con Piscina Privada", "Habitaci�n Loft", "Suite Rom�ntica", "Habitaci�n con Jacuzzi", "Caba�a junto al R�o",
            "Habitaci�n Eco-Friendly", "Alojamiento en �tico", "Habitaci�n de Lujo", "Suite Junior", "Habitaci�n con Terraza", "Caba�a de Campo",
            "Habitaci�n con Chimenea", "Loft Industrial",
        };

        String[] descriptions2 = {
            "80 Cr�ditos", "95 Cr�ditos", "70 cr�ditos", "85 cr�ditos", "90 cr�ditos", "75 cr�ditos",
            "80 cr�ditos", "99 cr�ditos", "85 cr�ditos", "95 cr�ditos", "90 cr�ditos", "80 cr�ditos",
            "70 cr�ditos", "85 cr�ditos", "99 cr�ditos", "75 cr�ditos", "80 cr�ditos", "70 cr�ditos",
            "90 cr�ditos", "85 cr�ditos",
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