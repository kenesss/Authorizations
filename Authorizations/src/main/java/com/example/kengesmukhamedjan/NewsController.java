package com.example.kengesmukhamedjan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewsController implements Initializable {
    @FXML
    private Button exitButton;
    @FXML
    public void exit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(LoginController.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
