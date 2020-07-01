package Controllers;

import AbstractClasses.AlertBox;
import Exceptions.UsernameOrPasswordWrongException;
import JSON.CommunicationClass;
import JSON.UserSer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

import static AbstractClasses.AlertBox.showAlert;

public class ControllerLogin {
    @FXML
    private AnchorPane anchfx;
    @FXML
    private TextField userfx;
    @FXML
    private PasswordField passfx;
    @FXML
    private Button inchidefx;
    @FXML
    private Hyperlink hyperfx;
    @FXML
    private Button salveazafx;
    private UserSer userSer;
    private HomePageMagazinMain homePageMagazinMain;
    private HomePageCumparatorMain homePageCumparatorMain;
   // private  Stage stage = (Stage) inchidefx.getScene().getWindow();
    public void inchide_fereastra(MouseEvent event){
        Stage stage = (Stage) inchidefx.getScene().getWindow();
        stage.close();
    }
    public void setHyperfx(MouseEvent event) throws InterruptedException {
        hyperfx.setVisited(false);
        RegisterMain registerMain=new RegisterMain();
        Stage stage = (Stage) inchidefx.getScene().getWindow();
        try{

        registerMain.start(stage);}catch (Exception exception){
            exception.printStackTrace();
        }

    }
    public void ButtonAction(ActionEvent event){
        userSer=new UserSer();
        if (userfx.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la autentificare!", "Introduceti un username!");
            return;
        }
        if (passfx.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la autentificare!", "Introduceti o parola!");
            return;
        }
        try {
            String rol=userSer.checkCredentials(userfx.getText(),passfx.getText());
            Stage stage=(Stage) salveazafx.getScene().getWindow();
            stage.close();
            homePageMagazinMain=new HomePageMagazinMain();
            if(Objects.equals(rol,"Magazin")){
                CommunicationClass.setUsername(userfx.getText());

                homePageMagazinMain.start(stage);
            }
            else if(Objects.equals(rol,"Cumparator")){
                homePageCumparatorMain=new HomePageCumparatorMain();
                homePageCumparatorMain.start(stage);
            }
        }catch (UsernameOrPasswordWrongException ex){
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la autentificare!", "Date de autentificare gresite!");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
