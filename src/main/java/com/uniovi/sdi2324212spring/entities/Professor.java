package com.uniovi.sdi2324212spring.entities;

public class Professor {
    private Long id;
    private String dni;
    private String nombre;
    private String apellidos;
    private String categoria;

    @Override
    public String toString() {
        return "Profesor{"+
                "id= " + id +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Professor(){}
    public Professor(Long id, String dni, String nombre, String apellidos, String categoria) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.categoria = categoria;
    }

}
