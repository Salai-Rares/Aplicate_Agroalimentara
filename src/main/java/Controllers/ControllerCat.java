package Controllers;

import AbstractClasses.AlertBox;
import AbstractClasses.SavePhotos;
import Exceptions.NameOfCategoryAlreadyExistsException;
import Exceptions.NameOfShopAlreadyExistsException;
import JSON.CommunicationClass;
import Models.Categorie;
import Models.Magazin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static AbstractClasses.AlertBox.showAlert;
import static Controllers.ControllerMag.gson;
import static Controllers.ControllerMag.magazine;

public class ControllerCat implements Initializable {
    protected Writer write;
    @FXML
    private TextField numefx;
    @FXML
    private TextField magazinfx;
    @FXML
    private AnchorPane anchfx;
    @FXML
    private Button salveazafx;
    @FXML
    private Button inchidefx;
    @FXML
    private ComboBox combofx;

    private Categorie categorie;
    public void setCombofx(){
        for(Magazin m:magazine)
            if (m.getId().equals(CommunicationClass.getUsername())){
                combofx.getItems().add(m.getNume());

            }
    }

    public void AdaugaPoza(MouseEvent event){
        categorie=new Categorie();
        try{
            String path=SavePhotos.SavePhotos(categorie,"Poze_Categori");
            CommunicationClass.setCat_Path(path);
        }catch (Exception exception){
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare Categorie!", "Alegeti o poza!");
            return;
        }


    }
    public void inchide_fereastra(MouseEvent event){
        Stage stage = (Stage) inchidefx.getScene().getWindow();
        stage.close();
    }
    private static void checkNameDoesNotAlreadyExist(String nume) throws NameOfCategoryAlreadyExistsException {
        for (Magazin m : magazine)
            if(m.getId().equals(CommunicationClass.getUsername()))
                for(Categorie categorie:m.getCategori())
                    if(Objects.equals(categorie.getNume(),nume))
                throw new NameOfCategoryAlreadyExistsException();

    }


    public void ButtonAction(ActionEvent event)
  {
      if(numefx.getText().isEmpty()){
          AlertBox.showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare categorie!", "Introduceti un nume!");
      return;}
      if(combofx.getSelectionModel().getSelectedIndex()==-1){
          AlertBox.showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare categorie!", "Introduceti un magazin!");
          return;}

      ControllerMag.FromAtoO();
      try {
          ControllerCat.checkNameDoesNotAlreadyExist(numefx.getText());
      }catch (NameOfCategoryAlreadyExistsException e){
          AlertBox.showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare categorie!", "Numele categoriei exista deja!");
          return;
      }
      String path= CommunicationClass.getCat_Path();
      if (path==null){
          showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare categorie!", "Introduceti o poza!");
          return;
      }


      Categorie categorie=new Categorie(path,null,numefx.getText());
      String name=combofx.getSelectionModel().getSelectedItem().toString();
      boolean adaugat=false;
      for(Magazin m:magazine)
          if (m.getNume().equals(name) && m.getId().equals(CommunicationClass.getUsername())) {
              m.getCategori().add(categorie);
              adaugat = true;
          }
      if(adaugat==false){
          AlertBox.showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare categorie!", "Nu s-a putut face adaugarea!");
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
      if(adaugat==true){
      Stage stage = (Stage) salveazafx.getScene().getWindow();
      stage.close();}
  }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setCombofx();
    }
}
