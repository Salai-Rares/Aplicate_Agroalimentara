package Controllers;

import AbstractClasses.AlertBox;
import JSON.CommunicationClass;
import Models.Categorie;
import Models.Magazin;
import Models.Produs;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ConcurrentModificationException;
import java.util.ResourceBundle;

import static Controllers.ControllerMag.gson;
import static Controllers.ControllerMag.magazine;


public class ControllerAfisareProdus implements Initializable {
    @FXML
    private Label labelfx;
    @FXML
    private AnchorPane anchfx;
    @FXML
    private Button inchidefx;
    @FXML
    private Label numefx;
    @FXML
    private Label pretfx;
    @FXML
    private Label descrierefx;
    @FXML
    private ImageView imagefx;
    @FXML
    private Button stergefx;

    protected Writer write;
    public void inchide_fereastra(MouseEvent event){
        Stage stage = (Stage) inchidefx.getScene().getWindow();
        stage.close();
    }
    private void cautaProdus(){
        ControllerMag.FromAtoO();
        for(Magazin magazin:magazine){
            if(magazin.getId().equals(CommunicationClass.getUsername()))
                for (Categorie categorie1:magazin.getCategori())
                    if(categorie1.getNume().equals(CommunicationClass.getCategorie()))
                        for(Produs produs:categorie1.getProduse())
                            if(produs.getNume().equals(CommunicationClass.getProdus())){
                                CommunicationClass.setPath_produs(produs.getPath());
                                CommunicationClass.setNume_produs(produs.getNume());
                                CommunicationClass.setPret_produs(produs.getPret());
                                CommunicationClass.setDescriere_produs(produs.getDescriere());
                                }
        }
    }
    private void stergeProdus() throws InvocationTargetException, ConcurrentModificationException {
        for (Magazin magazin : magazine) {
            if (magazin.getId().equals(CommunicationClass.getUsername()))
                for (Categorie categorie1 : magazin.getCategori())
                    if (categorie1.getNume().equals(CommunicationClass.getCategorie()))
                        for (Produs produs : categorie1.getProduse())
                            if (produs.getNume().equals(CommunicationClass.getProdus())) {
                                categorie1.getProduse().remove(produs);

                                try {
                                    write = Files.newBufferedWriter(Paths.get("Magazine_db//magazine.json"));
                                    gson.toJson(magazine,write);
                                    write.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
        }
    }

    public void Stergere(MouseEvent event) throws InterruptedException {
       if( AlertBox.showCofirmation("Stergere produs","Doriti sa stergeti acest produs?")) {
           Thread.sleep(1000);
           AlertBox.showAlert(Alert.AlertType.CONFIRMATION,anchfx.getScene().getWindow(),"Stergere produs","Stergerea a avut succes!");

           try{
           this.stergeProdus();
          }catch (InvocationTargetException e){

               return;
           }catch (ConcurrentModificationException exception){

               return;
           }

       }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cautaProdus();
        numefx.setText(CommunicationClass.getNume_produs());
        pretfx.setText(String.valueOf(CommunicationClass.getPret_produs()));
        descrierefx.setText(CommunicationClass.getDescriere_produs());
        Image img=new Image(CommunicationClass.getPath_produs());
        imagefx.setImage(img);
    }
}
