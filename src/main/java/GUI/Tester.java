package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Tester extends Application {



  private FlowPane flowPane = new FlowPane();
  private ScrollBar scrollBar= new ScrollBar();
    private static final String BUTTON_ICON_LOC ="file:Poze_Magazine/AMDV6465.JPG";
    private static final Text name=new Text("Something");
    private static final String BUTTON_ICON_LOC2 ="file:Poze_Categori/BRNU5726.JPG";
    private static final Text name2=new Text("Something2");



    @Override
    public void start(Stage primaryStage) throws Exception {
       final ImageView graphic = new ImageView(new Image(BUTTON_ICON_LOC));
          graphic.setFitHeight(100);
        graphic.setPreserveRatio(true);
        Button button = new Button(null ,graphic);
        button.setStyle("-fx-base: mistyrose;");
        name.setStyle("-fx-text-fill: black");
        button.setOnAction(event -> {
            System.out.println("Rares");
        });


        StackPane layout = new StackPane(button);
        layout.getChildren().add(name);
        layout.setAlignment(Pos.BOTTOM_CENTER);
        layout.setPadding(new Insets(10,10,10,10));

        final ImageView graphic2 = new ImageView(new Image(BUTTON_ICON_LOC2));
        graphic2.setFitHeight(100);
        graphic2.setPreserveRatio(true);
        Button button2 = new Button(null ,graphic2);
        button2.setStyle("-fx-base: mistyrose;");
        name2.setStyle("-fx-text-fill: black");
        button2.setOnAction(event -> {
            System.out.println("Rares2");
        });
        StackPane layout2 = new StackPane(button2);
        layout2.getChildren().add(name2);
        layout2.setAlignment(Pos.BOTTOM_CENTER);
        layout2.setPadding(new Insets(10,10,10,10));
        flowPane.getChildren().add(scrollBar);
        scrollBar.setOrientation(Orientation.VERTICAL);
        scrollBar.getOnScroll();



        flowPane.getChildren().addAll(layout,layout2);






        primaryStage.setScene(new Scene(flowPane));
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }


}
