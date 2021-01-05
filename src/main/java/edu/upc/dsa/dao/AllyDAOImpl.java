package edu.upc.dsa.dao;

import edu.upc.dsa.FactorySessionManager;
import edu.upc.dsa.SessionManager;
import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;

public class AllyDAOImpl implements AllyDAO {

    //log4j info
    final static Logger log = Logger.getLogger(AllyDAOImpl.class);
    private static AllyDAO instance;
    //constructor privado
    private AllyDAOImpl(){}
    //Singleton
    public static AllyDAO getInstance() {
        if (instance==null) instance = new AllyDAOImpl();
        return instance;
    }

    @Override
    public int addAlly(String name, String type, int life, int map, int positionX, int positionY, int user_id) {
        SessionManager session = null;
        int id = 0;
        try {
            session = FactorySessionManager.openSession();
            Ally ally = new Ally(name, type, life, map, positionX, positionY, user_id);
            session.save(ally);
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
    public Ally getAlly(int id) {
        SessionManager session = null;
        Ally ally = null;
        try {
            session = FactorySessionManager.openSession();
            ally = (Ally)session.get(Ally.class, id);
        }
        catch (Exception e) {
            log.error("Error al obtener una entidad", e);
        }
        finally {
            session.close();
        }
        return ally;
    }

    @Override
    public void updateAlly(int id, String name, String type, int life, int map, int positionX, int positionY, int user_id) {
        Ally ally = this.getAlly(id);
        ally.setName(name);
        ally.setType(type);
        ally.setLife(life);
        ally.setIdMap(map);
        ally.setPositionX(positionX);
        ally.setPositionY(positionY);
        ally.setIdUser(user_id);

        SessionManager session = null;
        try {
            session = FactorySessionManager.openSession();
            session.update(Ally.class, id);
        }
        catch (Exception e) {
            log.error("Error al modificar una entidad", e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public void deleteAlly(int id) {
        Ally ally = this.getAlly(id);
        SessionManager session = null;
        try {
            session = FactorySessionManager.openSession();
            session.delete(Ally.class, id);
        }
        catch (Exception e) {
            log.error("Error eliminando una entidad", e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<Ally> getAllies() {
        SessionManager session = null;
        List<Ally> allyList=null;
        try {
            session = FactorySessionManager.openSession();
            allyList = session.findAll(Ally.class);
        }
        catch (Exception e) {
            log.error("Error al obtener todas las entidades", e);
        }
        finally {
            session.close();
        }
        return allyList;
    }

    @Override
    public List<Ally> getAlliesByLevel(int level) {
        SessionManager session = null;
        List<Ally> allyList=null;
        try {
            session = FactorySessionManager.openSession();

            HashMap<String, Integer> params = new HashMap<String, Integer>();
            params.put("level", level);

            allyList = session.findAll(Ally.class, params);
        }
        catch (Exception e) {
            log.error("Error al obtener todos las entidades de un nivel", e);
        }
        finally {
            session.close();
        }
        return allyList;
    }

    @Override
    public int findMax() {
        SessionManager session = null;
        int idMax = 0;
        try {
            session = FactorySessionManager.openSession();
            idMax = session.findMax(Ally.class);
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
