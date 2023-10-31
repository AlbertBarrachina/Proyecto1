import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main extends JFrame {
    private JPanel panelPrincipal;
    private JPanel perfilPanel;
    private List<Contenido> contenidos = new ArrayList<>();
    private JPanel contentPanel;

    public main() {
        ////STRING CON LOS NOMBRES DE LAS HABITACIONES/CASAS RURALES////
    	String[] descriptions1 = {
            "Suite Deluxe", "Habitaci�n con Vista al Mar", "Caba�a Acogedora", "Loft Urbano", "Habitaci�n Familiar", "Bungalow de Monta�a",
            "Habitaci�n Tem�tica Vintage", "Villa con Piscina Privada", "Habitaci�n Loft", "Suite Rom�ntica", "Habitaci�n con Jacuzzi", "Caba�a junto al R�o",
            "Habitaci�n Eco-Friendly", "Alojamiento en �tico", "Habitaci�n de Lujo", "Suite Junior", "Habitaci�n con Terraza", "Caba�a de Campo",
            "Habitaci�n con Chimenea", "Loft Industrial",
        };
        
    	////STRING CON LOS PRECIOS DE LAS HABITACIONES/CASAS RURALES////
        String[] descriptions2 = {
            "80 Cr�ditos", "95 Cr�ditos", "70 cr�ditos", "85 cr�ditos", "90 cr�ditos", "75 cr�ditos",
            "80 cr�ditos", "99 cr�ditos", "85 cr�ditos", "95 cr�ditos", "90 cr�ditos", "80 cr�ditos",
            "70 cr�ditos", "85 cr�ditos", "99 cr�ditos", "75 cr�ditos", "80 cr�ditos", "70 cr�ditos",
            "90 cr�ditos", "85 cr�ditos",
        };

        ////PANEL PRINCIPAL DONDE SE A�ADIRAN EL RESTO DE PANELES////
        panelPrincipal = new JPanel(new BorderLayout());
        JPanel menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(100, 600));
        panelPrincipal.add(menuPanel, BorderLayout.WEST);

        ////PANEL SUPERIOR DONDE IRAN LOS PANELES DE BUSQUEDA, PERFIL Y TITULO////
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(173, 216, 230));  


        ////CREAMOS EL PANEL PARA EL TITULO////
        JPanel tituloPanel = new JPanel(new BorderLayout());  
        Font font = new Font("Arial", Font.PLAIN, 18);
        JLabel titulo = new JLabel("TITULO");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(font);
        tituloPanel.add(titulo, BorderLayout.CENTER);  
        tituloPanel.setPreferredSize(new Dimension(100,30));
        topPanel.add(tituloPanel, BorderLayout.WEST); 

        ////CREAMOS EL PANEL PARA EL CAMPO DE BUSQUEDA////
        JTextField busqueda = new JTextField("");
        busqueda.setFont(font);
        busqueda.setHorizontalAlignment(JTextField.CENTER);

     // CREAMOS EL BOTON DE BUSQUEDA
        CircularButton buscarButton = new CircularButton("", new Color(173, 120, 109));
        buscarButton.setPreferredSize(new Dimension(30, 30)); // Establece un tama�o preferido
        try {
            Image img = ImageIO.read(new File("ruta/a/tu/imagen.png")); // Reemplaza con la ruta a tu imagen
            Image resizedImg = img.getScaledInstance(buscarButton.getPreferredSize().width, buscarButton.getPreferredSize().height, Image.SCALE_SMOOTH);
            buscarButton.setIcon(new ImageIcon(resizedImg));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar(busqueda.getText().toLowerCase());
            }
        });
        
        JPanel busquedaPanel = new JPanel(new BorderLayout());
        busquedaPanel.add(busqueda, BorderLayout.CENTER);
        busquedaPanel.add(buscarButton, BorderLayout.EAST);

        topPanel.add(busquedaPanel, BorderLayout.CENTER);

        ////CREAMOS EL BOTON QUE SERVIRA PARA ACCEDER AL PERFIL DE USUARIO////
        CircularButton perfilButton = new CircularButton("Perfil", new Color(131 , 36 , 110));
        perfilButton.setPreferredSize(new Dimension(60 , 60));

        perfilButton.setFont(new Font("Arial", Font.PLAIN, 12));
        perfilButton.addActionListener(e -> {
            if (perfilPanel == null) {
                perfilPanel = new perfil_usuario(this, panelPrincipal);
            }
            setContentPane(perfilPanel);
            revalidate();
        });
        //A�ADIMOS EL BOTON A UN PANEL PERSONALIZADO//
        JPanel perfilPanelContainer = new JPanel();
        perfilPanelContainer.add(perfilButton);
        topPanel.add(perfilPanelContainer, BorderLayout.EAST);

        ////A�ADIMOS EL PANEL SUPERIOR AL PANEL PRINCIPAL////
        panelPrincipal.add(topPanel, BorderLayout.NORTH);
        
        
        ////CREAMOS EL PANEL DONDE IRAN ALOJADOS CADA UNO DE LOS CONTENIDOS////
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(173, 216, 230));  // Establece el color de fondo aqu�

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);

        
        ////MARCAMOS CUANTOS CONTENIDOS QUEREMOS QUE TENGA EL PANEL//// 
        int numContents = 20;
        
        ///INICIAMOS UN BUCLE PARA QUE CARGUE LA IMAGENES Y DESCRIPCIONES CORRESPONDIENTES A CADA UNO DE LOS CONTENIDOS///
        ///A SU VEZ SE MARCA COMO SE QUIERE QUE ESTOS CONTENIDOS ESTEN DISTRIBUIDOS///
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

        ///A�ADIMOS UN SCROLLPANE PARA PODER DESPLAZARSE 
        ///MANUALMENTE POR LOS CONTENIDOS QUE HAY EN LA APLICACION
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        
        ///A�ADIMOS EL PANEL PRINCIPAL AL FRAME///
        this.add(panelPrincipal);
        this.setTitle("BOOK4U");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    
    ///FUNCION QUE SIRVE PARA BUSCAR UNA HABITACION/CASA RURAL EN CONCRETO
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

    //FUNCION MAIN PARA HACER QUE SE EJECUTE LA APLICACION
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new main());
    }
}