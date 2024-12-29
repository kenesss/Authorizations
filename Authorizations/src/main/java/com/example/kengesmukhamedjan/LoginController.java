package com.example.kengesmukhamedjan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button signup;
    @FXML
    private Button login;
    @FXML
    private CheckBox remember;
    @FXML
    private ImageView progress;
    //private Label lbl_username;
    //private Label lbl_password;

    String Username, Password,Email;
    boolean check = false;
    int ln;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private Button forgetPswd;
    boolean Forget = true;

    @FXML
    public void loginAction(ActionEvent event) throws IOException {
        CountLines();
        //CheckData(username.getText(), password.getText());
        logic(username.getText(),password.getText());
        if(username.getText() == "" & password.getText() == ""){
            alert.setContentText("The username/password emty");
            alert.showAndWait();
        }else if(check){
            Parent root = FXMLLoader.load(NewsController.class.getResource("newspage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) login.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }else if(username.getText().equals("admin") & password.getText().equals("admin")){
            Parent root = FXMLLoader.load(AdminPanelController.class.getResource("adminpage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) login.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }else {
            alert.setContentText("The username/password incorrect");
            alert.showAndWait();
            username.setText("");
            password.setText("");
        }
    }
    @FXML
    public void signUpAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(SignUpController.class.getResource("SignUpPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) signup.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    File file = new File("C:\\Users\\kenes\\IdeaProjects\\Kenges Mukhamedjan\\src\\pas");
    void CheckData(String usr,String pswd){
        try {
            RandomAccessFile raf = new RandomAccessFile(file + "\\logins.txt", "rw");
            String line = raf.readLine();
            Username = line.substring(9);
            Password = raf.readLine().substring(9);
            Email = raf.readLine().substring(6);
            if(usr.equals(Username) & pswd.equals(Password)){
                check = true;
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
    void logic(String usr,String pswd){
        try {
            RandomAccessFile raf = new RandomAccessFile(file + "\\logins.txt", "rw");
            for (int i = 0; i < ln; i+=4) {
                String forUser = raf.readLine().substring(9);
                String forPswd = raf.readLine().substring(9);
                if(usr.equals(forUser) & pswd.equals(forPswd)){
                    check = true;
                    break;
                }
                else if(i==(ln-3)){
                    check = true;
                    break;
                }
                for (int j = 1; j <= 2; j++) {
                    raf.readLine();
                }
            }
        }catch (FileNotFoundException ex){
            throw new RuntimeException(ex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void forgetPassword(ActionEvent event) throws IOException{
        CountLines();
        try {
            RandomAccessFile raf = new RandomAccessFile(file + "\\logins.txt", "rw");
            for (int i = 0; i < ln; i+=4) {
                String forUser = raf.readLine().substring(9);
                String forPswd = raf.readLine().substring(9);
                if(username.getText().equals(forUser)){
                    alert.setContentText("You password " + "->" + forPswd + "<-");
                    alert.showAndWait();
                    Forget = false;
                    break;
                }
                else if(i==(ln-3)){
                    break;
                }
                for (int j = 1; j <= 2; j++) {
                    raf.readLine();
                }
            }
            if(password.getText() == ""){
                alert.setContentText("Plese enter the username");
                alert.showAndWait();
            }
            else if(Forget){
                alert.setContentText("You incorrect username");
                alert.showAndWait();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}