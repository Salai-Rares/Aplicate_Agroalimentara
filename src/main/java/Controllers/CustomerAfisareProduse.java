package Controllers;

import JSON.CommunicationClass;
import Models.Categorie;
import Models.Magazin;
import Models.Produs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static Controllers.ControllerMag.magazine;

public class CustomerAfisareProduse implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerMag.FromAtoO();
        this.afisareProduse(magazine);
    }
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
    private  List<Magazin> sortare(){

        for(Magazin magazin:magazine){
            if ( magazin.getNume().equals(CommunicationClass.getMagazin()))
                for (Categorie categorie : magazin.getCategori())
                    if(categorie.getNume().equals(CommunicationClass.getCategorie()))
                        Collections.sort(categorie.getProduse(),Produs.ProNameComparator);}
        return magazine;

    }
    public void AfisareSortat(ActionEvent event){

        this.afisareProduse(this.sortare());
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
        CustProdusAfisMain custProdusAfisMain=new CustProdusAfisMain();
        for(Magazin magazin:ref)
            if ( magazin.getNume().equals(CommunicationClass.getMagazin()))
                for (Categorie categorie : magazin.getCategori())
                    if(categorie.getNume().equals(CommunicationClass.getCategorie()))
                        for(Produs produs:categorie.getProduse()){
                            Hyperlink hyperlink = new Hyperlink(produs.getNume());

                            hyperlink.setOnAction(e -> {
                                try {
                                    CommunicationClass.setProdus(hyperlink.getText());
                                    hyperlink.setVisited(false);
                                    custProdusAfisMain.start(scene);
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

                            } }
    }

}
