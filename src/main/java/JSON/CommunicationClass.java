package JSON;

public class CommunicationClass {
    private static String username;
    private static String magazin;
    private static String categorie;
    private static String produs;
    private static String nume_produs;
    private static double pret_produs;
    private static String path_produs;

    public static String getPath_produs() {
        return path_produs;
    }

    public static void setPath_produs(String path_produs) {
        CommunicationClass.path_produs = path_produs;
    }

    public static String getNume_produs() {
        return nume_produs;
    }

    public static void setNume_produs(String nume_produs) {
        CommunicationClass.nume_produs = nume_produs;
    }

    public static double getPret_produs() {
        return pret_produs;
    }

    public static void setPret_produs(double pret_produs) {
        CommunicationClass.pret_produs = pret_produs;
    }

    public static String getDescriere_produs() {
        return descriere_produs;
    }

    public static void setDescriere_produs(String descriere_produs) {
        CommunicationClass.descriere_produs = descriere_produs;
    }

    private static String descriere_produs;


    public static String getProdus() {
        return produs;
    }

    public static void setProdus(String produs) {
        CommunicationClass.produs = produs;
    }

    public static String getCategorie() {
        return categorie;
    }

    public static void setCategorie(String categorie) {
        CommunicationClass.categorie = categorie;
    }

    private static int logreg=0;


    public static int getLogreg() {
        return logreg;
    }

    public static void setLogreg(int logreg) {
        CommunicationClass.logreg = logreg;
    }

    public static String getMagazin() {
        return magazin;
    }

    public static void setMagazin(String magazin) {
        CommunicationClass.magazin = magazin;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        CommunicationClass.username = username;
    }
}
