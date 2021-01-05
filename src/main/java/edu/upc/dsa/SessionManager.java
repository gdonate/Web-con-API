package edu.upc.dsa;

import edu.upc.dsa.models.UserNotFoundException;
import org.apache.log4j.Logger;
import java.util.*;

import java.sql.SQLException;

public interface SessionManager<E> {

    final static Logger logger = Logger.getLogger(SessionManager.class);

    public void save(Object entity);
    public void close();
    Object get(Class theClass, int ID);
    public void update(Object object, int ID);
    public void delete(Object object, int ID);
    List<Object> findAll(Class theClass);
    //customList
    List<Object> findAll(Class theClass, HashMap params); // de momento no está implementado
    //de momento no la estamos utilizando
    List<Object> query(String query, Class theClass, HashMap params); //alternativa, de momento sin utilidad
    //función devuelve máximo de cualquier tabla
    public int findMax(Class theClass);

    //obtener id de cualquier clase dando su username y password
    public int getId(Class theClass, String username, String password) throws UserNotFoundException;

    //aquí vienen la declaración de tres funciones complementarias para SQL
    //donde se hará: una consulta normal, una consulta preparada para el injection attack con el preparedstatement
    //y para finalizar una transacción con varias funciones dentro
    public void query() throws SQLException;
    public void queryInjection(String parameterInjection) throws SQLException;
    public void transaction() throws SQLException;

}
