package hu;

import hu.dao.AdresDaoHibernate;
import hu.dao.OVChipkaartDaoHibernate;
import hu.dao.ProductDaoHibernate;
import hu.dao.ReizigerDaoHibernate;
import hu.domein.Adres;
import hu.domein.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.SQLException;

public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

//    testing

    public static void main(String[] args) throws SQLException {
//        testFetchAll();
        testDao();
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {
        Session session = getSession();
        try {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        } finally {
            session.close();
        }
    }

    private static void testDao() {
        AdresDaoHibernate adao = new AdresDaoHibernate(getSession());
        ReizigerDaoHibernate rdao = new ReizigerDaoHibernate(getSession());
        OVChipkaartDaoHibernate odao = new OVChipkaartDaoHibernate(getSession());
        ProductDaoHibernate pdao = new ProductDaoHibernate(getSession());

        testReizigerDao(adao, rdao);
    }

    private static void testReizigerDao(AdresDaoHibernate adao, ReizigerDaoHibernate rdao) {
        rdao.setAdao(adao);

        Reiziger reiziger = new Reiziger(6, "test", "test", "test", java.sql.Date.valueOf("2002-12-03"));
        Adres adres = new Adres(6, "test", "test", "test", "test", reiziger);

        rdao.save(reiziger);
    }
}
