package hu.dao;

import hu.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReizigerDaoHibernate implements ReizigerDAO{
    private Session session;
    private AdresDaoHibernate adao;
    private Transaction transaction = null;

    public ReizigerDaoHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try {
            transaction = session.beginTransaction();
            session.save(reiziger);
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
    public boolean update(Reiziger reiziger) {
        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        return false;
    }

    @Override
    public Reiziger findById(int id) {
        return null;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        return null;
    }

    @Override
    public List<Reiziger> findAll() {
        return null;
    }

    public void setAdao(AdresDaoHibernate adao) {
        this.adao = adao;
    }
}
