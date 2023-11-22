package paneles;
import javax.swing.*;
import java.awt.event.*;

	public class Creditos extends JPanel {
	    private JLabel labelTitulo, labelCantidad, labelTotal;
	    private JTextField campoCantidad;
	    private JButton botonComprar;
	    private int precioCredito = 100; // Precio por crédito en dólares

	    public Creditos() {
	        setLayout(null);

	        labelTitulo = new JLabel("Compra de Créditos");
	        labelTitulo.setBounds(150, 20, 200, 30);
	        add(labelTitulo);

	        labelCantidad = new JLabel("Cantidad de créditos a comprar:");
	        labelCantidad.setBounds(50, 70, 200, 30);
	        add(labelCantidad);

	        campoCantidad = new JTextField();
	        campoCantidad.setBounds(250, 70, 100, 30);
	        add(campoCantidad);

	        botonComprar = new JButton("Comprar");
	        botonComprar.setBounds(150, 120, 100, 30);
	        add(botonComprar);
	        botonComprar.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
		        if (e.getSource() == botonComprar) {
		            String cantidadText = campoCantidad.getText();
		            try {
		                int cantidad = Integer.parseInt(cantidadText);
		                int total = cantidad * precioCredito;
		                labelTotal.setText("Total a pagar: $" + total);
		                JOptionPane.showMessageDialog(Creditos.this, "Compra realizada por $" + total, "Compra Exitosa", JOptionPane.INFORMATION_MESSAGE);
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(Creditos.this, "Ingresa una cantidad válida", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
	        }
	        });
	        labelTotal = new JLabel("Total a pagar: $0");
	        labelTotal.setBounds(120, 170, 150, 30);
	        add(labelTotal);
	    }	   
	   }