package edu.upc.dsa.cells;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//tenemos que entender esta clase extendida como pasar de formato JSON a un objeto normal
//si fuese serializar seria al revés pasar de un objeto a un formato JSON

//el objetivo principal de esta clase es deserializar los diferentes txt's de cada mapa: Map1, Map2, Map3
public class CellDeserializer extends StdDeserializer<Cell> {

    //creamos los dos constructores tal como nos pide la extensión de la clase
    protected CellDeserializer(Class<?> vc) {
        super(vc);
    }

    protected CellDeserializer(JavaType valueType) {
        super(valueType);
    }

    //y creamos funcion deserializar esta servirá para cambiar formato JSON a objeto normal
    //no evaluamos casos diferentes porque todas las entidades las consideraremos como iguales
    //esto deserializará los diferentes mapas que hemos creado
    //la idea es deserializar para API REST
    @Override
    public Cell deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        //primero de todo nos viene un objeto JSON (Mapa) que hay que parsear jsonParser
        //hemos utilizado objectnode (modificación) ya que a diferencia de jsonnode solo es para lectura
        //nos servirá después para modificar todo el mapa a mano si queremos
        ObjectNode objectnode = (ObjectNode) jsonParser.getCodec().readTree(jsonParser);
        //recuperamos el valor de {"name" : "value"}
        String cellName = objectnode.findValue("name").asText();

        try {
            //buscamos la clase a través de value del objeto JSON del mapa
            Class c = Class.forName("cells." + cellName);
            //construye un constructor vacío
            Constructor con = c.getDeclaredConstructor(null);
            //e instanciamos el constructor con 0 elementos
            return (Cell) con.newInstance(null);
            } //lanzamos excepción por si no se instancia correctamente
        catch (InstantiationException e) {
            e.printStackTrace();
        } //lanzamos excepciones tal y como nos muestra el programa
        catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
