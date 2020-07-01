package Controllers;

import AbstractClasses.AlertBox;
import AbstractClasses.SavePhotos;
import Exceptions.NameOfCategoryAlreadyExistsException;
import Exceptions.NameOfProducAlreadyExistsException;
import JSON.CommunicationClass;
import Models.Categorie;
import Models.Magazin;
import Models.Produs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ResourceBundle;

import static AbstractClasses.AlertBox.showAlert;
import static Controllers.ControllerMag.gson;
import static Controllers.ControllerMag.magazine;

public class ControllerProd implements Initializable {

    @FXML
    private ComboBox combofx;
    @FXML
    private AnchorPane anchfx;
    @FXML
    private TextField numefx;
    @FXML
    private TextField pretfx;
    @FXML
    private TextField descrierefx;
    @FXML
    private Button inchidefx;
    @FXML
    private Button salveazafx;
    private static String cale;
    public void setCombofx(){
        for(Magazin m:magazine)
            if (m.getId().equals(CommunicationClass.getUsername()) && m.getCategori().size()!=0){
                for(Categorie c:m.getCategori())

                    combofx.getItems().add(c.getNume());

            }
    }

    private static void checkNameDoesNotAlreadyExist(String nume) throws NameOfProducAlreadyExistsException {
        for (Magazin m : magazine)
            if(m.getId().equals(CommunicationClass.getUsername()))
                for(Categorie categorie:m.getCategori())
                 for(Produs produs:categorie.getProduse()){
                     if(Objects.equals(produs.getNume(),nume))
                        throw new NameOfProducAlreadyExistsException();

    }}
    public void inchide_fereastra(MouseEvent event){
        Stage stage = (Stage) inchidefx.getScene().getWindow();
        stage.close();

    }
    public static String getCale() {
        return cale;
    }

    public static void setCale(String cale) {
        ControllerProd.cale = cale;
    }

    protected Writer write;
    protected static Produs p;
    public void AdaugaPoza(MouseEvent event){
        p=new Produs();
        try{
            String path= SavePhotos.SavePhotos(p,"Poze_Produse");
            ControllerProd.setCale(path);
        }catch (Exception exception){
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare produs!", "Alegeti o poza!");
        }


    }

    public void ButtonAction(ActionEvent event) throws NumberFormatException
    {
       ControllerMag.FromAtoO();
        try {
            ControllerProd.checkNameDoesNotAlreadyExist(numefx.getText());
        }catch (NameOfProducAlreadyExistsException e){
            AlertBox.showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare produs!", "Numele produsului exista deja!");
            return;
        }
        String path= ControllerProd.getCale();
        if(numefx.getText().isEmpty()){
            AlertBox.showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare produs!", "Introduceti un nume!");
            return;}
        if(pretfx.getText().isEmpty()){
            AlertBox.showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare produs!", "Introduceti un pret!");
            return;}
        if(descrierefx.getText().isEmpty()){
            AlertBox.showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare produs!", "Introduceti o descriere!");
            return;}
        if(combofx.getSelectionModel().getSelectedIndex()==-1){
            AlertBox.showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare produs!", "Introduceti o categorie!");
            return;}
        //ControllerMag.FromAtoO();
        try{
        p =new Produs(numefx.getText(),Double.parseDouble(pretfx.getText()),descrierefx.getText(),path);}catch (NumberFormatException e){
            AlertBox.showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare produs!", "Pretul trebuie sa fie un numar!");
            return;
        }catch (IllegalArgumentException illegalArgumentException){
            AlertBox.showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare produs!", illegalArgumentException.getMessage());

        }
        String name=combofx.getSelectionModel().getSelectedItem().toString();

        boolean adaugat=false;
        for(Magazin m:magazine){
            if(m.getId().equals(CommunicationClass.getUsername()))
                for(Categorie c:m.getCategori())
                    if(c.getNume().equals(name)){
                        c.getProduse().add(p);
                        adaugat=true;
                    }
                }



        if(adaugat==false){
            AlertBox.showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare produs!", "Nu s-a putut face adaugarea!");
            return;
        }
        if (path==null){
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare categorie!", "Introduceti o poza!");
            return;
        }
        try {
            write = Files.newBufferedWriter(Paths.get("Magazine_db//magazine.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } try{
        gson.toJson(magazine,write);
        write.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
        Stage stage = (Stage) salveazafx.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setCombofx();
    }
}
