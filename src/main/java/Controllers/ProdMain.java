package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ProdMain extends Application {
private Stage primaryStage;
private AnchorPane anchorPane;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/photochooser.fxml"));
        anchorPane=loader.load();
        Scene scene =new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
