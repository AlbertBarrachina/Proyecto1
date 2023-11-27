package paneles;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import backend.db;
import main.loader;
import main.main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.text.NumberFormat;

public class Creditos extends JPanel {
	private JLabel labelTitulo, labelCantidad, labelTotal;
	private JTextField campoCantidad;
	private JButton botonComprar;
	private int precioCredito = 10; // Precio por cr�dito en d�lares
	int[] dimensiones = main.getDimensiones();
	String[] cliente = main.getSesion();

	public Creditos() {
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
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;

		labelTitulo = new JLabel("Compra de Cr�ditos");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0;
		constraints.weighty = 0.01;
		add(labelTitulo, constraints);

		labelCantidad = new JLabel("Cantidad de cr�ditos a comprar:");
		labelCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1.0;
		constraints.weighty = 0.01;
		add(labelCantidad, constraints);

		// Campo de texto (numeros) para seleccionar la cantidad de creditos

		JTextField campoCantidad = new JTextField();
		campoCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 1.0;
		constraints.weighty = 0.01;
		add(campoCantidad, constraints);

		campoCantidad.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateTotal();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateTotal();
			}

			private void updateTotal() {
				String cantidadText = campoCantidad.getText();
				try {
					int cantidad = Integer.parseInt(cantidadText);
					int total = cantidad * precioCredito;
					if (cantidad >= 0 && cantidad < 100) {
						labelTotal.setText("Total a pagar: �" + total);
					}else {
						labelTotal.setText("Total a pagar: �ERROR, no se pueden comprar tantos creditos a la vez");
					}
				} catch (NumberFormatException ex) {
					labelTotal.setText("Total a pagar: �0");
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// no hace falta para texo plano pero si para el listener

			}
		});

		botonComprar = new JButton("Comprar");
		botonComprar.setHorizontalAlignment(SwingConstants.CENTER);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.weightx = 1.0;
		constraints.weighty = 0.01;
		add(botonComprar, constraints);
		botonComprar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == botonComprar) {
					String cantidadText = campoCantidad.getText();
					try {
						int cantidad = Integer.parseInt(cantidadText);
						int total = cantidad * precioCredito;
						db.comprarCompras(Integer.parseInt(cliente[0]), cantidad, "Targeta de credito");
						JOptionPane.showMessageDialog(Creditos.this, "Compra realizada por $" + total, "Compra Exitosa",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(Creditos.this, "Ingresa una cantidad v�lida", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		labelTotal = new JLabel("Total a pagar: �0");
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.weightx = 1.0;
		constraints.weighty = 0.5;
		add(labelTotal, constraints);
		
		JButton backButton = new JButton("Volver");
		backButton.addActionListener(e -> {
			loader.cargarPerfil();
		});
		constraints.gridx = 0;
		constraints.gridy = 10;
		constraints.weightx = 1.0;
		constraints.weighty = 0.005; // 0.5% of the vertical space
		add(backButton, constraints);
	}
}