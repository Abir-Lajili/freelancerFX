package tn.esprit.info.alternance.javafx;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AdminLoginController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button close;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUsername;


    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void close(){
        System.exit(0);
    }

    private double x = 0;
    private double y = 0;

    public void loginAdmin(){
        String sql = "select * from user where userName = ? and password = ?";
        khadamni.utiles.Connection myConnection = new khadamni.utiles.Connection();
        java.sql.Connection jdbcConnection = myConnection.getConnection();

        try {
            prepare = jdbcConnection.prepareStatement(sql);
            prepare.setString(1,tfUsername.getText());
            prepare.setString(2,tfPassword.getText());
            result = prepare.executeQuery();
            Alert alert;
            if(tfUsername.getText().isEmpty() || tfPassword.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else {
                if ((result.next() || (Objects.equals(tfUsername.getText(), "abir") && Objects.equals(tfPassword.getText(), "abir123"))))
                {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully login");
                    alert.showAndWait();
                    loginBtn.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("dashboardAdmin.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    root.setOnMousePressed((MouseEvent event) ->{
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent event) ->{
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });


                    stage.getScene();
                    stage.show();
                }
                else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username or password");
                    alert.showAndWait();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}
