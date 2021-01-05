package edu.upc.dsa.dao;

import edu.upc.dsa.models.Ally;

import java.util.List;

public interface AllyDAO {

    //CRUD Ally
    public int addAlly(String name, String type, int life, int map, int positionX, int positionY, int user_id);
    public Ally getAlly(int id);
    public void updateAlly(int id, String name, String type, int life, int map, int positionX, int positionY, int user_id);
    public void deleteAlly(int id);
    public List<Ally> getAllies();
    public List<Ally> getAlliesByLevel(int level);

    //encuentra el número máximo de aliados
    public int findMax();

}
