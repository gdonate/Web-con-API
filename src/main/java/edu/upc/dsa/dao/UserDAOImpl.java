package edu.upc.dsa.dao;

import edu.upc.dsa.FactorySessionManager;
import edu.upc.dsa.SessionManager;
import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    //log4j info
    final static Logger log = Logger.getLogger(UserDAOImpl.class);
    private static UserDAO instance;
    //constructor privado
    private UserDAOImpl(){}
    //Singleton
    public static UserDAO getInstance() {
        if (instance==null) instance = new UserDAOImpl();
        return instance;
    }

    //implementar logIn
    @Override
    public User logIn(String username, String password) throws UserNotFoundException, PasswordNotMatchException, UserAlreadyConectedException {
        int id = getIdUser(username,password);
        SessionManager session = null;
        User user = null;
        try{
            session = FactorySessionManager.openSession();
            user = (User)session.get(User.class, id);
            if(user==null) throw new UserNotFoundException();
            if(!password.equals(user.getPassword())) throw new PasswordNotMatchException();
        } catch (Exception e) {
            log.error("Error al logearse en el sistema", e);
        }
        finally {
            session.close();
        }
        //si el estado del usuario conectado es false no estará conectado
        if(user.isConnected() == false) {
            //cambiamos el estado booleano a true
            user.setConnected(true);

            try {
                session = FactorySessionManager.openSession();
                session.update(user, id, false);
            } catch (Exception e) {
                throw new UserNotFoundException();
            } finally {
                session.close();
            }
        }
        else{
            throw new UserAlreadyConectedException();
        }
        return user;
    }

    //implementar la desconexión del sistema
    @Override
    public void logOut(int id) throws UserNotFoundException{
        SessionManager session = null;
        User user;

        try{
            session = FactorySessionManager.openSession();
            user = (User)session.get(User.class, id);
            user.setConnected(false);
            session.update(user, id, false);
        } catch (Exception e){
            log.error("Error al desconectar la entidad", e);
            throw new UserNotFoundException();
        } finally {
            session.close();
        }
    }

    @Override
    public void addUser(String username, String mail, String name, String lastname, String city, String password) {
        User user = this.getUser(username);
        SessionManager session = null;
        //posible lista para los items del usuario
        List<Item> items;
        try {
            if (user == null) {
                session = FactorySessionManager.openSession();
                //segundo constructor de la clase User.class
                user = new User(username, mail, name, lastname, city, password, false);
                session.save(user);
                //posible implementación de la lista de items y demás propiedades del usuario
            }
            else {
                throw new ExistantUserException();
            }
        }
        catch (Exception e) {
            log.error("Error añadiendo una entidad", e);
        }
        finally {
            session.close();
        }
    }

    //me falla el getUser / por si acaso lanzamos el UserNotFoundException para ver que no sea problema de excepción
    @Override
    public User getUser(int id) throws UserNotFoundException {
        SessionManager session = null;
        User user = null;
        try {
            session = FactorySessionManager.openSession();
            user = (User)session.get(User.class, id);
        }
        catch (Exception e) {
            log.error("Error al obtener una entidad", e);
            throw new UserNotFoundException();
        }
        finally {
            session.close();
        }

        return user;
    }

    @Override
    public User getUser(String username) {
        SessionManager session = null;
        User user = null;
        try {
            session = FactorySessionManager.openSession();
            user = (User)session.get(User.class, username);
        }
        catch (Exception e) {
            log.error("Error al obtener una entidad", e);
        }
        finally {
            session.close();
        }
        return user;
    }

    //probamos de lanzar excepciones tanto en el update parámetros como en el de eliminar usuario
    @Override
    public void updateUser(int id, String username, String mail, String name, String lastname, String city, String password, boolean connected) throws UserNotFoundException {
        User user = this.getUser(id);
        user.setUsername(username);
        user.setMail(mail);
        user.setName(name);
        user.setLastname(lastname);
        user.setCity(city);
        user.setPassword(password);
        //la conexión no la puede gestionar el usuario
        user.setConnected(connected);

        SessionManager session = null;
        try {
            session = FactorySessionManager.openSession();
            session.update(user, id, connected);
        }
        catch (Exception e) {
            log.error("Error al modificar una entidad", e);
            throw new UserNotFoundException();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void deleteUser(int id) throws UserNotFoundException{
        User user = this.getUser(id);
        SessionManager session = null;
        try {
            session = FactorySessionManager.openSession();
            session.delete(user, id);
        }
        catch (Exception e) {
            log.error("Error eliminando una entidad", e);
            throw new UserNotFoundException();
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<User> getUsers() {
        SessionManager session = null;
        List<User> userList=null;
        try {
            session = FactorySessionManager.openSession();
            userList = session.findAll(User.class);
        }
        catch (Exception e) {
            log.error("Error al obtener todas las entidades", e);
        }
        finally {
            session.close();
        }
        return userList;
    }

    @Override
    public List<User> getUsersByLevel(int level) {
        SessionManager session = null;
        List<User> userList=null;
        try {
            session = FactorySessionManager.openSession();

            HashMap<String, Integer> params = new HashMap<String, Integer>();
            params.put("level", level);

            userList = session.findAll(User.class, params);
        }
        catch (Exception e) {
            log.error("Error al obtener todos las entidades de un nivel", e);
        }
        finally {
            session.close();
        }
        return userList;
    }

    @Override
    public List<User> getUsersByPoints(int points) {
        SessionManager session = null;
        List<User> userList=null;
        try {
            session = FactorySessionManager.openSession();

            HashMap<String, Integer> params = new HashMap<String, Integer>();
            params.put("points", points);

            userList = session.findAll(User.class, params);
        }
        catch (Exception e) {
            log.error("Error al obtener todos las entidades de una puntuación", e);
        }
        finally {
            session.close();
        }
        return userList;
    }

    @Override
    public int getIdUser(String username, String password) {
        SessionManager session = null;
        int idUser = 0;
        try {
            session = FactorySessionManager.openSession();
            idUser = session.getId(User.class, username, password);
        }
        catch (Exception e) {
            log.error("Error al obtener una entidad", e);
        }
        finally {
            session.close();
        }
        return idUser;
    }

    @Override
    public void updateActualLife(int id, int updateActualLife) {

    }

    @Override
    public void updatePoints(int id, int points) {

    }

    @Override
    public void updateEnemiesKilled(int id, int enemiesKilled) {

    }

    @Override
    public void buyItem(int id, int idItem, int points, int price) {

    }

    @Override
    public void saveStatus(int idMap, int id) {

    }

    @Override
    public void finishPlay(int id) {

    }

    @Override
    public int findMax() {
        SessionManager session = null;
        int idMax = 0;
        try {
            session = FactorySessionManager.openSession();
            //revisar esta función
            idMax = session.findMax(User.class);
        }
        catch (Exception e) {
            log.error("Error al encontrar el número máximo de entidades", e);
        }
        finally {
            session.close();
        }
        return idMax;
    }
}
