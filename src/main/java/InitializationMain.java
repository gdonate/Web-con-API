
import edu.upc.dsa.cells.*;
import edu.upc.dsa.cells.Character;

import java.io.IOException;
import java.util.ArrayList;

public class InitializationMain {

    public static void main(String[]args){

        //final Logger logger = Logger.getLogger(InitializationMain.class);

        //Esto es una muestra para pasarlo después si tenemos tiempo a base de datos

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
        c2.add(new Ground());  //10
        c2.add(new Ground());  //11
        c2.add(new Ground());  //12
        c2.add(new Ground());  //13
        c2.add(new Ground());  //14
        c2.add(new Ground());  //15
        c2.add(new Wall());  //16
        ArrayList<Cell> c3 = new ArrayList<>();
        c3.add(new Wall());  //17
        c3.add(new Ground());  //18
        c3.add(new Character());  //19
        c3.add(new Ground());  //20
        c3.add(new Ground());  //21
        c3.add(new Ground());  //22
        c3.add(new Ground());  //23
        c3.add(new Wall());  //24
        ArrayList<Cell> c4 = new ArrayList<>();
        c4.add(new Wall());  //25
        c4.add(new Ground());  //26
        c4.add(new Ground());  //27
        c4.add(new Enemy());  //28
        c4.add(new Money());  //29
        c4.add(new Ground());  //30
        c4.add(new Ground());  //31
        c4.add(new Wall());  //32
        ArrayList<Cell> c5 = new ArrayList<>();
        c5.add(new Wall());  //33
        c5.add(new Ground());  //34
        c5.add(new Ground());  //35
        c5.add(new Ground());  //36
        c5.add(new Ground());  //37
        c5.add(new Ground());  //38
        c5.add(new Ground());  //39
        c5.add(new Wall());  //40
        ArrayList<Cell> c6 = new ArrayList<>();
        c6.add(new Wall());  //41
        c6.add(new Enemy());  //42
        c6.add(new Ground());  //43
        c6.add(new Ground());  //44
        c6.add(new Ground());  //45
        c6.add(new Ground());  //46
        c6.add(new Ground());  //47
        c6.add(new Wall());  //48
        ArrayList<Cell> c7 = new ArrayList<>();
        c7.add(new Wall());  //49
        c7.add(new Sword());  //50
        c7.add(new Ground());  //51
        c7.add(new Ground());  //52
        c7.add(new Ground());  //53
        c7.add(new Ground());  //54
        c7.add(new Ground());  //55
        c7.add(new Wall());  //56
        ArrayList<Cell> c8 = new ArrayList<>();
        c8.add(new Wall());  //57
        c8.add(new Wall());  //58
        c8.add(new Wall());  //59
        c8.add(new Wall());  //60
        c8.add(new Wall());  //61
        c8.add(new Wall());  //62
        c8.add(new Wall());  //63
        c8.add(new Wall());  //64
        //añadimos todas las celdas últimas
        c.add(c1);
        c.add(c2);
        c.add(c3);
        c.add(c4);
        c.add(c5);
        c.add(c6);
        c.add(c7);
        c.add(c8);

        Map map = new Map("Map1", 8,8);
        //implementamos funciones de establecer celdas y mostrar el mapa
        map.setCells(c);
        map.showMap();

        try {
            //guardamos el mapa creado
            map.saveMap();
        } catch (IOException e) {
            e.printStackTrace();
            //logger.info("Aseguremos que no vienen de aquí los errores");
        }

        Map m = new Map();
        //le damos nombre al mapa
        m.setName("Map1");
        try {
            //intentamos cargar el mapa
            m.load();
        } catch (IOException e) {
            e.printStackTrace();
            //logger.info("Y de aquí tampoco vienen errores");
        }
    }

}
