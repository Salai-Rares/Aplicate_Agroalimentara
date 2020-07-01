package Controllers;

import AbstractClasses.AlertBox;
import AbstractClasses.MakeaDir;
import AbstractClasses.SavePhotos;
import Exceptions.NameOfShopAlreadyExistsException;
import Exceptions.UsernameAlreadyExistsException;
import JSON.CommunicationClass;
import Models.Magazin;
import Models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static AbstractClasses.AlertBox.*;

public class ControllerMag {
    protected static Gson gson=new GsonBuilder().setPrettyPrinting().create();
    protected static List<Magazin> magazine=new ArrayList<>();
    protected Writer write;

    @FXML
    private AnchorPane anchfx;
    @FXML
    private Button salveazafx;
    @FXML
    private Button pozafx;
    @FXML
    private TextField numefx;
    @FXML
    private TextField telefonfx;
    @FXML
    private TextField adresafx;
    @FXML
    private Button inchidefx;
    private static String  cale;
    private  Magazin magazin;

    public static String getCale() {
        return cale;
    }

    public static void setCale(String cale) {
        ControllerMag.cale = cale;
    }


    public void AdaugaPoza(MouseEvent event){
        magazin=new Magazin();
        try{
       String path=SavePhotos.SavePhotos(magazin,"Poze_Magazine");
       ControllerMag.setCale(path);
        }catch (Exception exception){
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare magazin!", "Alegeti o poza!");
        }
    }


    public void inchide_fereastra(MouseEvent event){
        Stage stage = (Stage) inchidefx.getScene().getWindow();
        stage.close();
    }
    private static void checkNameDoesNotAlreadyExist(String nume) throws NameOfShopAlreadyExistsException {
        for (Magazin m : magazine) {
            if (Objects.equals(nume, m.getNume()))
                throw new NameOfShopAlreadyExistsException();
        }
    }

    public void ButtonAction(ActionEvent event) {

        ControllerMag.FromAtoO();
        try {
            ControllerMag.checkNameDoesNotAlreadyExist(numefx.getText());
        }catch (NameOfShopAlreadyExistsException e){
            AlertBox.showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare magazin!", "Numele magazinului exista deja!");
            return;
        }

        MakeaDir.makeaDir("Magazine_db");
        try {
            write = Files.newBufferedWriter(Paths.get("Magazine_db//magazine.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
     //   controllerMag=new ControllerMag();
        if (numefx.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare magazin!", "Introduceti un nume!");
            return;
        }
        if (adresafx.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare magazin!", "Introduceti o adresa!");
            return;
        }
        if (telefonfx.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare magazin!", "Introduceti un telefon!");
            return;
        }
        String path= ControllerMag.getCale();
        if (path==null){
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare Magazin!", "Introduceti o poza!");
            return;
        }
        try{
         magazin=new Magazin(path,null,numefx.getText(),CommunicationClass.getUsername(),adresafx.getText(),telefonfx.getText());}
        catch (IllegalArgumentException illegalArgumentException) {
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la creare Magazin!", illegalArgumentException.getMessage());
            return;
        }
        //controllerMag.addMagazin(magazin);
        magazine.add(magazin);
        try{
            gson.toJson(magazine,write);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) salveazafx.getScene().getWindow();
        stage.close();

    }


    public static void FromAtoO(){
        try{

            Reader reader= Files.newBufferedReader(Paths.get("Magazine_db//magazine.json"));
            List<Magazin> u = Arrays.asList(gson.fromJson(reader, Magazin[].class));
            magazine.removeAll(magazine);
            magazine.addAll(u);
            reader.close();


        }catch (Exception ex){
            return;
        }

    }



}

