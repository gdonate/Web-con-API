package edu.upc.dsa.dao;

import edu.upc.dsa.models.User;

import java.util.List;

public interface UserDAO {

    //CRUD User
    public int addUser(String username, String mail, String name, String lastname, String city, String password, boolean connected);
    public User getUser(int id);
    public void updateUser(int id, String username, String mail, String name, String lastname, String city, String password, boolean connected);
    public void deleteUser(int id);
    public List<User> getUsers();
    public List <User> getUsersByLevel(int level);
    public List <User> getUsersByPoints(int points);

}
