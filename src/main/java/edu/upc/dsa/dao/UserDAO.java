package edu.upc.dsa.dao;

import edu.upc.dsa.models.*;

import java.util.List;

public interface UserDAO {

    //CRUD User / Retocaremos todas estas funciones continuamente ya que hay algunas que posiblemente estén en desuso
    //hemos lanzado excepciones en varias funciones para ver si se arregla error en el getUser
    public User logIn(String username, String password) throws Exception;
    public void logOut(int id) throws UserNotFoundException;
    //addUser = signUp
    public void addUser(String username, String mail, String name, String lastname, String city, String password) throws Exception;
    public User getUser(int id) throws UserNotFoundException;
    public User getUser(String username) throws UserNotFoundException;
    public void updateUser(int id, String username, String mail, String name, String lastname, String city, String password, boolean connected) throws UserNotFoundException;
    public void deleteUser(int id) throws UserNotFoundException;
    public List<User> getUsers();
    public List <User> getUsersByLevel(int level);
    public List <User> getUsersByPoints(int points);

    public int getIdUser(String username, String password) throws UserNotFoundException;

    //CRUD Player
    //actualizar vida del jugador
    public void updateActualLife(int id, int updateActualLife) throws UserNotFoundException;
    //actualizar contador de puntuación del jugador
    public void updatePoints(int id, int points) throws UserNotFoundException;
    //actualizar contador de muertes
    public void updateEnemiesKilled(int id, int enemiesKilled) throws UserNotFoundException;
    //el usuario comprará items según su puntuación
    public void buyItem(int id, int idItem, int points, int price) throws UserNotFoundException;
    //salvar el estado de la partida
    public void saveStatus(int idMap, int id) throws UserNotFoundException;
    //acabar la partida
    public void finishPlay(int id) throws UserNotFoundException;


    //encuentra el número máximo de usuarios
    public int findMax();

}
