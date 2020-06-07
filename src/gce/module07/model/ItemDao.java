package gce.module07.model;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for the Item model
 */
public interface ItemDao {
    void insertItem(Item item) throws SQLException;

    List<Item> loadAllItems();

    void deleteItem(Item item);

    void deleteItemById(int itemId);

    Item selectItem(int id);

    void updateItem(Item updatedItem) throws SQLException;
}
