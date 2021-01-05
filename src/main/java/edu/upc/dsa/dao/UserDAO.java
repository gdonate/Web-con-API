package edu.upc.dsa.dao;

import edu.upc.dsa.models.User;

import java.util.List;

public interface UserDAO {

    //CRUD User
    public User logIn(String username, String password);
    public void logOut(int id);
    //addUser = signUp
    public int addUser(String username, String mail, String name, String lastname, String city, String password, boolean connected);
    public User getUser(int id);
    public void updateUser(int id, String username, String mail, String name, String lastname, String city, String password, boolean connected);
    public void deleteUser(int id);
    public List<User> getUsers();
    public List <User> getUsersByLevel(int level);
    public List <User> getUsersByPoints(int points);

    public int getIdUser(String username, String password);

    //CRUD Player
    //actualizar vida del jugador
    public void updateActualLife(int id, int updateActualLife);
    //actualizar contador de puntuación del jugador
    public void updatePoints(int id, int points);
    //actualizar contador de muertes
    public void updateEnemiesKilled(int id, int enemiesKilled);
    //el usuario comprará items según su puntuación
    public void buyItem(int id, int idItem, int points, int price);
    //salvar el estado de la partida
    public void saveStatus(int idMap, int id);
    //acabar la partida
    public void finishPlay(int id);


    //encuentra el número máximo de usuarios
    public int findMax();

}
