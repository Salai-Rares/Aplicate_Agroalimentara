package Models;

import java.util.ArrayList;
import java.util.List;

public class Magazin {
    private String mag_pic_path;
    protected List<Categorie> categori=new ArrayList<>();

    public Magazin(String mag_pic_path, List<Categorie> categori) {
        this.mag_pic_path = mag_pic_path;
        this.categori = categori;
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
