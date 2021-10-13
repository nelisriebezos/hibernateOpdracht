package hu.dao;

import hu.domein.Adres;
import hu.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AdresDaoHibernate implements AdresDAO{
    private Session session;
    private ReizigerDaoHibernate rdao;
    private Transaction transaction = null;

    public AdresDaoHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Adres adres) {
        try {
            transaction = session.beginTransaction();
            session.save(adres);
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
    public boolean update(Adres adres) {
        return false;
    }

    @Override
    public boolean delete(Adres adres) {
        return false;
    }

    @Override
    public Adres findById(int id) {
        return null;
    }

    @Override
    public Adres findbyReiziger(Reiziger reiziger) {
        return null;
    }

    @Override
    public List<Adres> findAll() {
        return null;
    }

    public void setRdao(ReizigerDaoHibernate rdao) {
        this.rdao = rdao;
    }
}
