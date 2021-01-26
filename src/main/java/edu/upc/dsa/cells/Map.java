package edu.upc.dsa.cells;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//aqui trataremos toda la info de Mapa
public class Map {
    //todos los atributos que pongamos irán visualizados para que se vean en el JSON
    //iremos haciendo las diferentes funciones para que el usuario pueda cargar el mapa, guardarlo, etc.

    //primero mostraremos un array de celdas donde irán las diferentes entidades
    //y como asignamos Normal quiere decir que se verá en el JSON
    @JsonView(Views.Normal.class)
    private ArrayList<ArrayList<Cell>> cells = new ArrayList<ArrayList<Cell>>();

    //seguimos con los atributos principales de cada mapa: nombre, altura y anchura
    //se verán visibles
    @JsonView(Views.Normal.class)
    private String name;

    @JsonView(Views.Normal.class)
    private int height;

    @JsonView(Views.Normal.class)
    private int width;

    @JsonView(Views.NotNormal.class)
    private Logger log = Logger.getLogger(Map.class.getName());

    //creación dos constructores: vacío y con 3 atributos principales, luego veremos el uso que le damos
    public Map() {
    }

    public Map(String name, int height, int width) {
        this.name = name;
        this.height = height;
        this.width = width;
    }

    //procederemos a hacer las siguientes funciones: showMap, load, loadMap, loadMaps, saveMap y readMap
    //estas funciones serán las más importantes a la hora de pedirlas y comunicarlas también con la base de datos
    //primero las integramos a la API / no creo que necesitemos conectaros a los DAOS puesto que va por JSONs
    public void showMap(){
        for (List<Cell> cell : getCells()) {
            for (Cell c : cell) {
                //log.info(c.getLetter());
                //no voy a utilizar log4j ya que necesito imprimir en pantalla valores
                //y no me sirven logs como mensaje de información
                System.out.print(c.getLetter());
            }//importante le doy un salto de línea cuando he acabado de escribir toda la fila
            System.out.println();
        }
    }

    public void saveMap() throws IOException {
        File f = new File("./src/main/resources/Maps/");
        //si fitxer primer no existeix crearem un de nou segons les nostres indicacions
        if (!f.exists()) {
            f.createNewFile();
            //donem una info sobre el fitxer que s'ha creat a la carpeta Maps
            log.info("File with name: " + this.name + ".txt created for map.");
        }
        ObjectMapper om = new ObjectMapper();
        try {
            om.writerWithView(Views.Normal.class).writeValue(
                    new File("./src/main/resources/Maps/" + this.name + ".txt"), this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }



    //como siempre ponemos todos los getters y setters implementados por el IntelliJ
    //antes mencionados al principio de la clase Map
    public ArrayList<ArrayList<Cell>> getCells() {
        return cells;
    }

    public void setCells(ArrayList<ArrayList<Cell>> cells) {
        this.cells = cells;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
    }
}
