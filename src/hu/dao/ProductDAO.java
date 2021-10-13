package hu.dao;

import hu.domein.OVChipkaart;
import hu.domein.Product;

import java.util.List;

public interface ProductDAO {
    public boolean save(Product product);
    public boolean update(Product product);
    public boolean delete(Product product);
    public Product findById(int id);
    public List<Product> findByOvchipkaart(OVChipkaart ovc);
    public List<Product> findAll();
}
