package GUI;

import Exceptions.UsernameAlreadyExistsException;
import JSON.CryptWithMD5;
import Models.User;
import JSON.UserSer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Registration extends Application {
    private UserSer userSer;
    private User user;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Inregistrare Aplicatie Agroalimentara");

        // Create the registration form grid pane
        GridPane gridPane = createRegistrationFormPane();
        // Add UI controls to the registration form grid pane
        addUIControls(gridPane);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    private GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Creare Cont");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        // Add Email Label
        Label emailLabel = new Label("Email ID : ");
        gridPane.add(emailLabel, 0, 1);
        emailLabel.autosize();

        // Add Email Text Field
        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        gridPane.add(emailField, 1, 1);


        // Add Username Label
        Label usernameLabel = new Label("Username : ");
        gridPane.add(usernameLabel, 0, 2);

        // Add Username Text Field
        TextField usernameField = new TextField();
        usernameField.setPrefHeight(40);
        gridPane.add(usernameField, 1, 2);

        // Add Password Label
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 3);

        // Add Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 3);


        // Add Password Label
        Label passwordLabel2 = new Label("Confirm : ");
        gridPane.add(passwordLabel2, 0, 4);


        // Add Password Field
        PasswordField passwordField2 = new PasswordField();
        passwordField2.setPrefHeight(40);
        gridPane.add(passwordField2, 1, 4);

        // Add FullName Label
        Label FullNumeLabel = new Label("Nume Intreg : ");
        gridPane.add(FullNumeLabel, 0, 5);

        // Add FullName Field
        TextField FullNumeField = new TextField();
        FullNumeField.setPrefHeight(40);
        gridPane.add(FullNumeField, 1, 5);


        // Add Phone Label
        Label PhoneLabel = new Label("Telefon : ");
        gridPane.add(PhoneLabel, 0, 6);

        // Add Phone Field
        TextField PhoneFiled = new TextField();
        PhoneFiled.setPrefHeight(40);
        gridPane.add(PhoneFiled, 1, 6);

        // Add Role ChoiceBox
        ChoiceBox cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList(
                "Cumparator",
                new Separator(), "Magazin")
        );
        gridPane.add(cb, 1, 7);


        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 10, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));


        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)  {

                if (usernameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Registration Error!", "Please enter your username");
                    return;
                }
                if (emailField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Registration Error!", "Please enter your email id");
                    return;
                }
                if (passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Registration Error!", "Please enter a password");
                    return;
                }
                if (FullNumeField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Registration Error!", "Please enter a name");
                    return;
                }
                if (PhoneFiled.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Registration Error!", "Please enter a phone");
                    return;
                }
                if (cb.getSelectionModel().getSelectedItem()==null) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Registration Error!", "Please enter a role ");
                    return;
                }
                if(!passwordField2.getText().equals(passwordField.getText())){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Registration Error!", "Parolele introduse nu coincid!");
                    return;}
                user=new User(usernameField.getText(), CryptWithMD5.cryptWithMD5(passwordField.getText()),FullNumeField.getText(),emailField.getText(),PhoneFiled.getText(),cb.getSelectionModel().getSelectedItem().toString());
                userSer=new UserSer();
                try {
                    userSer.addUsers(user);
                } catch (UsernameAlreadyExistsException e) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Registration Error!", "Username-ul exista deja!");
                    return;
                }


                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!", "Welcome " + usernameField.getText());
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

