package hu.domein;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    private int product_nummer;
    private String naam;
    private String beschrijving;
    private int prijs;

    @ManyToMany(mappedBy = "producten")
    private List<OVChipkaart> ovChipkaartList = new ArrayList<>();

    public Product(int product_nummer, String naam, String beschrijving, int prijs) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public Product() {}

    public boolean addOVChipkaart(OVChipkaart ovc) {
        if (!ovChipkaartList.contains(ovc)) {
            ovChipkaartList.add(ovc);
            return true;
        }
        return false;
    }

    public boolean removeOVChipkaart(OVChipkaart ovc) {
        if (ovChipkaartList.contains(ovc)) {
            ovChipkaartList.remove(ovc);
            return true;
        }
        return false;
    }

    public List<OVChipkaart> getOvChipkaartList() {
        return ovChipkaartList;
    }

    public int getProduct_nummer() {
        return product_nummer;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public int getPrijs() {
        return prijs;
    }

    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getProduct_nummer() == product.getProduct_nummer();
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_nummer=" + product_nummer +
                ", naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                '}';
    }
}
