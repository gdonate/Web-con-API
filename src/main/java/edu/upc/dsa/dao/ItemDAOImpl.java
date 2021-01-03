package edu.upc.dsa.dao;

import edu.upc.dsa.FactorySessionManager;
import edu.upc.dsa.SessionManager;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;

import java.util.HashMap;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public int addItem(String name, String type, int objectPoints, int price) {
        SessionManager session = null;
        int id = 0;
        try {
            session = FactorySessionManager.openSession();
            Item item = new Item(name, type, objectPoints, price);
            session.save(item);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return id;
    }

    @Override
    public Item getItem(int id) {
        SessionManager session = null;
        Item item = null;
        try {
            session = FactorySessionManager.openSession();
            item = (Item)session.get(Item.class, id);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return item;
    }

    @Override
    public void updateItem(int id, String name, String type, int objectPoints, int price) {
        Item item = this.getItem(id);
        item.setName(name);
        item.setType(type);
        item.setObjectPoints(objectPoints);
        item.setPrice(price);

        SessionManager session = null;
        try {
            session = FactorySessionManager.openSession();
            session.update(Item.class, id);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
    }

    @Override
    public void deleteItem(int id) {
        Item item = this.getItem(id);
        SessionManager session = null;
        try {
            session = FactorySessionManager.openSession();
            session.delete(Item.class, id);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<Item> getItems() {
        SessionManager session = null;
        List<Item> itemList=null;
        try {
            session = FactorySessionManager.openSession();
            itemList = session.findAll(Item.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return itemList;
    }

    @Override
    public List<Item> getItemsByPrice(int price) {
        SessionManager session = null;
        List<Item> itemList=null;
        try {
            session = FactorySessionManager.openSession();

            HashMap<String, Integer> params = new HashMap<String, Integer>();
            params.put("price", price);

            itemList = session.findAll(Item.class, params);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return itemList;
    }
}
