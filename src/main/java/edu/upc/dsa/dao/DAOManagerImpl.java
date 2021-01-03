package edu.upc.dsa.dao;

public class DAOManagerImpl implements DAOManager {

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
            users = new UserDAOImpl();
        }
        return users;
    }

    @Override
    public ItemDAO getItemDAO() {
        if(items == null){
            items = new ItemDAOImpl();
        }
        return items;
    }

    @Override
    public EnemyDAO getEnemyDAO() {
        if(enemies == null){
            enemies = new EnemyDAOImpl();
        }
        return enemies;
    }

    @Override
    public AllyDAO getAllyDAO() {
        if(allies == null){
            allies = new AllyDAOImpl();
        }
        return allies;
    }
}
