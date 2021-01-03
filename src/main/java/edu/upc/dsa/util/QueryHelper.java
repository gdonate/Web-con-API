package edu.upc.dsa.util;

import edu.upc.dsa.SessionManager;
import org.apache.log4j.Logger;

import java.util.*;


//es una caja magica que nos permite construir diferentes querys: INSERT, SELECT, UPDATE, DELETE
//lo construye en caliente querys necesarias para hacer querys

//TANTO EL QUERYHELPER COMO EL OBJECTHELPER VA A SER NUESTRO FRAMEWORK DE TRABAJO
//ayuda a hacer sentencias SQL
public class QueryHelper {

    final static Logger logger = Logger.getLogger(QueryHelper.class);

    //es una query para isertar algo en cualquier entidad
    //puede ser en vez de un entity un class
    public static String createQueryINSERT(Object entity) {

        //concatenamos strings
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        //si hay problemas quitamos la ruta edu.upc.dsa.util. del object helper
        //nos hace introspección recupera campos de la entidad en el objectHelper
        String [] fields = ObjectHelper.getFields(entity);

        //de momento supondremos que NO todas las clases deben tener id
        //sb.append("ID");
        for (String field: fields) {
            //sb.append(", ").append(field);
            sb.append(field);
            sb.append(", ");
        }

        //borrar la coma final
        sb.delete(sb.length() -1, sb.length());
        sb.append(") VALUES (");

        for (String field: fields) {
            sb.append("? ");
            sb.append(", ");
        }

        sb.delete(sb.length() -1, sb.length());
        sb.append(");"); //hemos añadido la coma por si acaso al final de la sentencia
        //valorar de poner ; después de cada sentencia?, después de where id = ?
        //sb.append(";");

        //resultado imaginario
        //INSERT INTO User(ID, lastname, firstName, address, city) VALUES (0, ? , ? , ? , ?)
        //mete tantos ids como parametros tenemos en la clase a través de sentencias preparadas
        return sb.toString();
    }

    public static String createQuerySELECTID(Class theClass) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ID FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE username = ? ").append("AND password = ?;");
        //sb.append(";");

        return sb.toString();
    }

    //hace un select en función del id dependiendo de la entidad de ese objeto
    public static String createQuerySELECT(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ID = ?;");
        //sb.append(";");

        return sb.toString();
    }

    //hace un select en función del id de dependiendo de la clase de ese objeto
    public static String createQuerySELECTClass(Class theClass) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE ID = ?;");
        //sb.append(";");

        return sb.toString();
    }

    //aqui adjuntamos comanda delete para cualquier entidad
    public static String createQueryDELETE(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(entity.getClass().getSimpleName()).append(" ");
        sb.append(" WHERE ID = ?;");
        //sb.append(";");

        return sb.toString();
    }

    public static String createQuerySELECTFINDALL(Class theClass, HashMap<String, String> params) {

        Set<Map.Entry<String, String>> set = params.entrySet();

        //redundancia en where id=1
        StringBuffer sb = new StringBuffer("SELECT * FROM "+theClass.getSimpleName()+"WHERE I=1");
        for(String key: params.keySet()){
            sb.append(" AND "+key+"=?");
        }
        sb.append(";");

        return sb.toString();
    }

    //nos faltaria adjuntar y crear la comanda update
    //lanzaremos las excepciones en los dao
    public static String createQueryUPDATE(Object entity) {

        StringBuffer sb = new StringBuffer();
        //if(entity.getClass().getSuperclass().getSimpleName().equals("Object"))

        sb.append("UPDATE ").append(entity.getClass().getSimpleName()).append(" ").append("SET");

        String [] fields = ObjectHelper.getFields(entity);

        //de momento supondremos que en todas las clases NO es obligatorio el id
        //sb.append(" ID");
        for (String field: fields) {
            sb.append(" ").append(field);
            sb.append(" = ?,");
        }
        //ahora eliminamos la coma que queda en el último atributo e indicamos que id queremos
        sb.delete(sb.length()-1, sb.length());
        sb.append(" WHERE ID = ?;");
        //sb.append(";");

        //y devolvemos la consulta al usuario
        return sb.toString();

    }

    /* nos faltan 2 consultas:
    - encontrar la lista de todos los objetos de una de las tablas de la base de datos
    - encontrar los objetos que cumplen una determinada condición
    */

    //devuelve todos los objetos de una tabla
    public static String findAllQuery(Class theClass){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName()).append(";");

        return sb.toString();
    }

    //objetos con una determinada condición / REPASAR
    public static String findAllQuery(Class theClass, HashMap params){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName()).append(" ");
        sb.append(" WHERE username = ? ").append("AND password = ?;");

        return sb.toString();
    }
}