package edu.upc.dsa;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.*;

public class GameManagerImpl implements GameManager{

    HashMap<String,User> listausuarios = new HashMap<String,User>();
    List<Objeto> listaObjetos = new LinkedList<>();

    static final Logger logger = Logger.getLogger(GameManagerImpl.class.getName());
    private static GameManagerImpl manager;

    //Singleton
    public static GameManagerImpl getInstance(){
        if(manager==null){
            manager= new GameManagerImpl();
        }
        return manager;
    }
    public GameManagerImpl(){}

    @Override
    public List<User> listaUsuarios() {
        List<User> resultado = new LinkedList<>(listausuarios.values());

        Collections.sort(resultado, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getNombre().compareToIgnoreCase(o2.getNombre());
            }
        });
        logger.info("Lista ordenada alfabeticamente: " + resultado.toString());
        return resultado;
    }

    @Override
    public void addUser(String n, String a, String id) {
        User user = new User(n,a,id);
        listausuarios.put(id,user);
    }

    @Override
    public User modificarUser(String n, String a, String id) {
        User user = this.listausuarios.get(id);
        if (user != null){ //Si lo encuentro
            user.setNombre(n);
            user.setApellido(a);
            user.setId(id);
            logger.info("Updated User parameters:" + user);
        }
        return user;
    }

    @Override
    public int numeroUsers() {
        return this.listausuarios.size();
    }

    @Override
    public User consultarUser(String id) {
        User user = getUserById(id);
        if (user != null){
            logger.info("User Found: " + user);
        }
        else
            logger.error("User not found for id: " + id);
        return user;
    }

    @Override
    public List<Objeto> consultarObjeto(String id) {
        User user = getUserById(id);
        List<Objeto> objetoList = user.getListaobjetos();
        for (Objeto objeto: objetoList){
            logger.info(objeto.getId());
            logger.info(objeto.getNombre());
        }
        return objetoList;
    }

    @Override
    public int numeroObjetosUser(String id) {
        User user =  getUserById(id);
        return user.getListaobjetos().size();
    }

    @Override
    public void addObjetoToUser(String idU, String idO) {
        User user = getUserById(idU);
        Objeto objeto = getObjetoByName(idO);
        List<Objeto> listaTemp = user.getListaobjetos();
        if (user != null){ //Si encuentro el usuario
            logger.info("User Found");
            listaTemp.add(objeto);
            user.setListaobjetos(listaTemp);
        }
        else
            logger.error("User not found for id: " + idU);
    }

    @Override
    public void addObjetoListaObjetos(Objeto objeto) {
        listaObjetos.add(objeto);
    }

    @Override
    public Objeto getObjetoByName(String idobjeto) {
        Objeto objetoTemp = null;
        for (Objeto objeto: this.listaObjetos){
            if (objeto.getId().compareTo(idobjeto)==0) {
                objetoTemp = objeto;
            }
        }
        return objetoTemp;
    }

    @Override
    public void clear() {
        this.listaObjetos.clear();
        this.listausuarios.clear();
    }

    @Override
    public User getUserById(String Id) {
        User user = this.listausuarios.get(Id);
        return user;
    }

    @Override
    public int numeroObjetosListaAPI() {
        return this.listaObjetos.size();
    }

    @Override
    public List<User> findAll() {
        if (listaObjetos.size() != 0){
            List<User> list = new LinkedList<>(listausuarios.values());
            return list;
        }
        return null;
    }
}
