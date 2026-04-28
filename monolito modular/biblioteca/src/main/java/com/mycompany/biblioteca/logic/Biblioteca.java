package com.mycompany.biblioteca.logic;

import com.mycompany.biblioteca.model.Libro;
import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Libro> libros = new ArrayList<>();

    public void agregarLibro(String titulo, String autor) {
        libros.add(new Libro(titulo, autor));
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public void eliminarLibro(int index) {
        if (index >= 0 && index < libros.size()) {
            libros.remove(index);
        }
    }

    public void cambiarEstado(int index) {
        Libro l = libros.get(index);
        l.setPrestado(!l.isPrestado());
    }
    
    public ArrayList<Libro> buscarLibro(String texto) {

        ArrayList<Libro> encontrados = new ArrayList<>();

        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(texto.toLowerCase())) {
                encontrados.add(libro);
            }
        }

        return encontrados;
    }
}