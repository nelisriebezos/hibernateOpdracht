package hu;

import hu.dao.AdresDaoHibernate;
import hu.dao.OVChipkaartDaoHibernate;
import hu.dao.ProductDaoHibernate;
import hu.dao.ReizigerDaoHibernate;
import hu.domein.Adres;
import hu.domein.OVChipkaart;
import hu.domein.Product;
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

        testAdresDao(adao);
        testReizigerDao(rdao);
        testOVChipDao(odao);
        testProductDao(pdao);
    }

    private static void testAdresDao(AdresDaoHibernate adao) {
        Adres adres = new Adres(6, "test", "test", "test", "test");
        Reiziger reiziger = new Reiziger(6, "", "", "", java.sql.Date.valueOf("2002-12-12"));
        adres.setReiziger(reiziger);

        for (Adres a : adao.findAll()) {
            System.out.println(a);
        }

        System.out.println("\nlengte lijst voor save: " + adao.findAll().size());
        adao.save(adres);
        System.out.println("lengte lijst na save: " + adao.findAll().size());

        adres.setPostcode("brrrrrrr");
        adao.save(adres);
        System.out.println("\nVind geupdate adres met id:");
        System.out.println(adao.findById(6));

        System.out.println("\nvind met reiziger:");
        System.out.println(adao.findbyReiziger(reiziger));

        System.out.println("\nvoor delete: " + adao.findAll().size());
        adao.delete(adres);
        System.out.println("na delete: " + adao.findAll().size());
    }

    private static void testReizigerDao(ReizigerDaoHibernate rdao) {
        Reiziger reiziger = new Reiziger(6, "", "", "", java.sql.Date.valueOf("2002-12-12"));

        for (Reiziger r : rdao.findAll()) {
            System.out.println(r);
        }

        System.out.println("\nlengte lijst voor save: " + rdao.findAll().size());
        rdao.save(reiziger);
        System.out.println("lengte lijst na save: " + rdao.findAll().size());

        reiziger.setVoorletters("gfdsagfdsa");
        rdao.save(reiziger);
        System.out.println("\nVind geupdate adres met id:");
        System.out.println(rdao.findById(6));

        System.out.println("\nvind met gdatum:");
        System.out.println(rdao.findByGbdatum("2002-12-12"));

        System.out.println("\nvoor delete: " + rdao.findAll().size());
        rdao.delete(reiziger);
        System.out.println("na delete: " + rdao.findAll().size());
    }

    private static void testOVChipDao(OVChipkaartDaoHibernate odao) {
        Reiziger reiziger = new Reiziger(5, "", "", "", java.sql.Date.valueOf("2002-12-12"));
        OVChipkaart ovChipkaart = new OVChipkaart(900000, java.sql.Date.valueOf("2002-12-12"), 2, 100);
        ovChipkaart.setReiziger(reiziger);

        for (OVChipkaart o : odao.findAll()) {
            System.out.println(o);
        }

        System.out.println("\nlengte lijst voor save: " + odao.findAll().size());
        odao.save(ovChipkaart);
        System.out.println("lengte lijst na save: " + odao.findAll().size());

        ovChipkaart.setKlasse(3);
        odao.update(ovChipkaart);
        System.out.println("\nVind geupdate OVchipkaart met id:");
        System.out.println(odao.findById(900000));

        System.out.println("\nvind met reiziger:");
        System.out.println(odao.findByReiziger(reiziger));

        System.out.println("\nvoor delete: " + odao.findAll().size());
        odao.delete(ovChipkaart);
        System.out.println("na delete: " + odao.findAll().size());
    }

    private static void testProductDao(ProductDaoHibernate pdao) {
        Product product = new Product(7, "fdas", "fdas", 80);
        OVChipkaart ovChipkaart = new OVChipkaart(90537, java.sql.Date.valueOf("2002-12-12"), 2, 100);
        ovChipkaart.addProduct(product);

        for (Product p : pdao.findAll()) {
            System.out.println(p);
        }

        System.out.println("\nlengte lijst voor save: " + pdao.findAll().size());
        pdao.save(product);
        System.out.println("lengte lijst na save: " + pdao.findAll().size());

        product.setBeschrijving("testiong");
        pdao.update(product);
        System.out.println("\nVind geupdate OVchipkaart met id:");
        System.out.println(pdao.findById(7));

        System.out.println("\nvind met ovchipkaart:");
        System.out.println(pdao.findByOvchipkaart(ovChipkaart));

        System.out.println("\nvoor delete: " + pdao.findAll().size());
        pdao.delete(product);
        System.out.println("na delete: " + pdao.findAll().size());


    }
}
