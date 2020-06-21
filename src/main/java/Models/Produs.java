package Models;

public class Produs {
    private String nume;
    private double pret;
    private String picture_path;
    private String descriere;
    private String id;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public String getPath() {
        return this.picture_path;
    }

    public void setPath(String path) {
        this.picture_path = path;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Produs(String nume, double pret, String descriere, String path, String id) {
        this.nume = nume;
        this.pret = pret;
        this.descriere = descriere;
        this.picture_path=path;
        this.id=id;

    }
}
