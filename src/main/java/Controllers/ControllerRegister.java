package Controllers;

import Exceptions.UsernameAlreadyExistsException;
import JSON.CryptWithMD5;
import JSON.UserSer;
import Models.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static AbstractClasses.AlertBox.showAlert;

public class ControllerRegister implements Initializable {
    @FXML
    private Button salveazafx;
    @FXML
    private Button inchidefx;
    @FXML
    private TextField userfx;
    @FXML
    private TextField emailfx;
    @FXML
    private PasswordField passfx;
    @FXML
    private PasswordField confirmfx;
    @FXML
    private TextField numefx;
    @FXML
    private TextField telefonfx;
    @FXML
    private ChoiceBox rolfx;
    @FXML
    private AnchorPane anchfx;
    LoginMain loginMain=new LoginMain();
    private UserSer userSer;
    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rolfx.setItems(FXCollections.observableArrayList("Cumparator", new Separator(), "Magazin"));
    }
    public void inchide_fereastra(MouseEvent event){
        Stage stage = (Stage) inchidefx.getScene().getWindow();
        stage.close();
        try {
            loginMain.start(stage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void ButtonAction(ActionEvent event) throws InterruptedException {
        if (emailfx.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la inregistrare!!", "Introduceti un email!");
            return;
        }
        if (userfx.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la inregistrare!!", "Introduceti un username!");
            return;
        }
        if (passfx.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la inregistrare!!", "Introduceti o parola!");
            return;
        }
        if (confirmfx.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "EEroare la inregistrare!!", "Confirmati parola!");
            return;
        }
        if (numefx.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la inregistrare!!", "Introduceti un nume!");
            return;
        }
        if (telefonfx.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la inregistrare!", "Introduceti un telefon!");
            return;
        }
        if (rolfx.getSelectionModel().getSelectedItem()==null) {
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la inregistrare!", "Introduceti un rol!");
            return;
        }
        if(!passfx.getText().equals(confirmfx.getText())){
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la inregistrare!", "Parolele nu corespund!");
            return;
        }
        user=new User(userfx.getText(), CryptWithMD5.cryptWithMD5(passfx.getText()),numefx.getText(),emailfx.getText(),telefonfx.getText(),rolfx.getSelectionModel().getSelectedItem().toString());
        userSer=new UserSer();
        try {
            userSer.addUsers(user);
        } catch (UsernameAlreadyExistsException e) {
            showAlert(Alert.AlertType.ERROR, anchfx.getScene().getWindow(), "Eroare la inregistrare!", "Username-ul exista deja!");
            return;
        }
        showAlert(Alert.AlertType.CONFIRMATION, anchfx.getScene().getWindow(), "Registration Successful!", "Welcome " + userfx.getText());
        Thread.sleep(2000);
        Stage stage=(Stage) salveazafx.getScene().getWindow();
        stage.close();

        try {
            loginMain.start(stage);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
