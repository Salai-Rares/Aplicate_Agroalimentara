package Models;

import Controllers.ProdMain;

import java.util.Comparator;

public class Produs  {
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

        if(pret>0)
            this.pret = pret;
        else
            throw new IllegalArgumentException("Pretul trebuie sa fie un numar pozitiv");
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
    public static Comparator<Produs> ProNameComparator = new Comparator<Produs>(){
        @Override
        public int compare(Produs o1, Produs o2) {
            String ProdusName1 = o1.getNume().toUpperCase();
            String ProdusName2 = o2.getNume().toUpperCase();

  return ProdusName1.compareTo(ProdusName2);

    }};


    public Produs(String nume, double pret, String descriere, String path) throws IllegalArgumentException{
        this.nume = nume;
        if(pret<0)
            throw new IllegalArgumentException("Pretul nu poate fi negativ");
        this.pret = pret;
        this.descriere = descriere;
        this.picture_path=path;
    }
    public Produs(){

    }


}
