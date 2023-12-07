package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImagenPerfil extends JButton {
	private Image imagenPerfil;

	public ImagenPerfil() {
		String path = ("src/assets/perfiles/default.png");
		String[] usuario = main.main.getSesion();
		if (Integer.parseInt(usuario[0]) > 0) {
			path = ("src/assets/perfiles/"+usuario[0]+".png");
		}else {
			
		}
		try {
			imagenPerfil = ImageIO.read(new File(path));
			imagenPerfil = imagenPerfil.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			setIcon(new ImageIcon(imagenPerfil));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		setBackground(new Color(245, 245, 220));
		setFocusPainted(false);
		setBorderPainted(false);

		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
			// Hacer el color un poco m�s oscuro cuando se presiona el bot�n
			g.setColor(new Color((int) (getBackground().getRed() * 0.5), (int) (getBackground().getGreen() * 0.5),
					(int) (getBackground().getBlue() * 0.5)));
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
