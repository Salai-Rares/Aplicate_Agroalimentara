package Controllers;

import AbstractClasses.MakeaDir;
import AbstractClasses.SavePhotos;
import Exceptions.UsernameAlreadyExistsException;
import JSON.UserSer;
import Models.Produs;
import Models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControllerProd{
    protected static Gson gson=new GsonBuilder().setPrettyPrinting().create();
    protected static List<Produs> produse=new ArrayList<>();
    protected Writer write;
@FXML
private Button btn;
@FXML
private javafx.scene.control.TextField descriere_field;
@FXML
private javafx.scene.control.TextField nume_field;
@FXML
private TextField pret_field;
@FXML
private AnchorPane anch;

private ControllerProd controllerProd;

public void ButtonAction(ActionEvent event){

    controllerProd=new ControllerProd();
    if (nume_field.getText().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, anch.getScene().getWindow(), "Eroare la creare produs!", "Introduceti un nume!");
        return;
    }
    if (pret_field.getText().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, anch.getScene().getWindow(), "Eroare la creare produs!", "Introduceti un pret!");
        return;
    }
    if (descriere_field.getText().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, anch.getScene().getWindow(), "Eroare la creare produs!", "Introduceti o descriere!");
        return;
    }
   Produs produs=new Produs(nume_field.getText(),Integer.parseInt(pret_field.getText()),descriere_field.getText(),"nvm");
    SavePhotos.SavePhotos(produs,"Photos");
        controllerProd.addProdus(produs);

}
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    public static void FromAtoO(){
        try{

            Reader reader= Files.newBufferedReader(Paths.get("Produse_db//produse.json"));
            List<Produs> u = Arrays.asList(gson.fromJson(reader, Produs[].class));
            produse.removeAll(produse);
            produse.addAll(u);
            reader.close();


        }catch (Exception ex){
            return;
        }

    }


    public void addProdus(Produs u) {
        ControllerProd.FromAtoO();
        MakeaDir.makeaDir("Produse_db");
        try {
            write = Files.newBufferedWriter(Paths.get("Produse_db//produse.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        produse.add(u);
        try{
            gson.toJson(produse,write);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
