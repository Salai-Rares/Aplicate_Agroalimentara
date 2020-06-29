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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static AbstractClasses.AlertBox.showAlert;
import static Controllers.ControllerMag.gson;
import static Controllers.ControllerMag.magazine;

public class ControllerCat {
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
    private static String cale;
/*
    @FXML
    private javafx.scene.control.Button adaugac;
    @FXML
    private javafx.scene.control.TextField numec;
    @FXML
    private javafx.scene.control.Button submitc;
    @FXML
    private AnchorPane anchm;
    @FXML
    private TextField magazin;
    @FXML
    private FlowPane flow;*/
    private Categorie categorie;
    public static void setCale(String cale) {
        ControllerCat.cale = cale;
    }

    public void AdaugaPoza(MouseEvent event){
        categorie=new Categorie();
        try{
            String path=SavePhotos.SavePhotos(categorie,"Poze_Categori");
            ControllerCat.setCale(path);
        }catch (Exception exception){
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare Categorie!", "Alegeti o poza!");
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
      if(magazinfx.getText().isEmpty()){
          AlertBox.showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare categorie!", "Introduceti un magazin!");
          return;}


      ControllerMag.FromAtoO();
      try {
          ControllerCat.checkNameDoesNotAlreadyExist(numefx.getText());
      }catch (NameOfCategoryAlreadyExistsException e){
          AlertBox.showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare categorie!", "Numele categoriei exista deja!");
          return;
      }
      String path= ControllerCat.getCale();
      if (path==null){
          showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare categorie!", "Introduceti o poza!");
          return;
      }
      Categorie categorie=new Categorie(path,null,numefx.getText());
      String name=magazinfx.getText();
      //String path= SavePhotos.SavePhotos(categorie,"Poze_Categori");
      //javafx.scene.image.Image img=new Image(path);
     // ImageView imageView=new ImageView();
     // imageView.setImage(img);
     // imageView.setFitWidth(260);
     // imageView.setFitHeight(210);
     // imageView.setSmooth(true);
    //  imageView.setCache(true);
    //  flow.getChildren().add(imageView);
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

    private static String getCale() {
        return ControllerCat.cale;
    }


}
