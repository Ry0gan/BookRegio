package com.ryogan.android.bookregio.model;

import java.io.Serializable;

public class Book implements Serializable {
    private String titulo;
    private String autor;
    private String editorial;
    private String año;
    private String imagen;
    private String precio;
    private String categoria;


    public Book(String titulo, String autor, String editorial, String año, String imagen, String precio, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.año = año;
        this.imagen = imagen;
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPrecio(){ return precio; }

    public void setPrecio(String precio) { this.precio = precio; }

    public String getCategoria(){ return categoria; }

    public void setCategoria(String categoria) { this.categoria = categoria; }

}
