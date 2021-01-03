package edu.upc.dsa;


import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//objetivo factoria
//crea una instancia de una sesión y utilizando driver del proveedor base datos establece la conexión
public class FactorySessionManager {

    final static Logger logger = Logger.getLogger(FactorySessionManager.class);

    public static SessionManager openSession(){

        //nos falta hacer la conexion base datos
        //connection conn/ahora ya está hecho
        Connection connection = getConnection();

        //información importante cuando yo inicie conexión con la base datos
        //he de conectarme al puerto 3306

        SessionManager session = new SessionManagerImpl(connection);
        return session;
    }

    //aqui vamos a crear la conexión a nuestra base datos con el driver
    public static Connection getConnection(){
        Connection conn = null;
        //vamos a configurar una url para que se pueda conectar correctamente a la base de datos
        String url = "jdbc:mysql://localhost:3306/eetacwarsdb";
        try {
            conn = DriverManager.getConnection(url, "hectorfdezg", "dsa12345");
                    /*DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                            "user=minty&password=greatsqldb");*/
            logger.info("I'm inside!");
        } //lanza excepción si hay algún problema con la base de datos por eso la lanzamos de esta manera
        //tratar excepción
        catch (SQLException ex) {
            // handle any errors
            logger.warn("SQLException: " + ex.getMessage());
            logger.warn("SQLState: " + ex.getSQLState());
            logger.warn("VendorError: " + ex.getErrorCode());
        } // al final no lanzaremos el finally porque está en el session
        return conn;
    }
}
