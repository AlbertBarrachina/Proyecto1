import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class main extends JFrame {

    public main() {
        // Crear el panel principal con BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // PANEL DE MEN� EN EL LADO IZQUIERDO
        JPanel menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(100, 600)); // Ancho fijo
        panel.add(menuPanel, BorderLayout.WEST);

        // PANEL SUPERIOR PARA T�TULO, B�SQUEDA Y PERFIL
        JPanel topPanel = new JPanel(new BorderLayout());

     // Crear los componentes en la parte superior (t�tulo y b�squeda)
        Font font = new Font("Arial", Font.PLAIN, 18); // Modifica el tama�o de fuente
        JLabel titulo = new JLabel("TITULO");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(font); // Establece la fuente
        titulo.setPreferredSize(new Dimension(300, 10)); // Ancho m�s ancho
        topPanel.add(titulo, BorderLayout.WEST);

        JTextField busqueda = new JTextField("Buscar...");
        busqueda.setFont(font); // Establece la fuente
        busqueda.setHorizontalAlignment(JTextField.CENTER);
        busqueda.setPreferredSize(new Dimension(300, 10)); // Ancho m�s ancho
        topPanel.add(busqueda, BorderLayout.CENTER);

        // Crear el bot�n de perfil con forma circular
        CircularButton perfilButton = new CircularButton("Perfil");
        perfilButton.setForeground(Color.BLUE);
        perfilButton.setFont(new Font("Arial", Font.PLAIN, 12));

        // Agregar el bot�n de perfil en la parte superior (EAST) del panel superior
        topPanel.add(perfilButton, BorderLayout.EAST);

        panel.add(topPanel, BorderLayout.NORTH);

        // PANEL CENTRAL PARA EL CONTENIDO
        JPanel contentPanel = new JPanel(new GridBagLayout());
        Border contentPanelBorder = new LineBorder(Color.BLACK);
        contentPanel.setBorder(contentPanelBorder);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(25, 40, 25, 40);

        int numContents = 20; // N�mero de contenidos a agregar (aumentado)

        for (int i = 0; i < numContents; i++) {
            JPanel contentItem = new JPanel();
            contentItem.setLayout(new BoxLayout(contentItem, BoxLayout.Y_AXIS));

            // Agregar una etiqueta para la imagen del contenido
            ImageIcon imageIcon = new ImageIcon("./src/descarga.jfif");
            JLabel imageLabel1 = new JLabel(imageIcon);
            contentItem.add(imageLabel1);

            // Agregar dos etiquetas de descripci�n independientes para el contenido
            JLabel descriptionLabel1 = new JLabel("Descripci�n 1 - Contenido " + (i + 1));
            descriptionLabel1.setFont(font);
            contentItem.add(descriptionLabel1);

            JLabel descriptionLabel2 = new JLabel("Descripci�n 2 - Contenido " + (i + 1));
            descriptionLabel2.setFont(font);
            contentItem.add(descriptionLabel2);

            gbc.gridx = i % 4; // Columna
            gbc.gridy = i / 4; // Fila
            contentPanel.add(contentItem, gbc);
        }

        // Agregar el panel de contenidos a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scrollPane, BorderLayout.CENTER);

        // Ajustar el tama�o de la ventana
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
