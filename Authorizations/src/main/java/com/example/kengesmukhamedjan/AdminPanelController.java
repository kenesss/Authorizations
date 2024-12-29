package com.example.kengesmukhamedjan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPanelController  implements Initializable {
    @FXML
    private Button exitButton;
    @FXML
    private Button UpdetButton;
    int ln;

    //@FXML
    @FXML
    private TableColumn<?, ?> Email;

    @FXML
    private TableColumn<?, ?> Password;

    @FXML
    private TableColumn<AdminPanelController, String> Username;

    @FXML
    private TableView<?> table;

    File file = new File("C:\\Users\\kenes\\IdeaProjects\\Kenges Mukhamedjan\\src\\pas");
    @FXML
    void Update(ActionEvent event) throws FileNotFoundException {
        CountLines();
        try {
            RandomAccessFile raf = new RandomAccessFile(file + "\\logins.txt", "rw");
            for (int i = 0; i < ln; i++) {
                String forUser = raf.readLine().substring(9);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void CountLines(){
        try{
            ln = 1;
            RandomAccessFile raf = new RandomAccessFile(file + "\\logins.txt", "rw");
            for (int i = 0; raf.readLine() != null; i++) {
                ln++;
            }
            System.out.println("number of lines:" + ln);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
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
