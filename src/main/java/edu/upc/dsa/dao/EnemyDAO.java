package edu.upc.dsa.dao;

import edu.upc.dsa.models.Enemy;

import java.util.List;

public interface EnemyDAO {

    //CRUD Enemy
    public int addEnemy(String name, String type, int life, int map, int positionX, int positionY, int user_id);
    public Enemy getEnemy(int id);
    //update de momento invalidado
    public void updateEnemy(int id, String name, String type, int life, int map, int positionX, int positionY, int user_id);
    public void deleteEnemy(int id);
    public List<Enemy> getEnemies();
    public List<Enemy> getEnemiesByLevel(int level);
    //eliminar un enemigo de un usuario, cuando estén luchando
    public void deleteEnemyUser(int idUser, int id);

    //encuentra el número máximo de enemigos
    public int findMax();
}
