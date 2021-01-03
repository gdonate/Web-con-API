package edu.upc.dsa.dao;

public interface DAOManager {

    //te devuelve los daos de cada entidad

    //creemos que falta alguna modificación en los diferentes DAOs
    UserDAO getUserDAO();
    ItemDAO getItemDAO();
    EnemyDAO getEnemyDAO();
    AllyDAO getAllyDAO();

}
