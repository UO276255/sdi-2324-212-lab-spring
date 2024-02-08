package com.uniovi.sdi2324212spring.entities;

public class Professor {
    private Long id;
    private String dni;
    private String name;
    private String surname;
    private String category;

    @Override
    public String toString() {
        return "Profesor{"+
                "id= " + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + name + '\'' +
                ", apellidos='" + surname + '\'' +
                ", categoria='" + category + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCategory() {
        return category;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Professor(){}
    public Professor(Long id, String dni, String name, String surname, String categorie) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.category = categorie;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
