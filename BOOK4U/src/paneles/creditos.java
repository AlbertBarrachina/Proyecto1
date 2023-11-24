package paneles;
import javax.swing.*;

import main.main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;

	public class creditos extends JPanel {
	    private JLabel labelTitulo, labelCantidad, labelTotal;
	    private JTextField campoCantidad;
	    private JButton botonComprar;
	    private int precioCredito = 100; // Precio por crédito en dólares
	    int[] dimensiones = main.getDimensiones();
		String[] cliente = main.getSesion();

	    public creditos() {
	    	addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					Dimension newSize = e.getComponent().getSize();
					int altura = newSize.height;
					int ancho = newSize.width;
					main.setDimensiones(altura, ancho);
				}
			});
	    	// establece el display para que sea responsive
//	    	setLayout(null);
			setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.fill = GridBagConstraints.BOTH;

	        labelTitulo = new JLabel("Compra de Créditos");
	        constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.weightx = 1.0;
			constraints.weighty = 0.1;
	        add(labelTitulo, constraints);

	        labelCantidad = new JLabel("Cantidad de créditos a comprar:");
	        constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.weightx = 1.0;
			constraints.weighty = 0.1;
	        add(labelCantidad, constraints);

	        campoCantidad = new JTextField();
	        constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.weightx = 1.0;
			constraints.weighty = 0.1;
	        add(campoCantidad, constraints);

	        botonComprar = new JButton("Comprar");
	        constraints.gridx = 0;
			constraints.gridy = 3;
			constraints.weightx = 1.0;
			constraints.weighty = 0.1;
	        add(botonComprar, constraints);
	        botonComprar.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
		        if (e.getSource() == botonComprar) {
		            String cantidadText = campoCantidad.getText();
		            try {
		                int cantidad = Integer.parseInt(cantidadText);
		                int total = cantidad * precioCredito;
		                labelTotal.setText("Total a pagar: $" + total);
		                JOptionPane.showMessageDialog(creditos.this, "Compra realizada por $" + total, "Compra Exitosa", JOptionPane.INFORMATION_MESSAGE);
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(creditos.this, "Ingresa una cantidad válida", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
	        }
	        });
	        labelTotal = new JLabel("Total a pagar: $0");
	        constraints.gridx = 0;
			constraints.gridy = 4;
			constraints.weightx = 1.0;
			constraints.weighty = 0.1;
	        add(labelTotal, constraints);
	    }	   
	   }