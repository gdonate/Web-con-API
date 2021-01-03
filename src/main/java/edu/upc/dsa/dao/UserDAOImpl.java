package edu.upc.dsa.dao;

import edu.upc.dsa.FactorySessionManager;
import edu.upc.dsa.SessionManager;
import edu.upc.dsa.models.User;

import java.util.HashMap;
import java.util.List;

public class UserDAOImpl implements UserDAO {
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
            // LOG
        }
        finally {
            session.close();
        }
        return id;
    }

    @Override
    public User getUser(int id) {
        SessionManager session = null;
        User user = null;
        try {
            session = FactorySessionManager.openSession();
            user = (User)session.get(User.class, id);
        }
        catch (Exception e) {
            // LOG
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
            // LOG
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
            session.delete(User.class, id);
        }
        catch (Exception e) {
            // LOG
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
            // LOG
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
            // LOG
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
            // LOG
        }
        finally {
            session.close();
        }
        return userList;
    }
}
