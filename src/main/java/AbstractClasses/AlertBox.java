package AbstractClasses;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

import java.util.Optional;

public abstract class AlertBox {
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();

    }
    public static boolean showCofirmation(String title,String message){
     Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
     alert.setTitle(title);
     alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
           return false;
        }
    }
}
