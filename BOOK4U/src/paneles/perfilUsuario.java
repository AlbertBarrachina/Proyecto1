package paneles;

import java.awt.*;
import javax.swing.*;
import main.*;

public class perfilUsuario extends JPanel {
    public perfilUsuario() {
        // Establece el layout para que sea responsive
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        setBackground(new Color(173, 216, 230)); // Establecer el color de fondo deseado

        // Componente para la foto del usuario
        ImageIcon userPhoto = new ImageIcon("src/assets/perfiles/1.png"); // Asegúrate de cambiar "path/to/user/photo" a la ruta real de la imagen
        JLabel photoLabel = new JLabel(userPhoto);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 4; // La imagen se extiende sobre 4 filas hasta el botón "Volver"
        constraints.weightx = 0.5;
        constraints.weighty = 1.0; // La imagen ocupa el total de la altura disponible hasta el botón "Volver"
        constraints.insets = new Insets(10, 10, 10, 10); // Margen para la etiqueta
        add(photoLabel, constraints);

        // Panel para los datos del usuario
        JPanel userDataPanel = new JPanel();
        userDataPanel.setLayout(new BoxLayout(userDataPanel, BoxLayout.Y_AXIS));
        userDataPanel.add(new JLabel("Nombre: [Nombre del Usuario]")); // Añade más etiquetas según sea necesario
        userDataPanel.setBackground(new Color(173, 216, 230)); // Establecer el color de fondo igual al del panel principal
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1; // El panel de datos ocupa solo 1 fila
        constraints.weightx = 0.5;
        constraints.weighty = 0.1;
        add(userDataPanel, constraints);

        // Botones como en tu código original
        JButton reservasButton = new JButton("Reservas actuales");
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.1;
        add(reservasButton, constraints);
        reservasButton.addActionListener(e -> {
            loader.cargarMisReservas();
        });

        JButton historialButton = new JButton("Histórico de reservas");
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weightx = 0.5;
        constraints.weighty = 0.1;
        add(historialButton, constraints);
        historialButton.addActionListener(e -> {
            loader.cargarHistoricoReservas();
        });

        JButton creditosButton = new JButton("Comprar Créditos");
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.weightx = 0.5;
        constraints.weighty = 0.1;
        add(creditosButton, constraints);
        creditosButton.addActionListener(e -> {
            loader.cargarCreditos();
        });

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> {
            loader.cargarPrincipal();
        });
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2; // El botón de volver se extiende sobre 2 columnas
        constraints.weightx = 1.0;
        constraints.weighty = 0.05;
        add(backButton, constraints);
    }
}
