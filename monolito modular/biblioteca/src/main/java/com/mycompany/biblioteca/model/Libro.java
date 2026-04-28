package com.mycompany.biblioteca.model;

public class Libro {

    private String titulo;
    private String autor;
    private boolean prestado;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.prestado = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    public String getEstado() {
        return prestado ? "Prestado" : "Disponible";
    }
}