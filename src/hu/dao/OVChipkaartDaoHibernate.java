package hu.dao;

import hu.domein.OVChipkaart;
import hu.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OVChipkaartDaoHibernate implements OVChipkaartDAO{
    private Session session;
    private Transaction transaction = null;

    public OVChipkaartDaoHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        return false;
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) {
        return false;
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        return false;
    }

    @Override
    public OVChipkaart findById(int id) {
        return null;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        return null;
    }

    @Override
    public List<OVChipkaart> findAll() {
        return null;
    }
}
