import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

class CircularButton extends JButton {
    public CircularButton(String text) {
        super(text);
        setPreferredSize(new Dimension(100, 100));
        setContentAreaFilled(true);
        setFocusPainted(true);
        setBorderPainted(true);
        setVisible(true);

        // Agregar un ActionListener si deseas que el bot�n realice alguna acci�n al hacer clic
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Agregar aqu� la acci�n que deseas realizar al hacer clic en el bot�n de perfil
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.RED); // Color cuando se presiona el bot�n
        } else {
            g.setColor(getBackground());
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.fill(new Ellipse2D.Double(0, 0, getWidth(), getHeight()));
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No dibujar borde
    }
}