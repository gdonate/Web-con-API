package edu.upc.dsa.dao;

import edu.upc.dsa.FactorySessionManager;
import edu.upc.dsa.SessionManager;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

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

    @Override
    public User logIn(String username, String password) {
        return null;
    }

    @Override
    public void logOut(int id) {

    }

    @Override
    public int addUser(String username, String mail, String name, String lastname, String city, String password, boolean connected) {
        SessionManager session = null;
        int id = 0;
        try {
            session = FactorySessionManager.openSession();
            User user = new User(username, mail, name, lastname, city, password, connected);
            session.save(user);
        }
        catch (Exception e) {
            log.error("Error añadiendo una entidad", e);
        }
        finally {
            session.close();
        }
        return id;
    }

    //me falla el getUser
    @Override
    public User getUser(int id) {
        SessionManager session = null;
        User user = null;
        try {
            session = FactorySessionManager.openSession();
            user = (User)session.get(User.class, id);
        }
        catch (Exception e) {
            log.error("Error al obtener una entidad", e);
        }
        finally {
            session.close();
        }

        return user;
    }

    @Override
    public void updateUser(int id, String username, String mail, String name, String lastname, String city, String password, boolean connected) {
        User user = this.getUser(id);
        user.setUsername(username);
        user.setMail(mail);
        user.setName(name);
        user.setLastname(lastname);
        user.setCity(city);
        user.setPassword(password);
        user.setConnected(connected);

        SessionManager session = null;
        try {
            session = FactorySessionManager.openSession();
            session.update(User.class, id);
        }
        catch (Exception e) {
            log.error("Error al modificar una entidad", e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public void deleteUser(int id) {
        User user = this.getUser(id);
        SessionManager session = null;
        try {
            session = FactorySessionManager.openSession();
            session.delete(user, id);
        }
        catch (Exception e) {
            log.error("Error eliminando una entidad", e);
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
