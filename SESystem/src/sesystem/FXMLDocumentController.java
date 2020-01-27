/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesystem;

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
 * @author JAFRY
 */
public class FXMLDocumentController implements Initializable {



    @FXML
    private Label label;
    @FXML
    private TextField loginName;
    @FXML
    private PasswordField loginPass;
    @FXML
    private Text invalidLogin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleLoginAction(ActionEvent event) throws IOException {
        if (loginName.getText().equals("jafry") && loginPass.getText().equals("jafry")){
        ((Node)event.getSource()).getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("main_page.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setTitle("Admin Panel - Course Registration");
       // stage.setIconified(new icon("http://k.png"));
        stage.setScene(scene);
        stage.show();
        
        }
        else 
           invalidLogin.setText("Invalid User Details!");
    }

    
}
