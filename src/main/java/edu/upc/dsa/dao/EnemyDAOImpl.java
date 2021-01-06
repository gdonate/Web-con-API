package edu.upc.dsa.dao;

import edu.upc.dsa.FactorySessionManager;
import edu.upc.dsa.SessionManager;
import edu.upc.dsa.models.Ally;
import edu.upc.dsa.models.Enemy;
import edu.upc.dsa.models.Item;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;

public class EnemyDAOImpl implements EnemyDAO {

    //log4j info
    final static Logger log = Logger.getLogger(EnemyDAOImpl.class);
    private static EnemyDAO instance;
    //constructor privado
    private EnemyDAOImpl(){}
    //Singleton
    public static EnemyDAO getInstance() {
        if (instance==null) instance = new EnemyDAOImpl();
        return instance;
    }

    @Override
    public int addEnemy(String name, String type, int life, int map, int positionX, int positionY, int user_id) {
        SessionManager session = null;
        int id = 0;
        try {
            session = FactorySessionManager.openSession();
            Enemy enemy = new Enemy(name, type, life, map, positionX, positionY, user_id);
            session.save(enemy);
        }
        catch (Exception e) {
            log.error("Error añadiendo una entidad", e);
        }
        finally {
            session.close();
        }
        return id;
    }

    @Override
    public Enemy getEnemy(int id) {
        SessionManager session = null;
        Enemy enemy = null;
        try {
            session = FactorySessionManager.openSession();
            enemy = (Enemy)session.get(Enemy.class, id);
        }
        catch (Exception e) {
            log.error("Error al obtener una entidad", e);
        }
        finally {
            session.close();
        }
        return enemy;
    }

    //update de momento invalidado
    @Override
    public void updateEnemy(int id, String name, String type, int life, int map, int positionX, int positionY, int user_id) {
        Enemy enemy = this.getEnemy(id);
        enemy.setName(name);
        enemy.setType(type);
        enemy.setLife(life);
        enemy.setIdMap(map);
        enemy.setPositionX(positionX);
        enemy.setPositionY(positionY);
        enemy.setIdUser(user_id);

        SessionManager session = null;
        try {
            session = FactorySessionManager.openSession();
            //session.update(Enemy.class, id);
        }
        catch (Exception e) {
            log.error("Error al modificar una entidad", e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public void deleteEnemy(int id) {
        Enemy enemy = this.getEnemy(id);
        SessionManager session = null;
        try {
            session = FactorySessionManager.openSession();
            session.delete(Enemy.class, id);
        }
        catch (Exception e) {
            log.error("Error eliminando una entidad", e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<Enemy> getEnemies() {
        SessionManager session = null;
        List<Enemy> enemyList=null;
        try {
            session = FactorySessionManager.openSession();
            enemyList = session.findAll(Enemy.class);
        }
        catch (Exception e) {
            log.error("Error al obtener todas las entidades", e);
        }
        finally {
            session.close();
        }
        return enemyList;
    }

    @Override
    public List<Enemy> getEnemiesByLevel(int level) {
        SessionManager session = null;
        List<Enemy> enemyList=null;
        try {
            session = FactorySessionManager.openSession();

            HashMap<String, Integer> params = new HashMap<String, Integer>();
            params.put("level", level);

            enemyList = session.findAll(Enemy.class, params);
        }
        catch (Exception e) {
            log.error("Error al obtener todos las entidades de un nivel", e);
        }
        finally {
            session.close();
        }
        return enemyList;
    }

    @Override
    public void deleteEnemyUser(int idUser, int id) {

    }

    @Override
    public int findMax() {
        SessionManager session = null;
        int idMax = 0;
        try {
            session = FactorySessionManager.openSession();
            idMax = session.findMax(Enemy.class);
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
