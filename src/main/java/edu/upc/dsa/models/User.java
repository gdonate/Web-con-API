package edu.upc.dsa.models;

import java.util.List;

//Usuario = Jugador
public class User {


    //aplicando modelo de usuario del juego
    //en el UML teniamos usuario y jugador
    //lo modificaremos de tal manera que usuario y jugador sean lo mismo
    //integer
    private int id;
    private String username;
    private String mail;
    private String name;
    private String lastname;
    private String city;
    private String password;
    private String image;

    //falta relacionar atributos base datos: conected, actualLife, maxLife, attack, checkPoint
    //points, enemiesKilled
    private boolean connected;
    private int actualLife;
    private int maxLife;
    private int attack;
    private int checkPoint;
    private int points;
    private int enemiesKilled;

    //atributos originalmente para jugador pero finalmente para usuario
    //de momento solo añadimos estos dos, se pueden añadir mucho más depende la complejidad del juego
    //integer
    private int level;

    //constructor vacio para el json
    public User(Integer id) {
    }

    //constructor vacio error para el 415
    //preguntarle porque aun no se porque motivo???
    public User() {
    }

    //de momento quitamos los arrays de los constructores debido a un problema de serialización
    //clase UserTO

    //constructor a parte para añadir usuarios con lo básico
    public User(String username, String mail, String name, String lastname, String city, String password, boolean connected) {
        //nuevo constructor
        this.username = username;
        this.mail = mail;
        this.name=name;
        this.lastname=lastname;
        this.city=city;
        this.password=password;
        this.connected = connected;
    }

    //constructor para register básico y más simple
    public User(String username, String password, boolean connected) {
        this.username = username;
        this.password = password;
        this.connected = connected;
    }

    //nuevo constructor entero para info app android

    public User(int id, String username, String mail, String name, String lastname, String city, String password, String image, boolean connected, int actualLife, int maxLife, int attack, int checkPoint, int points, int enemiesKilled, int level) {
        this.id = id;
        this.username = username;
        this.mail = mail;
        this.name = name;
        this.lastname = lastname;
        this.city = city;
        this.password = password;
        this.image = image;
        this.connected = connected;
        this.actualLife = actualLife;
        this.maxLife = maxLife;
        this.attack = attack;
        this.checkPoint = checkPoint;
        this.points = points;
        this.enemiesKilled = enemiesKilled;
        this.level = level;
    }


    //ahora pondremos los getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public int getActualLife() {
        return actualLife;
    }

    public void setActualLife(int actualLife) {
        this.actualLife = actualLife;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(int checkPoint) {
        this.checkPoint = checkPoint;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public void setEnemiesKilled(int enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    //a continuación pondremos el tostring por si hace falta mostrarlo por consola

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", city='" + city + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", connected=" + connected +
                '}';
    }
}
