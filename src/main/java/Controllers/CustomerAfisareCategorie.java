package Controllers;

import JSON.CommunicationClass;
import Models.Categorie;
import Models.Magazin;
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

public class CustomerAfisareCategorie implements Initializable {
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

        for(Magazin magazin:magazine)
            if ( magazin.getNume().equals(CommunicationClass.getMagazin()))
                Collections.sort(magazin.getCategori(), Categorie.CatNameComparator);
        return magazine;

    }
    public void AfisareSortat(ActionEvent event){

        this.afisareCategorii(this.sortare());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerMag.FromAtoO();
        this.afisareCategorii(magazine);
    }
    private void afisareCategorii(List<Magazin> ref){


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
        CustProdAfisMain custProdAfisMain = new CustProdAfisMain();
        for(Magazin magazin:ref)
            if ( magazin.getNume().equals(CommunicationClass.getMagazin())){

                //     size=magazin.getCategori().size();
                for (Categorie categorie : magazin.getCategori()) {
                    Hyperlink hyperlink = new Hyperlink(categorie.getNume());

                    hyperlink.setOnAction(e -> {
                        try {
                            CommunicationClass.setCategorie(hyperlink.getText());
                            hyperlink.setVisited(false);
                            custProdAfisMain.start(scene);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    });
                    btnar.add(hyperlink);
                    Image image = new Image(categorie.getPic_path());
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
                }}
    }

}
