package Models;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
    private String pic_path;
    protected List<Produs> produse=new ArrayList<>();

    public Categorie(String pic_path, List<Produs> produse) {
        this.pic_path = pic_path;
        this.produse = produse;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public void setProduse(List<Produs> produse) {
        this.produse = produse;
    }
}
