package Models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Categorie {
    private String pic_path;
    protected List<Produs> produses= new ArrayList<>();
    private String nume;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Categorie(String pic_path, List<Produs> produse, String nume) {
        this.pic_path = pic_path;
        produse=null;
        this.nume=nume;
    }
    public Categorie(){

    }
    public static Comparator<Categorie> CatNameComparator = new Comparator<Categorie>(){
        @Override
        public int compare(Categorie o1, Categorie o2) {
            String CategorieName1 = o1.getNume().toUpperCase();
            String CategorieName2 = o2.getNume().toUpperCase();

            return CategorieName1.compareTo(CategorieName2);

        }};

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public List<Produs> getProduse() {
        return produses;
    }

    public void setProduse(List<Produs> produse) {
        this.produses = produse;
    }
}
