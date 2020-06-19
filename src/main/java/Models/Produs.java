package Models;

public class Produs {
    private String nume;
    private double pret;
    private String picture_path;
    private String descriere;

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

    public Produs(String nume, double pret, String descriere,String path) {
        this.nume = nume;
        this.pret = pret;
        this.descriere = descriere;
        this.picture_path=path;
    }
}
