package com.valdiviezo.aniss.anahivaldiviezocursoandroid;

/**
 * Created by Aniss on 15/09/2015.
 */

public class Alumno {

    String nombre;
    String apellido;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Alumno(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
