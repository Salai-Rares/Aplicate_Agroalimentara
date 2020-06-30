package Controllers;

import JSON.CommunicationClass;
import Models.InfoComanda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import static Controllers.CustomerInfo.comenzi;

public class ControllerTabel implements Initializable {
    @FXML
    private ScrollPane scroll;
    @FXML
    private Button inchidefx;
    public ObservableList<InfoComanda> loadInfo(){
        ObservableList<InfoComanda> comandas=FXCollections.observableArrayList();
        for(InfoComanda infoComanda:comenzi)
            if(infoComanda.getMagazin().equals(CommunicationClass.getMagazin()))
                comandas.add(infoComanda);
            return comandas;
    }
    private void Afisare(){
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        TableView<InfoComanda> tableView;
        TableColumn<InfoComanda,String> nameColumn=new TableColumn<>("Nume");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));

        TableColumn<InfoComanda,String> adresaColumn=new TableColumn<>("Adresa");
        adresaColumn.setMinWidth(200);
        adresaColumn.setCellValueFactory(new PropertyValueFactory<>("adresa"));

        TableColumn<InfoComanda,String> phoneColumn=new TableColumn<>("Telefon");
        phoneColumn.setMinWidth(200);
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("numar"));

        TableColumn<InfoComanda,String> productColumn=new TableColumn<>("Produs");
        productColumn.setMinWidth(200);
        productColumn.setCellValueFactory(new PropertyValueFactory<>("produs"));

        TableColumn<InfoComanda,Integer> quantityColumn=new TableColumn<>("Cantitate");
        quantityColumn.setMinWidth(200);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("cantitate"));

        tableView=new TableView<>();
        tableView.setItems(loadInfo());
        tableView.getColumns().addAll(nameColumn,adresaColumn,phoneColumn,productColumn,quantityColumn);
        scroll.setContent(tableView);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomerInfo.FromAtoOcomenzi();
        this.Afisare();
    }
    public void inchide_fereastra(MouseEvent event){
        Stage stage = (Stage) inchidefx.getScene().getWindow();
        stage.close();
    }
}
