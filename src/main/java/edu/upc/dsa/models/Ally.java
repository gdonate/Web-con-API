package edu.upc.dsa.models;

//modelo Aliado = Enemigo
public class Ally {

    //atributos del modelo Aliado
    private int id;
    private String name;
    private String type;
    private int life;
    private int attack;
    private int level;
    private int idMap;
    private int idUser;

    //falta incluir atributos de la base datos: positionX, positionY
    private int positionX;
    private int positionY;

    //constructor vacio
    public Ally() {
    }

    //constructor vacio
    public Ally(String name, String type, Integer life, Integer idMap, Integer positionX, Integer positionY, Integer idUser) {
        this.name = name;
        this.type = type;
        this.life = life;
        this.idMap = idMap;
        this.positionX = positionX;
        this.positionY = positionY;
        this.idUser = idUser;
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIdMap() {
        return idMap;
    }

    public void setIdMap(int idMap) {
        this.idMap = idMap;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    @Override
    public String toString() {
        return "Ally{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", life=" + life +
                ", attack=" + attack +
                ", level=" + level +
                ", idMap=" + idMap +
                ", idUser=" + idUser +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                '}';
    }
}
