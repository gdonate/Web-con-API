package edu.upc.dsa.dao;

import edu.upc.dsa.models.Item;

import java.util.List;

public interface ItemDAO {

    //CRUD Item
    public int addItem(String name, String type, int objectPoints, int price);
    public Item getItem(int id);
    //update de momento invalidado
    public void updateItem(int id, String name, String type, int objectPoints, int price);
    public void deleteItem(int id);
    public List<Item> getItems();
    public List<Item> getItemsByPrice(int price);
    //obtener los items de un usuario
    public List<Item> getAllItemsUser(int idUser);

    //encuentra el número máximo de items
    public int findMax();
}
