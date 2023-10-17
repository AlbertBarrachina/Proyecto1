import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class main extends JFrame {

    public main() {
        // Crear el panel con el BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // Crear los componentes y añadirlos al panel
        JLabel menu = new JLabel("MENU");
        panel.add(menu, BorderLayout.WEST); // Poner el menú en la izquierda

        JPanel topPanel = new JPanel(new BorderLayout()); // Nuevo panel para el título y la búsqueda
        JLabel titulo = new JLabel("TITULO");
        titulo.setHorizontalAlignment(JLabel.CENTER); // Centrar el JLabel
        titulo.setSize(50, 25);
        topPanel.add(titulo, BorderLayout.CENTER);

        JTextField busqueda = new JTextField("Buscar...");
        topPanel.add(busqueda, BorderLayout.SOUTH); // Poner el cuadro de búsqueda debajo del título
        busqueda.setSize(50, 25);
        busqueda.setHorizontalAlignment(JTextField.CENTER);
        panel.add(topPanel, BorderLayout.NORTH); // Poner el panel en la parte superior

        JButton perfil = new JButton("Perfil"); // Puedes usar un icono en lugar de texto
        panel.add(perfil, BorderLayout.EAST); // Poner el perfil en la derecha

        JLabel contenido1 = new JLabel("CONTENIDO");
        JLabel contenido2 = new JLabel("CONTENIDO");
        JLabel contenido3 = new JLabel("CONTENIDO");
        JPanel centerPanel = new JPanel(new GridLayout(1, 3)); // Crear un panel intermedio para el contenido
        centerPanel.add(contenido1);
        centerPanel.add(contenido2);
        centerPanel.add(contenido3);
        panel.add(centerPanel, BorderLayout.CENTER); // Poner el panel intermedio en el centro

        // Añadir el panel al marco
        this.add(panel);

        // Configurar el marco
        this.setTitle("PAGINA PRINCIPAL");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        // Crear una instancia de la clase
        new main();
    }
}
