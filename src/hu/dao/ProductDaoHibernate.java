package hu.dao;

import hu.domein.OVChipkaart;
import hu.domein.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDaoHibernate implements ProductDAO{
    private Session session;
    private Transaction transaction = null;

    public ProductDaoHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Product product) {
        try {
            transaction = session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try {
            transaction = session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        try {
            transaction = session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product findById(int id) {
        session.beginTransaction();
        Product product = session.load(Product.class, id);
        session.getTransaction().commit();
        return product;
    }

    @Override
    public List<Product> findByOvchipkaart(OVChipkaart ovc) {
        session.beginTransaction();
        List <Product> productList =((List<Product>) this.session.createQuery
                ("select p from OVChipkaart o join o.productList p where o.kaartNummer = :kaart_nummer")
                .setParameter("kaart_nummer", ovc.getKaartNummer())
                .getResultList());
        session.getTransaction().commit();
        return productList;
    }

    @Override
    public List<Product> findAll() {
        session.beginTransaction();
        List<Product> ovChipkaartList = this.session.createQuery("select p from Product p").getResultList();
        session.getTransaction().commit();
        return ovChipkaartList;
    }
}
