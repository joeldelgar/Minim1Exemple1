package edu.upc.dsa;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.User;

import java.util.List;

public interface GameManager {
    public List<User> listaUsuarios();
    public void addUser(String n, String a, String id);
    public User modificarUser(String n, String a, String id);
    public int numeroUsers();
    public User consultarUser(String id);
    public List<Objeto> consultarObjeto(String id);
    public int numeroObjetosUser(String id);
    public void addObjetoToUser(String idU, String idO);
    public void addObjetoListaObjetos(Objeto objeto);
    public Objeto getObjetoByName(String objeto);

    public void clear();
    public User getUserById(String Id);
    public int numeroObjetosListaAPI();
    public List<User> findAll();
}
