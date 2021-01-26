DROP DATABASE IF EXISTS eetacwarsdb;
CREATE DATABASE eetacwarsdb;
USE eetacwarsdb;

/*Cada bloque lo ejecutamos por separado en el HeidiSQL*/
CREATE TABLE User (
ID INTEGER NOT NULL AUTO_INCREMENT,
username VARCHAR(20),
mail VARCHAR(64),
name VARCHAR(20),
lastname VARCHAR(20),
city VARCHAR(20),
password VARCHAR(20),
image VARCHAR(20),
connected TINYINT(1),
actualLife INTEGER,
maxLife INTEGER,
attack INTEGER,
checkPoint INTEGER,
points INTEGER,
enemiesKilled INTEGER,
level INTEGER,
UNIQUE KEY idUserUq (ID)
);

CREATE TABLE Item (
ID INTEGER NOT NULL AUTO_INCREMENT,
name VARCHAR(20),
type VARCHAR(20),
objectPoints INTEGER,
price INTEGER,
value INTEGER,
image VARCHAR(20),
UNIQUE KEY idItemUq (ID)
);

CREATE TABLE Enemy (
ID INTEGER NOT NULL AUTO_INCREMENT,
name VARCHAR(20),
type VARCHAR(20),
life INTEGER,
attack INTEGER,
level INTEGER,
map INTEGER,
positionX INTEGER,
positionY INTEGER,
UNIQUE KEY idEnemyUq (ID),
user_id INTEGER,
FOREIGN KEY (user_id) REFERENCES User(ID)
);

CREATE TABLE Ally (
ID INTEGER NOT NULL AUTO_INCREMENT,
name VARCHAR(20),
type VARCHAR(20),
life INTEGER,
attack INTEGER,
level INTEGER,
map INTEGER,
positionX INTEGER,
positionY INTEGER,
UNIQUE KEY idAllyUq (ID),
user_id INTEGER,
FOREIGN KEY (user_id) REFERENCES User(ID)
);

/*Tabla relación usuarios-items del juego n:m*/
CREATE TABLE Users_Items (
user_id INTEGER NOT NULL, foreign key(user_id) references User(ID),
item_id INTEGER NOT NULL, foreign key(item_id) references Item(ID)
);

/*Añadir valores a la base de datos*/
INSERT INTO User(ID, username) VALUES (0, 'ejemplooo');
INSERT INTO User (username, mail, name, lastname, city,  password, connected) VALUES ('dani98', 'dani@upc.edu', 'Dani', 'Gonzalez', 'Paris', 'dani98', false);
INSERT INTO User (username, mail, name, lastname, city,  password, connected) VALUES ('maria16', 'maria@upc.edu', 'Maria', 'Hernandez', 'Roma', 'maria16', false);
INSERT INTO User (username, mail, name, lastname, city,  password, connected) VALUES ('pedro7', 'pedro@upc.edu', 'Pedro', 'Fernandez', 'Bruselas', 'pedro7', false);
INSERT INTO User (username, mail, name, lastname, city,  password, connected) VALUES ('rodrigo25', 'rodrigo@upc.edu', 'Rodrigo', 'Ramirez', 'Barcelona', 'rodrigo25', false);
INSERT INTO Item(name, type, objectPoints, price) VALUES ('espada','Ataque',1, 10);
INSERT INTO Item(name, type, objectPoints, price) VALUES ('martillo','Ataque',2, 60);
INSERT INTO Item(name, type, objectPoints, price) VALUES ('escudo','Defensa',3, 30);
INSERT INTO Item(name, type, objectPoints, price) VALUES ('pocion','Vida',1, 30);
INSERT INTO Item(name, type, objectPoints, price) VALUES ('barrita','Vida',2, 50);
INSERT INTO Enemy (name, type, life, map, positionX, positionY, user_id) VALUES ('Toni','Villano',120,2,20,30,1);
INSERT INTO Enemy (name, type, life, map, positionX, positionY, user_id) VALUES ('Juan','Villano',80,1,20,30,2);
INSERT INTO Enemy (name, type, life, map, positionX, positionY, user_id) VALUES ('EstudianteAero','Secuaz',40,2,10,10,2);
INSERT INTO Enemy (name, type, life, map, positionX, positionY, user_id) VALUES ('EstudianteAgro','Secuaz',40,1,10,10,1);
INSERT INTO Enemy (name, type, life, map, positionX, positionY, user_id) VALUES ('Rector','Jefe Final',200,3,20,30,1);
INSERT INTO Ally (name, type, life, map, positionX, positionY, user_id) VALUES ('EstudianteTelco','Compañero',40,2,20,30,1);
INSERT INTO Ally (name, type, life, map, positionX, positionY, user_id) VALUES ('EstudianteTelma','Compañero',40,1,20,30,2);
INSERT INTO Users_Items (user_id, item_id) VALUES (1,1);
INSERT INTO Users_Items (user_id, item_id) VALUES (1,3);
INSERT INTO Users_Items (user_id, item_id) VALUES (2,(SELECT ID FROM Item WHERE name="martillo"));
INSERT INTO Users_Items (user_id, item_id) VALUES (2,(SELECT ID FROM Item WHERE name="pocion"));
INSERT INTO Users_Items (user_id, item_id) VALUES (3,(SELECT ID FROM Item WHERE name="escudo"));
