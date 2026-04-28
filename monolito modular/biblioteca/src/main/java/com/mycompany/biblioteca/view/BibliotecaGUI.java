package com.mycompany.biblioteca.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BibliotecaGUI extends JFrame {

    private JTextField txtTitulo;
    private JTextField txtAutor;

    private JButton btnAgregar;
    private JButton btnBuscar;
    private JButton btnMostrar;

    private JTable tabla;
    private DefaultTableModel modelo;

    public BibliotecaGUI() {
        iniciarVentana();
        crearComponentes();
    }

    private void iniciarVentana() {
        setTitle("Biblioteca");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void crearComponentes() {

        JPanel panelSuperior = new JPanel(new GridLayout(4,3,5,5));

        panelSuperior.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        panelSuperior.add(txtTitulo);

        panelSuperior.add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        panelSuperior.add(txtAutor);

        btnAgregar = new JButton("Agregar Libro");
        btnBuscar = new JButton("Buscar");
        btnMostrar = new JButton("Mostrar Todos");

        panelSuperior.add(btnAgregar);
        panelSuperior.add(btnBuscar);
        panelSuperior.add(btnMostrar);

        add(panelSuperior, BorderLayout.NORTH);

        modelo = new DefaultTableModel(
                new Object[]{"#", "Título", "Autor", "Estado", "Eliminar"},0
        ){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        tabla = new JTable(modelo);
        tabla.setRowHeight(25);

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    // GETTERS
    public JTextField getTxtTitulo() { return txtTitulo; }
    public JTextField getTxtAutor() { return txtAutor; }

    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnMostrar() { return btnMostrar; }

    public JTable getTabla() { return tabla; }
    public DefaultTableModel getModelo() { return modelo; }
}