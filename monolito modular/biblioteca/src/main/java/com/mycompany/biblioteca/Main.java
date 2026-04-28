package com.mycompany.biblioteca;

import com.mycompany.biblioteca.controller.BibliotecaController;
import com.mycompany.biblioteca.repository.BibliotecaRepository;
import com.mycompany.biblioteca.service.BibliotecaService;
import com.mycompany.biblioteca.view.BibliotecaGUI;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            BibliotecaGUI vista = new BibliotecaGUI();

            BibliotecaRepository repository =
                    new BibliotecaRepository();

            BibliotecaService service =
                    new BibliotecaService(repository);

            new BibliotecaController(vista, service);

            vista.setVisible(true);
        });
    }
}