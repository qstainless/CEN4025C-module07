package gce.module07.model;

import gce.module07.controller.HibernateController;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    static Session session = null;
    static Transaction transaction = null;

    public ItemDaoImpl() {
    }

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
            System.out.println("Error writing to database. Item not added.");
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

    @Override
    public void deleteItemById(int itemId) {
        Item item = new Item();

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

            if (item != null) {
                deleteItem(item);
            }
        }
    }

    private void closingError(Exception e) {
        System.out.println("Error closing database connection.");
        e.printStackTrace();
    }
}
