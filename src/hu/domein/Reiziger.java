package hu.domein;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "reiziger")
public class Reiziger implements Serializable {
    @Id
    @Column(name = "reiziger_id")
    private int id;

    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;

    @OneToOne(mappedBy = "reiziger", cascade = CascadeType.ALL)
    private Adres adres;

    @OneToMany(mappedBy = "reiziger")
    private List<OVChipkaart> ovChipkaartList= new ArrayList<>();

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public Reiziger() {}

    public void addOVChipkaart(OVChipkaart ovChipkaart) {
        if (!ovChipkaartList.contains(ovChipkaart)) {
            ovChipkaartList.add(ovChipkaart);
        }
    }

    public void removeOVChipkaart(OVChipkaart ovc) {
        ovChipkaartList.remove(ovc);
    }

    public List<OVChipkaart> getOvChipkaartList() {
        return ovChipkaartList;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        if (this.tussenvoegsel == null) {
            return voorletters + " " + achternaam;
        } else {
            return voorletters + " " + tussenvoegsel + " " +achternaam;
        }
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    @Override
    public String toString() {
        return "Reiziger{" +
                "id=" + id +
                ", voorletters='" + voorletters + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", adres=" + adres +
                ", ovChipkaartList=" + ovChipkaartList +
                '}';
    }
}
