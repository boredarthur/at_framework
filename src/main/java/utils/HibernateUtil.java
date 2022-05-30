package main.java.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import test.java.API.data.UserPOJO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = initSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory initSessionFactory() {
        Configuration configuration = new Configuration();
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/test/java/API/config/db.properties"));
            configuration.setProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        configuration.addAnnotatedClass(UserPOJO.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        System.out.println("Hibernate config created");

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
