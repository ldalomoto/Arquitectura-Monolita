package com.mycompany.espagueti;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ESPAGUETI extends JFrame {

    // ===== DATOS =====
    ArrayList<String> titulos = new ArrayList<>();
    ArrayList<String> autores = new ArrayList<>();
    ArrayList<Boolean> prestados = new ArrayList<>();

    // ===== COMPONENTES =====
    JTextField txtTitulo;
    JTextField txtAutor;
    JTable tabla;
    DefaultTableModel modelo;

    // ===== CONSTRUCTOR =====
    public ESPAGUETI() {
        iniciarVentana();
        crearComponentes();
        setVisible(true); // mostrar al final
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ESPAGUETI());
    }

    // ===== VENTANA =====
    public void iniciarVentana() {
        setTitle("Biblioteca");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    // ===== INTERFAZ =====
    public void crearComponentes() {

        JPanel arriba = new JPanel(new GridLayout(4, 3, 5, 5));

        arriba.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        arriba.add(txtTitulo);

        arriba.add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        arriba.add(txtAutor);

        JButton btnAgregar = new JButton("Agregar Libro");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnMostrar = new JButton("Mostrar Todos");

        arriba.add(btnAgregar);
        arriba.add(btnBuscar);
        arriba.add(btnMostrar);

        add(arriba, BorderLayout.NORTH);

        // ===== TABLA =====
        modelo = new DefaultTableModel(
                new Object[]{"#", "Título", "Autor", "Estado", "Eliminar"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // bloquear edición manual
            }
        };

        tabla = new JTable(modelo);
        tabla.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        // ===== EVENTOS =====
        btnAgregar.addActionListener(e -> agregarLibro());
        btnBuscar.addActionListener(e -> buscarLibro());
        btnMostrar.addActionListener(e -> actualizarTabla());

        // Enter agrega libro
        txtAutor.addActionListener(e -> agregarLibro());

        // CLICK TABLA
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int filaVista = tabla.getSelectedRow();
                int columna = tabla.getSelectedColumn();

                if (filaVista < 0) return;

                // como no hay sorter, filaVista == filaReal
                int fila = filaVista;

                // CAMBIAR ESTADO
                if (columna == 3) {
                    prestados.set(fila, !prestados.get(fila));
                    actualizarTabla();
                }

                // ELIMINAR
                if (columna == 4) {

                    int r = JOptionPane.showConfirmDialog(
                            null,
                            "¿Eliminar libro?",
                            "Confirmar",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (r == JOptionPane.YES_OPTION) {
                        titulos.remove(fila);
                        autores.remove(fila);
                        prestados.remove(fila);
                        actualizarTabla();
                    }
                }
            }
        });
    }

    // ===== ACTUALIZAR TABLA =====
    public void actualizarTabla() {

        modelo.setRowCount(0);

        for (int i = 0; i < titulos.size(); i++) {

            String estado = prestados.get(i) ? "Prestado" : "Disponible";

            modelo.addRow(new Object[]{
                    i + 1,
                    titulos.get(i),
                    autores.get(i),
                    estado,
                    "Eliminar"
            });
        }
    }

    // ===== AGREGAR =====
    public void agregarLibro() {

        String titulo = txtTitulo.getText().trim();
        String autor = txtAutor.getText().trim();

        if (titulo.isEmpty() || autor.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete los campos");
            return;
        }

        titulos.add(titulo);
        autores.add(autor);
        prestados.add(false);

        txtTitulo.setText("");
        txtAutor.setText("");
        txtTitulo.requestFocus();

        actualizarTabla();
    }

    // ===== BUSCAR =====
    public void buscarLibro() {

        String buscar = JOptionPane.showInputDialog("Título a buscar");

        if (buscar == null) return; // canceló

        buscar = buscar.trim();

        if (buscar.isEmpty()) {
            actualizarTabla();
            return;
        }

        modelo.setRowCount(0);

        boolean encontrado = false;

        for (int i = 0; i < titulos.size(); i++) {

            if (titulos.get(i).toLowerCase().contains(buscar.toLowerCase())) {

                String estado = prestados.get(i) ? "Prestado" : "Disponible";

                modelo.addRow(new Object[]{
                        i + 1,
                        titulos.get(i),
                        autores.get(i),
                        estado,
                        "Eliminar"
                });

                encontrado = true;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No encontrado");
            actualizarTabla();
        }
    }
}