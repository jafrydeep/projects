/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymgt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Text wrong;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void onLoginAction(ActionEvent event) throws IOException {
        if (userName.getText().equals("admin") && password.getText().equals("jafry")){
        ((Node)event.getSource()).getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("adminPanel.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setTitle("Admin Panel - Library Management System");
       // stage.setIconified(new icon("http://k.png"));
        stage.setScene(scene);
        stage.show();
        
        }
        else 
           wrong.setText("Wrong Username / Password !!");
        }
    
    
    
}
