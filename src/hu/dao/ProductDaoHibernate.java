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
        return false;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public boolean delete(Product product) {
        return false;
    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public List<Product> findByOvchipkaart(OVChipkaart ovc) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }
}
