import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
 
public class main extends JFrame {

    public main() {
        // Crear el panel principal con BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // PANEL DE MENÚ EN EL LADO IZQUIERDO
        JPanel menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(100, 600)); // Ancho fijo
        panel.add(menuPanel, BorderLayout.WEST);

        // PANEL SUPERIOR PARA TÍTULO, BÚSQUEDA Y PERFIL
        JPanel topPanel = new JPanel(new BorderLayout());

     // Crear los componentes en la parte superior (título y búsqueda)
        Font font = new Font("Arial", Font.PLAIN, 18); // Modifica el tamaño de fuente
        JLabel titulo = new JLabel("TITULO");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(font); // Establece la fuente
        titulo.setPreferredSize(new Dimension(300, 10)); // Ancho más ancho
        topPanel.add(titulo, BorderLayout.WEST);

        JTextField busqueda = new JTextField("Buscar...");
        busqueda.setFont(font); // Establece la fuente
        busqueda.setHorizontalAlignment(JTextField.CENTER);
        busqueda.setPreferredSize(new Dimension(300, 10)); // Ancho más ancho
        topPanel.add(busqueda, BorderLayout.CENTER);

        // Crear el botón de perfil con forma circular
        CircularButton perfilButton = new CircularButton("Perfil");
        perfilButton.setForeground(Color.BLUE); 
        perfilButton.setFont(new Font("Arial", Font.PLAIN, 12));

        // Agregar el botón de perfil en la parte superior (EAST) del panel superior
        topPanel.add(perfilButton, BorderLayout.EAST);

        panel.add(topPanel, BorderLayout.NORTH);
        String currentDirectory = System.getProperty("user.dir");

     // Usa la barra diagonal inversa (\) para las rutas en Windows
     String[] imagePaths = {
         currentDirectory + "\\imagenes\\descarga.jfif",
         currentDirectory + "\\imagenes\\1.jfif",
         currentDirectory + "\\imagenes\\2.jfif",
         currentDirectory + "\\imagenes\\3.jfif",
         currentDirectory + "\\imagenes\\4.jfif",
         currentDirectory + "\\imagenes\\5.jfif",
         currentDirectory + "\\imagenes\\6.jfif",
         currentDirectory + "\\imagenes\\7.jfif", 
         currentDirectory + "\\imagenes\\8.jfif",
         currentDirectory + "\\imagenes\\9.jfif",
         currentDirectory + "\\imagenes\\10.jfif",
         currentDirectory + "\\imagenes\\11.jfif",
         currentDirectory + "\\imagenes\\12.jfif",
         currentDirectory + "\\imagenes\\13.jfif",
         currentDirectory + "\\imagenes\\14.jfif",
         currentDirectory + "\\imagenes\\15.jfif",
         currentDirectory + "\\imagenes\\16.jfif",
         currentDirectory + "\\imagenes\\17.jfif",
         currentDirectory + "\\imagenes\\18.jfif",
         currentDirectory + "\\imagenes\\19.jfif"
         // Agrega rutas para todas tus imágenes
     };

        
        // Crear el panel central para el contenido
        JPanel contentPanel = new JPanel(new GridBagLayout());
        Border contentPanelBorder = new LineBorder(Color.BLACK);
        contentPanel.setBorder(contentPanelBorder);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(25, 40, 25, 40);

        int numContents = 20;

        for (int i = 0; i < numContents; i++) {
            String[] imagePathsForContent = new String[1];
            imagePathsForContent[0] = imagePaths[i];
            String description1 = "Descripción 1 - Contenido " + (i + 1);
            String description2 = "Descripción 2 - Contenido " + (i + 1);
            Contenido contenido = new Contenido(imagePathsForContent, description1, description2);

            gbc.gridx = i % 4;
            gbc.gridy = i / 4;
            contentPanel.add(contenido, gbc);
        }


 
        // Agregar el panel de contenidos a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scrollPane, BorderLayout.CENTER);

        // Ajustar el tamaño de la ventana
        this.add(panel);
        this.setTitle("BOOK4U");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void setPreferedSize(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
        new main();
    }
}
