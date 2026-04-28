package com.mycompany.biblioteca.repository;

import com.mycompany.biblioteca.model.Libro;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaRepository {

    private final ArrayList<Libro> libros = new ArrayList<>();

    public void guardar(Libro libro) {
        libros.add(libro);
    }

    public List<Libro> listar() {
        return libros;
    }

    public Libro obtener(int index) {
        return libros.get(index);
    }

    public void eliminar(int index) {
        libros.remove(index);
    }
}