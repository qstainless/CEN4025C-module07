package gce.module08.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Singleton class to create, read, and delete the to-do items in memory.
 */
public class Data {

    private static final Data instance = new Data();

    // Where the to-do items will be stored in memory
    private ObservableList<Item> items;

    private final ItemCrud itemCrud;

    /**
     * Class constructor
     */
    private Data() {
        itemCrud = new ItemCrud();
    }

    // Getters
    public static Data getInstance() {
        return instance;
    }

    public ObservableList<Item> getItems() {
        return items;
    }

    // Add a new to-do item to the database and the Data model
    public void addItem(Item item) {
        try {
            itemCrud.insertItem(item);
        } catch (Exception e) {
            System.out.println("Data: Error deleting from database. Item not deleted.");
            e.printStackTrace();
        } finally {
            // Add item to Data model if successfully deleted from the database
            items.add(item);
        }
    }

    /**
     * Loads the to-do items from the database. If the table does not exist,
     * hibernate will create it and the to-do list will be empty. Once a to-do
     * item is submitted by the user, it will be added to the Data model for
     * display in the GUI if successfully saved to the database.
     */
    public void loadItems() {
        // Must use an observableArrayList to populate the GUI ListView
        items = FXCollections.observableArrayList();

        List<Item> itemData = itemCrud.loadAllItems();

        // Add queried to-do items to the observableArrayList
        items.addAll(itemData);
    }

    /**
     * Deletes an item from the database and removes it from the Data model
     *
     * @param item The item to delete
     */
    public void deleteItem(Item item) {
        try {
            itemCrud.deleteItem(item);
        } catch (Exception e) {
            System.out.println("Data: Error deleting from database. Item not deleted.");
            e.printStackTrace();
        } finally {
            // Remove item from Data model if successfully deleted from the database
            items.remove(item);
        }
    }
}
