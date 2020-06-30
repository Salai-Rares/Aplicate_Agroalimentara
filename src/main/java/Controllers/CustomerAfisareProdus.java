package Controllers;

import JSON.CommunicationClass;
import Models.Categorie;
import Models.Magazin;
import Models.Produs;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.Writer;
import java.net.URL;
import java.util.ResourceBundle;

import static AbstractClasses.AlertBox.showAlert;
import static Controllers.ControllerMag.magazine;

public class CustomerAfisareProdus implements Initializable {
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
    private ComboBox combofx;
    private String address= " ";
    public void inchide_fereastra(MouseEvent event){
        Stage stage = (Stage) inchidefx.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerMag.FromAtoO();
        this.cautaProdus();
        numefx.setText(CommunicationClass.getNume_produs());
        pretfx.setText(String.valueOf(CommunicationClass.getPret_produs()));
        descrierefx.setText(CommunicationClass.getDescriere_produs());
        Image img=new Image(CommunicationClass.getPath_produs());
        imagefx.setImage(img);
        this.setCombofx();
    }
    public void setCombofx(){
        combofx.getItems().addAll("1","2","3","4","5");
        combofx.setPromptText("NR");
        combofx.setEditable(true);
        combofx.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                address=newValue;
            }
        });
    }
    private void cautaProdus(){
        for(Magazin magazin:magazine){
            if(magazin.getNume().equals(CommunicationClass.getMagazin()))
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
    public void ButtonAction(ActionEvent event) throws NumberFormatException{
        CommunicationClass.setNume_produs(numefx.getText());
        try{
        CommunicationClass.setCantitate(Integer.parseInt(combofx.getSelectionModel().getSelectedItem().toString()));}catch (NumberFormatException exception){
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la comanda!", "Cantitatea este reprezentata de un numar!");
            return;
        }catch (NullPointerException e){
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la comanda!", "Alegeti cantitatea dorita!");
            return;
        }
        Stage stage=(Stage)inchidefx.getScene().getWindow();
        stage.close();
        CustInfoMain custInfoMain=new CustInfoMain();
        try {
            custInfoMain.start(stage);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
