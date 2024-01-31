package tn.esprit.info.alternance.javafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAnnuler;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfPass;


    @FXML
    void ajouterUser(ActionEvent event) {

    }

    @FXML
    void annulerUser(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnAnnuler != null : "fx:id=\"btnAnnuler\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert tfNom != null : "fx:id=\"tfNom\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert tfPass != null : "fx:id=\"tfPass\" was not injected: check your FXML file 'hello-view.fxml'.";

    }

}
