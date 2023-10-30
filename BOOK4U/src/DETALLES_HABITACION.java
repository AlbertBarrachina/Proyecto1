
import javax.swing.*;
import java.awt.*;

public class DETALLES_HABITACION extends JPanel {
    public DETALLES_HABITACION(String texto, JFrame frame, JPanel mainPanel) {
        setLayout(new BorderLayout());
        
        JLabel label = new JLabel(texto);
        add(label, BorderLayout.CENTER);
        
        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> {
            frame.setContentPane(mainPanel);
            frame.revalidate();
        });
        add(backButton, BorderLayout.SOUTH);
    }
}
