package hu.domein;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "adres")
public class Adres implements Serializable {
    @Id
    @Column(name = "adres_id")
    private int id;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reiziger_id", nullable = false)
    private Reiziger reiziger;

    public Adres(int id, String postcode, String huisnumer, String straat, String woonplaats) {
        this.id = id;
        this.postcode = postcode;
        this.huisnummer = huisnumer;
        this.straat = straat;
        this.woonplaats = woonplaats;
    }

    public Adres() {}

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHuisnumer() {
        return huisnummer;
    }

    public void setHuisnumer(String huisnumer) {
        this.huisnummer = huisnumer;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }


    @Override
    public String toString() {
        return "Adres: " +
                "id=" + id +
                ", postcode='" + postcode;
    }
}
