package edu.upc.dsa.dao;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import org.apache.log4j.Logger;

public class DAOManagerImpl implements DAOManager {

    //log4j info
    //en principio no creo que lo necesitemos
    final static Logger log = Logger.getLogger(DAOManagerImpl.class);
    private static DAOManager instance;
    //constructor privado
    private DAOManagerImpl(){}
    //Singleton
    public static DAOManager getInstance() {
        if (instance==null) instance = new DAOManagerImpl();
        return instance;
    }

    //creamos las variables de cada dao y gestionamos la obtención de cada DAO abajo
    private UserDAO users = null;
    private ItemDAO items = null;
    private EnemyDAO enemies = null;
    private AllyDAO allies = null;

    //la conexión a la base datos supondremos que el propio DAO ya la hace a través de la factoria
    //y por lo tanto no hay que pasarle la connection dentro de la clase de cada DAO
    @Override
    public UserDAO getUserDAO() {
        if(users == null){
            this.users = UserDAOImpl.getInstance();
        }
        return users;
    }

    @Override
    public ItemDAO getItemDAO() {
        if(items == null){
            this.items = ItemDAOImpl.getInstance();
        }
        return items;
    }

    @Override
    public EnemyDAO getEnemyDAO() {
        if(enemies == null){
            this.enemies = EnemyDAOImpl.getInstance();
        }
        return enemies;
    }

    @Override
    public AllyDAO getAllyDAO() {
        if(allies == null){
            this.allies = AllyDAOImpl.getInstance();
        }
        return allies;
    }
}
