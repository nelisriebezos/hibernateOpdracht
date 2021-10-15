package hu.dao;

import hu.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

public class ReizigerDaoHibernate implements ReizigerDAO{
    private Session session;
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
        try {
            transaction = session.beginTransaction();
            session.update(reiziger);
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
    public boolean delete(Reiziger reiziger) {
        try {
            transaction = session.beginTransaction();
            session.delete(reiziger);
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
    public Reiziger findById(int id) {
        session.beginTransaction();
        Reiziger reiziger = session.load(Reiziger.class, id);
        session.getTransaction().commit();
        return reiziger;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        session.beginTransaction();
        List<Reiziger> reizigers = this.session
                .createQuery("select r from Reiziger r where geboortedatum = :datum")
                .setParameter("datum", Date.valueOf(datum))
                .getResultList();
        session.getTransaction().commit();
        return reizigers;
    }

    @Override
    public List<Reiziger> findAll() {
        session.beginTransaction();
        List reizigers = this.session.createQuery("select r from Reiziger r").getResultList();
        session.getTransaction().commit();
        return reizigers;
    }
}
