package Controllers;

import AbstractClasses.MakeaDir;
import JSON.CommunicationClass;
import Models.InfoComanda;
import Models.Magazin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static AbstractClasses.AlertBox.showAlert;

public class CustomerInfo implements Initializable {
    @FXML
    private Button salveazafx;
    @FXML
    private Button inchidefx;
    @FXML
    private TextField numefx;
    @FXML
    private TextField adresafx;
    @FXML
    private TextField telefonfx;
    @FXML
    private AnchorPane anchfx;
    protected static Gson gson=new GsonBuilder().setPrettyPrinting().create();
    protected static List<InfoComanda> comenzi=new ArrayList<>();
    private Writer write;
    private InfoComanda infoComanda;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(CommunicationClass.getNume_produs());
        System.out.println(CommunicationClass.getMagazin());
        CustomerInfo.FromAtoOcomenzi();
    }

    public void inchide_fereastra(MouseEvent event){
        Stage stage = (Stage) inchidefx.getScene().getWindow();
        stage.close();
    }
    public void ButtonAction(ActionEvent event){
        MakeaDir.makeaDir("Comenzi_db");


        if (numefx.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la comandare produs!", "Introduceti un nume!");
            return;
        }
        if (adresafx.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la comandare produs!", "Introduceti o adresa!");
            return;
        }
        if (telefonfx.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la comandare produs!", "Introduceti un telefon!");
            return;
        }
        MakeaDir.makeaDir("Comenzi_db");
        try {
            write = Files.newBufferedWriter(Paths.get("Comenzi_db//comenzi.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        infoComanda=new InfoComanda(numefx.getText(),adresafx.getText(),telefonfx.getText(),CommunicationClass.getMagazin(),CommunicationClass.getNume_produs(),CommunicationClass.getCantitate());

        comenzi.add(infoComanda);
        try{
            gson.toJson(comenzi,write);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) salveazafx.getScene().getWindow();
        stage.close();


    }

    public static void FromAtoOcomenzi(){
        try{

            Reader reader= Files.newBufferedReader(Paths.get("Comenzi_db//comenzi.json"));
            List<InfoComanda> u = Arrays.asList(gson.fromJson(reader, InfoComanda[].class));
            comenzi.removeAll(comenzi);
            comenzi.addAll(u);
            reader.close();


        }catch (Exception ex){
            return;
        }

    }
}
