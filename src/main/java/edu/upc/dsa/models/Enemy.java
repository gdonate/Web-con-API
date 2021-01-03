package edu.upc.dsa.models;

public class Enemy {

    //atributos del modelo Enemigo
    private int id;
    private String name;
    private String type;
    //eliminado atributo total en los dos modelos: aliado y enemigo
    private int life;
    private int attack;
    private int level;
    //atributo del id del mapa al que pertenecen
    private int idMap;
    private int idUser;

    //falta incluir atributos de la base datos: positionX, positionY
    private int positionX;
    private int positionY;

    //constructor vacio, con parametros, getters y setters
    public Enemy() {
    }

    //constructor basico
    public Enemy(String name, String type, Integer life, Integer idMap, Integer positionX, Integer positionY, Integer idUser) {
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

    //devolver enemigo en consola por si lo necesitamos

    @Override
    public String toString() {
        return "Enemy{" +
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
