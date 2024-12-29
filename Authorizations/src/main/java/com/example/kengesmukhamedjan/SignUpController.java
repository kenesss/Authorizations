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

public class SignUpController implements Initializable{
//    @FXML
//    private TextField text_example;
//    @FXML
//    private AnchorPane parentPane;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
//    @FXML
//   //private RadioButton male;
//    @FXML
//    //private ToggleGroup genders;
//    @FXML
//    private RadioButton female;
//    @FXML
//    private RadioButton other;
    @FXML
    private TextField email;
    @FXML
    private Button signup;
    @FXML
    private Button login;
    @FXML
    private ImageView progress;

    File file = new File("C:\\Users\\kenes\\IdeaProjects\\Kenges Mukhamedjan\\src\\pas");
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    String Username, Password, Email;
    int ln;
    boolean check = true;
    @FXML
    public void signUp(ActionEvent event){
        CountLines();
        //CheckData(username.getText(), password.getText());
        logic(username.getText(), password.getText());
        if(username.getText() == "" & password.getText() == ""){
            alert.setContentText("The username/password emty");
            alert.showAndWait();
        }else if(check){
        createFolder();
        readFile();
        addData(username.getText(), password.getText(), email.getText());
        }
    }
    @FXML
    public void loginAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(LoginController.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) login.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    void createFolder(){
        if(!file.exists()){
            file.mkdirs();
        }
    }
    void readFile(){
        try {
            FileReader fileReader = new FileReader(file + "\\logins.txt");
            System.out.println("file exits");
        } catch (FileNotFoundException ex) {
            try {
                FileWriter fileWriter = new FileWriter(file + "\\logins.txt");
                System.out.println("File created");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    void addData(String usr,String psvd, String mail){
        try {
            RandomAccessFile raf = new RandomAccessFile(file + "\\logins.txt", "rw");
            for (int i = 0; i < ln; i++) {
                raf.readLine();
            }
            raf.writeBytes("\r\n");
            raf.writeBytes("\r\n");
            raf.writeBytes("Username:" + usr + "\r\n");
            raf.writeBytes("Password:" +  psvd + "\r\n");
            raf.writeBytes("Email:" + mail);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        username.setText("");
        password.setText("");
        email.setText("");
    }
    void CheckData(String usr,String pswd){
        try {
            RandomAccessFile raf = new RandomAccessFile(file + "\\logins.txt", "rw");
            String line = raf.readLine();
            Username = line.substring(9);
            Password = raf.readLine().substring(9);
            Email = raf.readLine().substring(6);
            if(usr.equals(Username)){
                alert.setContentText("Sorry, but there is such a user");
                alert.showAndWait();
                check = false;
            } else if (usr.equals(pswd)) {
                alert.setContentText("Username equals to password");
                alert.showAndWait();
                check = false;
            }
            else if (usr.length() >= 9 || pswd.length() >= 9) {
                alert.setContentText("Limit username, password and email to 9 characters");
                alert.showAndWait();
                check = false;
            }
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
                    check = false;
                    break;
                }
                else if(i==(ln-3)){
                    check = false;
                    break;
                }
                else if (usr.length() >= 9 || pswd.length() >= 9) {
                alert.setContentText("Limit username, password and email to 9 characters");
                alert.showAndWait();
                check = false;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
