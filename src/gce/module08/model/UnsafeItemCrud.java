package gce.module08.model;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

/**
 * Insecure and unreliable class and methods for purposes of demonstrating
 * SQL injection vulnerabilities when not using an ORM framework such as
 * Hibernate
 */
public class UnsafeItemCrud implements ItemDao {

    // Database connection session
    private static Connection connection;
    Statement st;
    String sql;

    // Database credentials
    private static final String databaseName = "module_07_db";
    private static final String databaseUser = "module07user";
    private static final String databasePass = "module07pass";
    private static final String databaseTable = "item";

    /**
     * Constructor
     */
    public UnsafeItemCrud() {
    }

    /**
     * Connects to the database
     * <p>
     * For purposes of this exercise, database credentials are hardcoded
     */
    private void dbConnect() {
        try {
            String mysqlDriver = "com.mysql.cj.jdbc.Driver";

            Class.forName(mysqlDriver);

            String connectionURL = "jdbc:mysql://localhost:3306/"
                    + databaseName
                    + "?allowMultiQueries=true"
                    + "&serverTimezone=America/New_York";

            connection = DriverManager.getConnection(connectionURL, databaseUser, databasePass);
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found error.");
            exit(0);
        } catch (SQLException ex) {
            System.out.println("SQL Error.");
            exit(0);
        }
    }

    /**
     * Inserts the passed item into the database
     *
     * @param item The submitted item
     */
    @Override
    public void insertItem(Item item) {
        dbConnect();

        try {
            sql = "INSERT INTO `" + databaseTable + "`"
                    + "(`itemDueDate`, `itemDescription`, `itemDetails`) "
                    + "VALUES ("
                    + "'" + item.getItemDueDate() + "'"
                    + ",'" + item.getItemDescription() + "'"
                    + ",'" + item.getItemDetails() + "'"
                    + ");";

            st = connection.createStatement();

            if (st.executeUpdate(sql) != 0) {
                System.out.println("Item created.");
            } else {
                System.out.println("Error creating item.");
            }
        } catch (Exception e) {
            System.out.println("Crud error writing to database. Item not added.");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                closingError(e);
            }
        }
    }

    /**
     * Loads all to-do items fromt the database
     *
     * @return The queried to-do items
     */
    @Override
    public List<Item> loadAllItems() {
        dbConnect();

        List<Item> itemData = new ArrayList<>();

        try {
            sql = "SELECT * FROM `" + databaseTable + "`";

            st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String itemDescription = rs.getString("itemDescription");
                String itemDetails = rs.getString("itemDetails");
                String itemDueDate = rs.getString("itemDueDate");

                // Debug
                System.out.println(id + "\n" + itemDescription + "\n" + itemDetails + "\n" + itemDueDate + "\n");

                Item item = new Item();
                item.setId(id);
                item.setItemDescription(itemDescription);
                item.setItemDetails(itemDetails);
                item.setItemDueDate(LocalDate.parse(itemDueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));

                assert false;
                itemData.add(item);
            }
        } catch (Exception e) {
            System.out.println("Crud error querying the database.");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                closingError(e);
            }
        }

        return itemData;
    }

    /**
     * Deletes the specified item from the database
     *
     * @param item The item to be deleted
     */
    @Override
    public void deleteItem(Item item) {
        dbConnect();

        try {
            sql = "DELETE FROM `" + databaseTable + "` WHERE id = " + item.getId();

            System.out.println("Delete:\n" + sql);

            st = connection.createStatement();

            if (st.executeUpdate(sql) != 0) {
                System.out.println("Item deleted.");
            } else {
                System.out.println("Error deleting item.");
            }
        } catch (Exception e) {
            System.out.println("Crud error deleting from database.");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                closingError(e);
            }
        }
    }

    /**
     * Deletes an item from the database by its id
     *
     * @param itemId The item's id in the table
     */
    @Override
    public void deleteItemById(int itemId) {
        Item selectedItem = selectItem(itemId);

        if (selectedItem != null) {
            deleteItem(selectedItem);
        }
    }

    /**
     * Query the to-do item from the database by its id
     *
     * @param itemId The item's id
     * @return The queried item
     */
    @Override
    public Item selectItem(int itemId) {
        dbConnect();

        Item item = null;

        try {
            sql = "SELECT * FROM `" + databaseTable + "` WHERE id = " + itemId;

            // Debug
            System.out.println("Select: \n" + sql);

            st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String itemDescription = rs.getString("itemDescription");
                String itemDetails = rs.getString("itemDetails");
                String itemDueDate = rs.getString("itemDueDate");

                item = new Item();
                item.setId(id);
                item.setItemDescription(itemDescription);
                item.setItemDetails(itemDetails);
                item.setItemDueDate(LocalDate.parse(itemDueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        } catch (Exception e) {
            System.out.println("Crud Error. Item " + itemId + " not found.");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                closingError(e);
            }
        }

        return item;
    }

    /**
     * Updates an edited item ont he database
     *
     * @param item The item to be updated
     */
    @Override
    public void updateItem(Item item) {
        dbConnect();

        try {
            sql = "UPDATE `" + databaseTable + "` "
                    + "set `itemDueDate` = " + "'" + item.getItemDueDate() + "'"
                    + ", `itemDescription` = '" + item.getItemDescription() + "'"
                    + ", `itemDetails` = '" + item.getItemDetails() + "'"
                    + " WHERE id = " + item.getId();

            // Debug
            System.out.println("Update: \n" + sql);

            st = connection.createStatement();

            if (st.executeUpdate(sql) != 0) {
                System.out.println("Item updated.");
            } else {
                System.out.println("Error updating item.");
            }
        } catch (Exception e) {
            System.out.println("Crud error updating database.");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                closingError(e);
            }
        }
    }

    /**
     * DRY implementation of graceful exception handling
     *
     * @param e The generated exception
     */
    private void closingError(Exception e) {
        System.out.println("Crud error closing database connection.\n" + e);
    }
}
