package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HomePageCumparatorMain extends Application {
    private AnchorPane anchorPane;
    protected Stage window;
    private double xOffset = 0;
    private double yOffset = 0;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        // window.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/HomePageCustomer.fxml"));
        anchorPane=loader.load();
        anchorPane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        //move around here
        anchorPane.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        Scene scene =new Scene(anchorPane);
        window.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        window.show();
    }
}
