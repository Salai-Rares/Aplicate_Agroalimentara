package Controllers;

import AbstractClasses.AlertBox;
import JSON.CommunicationClass;
import Models.Categorie;
import Models.Magazin;
import Models.Produs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static Controllers.ControllerMag.gson;
import static Controllers.ControllerMag.magazine;

public class ControllerAfisareProduse implements Initializable {
    @FXML
    private Button sortarefx;
    @FXML
    private Button inchidefx;
    @FXML
    private ScrollPane scroll;
    @FXML
    private AnchorPane anchfx;

    public void inchide_fereastra(MouseEvent event){
        Stage stage = (Stage) inchidefx.getScene().getWindow();
        stage.close();
    }
    private void afisareProduse(List<Magazin> ref){
        int contor=0;
        int imageCol = 0;
        int imageRow = 0;
        GridPane gridpane = new GridPane();
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setPadding(new Insets(50, 50, 50, 50));
        gridpane.setHgap(50);
        gridpane.setVgap(50);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setFillWidth(true);
        columnConstraints.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().add(columnConstraints);
        // scroll.setPrefSize(390, 390);
        scroll.setContent(gridpane);
        List btnar=new ArrayList<>();
        Stage scene = new Stage();
       produsAfisMain produsAfisMain=new produsAfisMain();
        for(Magazin magazin:ref)
            if (magazin.getId().equals(CommunicationClass.getUsername()) && magazin.getNume().equals(CommunicationClass.getMagazin()))
                for (Categorie categorie : magazin.getCategori())
                if(categorie.getNume().equals(CommunicationClass.getCategorie()))
                for(Produs produs:categorie.getProduse()){
                    Hyperlink hyperlink = new Hyperlink(produs.getNume());

                    hyperlink.setOnAction(e -> {
                        try {
                            CommunicationClass.setProdus(hyperlink.getText());
                            hyperlink.setVisited(false);
                            produsAfisMain.start(scene);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    });
                    btnar.add(hyperlink);
                    Image image = new Image(produs.getPath());
                    ImageView pic = new ImageView();
                    pic.setFitWidth(130);
                    pic.setFitHeight(130);
                    pic.setImage(image);
                    VBox vb = new VBox();
                    vb.setAlignment(Pos.CENTER);
                    vb.getChildren().addAll(pic, (Hyperlink) btnar.get(contor));
                    contor++;
                    gridpane.add(vb, imageCol, imageRow);
                    GridPane.setMargin(pic, new Insets(2, 2, 2, 2));
                    imageCol++;

                    // To check if all the 3 images of a row are completed
                    if (imageCol >= 3) {
                        // Reset Column
                        imageCol = 0;
                        // Next Row
                        imageRow++;

                    }
                }
    }
    private void stergeCategorie() throws ConcurrentModificationException{
        for(Magazin magazin:magazine)
            if (magazin.getId().equals(CommunicationClass.getUsername()) && magazin.getNume().equals(CommunicationClass.getMagazin()))
                for (Categorie categorie : magazin.getCategori())
                    if(categorie.getNume().equals(CommunicationClass.getCategorie())){
                        magazin.getCategori().remove(categorie);
                        try {
                            Writer write = Files.newBufferedWriter(Paths.get("Magazine_db//magazine.json"));
                            gson.toJson(magazine,write);
                            write.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

    }
    public void Stergere(MouseEvent event) throws InterruptedException {
        if( AlertBox.showCofirmation("Stergere Categorie","Doriti sa stergeti acesta categorie?")) {
            Thread.sleep(1000);
            AlertBox.showAlert(Alert.AlertType.CONFIRMATION,anchfx.getScene().getWindow(),"Stergere Categorie","Stergerea a avut succes!");

            try{
                this.stergeCategorie();
            } catch (ConcurrentModificationException exception){

                return;
            }

        }

    }


    private  List<Magazin> sortare(){

        for(Magazin magazin:magazine){
            if (magazin.getId().equals(CommunicationClass.getUsername()) && magazin.getNume().equals(CommunicationClass.getMagazin()))
                for (Categorie categorie : magazin.getCategori())
                    if(categorie.getNume().equals(CommunicationClass.getCategorie()))
                        Collections.sort(categorie.getProduse(),Produs.ProNameComparator);}
                    return magazine;

    }
    public void AfisareSortat(ActionEvent event){

        this.afisareProduse(this.sortare());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerMag.FromAtoO();
        this.afisareProduse(magazine);
    }
}
