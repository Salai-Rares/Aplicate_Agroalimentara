package Models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static Exceptions.isPhoneException.isPhone;

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

    public Magazin(String mag_pic_path, List<Categorie> categori, String nume, String id, String adresa, String telefon) throws IllegalArgumentException {
        this.nume=nume;
        this.mag_pic_path = mag_pic_path;
        this.categori = categori;
        this.id=id;
        this.adresa=adresa;
        if(telefon.length()!=10)
            throw new IllegalArgumentException("Introduceti un numar valid");
        if(!isPhone(telefon))
            throw new NumberFormatException("Numarul trebuie sa inceapa cu 0");
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
