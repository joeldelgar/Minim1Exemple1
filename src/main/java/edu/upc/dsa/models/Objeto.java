package edu.upc.dsa.models;

public class Objeto {
    private String nombre;
    private String id;

    public Objeto(){}

    public Objeto(String n, String i){
        this.nombre=n;
        this.id = i;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
