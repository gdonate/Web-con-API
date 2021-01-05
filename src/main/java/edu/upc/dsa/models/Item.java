package edu.upc.dsa.models;

import java.util.List;

public class Item {

    //atributos del modelo Item
    private int id;
    private String name;
    private String type;
    //atributo eliminado String total;
    //precio del item
    private int price;
    //el atributo value viene conectado a través del nivel del jugador
    private int value;
    private String image;
    //id del jugador que tiene estos items
    private int idPlayer;

    //falta relacionar atributos base datos: objectPoints
    private int objectPoints;

    //constructor vacio para el json
    public Item(Integer id) {
    }

    //constructor vacio, con parametros, getters y setters
    public Item() {
    }

    public Item(String name, String type) {
        this.name = name;
        this.type = type;
    }

    //constructor completo
    public Item(String name, String type, Integer objectPoints, Integer price) {
        this.name = name;
        this.type = type;
        this.objectPoints = objectPoints;
        this.price = price;
    }

    //getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public int getObjectPoints() {
        return objectPoints;
    }

    public void setObjectPoints(int objectPoints) {
        this.objectPoints = objectPoints;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", value=" + value +
                ", image='" + image + '\'' +
                ", idPlayer=" + idPlayer +
                ", objectPoints=" + objectPoints +
                '}';
    }
}
