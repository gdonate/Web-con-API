package edu.upc.dsa;

import java.sql.Connection;
import java.io.File;
import java.io.FileReader;

import java.sql.*;
import java.util.Properties;
import java.sql.DriverManager;

public class DBJDBC {

    //implementar ORM
    //O
    //R
    //Mapping Convenció

    //desenvolupar Framework ORM: Factoria-Sessió i QueyHelper i ObjectHelper
    //que totes les entitas es puguin guardar a la base de dades
    //en calent i en temps real generi les querys

    //datos principales mariaDB
    //user=root / password=dsa12345 ahora ha variado pass:Mazinger72

    //datos miusuario especifico

    //crearemos un framework per DAOS semblant a Jersey
    //serà framework molt limitat

    //de momento esta clase no tiene ninguna implementación
    //Metodo que incia y devuelve la conexion con la BBDD
    public static Connection getConnection() throws SQLException {
        Connection connection = null;

        try{
            Properties p = new Properties();
            //Seleccionem fitxer de properties
            File dbPathPropertiesFile = new File("src/main/resources/db.properties");
            FileReader r = new FileReader(dbPathPropertiesFile);
            p.load(r);
            String host = p.getProperty("db.host");
            String port = p.getProperty("db.port");
            String user = p.getProperty("db.username");
            String db = p.getProperty("db.database");
            String pswd = p.getProperty("db.password");

            //Connexió
            connection = DriverManager.getConnection("jdbc:mariadb://"+host+":"+port+"/"
                    +db+"?user="+user+"&password="+pswd);

        } catch (Exception e){
            e.printStackTrace();
        }

        //Retornem connexió
        return connection;
    }

}
