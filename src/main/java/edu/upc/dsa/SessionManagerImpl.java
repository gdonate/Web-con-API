package edu.upc.dsa;

import edu.upc.dsa.models.UserNotFoundException;
import edu.upc.dsa.util.ObjectHelper;
import edu.upc.dsa.util.QueryHelper;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SessionManagerImpl implements SessionManager{

    //como siempre variable logger para informar de todos los procesos
    final static Logger logger = Logger.getLogger(SessionManagerImpl.class);

    //creamos variable conexión, evidentemente si no estamos conectados no podemos crear sesión
    private final Connection connection;

    // constructor para inicializar variables
    public SessionManagerImpl(Connection connection){
        //inicializamos esta variable
        //nos servirá para iniciar, cerrar, etc. sesión con la base de datos
        this.connection = connection;
    }


    public void save(Object entity){
        String insertQuery = QueryHelper.createQueryINSERT(entity);

        PreparedStatement pstm = null;

        try {
            pstm = connection.prepareStatement(insertQuery);
            //el setObject con parametro 1 indica que cambiaremos el primer atributo de la entidad por un 0
            //0, alex87, Alex, etc.
            pstm.setObject(1, 0);
            int i = 1;

            //el primer valor que tomará será el pstm.setObject(2, username) y así hasta completar la query
            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //lanzamos las dos excepciones que faltan del getter
        catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //Aquí cerraremos la sesión con la base de datos
    public void close(){
        try{
            this.connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object object, int ID) {
        String updateQuery=QueryHelper.createQueryUPDATE(object);
        PreparedStatement pstm = null;

        //todas estas consultas se basan en que cada entidad/objeto tiene su propio ID
        try {
            pstm = connection.prepareStatement(updateQuery);
            int i = 1;

            //actualizamos todos los atributos de la entidad/objeto a través de un recorrido
            for (String field: ObjectHelper.getFields(object)) {
                pstm.setObject(i++, ObjectHelper.getter(object, field));
            }

            pstm.setObject(i, ID);

            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //lanzamos las dos excepciones que faltan del getter
        catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object object, int ID) {
        String deleteQuery=QueryHelper.createQueryDELETE(object);
        PreparedStatement pstm;
        try{
            pstm = connection.prepareStatement(deleteQuery);
            pstm.setObject(1, ID);
            pstm.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //objetivo función: devuelve el campo de la entidad del objeto que deseemos de la tabla, ej: id, username, etc.
    //al ser privada la función no necesita de implementación en la interfaz
    private String getFieldName(int i, ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        String name = rsmd.getColumnName(i);

        logger.info("Column"+i+ "and Name: "+name);
        return name;
    }

    //esta función no es independiente a otras entidades, solo sirve para User
    @Override
    public int getId(Class theClass, String username, String password) throws UserNotFoundException {
        String selectQuery = QueryHelper.createQuerySELECTID(theClass);
        //inicializamos integer del id debido a error que luego daremos valor real
        int id = 0;

        //preparar valores que hay que pasar a la consulta
        ResultSet rs;
        PreparedStatement pstm;

        try {
            pstm = connection.prepareStatement(selectQuery);
            pstm.setObject(1, username);
            pstm.setObject(2, password);
            rs = pstm.executeQuery();

            //No nos va a devolver directamente el id está una fila por debajo
            rs.next();

            id = rs.getInt(1);

        } catch(SQLException e){
            e.printStackTrace();
        }

        return id;
    }

    //revisar operación get objeto
    @Override
    public Object get(Class theClass, int ID) {
        String selectQuery = QueryHelper.createQuerySELECTClass(theClass);
        Object entity = null;

        //instanciamos nuevo constructor vacío y sin argumentos
        try{
            //creamos constructor nuevo para añadir valores después
            //deprecated theClass.newInstance
            entity = theClass.getDeclaredConstructor().newInstance();
        }
        catch (IllegalAccessException e){
            e.printStackTrace();
        } catch (InstantiationException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e){
            e.printStackTrace();
        } catch (InvocationTargetException e){
            e.printStackTrace();
        }

        ResultSet rs;
        PreparedStatement pstm;

        try{
            pstm = connection.prepareStatement(selectQuery);
            pstm.setObject(1, ID);
            rs = pstm.executeQuery();

            while(rs.next()){
                //dentro de la lista fields guardaremos los atributos de la clase requerida. ej: username, mail, etc.
                Field[] fields = theClass.getDeclaredFields();
                rs.getString(1);
                for(int i = 0; i<fields.length; i++){
                    String fieldName = this.getFieldName(i+2, rs);
                    ObjectHelper.setter(entity, fieldName, rs.getObject(i+2));
                }

            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        } catch (InvocationTargetException e){
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public List<Object> findAll(Class theClass, HashMap params) {
        return null;
    }

    //devuelve toda la lista de objetos
    @Override
    public List<Object> findAll(Class theClass) {
        String findAllQuery = QueryHelper.findAllQuery(theClass);

        Object entity = null;
        List<Object> listObjects = new ArrayList<>();

        try{
            //declaramos de la entidad pasada como parámetro un constructor vacío y sin argumentos
            //deprecated theClass.newInstance
            entity = theClass.getDeclaredConstructor().newInstance();
        }
        //la función que he integrado en el try me pide lanzar todas estas excepciones
        catch (IllegalAccessException e){
            e.printStackTrace();
        } catch (InstantiationException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e){
            e.printStackTrace();
        } catch (InvocationTargetException e){
            e.printStackTrace();
        }

        ResultSet rs;
        PreparedStatement pstm;
        //respuesta
        try{
            pstm = connection.prepareStatement(findAllQuery);
            rs = pstm.executeQuery();

            while(rs.next()){
                Field[] fields = theClass.getSuperclass().getDeclaredFields();
                rs.getString(1);
                for(int i = 0; i<fields.length; i++){
                    ObjectHelper.setter(entity, fields[i].getName(), rs.getObject(i+2));
                }
                //agregamos a la lista el objeto de la tabla
                //add boolean que añade al final de la lista
                listObjects.add(entity);

                //y volvemos a construir constructor vacío sin parámetros para que el siguiente objeto se ponga
                entity = theClass.getDeclaredConstructor().newInstance();
            }
        }
        //otra vez me veo obligado a lanzar todas las excepciones que me pide el try que he hecho arriba
        catch (SQLException e){
            e.printStackTrace();
        } catch (InvocationTargetException e){
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e){
            e.printStackTrace();
        } catch (InstantiationException e){
            e.printStackTrace();
        }
        return listObjects;
    }

    @Override
    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }

    @Override
    public int findMax(Class theClass) {
        String selectMaxQuery = QueryHelper.findMaxQuery(theClass);
        //inicializamos integer del id debido a error que luego daremos valor real
        int idMax = 0;

        //preparar valores que hay que pasar a la consulta
        ResultSet rs;
        PreparedStatement pstm;

        try {
            pstm = connection.prepareStatement(selectMaxQuery);
            rs = pstm.executeQuery();

            //No nos va a devolver directamente el id está una fila por debajo
            rs.next();
            idMax = rs.getInt(1);

        } catch(SQLException e){
            e.printStackTrace();
        }
        return idMax;
    }

    //operación consulta para verificar un tipo de query
    //ejemplo de consulta que luego se borrará
    @Override
    public void query() throws SQLException {
        //objeto para crear sentencias sql
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select idUser, username, lastname from User");
        //el set me devuelve cursor que apunta a una fila
        while(set.next()){
            int idUser = set.getInt("idUser");
            String username = set.getString("username");
            String lastname = set.getString("lastname");
            logger.info("Usuario "+idUser+" with username "+username+" and lastname "+lastname);
        }
        //una vez que hemos terminado de trabajar con cursor
        //cerraremos set y statement
        set.close();
        statement.close();
    }
    //vamos a trebajar con otro tipo de statement para cualquier tipo de inyección SQL
    //tipico ataque injection ARREGLADO
    //createStatement por preparedStatement
    public void queryInjection(String parameterInjection) throws SQLException {
        //objeto para crear sentencias sql
        PreparedStatement statement = connection.prepareStatement("select idUser, username, lastname from User where image = ?");
        //ResultSet set = statement.executeQuery("select idUser, username, lastname from User where image = '" +parameterInjection+"'");
        statement.setString(1, parameterInjection);
        ResultSet set = statement.executeQuery();
        while(set.next()){
            int idUser = set.getInt("idUser");
            String username = set.getString("username");
            String lastname = set.getString("lastname");
            logger.info("Usuario "+idUser+" with username "+username+" and lastname "+lastname);
        }
        //una vez que hemos terminado de trabajar con cursor
        //cerraremos set y statement
        set.close();
        statement.close();
    }

    @Override
    public void transaction() throws SQLException {
        //ponerme en metodo transaccional
        //AutoCommit = cada sentencia tenga su propia transacción modo normal
        //savepoint guardar puntos concretos de las transacciones
        connection.setAutoCommit(false);
        String USER = "INSERT INTO User(idUser, name, lastname) VALUES(?,?,?)";
        String ITEM = "INSERT INTO Item(idItem, name, type) VALUES(?,?,?)";
        PreparedStatement user=null, item=null;
        try{
            user=connection.prepareStatement(USER);
            user.setInt(1,5);
            user.setString(2, "Pepe");
            user.setString(3,"Luís");
            //nos va a devolver un entero con el que NO vamos a hacer nada
            user.executeUpdate();

            item=connection.prepareStatement(ITEM);
            item.setInt(1,7);
            item.setString(2,"espada magica");
            item.setString(3, "Ataque");
            //devuelve entero en INSERT, UPDATE y DELETE
            item.executeUpdate();

            //se ejecutan las dos funciones (toda la transacción) a través del commit
            connection.commit();
            logger.info("¡Se ha ejecutado toda la transacción!");
        } catch(SQLException e){
            //el rollblack es fundamental porque en caso que haya un error en la segunda query
            //volverá atrás y no se ejecutará ni la primera query
            connection.rollback();
        } finally {
            if(user!=null){
                user.close();
            }
            if(item!=null){
                item.close();
            }
        }

    }
}
