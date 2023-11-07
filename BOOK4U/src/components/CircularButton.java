package components;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class CircularButton extends JButton {
    public CircularButton(String text, Color color) {
        super(text);
        setPreferredSize(new Dimension(100, 100));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setBackground(color);
        setForeground(Color.WHITE); // Establecer el color de texto (si lo necesitas)
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            // Hacer el color un poco m�s oscuro cuando se presiona el bot�n
            g.setColor(new Color(
                (int) (getBackground().getRed() * 0.5),
                (int) (getBackground().getGreen() * 0.5),
                (int) (getBackground().getBlue() * 0.5)
            ));
        } else {
            g.setColor(getBackground());
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.fill(new Ellipse2D.Double(0, 0, getWidth(), getHeight()));
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getForeground());
        g2.draw(new Ellipse2D.Double(0, 0, getWidth(), getHeight()));
    }
}