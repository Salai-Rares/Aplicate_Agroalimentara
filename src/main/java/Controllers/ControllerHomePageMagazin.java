package Controllers;

import JSON.CommunicationClass;
import Models.Categorie;
import Models.Magazin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static Controllers.ControllerMag.magazine;
public class ControllerHomePageMagazin implements Initializable {

    @FXML
    private Button inchidefx;
    @FXML
    private ScrollPane scroll;
    public void CreazaMagazin (MouseEvent event){
        Stage primaryStage=new Stage();
        MagMain magMain=new MagMain();
        try{
            magMain.start(primaryStage);}catch (Exception e){
            e.printStackTrace();
        }

    }
    public void CreazaCategorie (MouseEvent event){
        Stage primaryStage=new Stage();
        CatMain catMain=new CatMain();
        try{
            catMain.start(primaryStage);}catch (Exception e){
            e.printStackTrace();
        }

    }
    public void CreazaProdus (MouseEvent event){
        Stage primaryStage=new Stage();
        ProdMain prodMain=new ProdMain();
        try{
            prodMain.start(primaryStage);}catch (Exception e){
            e.printStackTrace();
        }

    }
    public void inchide_fereastra(MouseEvent event){
        Stage stage = (Stage) inchidefx.getScene().getWindow();
        stage.close();
    }
    private  List<Magazin> sortare(){

        Collections.sort(magazine, Magazin.MagNameComparator);
        return magazine;

    }
    public void loadPhotos(){

        GridPane gridpane = new GridPane();
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
      //  scroll.setPrefSize(390, 390);
        scroll.setContent(gridpane);
        List btnar=new ArrayList<>();
        for(Magazin m : magazine){
            if(m.getId().equals(CommunicationClass.getUsername())){
            Hyperlink hyperlink=new Hyperlink(m.getNume());

               Stage scene=new Stage();
            CatAfisMain catAfisMain=new CatAfisMain();
            hyperlink.setOnAction(e-> {
                try {
                    CommunicationClass.setMagazin(hyperlink.getText());
                    hyperlink.setVisited(false);
                    catAfisMain.start(scene);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
            btnar.add(hyperlink);}
        }

        gridpane.setAlignment(Pos.CENTER);
        gridpane.setPadding(new Insets(50, 50, 50, 50));
        gridpane.setHgap(50);
        gridpane.setVgap(50);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setFillWidth(true);
        columnConstraints.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().add(columnConstraints);
        int imageCol = 0;
        int imageRow = 0;
        int contor =-1;
        for (int i = 0; i < magazine.size(); i++) {

            //   System.out.println(magazine.get(i).getName());
            if(magazine.get(i).getId().equals(CommunicationClass.getUsername())) {
                contor++;
                Image image = new Image(magazine.get(i).getMag_pic_path());
                ImageView pic = new ImageView();
                pic.setFitWidth(130);
                pic.setFitHeight(130);


                pic.setImage(image);
                VBox vb = new VBox();
                vb.setAlignment(Pos.CENTER);
                vb.getChildren().addAll(pic, (Hyperlink) btnar.get(contor));
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
    }
    public void AfisareSortare(ActionEvent event){
        this.sortare();
        this.loadPhotos();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerMag.FromAtoO();
        this.loadPhotos();
    }
}
