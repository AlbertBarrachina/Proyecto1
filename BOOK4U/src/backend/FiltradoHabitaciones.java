package backend;

import java.util.*;
import java.util.List;

import javax.swing.JPanel;

import java.awt.*;

public class FiltradoHabitaciones {
	
	//esta clase no esta en uso!!!
    private List<paneles.Contenido> contenidos;
    private JPanel contentPanel;

    public FiltradoHabitaciones(List<paneles.Contenido> contenidos, JPanel contentPanel) {
        this.contenidos = contenidos;
        this.contentPanel = contentPanel;
    }

    public void filtrarContenidos(String precio, boolean conDescuento, String tipoHabitacion, int numCamas) {
        contentPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(75, 75, 75, 75);

        int count = 0;
        for (paneles.Contenido contenido : contenidos) {
            if (cumpleCriterio(contenido, precio, conDescuento, tipoHabitacion, numCamas)) {
                gbc.gridx = count % 4;
                gbc.gridy = count / 4;
                contentPanel.add(contenido, gbc);
                count++;
            }
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private boolean cumpleCriterio(paneles.Contenido contenido, String precio, boolean conDescuento, String tipoHabitacion, int numCamas) {
        // Implementa la lógica de filtrado aquí
        return true; // Devuelve true si cumple con los criterios
    }
}
