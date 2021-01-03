package edu.upc.dsa.dao;

import edu.upc.dsa.FactorySessionManager;
import edu.upc.dsa.SessionManager;
import edu.upc.dsa.models.Enemy;
import edu.upc.dsa.models.Item;

import java.util.HashMap;
import java.util.List;

public class EnemyDAOImpl implements EnemyDAO {
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
            // LOG
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
            // LOG
        }
        finally {
            session.close();
        }
        return enemy;
    }

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
            session.update(Enemy.class, id);
        }
        catch (Exception e) {
            // LOG
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
            // LOG
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
            // LOG
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
            // LOG
        }
        finally {
            session.close();
        }
        return enemyList;
    }
}
