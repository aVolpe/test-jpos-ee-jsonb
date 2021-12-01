package org.jpos.test;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Copied from https://github.com/lokeshgupta1981/hibernate/blob/master/hibernate-hello-world/src/main/java/com/howtodoinjava/hibernate/test/HibernateUtil.java
 **/
public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() 
    {
        try 
        {
            if (sessionFactory == null) 
            {
                StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();

                Metadata metaData = new MetadataSources(standardRegistry)
                    .getMetadataBuilder()
                    .build();

                sessionFactory = metaData.getSessionFactoryBuilder().build();
            }
            return sessionFactory;
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
