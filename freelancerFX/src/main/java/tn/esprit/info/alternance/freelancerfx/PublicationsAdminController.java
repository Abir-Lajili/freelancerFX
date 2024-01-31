package tn.esprit.info.alternance.javafx;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import khadamni.gui.PublicationGui;
import khadamni.modeles.Publication;
import khadamni.utiles.DbConnection;

import static java.sql.DriverManager.getConnection;

public class PublicationsAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Publication> table;


    @FXML
    private Button btnEnregistrer;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Button btnSupprimerTous;

    @FXML
    private TableColumn<Publication, String> contenuPub;


    @FXML
    private TableColumn<Publication, String> datePub;

    @FXML
    private TableColumn<Publication, Integer> idPub;

    @FXML
    private TableColumn<Publication, String> statusPub;

    @FXML
    private TextField tfContenu;

    @FXML
    private TextField tfDate;

    @FXML
    private TextField tfStatus;

    @FXML
    private TextField tfTitre;



    @FXML
    private TableColumn<Publication, String> titrePub;

    private ObservableList<Publication> publicationsList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        // Set up table columns
        idPub.setCellValueFactory(new PropertyValueFactory<>("publicationId"));
        titrePub.setCellValueFactory(new PropertyValueFactory<>("title"));
        contenuPub.setCellValueFactory(new PropertyValueFactory<>("content"));
        datePub.setCellValueFactory(new PropertyValueFactory<>("date"));
        statusPub.setCellValueFactory(new PropertyValueFactory<>("status"));




        // Call method to load data from the database
    }


    @FXML
    void onEnregisterPublication(ActionEvent event) {

    }

    @FXML
    void onModifierPublication(ActionEvent event) {

    }

    @FXML
    void onSupprimerPublication(ActionEvent event) {

    }

    @FXML
    void onSupprimerTous(ActionEvent event) {

    }



}
