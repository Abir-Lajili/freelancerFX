package tn.esprit.info.alternance.freelancerfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Reclamation;
import services.ReclamationService;

import java.sql.SQLException;
import java.util.Date;

public class ReclamationController {

    @FXML
    private TextField nom;

    @FXML
    private TextField email;

    @FXML
    private TextArea message;

    @FXML
    private Label idMsg;

    private final ReclamationService reclamationCrud = new ReclamationService();

    public void envoyerReclamation() {
        // Perform input validation
        String fullName = nom.getText().trim();
        String emailAddress = email.getText().trim();
        String messageText = message.getText().trim();

        if (fullName.isEmpty() || emailAddress.isEmpty() || messageText.isEmpty()) {
            idMsg.setText("Please fill in all fields.");
            return;
        }

        // Create a new Reclamation object
        Reclamation reclamation = new Reclamation();
        reclamation.setType("Type"); // You might want to specify the type here
        reclamation.setDescription(messageText);
        reclamation.setStatut("Open");
        reclamation.setIdUser(1); // Assuming user ID is 1 for now
        reclamation.setDateCreation(new Date());

        // Save the reclamation to the database
        int newReclamationId = 0;

        newReclamationId = reclamationCrud.createReclamation(reclamation);

        if (newReclamationId != -1) {
            idMsg.setText("Reclamation sent successfully with ID: " + newReclamationId);
            // Clear fields after successful submission
            nom.clear();
            email.clear();
            message.clear();
        } else {
            idMsg.setText("Failed to send reclamation.");
        }
    }

    public void annuler() {
        // Clear all fields on cancel
        nom.clear();
        email.clear();
        message.clear();
        idMsg.setText(""); // Clear any previous message
    }

    @FXML
    void initialize() {
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'Reclamation.fxml'.";
        assert idMsg != null : "fx:id=\"idMsg\" was not injected: check your FXML file 'Reclamation.fxml'.";
        assert message != null : "fx:id=\"message\" was not injected: check your FXML file 'Reclamation.fxml'.";
        assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'Reclamation.fxml'.";

    }
}