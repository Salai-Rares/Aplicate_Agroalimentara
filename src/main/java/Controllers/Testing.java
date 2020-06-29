package Controllers;

import Models.Magazin;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static Controllers.ControllerMag.magazine;
public class Testing extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ControllerMag.FromAtoO();
        ScrollPane scrollPane = new ScrollPane();
        GridPane gridpane = new GridPane();
        scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPrefSize(390, 390);
        scrollPane.setContent(gridpane);
        Scene scene = new Scene(scrollPane);
        primaryStage.setScene(scene);
        primaryStage.show();
        List btnar=new ArrayList<>();
        for (int i = 0; i < magazine.size(); i++) {
            Button downloadbtn=new Button("Download");
            btnar.add(downloadbtn);
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
        for (int i = 0; i < magazine.size(); i++) {
         //   System.out.println(magazine.get(i).getName());
            Image image = new Image(magazine.get(i).getMag_pic_path());

            ImageView pic = new ImageView();
            pic.setFitWidth(130);
            pic.setFitHeight(130);


            pic.setImage(image);
            VBox vb = new VBox();
            vb.setAlignment(Pos.CENTER);
            vb.getChildren().addAll(pic,(Button)btnar.get(i));
            gridpane.add(vb, imageCol, imageRow);
            GridPane.setMargin(pic, new Insets(2,2,2,2));
            imageCol++;

            // To check if all the 3 images of a row are completed
            if (imageCol > 2) {
                // Reset Column
                imageCol = 0;
                // Next Row
                imageRow++;
          }
        }
    }
}