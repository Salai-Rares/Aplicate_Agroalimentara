package Models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Magazin {
    private String mag_pic_path;
    private String nume;
    private String adresa;
    private String telefon;
    private String id;

    public String getNume() {
        return nume;
    }
    public Magazin(){

    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    protected List<Categorie> categori=new ArrayList<>();

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Magazin(String mag_pic_path, List<Categorie> categori, String nume, String id, String adresa, String telefon) {
        this.nume=nume;
        this.mag_pic_path = mag_pic_path;
        this.categori = categori;
        this.id=id;
        this.adresa=adresa;
        this.telefon=telefon;

    }
    public static Comparator<Magazin> MagNameComparator = new Comparator<Magazin>(){
        @Override
        public int compare(Magazin o1, Magazin o2) {
            String MagazinName1 = o1.getNume().toUpperCase();
            String MagazinName2 = o2.getNume().toUpperCase();

            return MagazinName1.compareTo(MagazinName2);

        }};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMag_pic_path() {
        return mag_pic_path;
    }

    public void setMag_pic_path(String mag_pic_path) {
        this.mag_pic_path = mag_pic_path;
    }

    public List<Categorie> getCategori() {
        return categori;
    }

    public void setCategori(List<Categorie> categori) {
        this.categori = categori;
    }
}
