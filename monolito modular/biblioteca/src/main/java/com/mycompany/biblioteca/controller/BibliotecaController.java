package com.mycompany.biblioteca.controller;

import com.mycompany.biblioteca.model.Libro;
import com.mycompany.biblioteca.service.BibliotecaService;
import com.mycompany.biblioteca.view.BibliotecaGUI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class BibliotecaController {

    private final BibliotecaGUI vista;
    private final BibliotecaService service;

    public BibliotecaController(BibliotecaGUI vista,
                                BibliotecaService service) {
        this.vista = vista;
        this.service = service;

        iniciarEventos();
        actualizarTabla(service.listarLibros());
    }

    private void iniciarEventos() {

        vista.getBtnAgregar().addActionListener(e -> agregarLibro());

        vista.getBtnBuscar().addActionListener(e -> buscarLibro());

        vista.getBtnMostrar().addActionListener(
                e -> actualizarTabla(service.listarLibros())
        );

        vista.getTxtAutor().addActionListener(e -> agregarLibro());

        vista.getTabla().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int fila = vista.getTabla().getSelectedRow();
                int columna = vista.getTabla().getSelectedColumn();

                if (fila < 0) return;

                if (columna == 3) {
                    service.cambiarEstado(fila);
                    actualizarTabla(service.listarLibros());
                }

                if (columna == 4) {

                    int r = JOptionPane.showConfirmDialog(
                            vista,
                            "¿Eliminar libro?",
                            "Confirmar",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (r == JOptionPane.YES_OPTION) {
                        service.eliminarLibro(fila);
                        actualizarTabla(service.listarLibros());
                    }
                }
            }
        });
    }

    private void agregarLibro() {

        try {
            String titulo = vista.getTxtTitulo().getText();
            String autor = vista.getTxtAutor().getText();

            service.agregarLibro(titulo, autor);

            vista.getTxtTitulo().setText("");
            vista.getTxtAutor().setText("");
            vista.getTxtTitulo().requestFocus();

            actualizarTabla(service.listarLibros());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage());
        }
    }

    private void buscarLibro() {

        String texto = JOptionPane.showInputDialog(
                vista,
                "Título a buscar"
        );

        if (texto == null) return;

        texto = texto.trim();

        if (texto.isEmpty()) {
            actualizarTabla(service.listarLibros());
            return;
        }

        List<Libro> lista = service.buscarPorTitulo(texto);

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No encontrado");
            actualizarTabla(service.listarLibros());
        } else {
            actualizarTabla(lista);
        }
    }

    private void actualizarTabla(List<Libro> lista) {

        vista.getModelo().setRowCount(0);

        for (int i = 0; i < lista.size(); i++) {

            Libro libro = lista.get(i);

            vista.getModelo().addRow(new Object[]{
                    i + 1,
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getEstado(),
                    "Eliminar"
            });
        }
    }
}