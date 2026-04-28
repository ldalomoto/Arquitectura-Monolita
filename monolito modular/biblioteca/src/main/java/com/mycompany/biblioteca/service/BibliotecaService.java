package com.mycompany.biblioteca.service;

import com.mycompany.biblioteca.model.Libro;
import com.mycompany.biblioteca.repository.BibliotecaRepository;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {

    private final BibliotecaRepository repository;

    public BibliotecaService(BibliotecaRepository repository) {
        this.repository = repository;
    }

    public void agregarLibro(String titulo, String autor) {

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingrese título");
        }

        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingrese autor");
        }

        Libro libro = new Libro(titulo.trim(), autor.trim());
        repository.guardar(libro);
    }

    public List<Libro> listarLibros() {
        return repository.listar();
    }

    public void eliminarLibro(int index) {
        repository.eliminar(index);
    }

    public void cambiarEstado(int index) {
        Libro libro = repository.obtener(index);
        libro.setPrestado(!libro.isPrestado());
    }

    public List<Libro> buscarPorTitulo(String texto) {

        List<Libro> resultado = new ArrayList<>();

        for (Libro libro : repository.listar()) {
            if (libro.getTitulo().toLowerCase()
                    .contains(texto.toLowerCase())) {
                resultado.add(libro);
            }
        }

        return resultado;
    }
}