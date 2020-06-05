package gce.module07.model;

import gce.module07.controller.HibernateController;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ItemDao {

    static Session session = null;
    static Transaction transaction = null;

    public ItemDao() {
    }

    public static void insertItem(Item item) {
        try {
            session = HibernateController.getSession();
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
                System.out.println("Error closing database connection.");
                e.printStackTrace();
            }
        }
    }

    public static List<Item> loadAllItems() {
        List<Item> itemData = null;

        try {
            session = HibernateController.getSession();
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
                System.out.println("Error closing database connection.");
                e.printStackTrace();
            }
        }

        return itemData;
    }

    public static void deleteItem(Item item) {
        try {
            session = HibernateController.getSession();
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
                System.out.println("Error closing database connection.");
                e.printStackTrace();
            }
        }
    }
}
