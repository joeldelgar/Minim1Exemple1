package edu.upc.dsa;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

public class GameTest {
    private static Logger logger = Logger.getLogger(GameTest.class);
    GameManager pm = GameManagerImpl.getInstance();

    public void SetUp() {


        //
        Objeto objeto1 = new Objeto("Espada","1");
        Objeto objeto2 = new Objeto("Escudo","2");
        Objeto objeto3 = new Objeto("Moneda","3");

        //Metemos objetos en la lista
        pm.addObjetoListaObjetos(objeto1);
        pm.addObjetoListaObjetos(objeto2);
        pm.addObjetoListaObjetos(objeto3);


        //Creamos los usuarios (addUser)
        //Añadimos los usuarios al HashMap
        pm.addUser("Joel","Delgado", "1");
        pm.addUser("Esther","Martin", "2");
        pm.addUser("Maria","Garcia", "3");

        pm.addObjetoToUser("3","1"); //Toni tiene Espada
    }

    public void test1(){
        logger.info("ANTES Hay estos usuario " + pm.findAll());
        pm.addUser("Alex","Moya", "8");
        User user = pm.getUserById("8");
        logger.info("Usuario añadido: " + user);
        logger.info("Hay estos usuario " + pm.findAll());
    }

    public void test2(){
        User user = pm.getUserById("3");
        logger.info("Antes tiene: " + pm.consultarObjeto("3")); //Espada
        user.getListaobjetos();
        pm.addObjetoToUser("3","2");
        logger.info("Despues tiene: " + pm.consultarObjeto("3")); //Espada y Escudo
        user.getListaobjetos();
    }

    public void reset(){
        pm.clear();
    }
}
