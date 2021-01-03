package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Play {

    //revisar modelo partida
    private int id;
    private int positionX;
    private int positionY;
    private int total;
    private int time;

    //hay que recordar que una partida tendrá una serie de mapas
    //como no habrá muchos mapas en el juego nos hemos decidido por una LinkedList
    //List<Map> mapsByPlay;

    //constructor vacio, basico y getters y setters
    public Play() {
    }

    //constructor básico
    public Play(Integer id, Integer positionX, Integer positionY, Integer total, Integer time) {
        this.id = id;
        this.positionX = positionX;
        this.positionY = positionY;
        this.total = total;
        this.time = time;
        //this.mapsByPlay = new LinkedList<>();
    }

    //getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    //devolver partida en string
    @Override
    public String toString() {
        return "Play{" +
                "id=" + id +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", total=" + total +
                ", time=" + time +
                '}';
    }
}
