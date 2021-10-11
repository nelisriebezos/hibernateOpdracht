package hu.domein;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {
    @Id
    @Column(name = "kaart_nummer")
    private int kaartNummer;
    @Column(name = "geldig_tot")
    private Date geldigTot;
    private int klasse;
    private int saldo;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "reiziger_id",
            referencedColumnName = "reiziger_id", nullable = false)
    private Reiziger reiziger;

    @ManyToMany
    @JoinTable(
            name = "ov_chipkaart_product",
            joinColumns = {@JoinColumn(name = "kaart_nummer")},
            inverseJoinColumns = {@JoinColumn(name = "product_nummer")}
    )
    private List<Product> productList = new ArrayList<>();

    public OVChipkaart(int kaartNummer, Date geldigTot, int klasse, int saldo) {
        this.kaartNummer = kaartNummer;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
    }

    public OVChipkaart() {}

    public boolean addProduct(Product p) {
        if (!productList.contains(p)) {
            productList.add(p);
            return true;
        }
        return false;
    }

    public boolean removeProduct(Product p) {
        if (productList.contains(p)) {
            productList.remove(p);
            return true;
        }
        return false;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public int getKaartNummer() {
        return kaartNummer;
    }

    public void setKaartNummer(int kaartNummer) {
        this.kaartNummer = kaartNummer;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OVChipkaart)) return false;
        OVChipkaart that = (OVChipkaart) o;
        return getKaartNummer() == that.getKaartNummer();
    }

    @Override
    public String toString() {
        return "OVChipkaart{" +
                "reiziger=" + reiziger +
                ", kaartNummer=" + kaartNummer +
                ", productList=" + productList +
                '}';
    }
}
