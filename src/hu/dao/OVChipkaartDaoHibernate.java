package hu.dao;

import hu.domein.Adres;
import hu.domein.OVChipkaart;
import hu.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OVChipkaartDaoHibernate implements OVChipkaartDAO{
    private Session session;
    private Transaction transaction = null;

    public OVChipkaartDaoHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        try {
            transaction = session.beginTransaction();
            session.save(ovChipkaart);
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
    public boolean update(OVChipkaart ovChipkaart) {
        try {
            transaction = session.beginTransaction();
            session.update(ovChipkaart);
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
    public boolean delete(OVChipkaart ovChipkaart) {
        try {
            transaction = session.beginTransaction();
            session.delete(ovChipkaart);
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
    public OVChipkaart findById(int id) {
        session.beginTransaction();
        OVChipkaart ovChipkaart = session.load(OVChipkaart.class, id);
        session.getTransaction().commit();
        return ovChipkaart;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        session.beginTransaction();
        List <OVChipkaart> ovChipkaartList =((List<OVChipkaart>) this.session.createQuery
                ("select o from OVChipkaart o where o.reiziger = :reiziger")
                .setParameter("reiziger", reiziger)
                .getResultList());
        session.getTransaction().commit();
        return ovChipkaartList;
    }

    @Override
    public List<OVChipkaart> findAll() {
        session.beginTransaction();
        List<OVChipkaart> ovChipkaartList = this.session.createQuery("select o from OVChipkaart o").getResultList();
        session.getTransaction().commit();
        return ovChipkaartList;
    }
}
