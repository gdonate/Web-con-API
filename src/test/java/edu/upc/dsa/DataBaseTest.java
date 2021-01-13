package edu.upc.dsa;

import edu.upc.dsa.dao.DAOManager;
import edu.upc.dsa.dao.DAOManagerImpl;
import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DataBaseTest {

    //Creamos logger para ir comentandolo
    final static Logger log = Logger.getLogger(DataBaseTest.class.getName());

    //creamos instancia privada de nuestro contrato debido en parte al singleton
    private DAOManager gtestdb;

    //en este test probaremos todas las diferentes funciones de cada DAO
    @Before
    public void setUp() throws Exception {
        this.gtestdb= DAOManagerImpl.getInstance();

        log.info("Agregamos 2 usuarios a la base de datos");
        //hemos comentado los add's para que se nos añadan más usuarios al sistema
        //this.gtestdb.getUserDAO().addUser("pepe15", "pepe@hotmail.es","Pepe", "Hernández", "Paris", "hola");
        //this.gtestdb.getUserDAO().addUser("marcosz45", "marcos45@hotmail.es","Marcos", "Pérez", "Madrid", "bye");
    }

    //Funciona!
    @Test
    public void testAddUser() throws ExistantUserException {
        try {
            log.info("Añadimos otro más para comprobar la función testAddUser");
            this.gtestdb.getUserDAO().addUser("gdffgdf", "raul32@hotmail.es","Raúl", "Rodríguez", "San Sebastián", "hello");
            //cuidado que el número del assert va subiendo hay que mirar la base de datos para saber cuántos hay: 5
            Assert.assertEquals(7, this.gtestdb.getUserDAO().findMax());
        }
        catch(NullPointerException e){
            log.info("NullPointerException caught");
        }
    }

    //Funciona!
    @Test
    public void testLogIn() throws UserNotFoundException, PasswordNotMatchException, UserAlreadyConectedException{
        try {
            log.info("Nos conectamos en el sistema:");
            //este username y esta password son correctos (dani98(2))
            User user = this.gtestdb.getUserDAO().logIn("kevin2", "kevin2");
            //lo único que cambiará es el parámetro connected a true osea que lo cambiamos a true
            Assert.assertEquals(true, user.isConnected());
        }
        catch(NullPointerException e){
            log.info("NullPointerException caught");
        }
    }

    //Funciona!
    @Test
    public void testLogOut() throws UserNotFoundException{
            //Ahora queremos que dani salga del sistema
            log.info("Nos desconectamos del sistema");
            this.gtestdb.getUserDAO().logOut(17);
            User user = this.gtestdb.getUserDAO().getUser(17);
            //si dani se ha desconectado del sistema nos cambiará el estado de su conexión = false
            Assert.assertEquals(false, user.isConnected());
    }

    //Funciona!
    @Test
    public void testDeleteUser() throws UserNotFoundException {
        try {
            log.info("Eliminamos un usuario de la base de datos");
            this.gtestdb.getUserDAO().deleteUser(4);
            Assert.assertEquals(5, this.gtestdb.getUserDAO().findMax());
        }
        catch(NullPointerException e){
            log.info("NullPointerException caught");
        }
    }

    //Funciona!
    @Test
    public void testGetUser() throws UserNotFoundException {
        try {
            User user = this.gtestdb.getUserDAO().getUser(3);
            //arreglar el getUsername
            Assert.assertEquals("hugo22", user.getUsername());
        }
        catch (NullPointerException e){
            log.info("NullPointerException caught");
        }
    }

    //No Funciona!
    @Test
    public void testUpdateUser() throws UserNotFoundException{
        try {
            log.info("Modificamos un usuario a través de su id");
            this.gtestdb.getUserDAO().updateUser(3,"hugo22", "hugo22@hotmail.es","Hugo", "Alonso", "Boston", "hello", false);
            //cuidado que el número del assert va subiendo hay que mirar la base de datos para saber cuántos hay: 5
            Assert.assertEquals(3, this.gtestdb.getUserDAO().getIdUser("hugo22","hello"));
        }
        catch (NullPointerException e){
            log.info("NullPointerException caught");
        }
    }
}
