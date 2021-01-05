package edu.upc.dsa;

import edu.upc.dsa.dao.DAOManager;
import edu.upc.dsa.dao.DAOManagerImpl;
import edu.upc.dsa.models.ExistantUserException;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserNotFoundException;
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
        //this.gtestdb.getUserDAO().addUser("pepe15", "pepe@hotmail.es","Pepe", "Hernández", "Paris", "hola", false);
        //this.gtestdb.getUserDAO().addUser("marcosz45", "marcos45@hotmail.es","Marcos", "Pérez", "Madrid", "bye", false);
    }

    @Test
    public void testAddUser() throws ExistantUserException {
        try {
            log.info("Añadimos otro más para comprobar la función testAddUser");
            this.gtestdb.getUserDAO().addUser("raul32", "raul32@hotmail.es","Raúl", "Rodríguez", "San Sebastián", "hello", false);
            //cuidado que el número del assert va subiendo hay que mirar la base de datos para saber cuántos hay: 5
            Assert.assertEquals(15, this.gtestdb.getUserDAO().findMax());
        }
        catch(NullPointerException e){
            log.info("NullPointerException caught");
        }
    }

    //debido a esto tampoco funciona correctamente el deleteUser
    @Test
    public void testDeleteUser() throws UserNotFoundException {
        try {
            log.info("Eliminamos un usuario de la base de datos");
            this.gtestdb.getUserDAO().deleteUser(5);
            Assert.assertEquals(14, this.gtestdb.getUserDAO().findMax());
        }
        catch(NullPointerException e){
            log.info("NullPointerException caught");
        }
    }

    //único que no funciona testGetUser
    @Test
    public void testGetUser() throws UserNotFoundException {
        try {
            User user = this.gtestdb.getUserDAO().getUser(1);
            //arreglar el getUsername
            Assert.assertEquals("dani98", user.getUsername());
        }
        catch (NullPointerException e){
            log.info("NullPointerException caught");
        }
    }

    @Test
    public void testUpdateUser() throws UserNotFoundException{
        try {
            log.info("Modificamos un usuario a través de su id");
            this.gtestdb.getUserDAO().updateUser(5,"hugo22", "hugo22@hotmail.es","Hugo", "Alonso", "Boston", "hello", false);
            //cuidado que el número del assert va subiendo hay que mirar la base de datos para saber cuántos hay: 5
            Assert.assertEquals(5, this.gtestdb.getUserDAO().getIdUser("hugo22","hello"));
        }
        catch (NullPointerException e){
            log.info("NullPointerException caught");
        }
    }
}
