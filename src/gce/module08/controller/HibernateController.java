package gce.module08.controller;

import gce.module08.model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Starts the hibernate session factory
 */
public class HibernateController {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // Loads configuration file and model mapping
            Configuration configuration = new Configuration().configure("/gce/module08/model/hibernate.cfg.xml").addAnnotatedClass(Item.class);
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            // Builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }

    public static Session getHibernateSession() {

        final SessionFactory sf = new Configuration()
                .configure("/gce/module08/model/hibernate.cfg.xml").buildSessionFactory();

        return sf.openSession();
    }
}
