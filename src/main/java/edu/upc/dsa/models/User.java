package edu.upc.dsa.models;

import edu.upc.dsa.GameManagerImpl;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class User {
    private String nombre;
    private String apellido;
    private String id;

    static final Logger logger = Logger.getLogger(GameManagerImpl.class.getName());
    List<Objeto> listaobjetos = null;

    public User(){
    }

    public User(String nombre, String apellido, String id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.listaobjetos = new LinkedList<>();
    }

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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Objeto> getListaobjetos() {
        return listaobjetos;
    }

    public void setListaobjetos(List<Objeto> listaobjetos) {
        this.listaobjetos = listaobjetos;
    }

}
