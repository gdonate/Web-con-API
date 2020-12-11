package edu.upc.dsa;

import edu.upc.dsa.models.ExistantUserException;
import edu.upc.dsa.models.PasswordNotMatchException;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserNotFoundException;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.Null;

public class GameManagerTest {

    //Creamos logger para ir comentandolo
    final static Logger log = Logger.getLogger(GameManagerTest.class.getName());

    //creamos instancia privada de nuestro contrato debido en parte al singleton
    private GameManager gtest;

    //en esta función de prueba inicializaremos todos los casos
    //crearemos juego de pruebas inicial
    @Before
    public void setUp() throws Exception {
        this.gtest=GameManagerImpl.getInstance();

        log.info("Agregamos los 4 primeros usuarios al sistema antes de iniciar el juego");
        this.gtest.addUser("pepe15", "hola","pepe@hotmail.es", "Pepe", "Hernández", "Paris");
        this.gtest.addUser("marcosz45", "bye","marcos45@hotmail.es", "Marcos", "Pérez", "Madrid");
        this.gtest.addUser("raul32", "hello","raul32@hotmail.es", "Raúl", "Rodríguez", "San Sebastián");
        this.gtest.addUser("helenapr", "adios","helenapr@hotmail.es", "Helena", "Mata", "León");

        //nos faltan añadir más parámetros antes de que empiece el juego

    }

    //2 metodos / crear nuevo usuario / procesar una muestra
    @Test
    public void testAddUser() throws ExistantUserException {
        try {
            log.info("Añadimos otro más para comprobar la función testAddUser");
            this.gtest.addUser("blancas", "otra", "blanca@hotmail.es", "Blanca", "Sanchez", "Barcelona");
            Assert.assertEquals(5, this.gtest.numUsers());
        }

        catch(NullPointerException e){
            log.info("NullPointerException caught");
        }
    }

    //limpiamos toda la estructura de datos
    @After
    public void tearDown() {
        try {
            log.info("Limpiamos toda la estructura de datos");
            this.gtest.clear();
        }
        catch(NullPointerException e){
            log.info("NullPointerException caught");
        }
    }

    //añadimos unas cuantas funciones más de prueba
    //vamos a comprobar el getUser del Login pasando username y contraseña
    @Test
    public void testGetUserLogin() throws UserNotFoundException, PasswordNotMatchException {
        try {
            User user = this.gtest.getUserLogin("helenapr", "adios");
            Assert.assertEquals("helenapr", user.getUsername());
        }
        catch (NullPointerException e){
            log.info("NullPointerException caught");
        }
    }

    @Test
    public void testGetUser() throws UserNotFoundException {
        try {
            User user = this.gtest.getUser("helenapr");
            Assert.assertEquals("helenapr", user.getUsername());
        }
        catch (NullPointerException e){
            log.info("NullPointerException caught");
        }
    }

    //vamos a probar de paso todas las excepciones
    @Test(expected = UserNotFoundException.class)
    public void testGetUserLoginNotFound() throws Exception {
        try {
            User user = this.gtest.getUserLogin("andrespr", "andres");
        }
        catch (NullPointerException e){
            log.info("NullPointerException caught");
        }
    }

    @Test(expected = PasswordNotMatchException.class)
    public void testGetUserPasswordNotMatch() throws Exception {
        try {
            User user = this.gtest.getUserLogin("helenapr", "hola");
        }
        catch(NullPointerException e){
            log.info("NullPointerException caught");
        }
    }

    @Test(expected = ExistantUserException.class)
    public void testAddExistingUser() throws Exception {
        try {
            this.gtest.addUser("raul32", "heelloos", "raulloquesea", "Raúl", "Horta", "Barcelona");
        }
        catch (NullPointerException e){
            log.info("NullPointerException caught");
        }
    }







}
