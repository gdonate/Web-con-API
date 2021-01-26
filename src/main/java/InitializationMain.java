import edu.upc.dsa.cells.Cell;
import edu.upc.dsa.cells.Ground;
import edu.upc.dsa.cells.Map;
import edu.upc.dsa.cells.Wall;

import java.io.IOException;
import java.util.ArrayList;

public class InitializationMain {

    public static void main(String[]args){
        //Aquí haremos una prueba de inicialización de nuestro mapa
        //Declaro las celdas y las inicializo
        ArrayList<ArrayList<Cell>> c = new ArrayList<ArrayList<Cell>>();
        ArrayList<Cell> c1 = new ArrayList<>();
        c1.add(new Wall());  //1
        c1.add(new Wall());  //2
        c1.add(new Wall());  //3
        c1.add(new Wall());  //4
        c1.add(new Wall());  //5
        c1.add(new Wall());  //6
        c1.add(new Wall());  //7
        c1.add(new Wall());  //8
        ArrayList<Cell> c2 = new ArrayList<>();
        c2.add(new Wall());  //9
        c1.add(new Ground());  //10
        c2.add(new Wall());  //1
        /*ArrayList<Celda> c3 = new ArrayList<>();
        c3.add(new Muro());  //9
        c3.add(new Camino());  //10
        c3.add(new Camino());  //11
        c3.add(new Muro());  //12
        ArrayList<Celda> c4 = new ArrayList<>();
        c4.add(new Muro());  //13
        c4.add(new Muro());  //14
        c4.add(new Muro());  //15
        c4.add(new Muro());  //16
        c.add(c1);
        c.add(c2);
        c.add(c3);
        c.add(c4);*/


        Map map = new Map("Map1", 8,8);
        //implementamos funciones de establecer celdas y mostrar el mapa
        //map.setCeldas(c);
        //map.mostrarMapa();

        /*try {
            //guardamos el mapa creado
            //map.guardarMapa();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*Map m = new Map();
        //le damos nombre al mapa
        //m.setName("Map1");
        try {
            //intentamos cargar el mapa
            //m.cargar();
            System.out.println("etc");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
