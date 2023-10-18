import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class main extends JFrame {

    public main() {
        // Crear el panel con el BorderLayout y ajustar su tamaño
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(150, 100);

        // Crear los componentes y ajustar su tamaño
        Font font = new Font("Arial", Font.PLAIN, 12);

        JLabel menu = new JLabel("MENU");
        menu.setFont(font);
        panel.add(menu, BorderLayout.WEST);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setSize(150, 25); // Reducir el tamaño del panel superior
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
        JButton perfil = new JButton("Perfil");
        perfil.setFont(font);
        perfil.setForeground(Color.WHITE); // Color del texto en el botón
        perfil.setBackground(Color.BLUE); // Color de fondo del botón
        perfil.setOpaque(true);
        perfil.setBorderPainted(false);
        perfil.setSize(75, 75); // Tamaño del botón circular
        profileButtonPanel.add(perfil, BorderLayout.EAST);
        panel.add(profileButtonPanel, BorderLayout.EAST);

        // Crear un panel para los contenidos centrados en tres columnas
        JPanel contentPanel = new JPanel(new GridLayout(2, 3)); // 2 filas y 3 columnas
        contentPanel.setSize(150, 50); // Aumentar el tamaño del panel intermedio para acomodar los contenidos

        JLabel contenido1 = new JLabel("CONTENIDO");
        contenido1.setFont(font);
        contentPanel.add(contenido1);

        JLabel contenido2 = new JLabel("CONTENIDO");
        contenido2.setFont(font);
        contentPanel.add(contenido2);

        JLabel contenido3 = new JLabel("CONTENIDO");
        contenido3.setFont(font);
        contentPanel.add(contenido3);

        JLabel contenido4 = new JLabel("CONTENIDO");
        contenido4.setFont(font);
        contentPanel.add(contenido4);

        JLabel contenido5 = new JLabel("CONTENIDO");
        contenido5.setFont(font);
        contentPanel.add(contenido5);

        JLabel contenido6 = new JLabel("CONTENIDO");
        contenido6.setFont(font);
        contentPanel.add(contenido6);

        panel.add(contentPanel, BorderLayout.CENTER);

        // Ajustar el tamaño de la ventana
        this.add(panel);
        this.setTitle("PAGINA PRINCIPAL");
        this.setSize(300, 150); // Aumentar el tamaño de la ventana para mostrar mejor los contenidos
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new main();
    }
}
