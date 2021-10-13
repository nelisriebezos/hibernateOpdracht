package hu.dao;

import hu.domein.Adres;
import hu.domein.Reiziger;

import java.util.List;

public interface AdresDAO {
    public boolean save(Adres adres);
    public boolean update(Adres adres);
    public boolean delete(Adres adres);
    public Adres findById(int id);
    public Adres findbyReiziger(Reiziger reiziger);
    public List<Adres> findAll();
}
