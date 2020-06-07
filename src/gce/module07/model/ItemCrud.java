package gce.module07.model;

import gce.module07.controller.HibernateController;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Performs database CRUD operations on the Item entity
 */
public class ItemCrud implements ItemDao {

    static Session session = null;
    static Transaction transaction = null;

    /**
     * Constructor
     */
    public ItemCrud() {
    }

    /**
     * Inserts the passed item into the database
     *
     * @param item The submitted item
     */
    @Override
    public void insertItem(Item item) {
        try {
            session = HibernateController.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error writing to database. Item not added.");
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            try {
                if (session != null) {
                    session.close();
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
        List<Item> itemData = null;

        try {
            session = HibernateController.getSessionFactory().openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Item> itemCriteriaQuery = criteriaBuilder.createQuery(Item.class);
            Root<Item> itemRoot = itemCriteriaQuery.from(Item.class);
            CriteriaQuery<Item> allItems = itemCriteriaQuery.select(itemRoot);

            TypedQuery<Item> allQuery = session.createQuery(allItems);

            itemData = allQuery.getResultList();
        } catch (Exception e) {
            System.out.println("Error querying the database. No to-do items were found.");
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
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
        try {
            session = HibernateController.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(item);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error deleting from database. Item not deleted.");
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            try {
                if (session != null) {
                    session.close();
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
        Item item = null;

        try {
            session = HibernateController.getSessionFactory().openSession();
            item = session.get(Item.class, itemId);
        } catch (Exception e) {
            System.out.println("Error. Item not found.");
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
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
        try {
            session = HibernateController.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(item);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error writing to database. Item not update.");
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            try {
                if (session != null) {
                    session.close();
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
        System.out.println("Error closing database connection.");
        e.printStackTrace();
    }
}
