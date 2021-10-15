package hu.dao;

import hu.domein.Adres;
import hu.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
        try {
            transaction = session.beginTransaction();
            session.update(adres);
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
    public boolean delete(Adres adres) {
        try {
            transaction = session.beginTransaction();
            session.delete(adres);
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
    public Adres findById(int id) {
            session.beginTransaction();
            Adres adres = session.load(Adres.class, id);
            session.getTransaction().commit();
            return adres;
    }

    @Override
    public Adres findbyReiziger(Reiziger reiziger) {
        String hql = "select a from Adres a where a.reiziger = " + reiziger.getId();
        Query query = session.createQuery(hql);
        Adres result = (Adres) query.uniqueResult();
        return result;
    }

    @Override
    public List<Adres> findAll() {
        session.beginTransaction();
        List<Adres> adres = this.session.createQuery("select a from Adres a").getResultList();
        session.getTransaction().commit();
        return adres;
    }
}
