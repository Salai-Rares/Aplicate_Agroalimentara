package Models;

import java.util.Objects;

public class InfoComanda {
    private String nume;
    private String adresa;
    private String numar;
    private String magazin;
    private String produs;
    private int cantitate;

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public InfoComanda(String nume, String adresa, String numar, String magazin, String produs, int cantitate) {
        this.nume = nume;
        this.adresa = adresa;
        this.numar = numar;
        this.magazin=magazin;
        this.produs=produs;
        this.cantitate=cantitate;
    }

    public String getMagazin() {
        return magazin;
    }

    public void setMagazin(String magazin) {
        this.magazin = magazin;
    }

    public String getProdus() {
        return produs;
    }

    public void setProdus(String produs) {
        this.produs = produs;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNumar() {
        return numar;
    }

    public void setNumar(String numar) {
        this.numar = numar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InfoComanda)) return false;
        InfoComanda that = (InfoComanda) o;
        return Objects.equals(getNume(), that.getNume()) &&
                Objects.equals(getAdresa(), that.getAdresa()) &&
                Objects.equals(getNumar(), that.getNumar());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNume(), getAdresa(), getNumar());
    }
}
