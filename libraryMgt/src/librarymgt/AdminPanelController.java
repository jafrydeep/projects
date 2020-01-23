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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AdminPanelController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onExitAction(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setTitle("Login Panel - Library Management System");
       // stage.setIconified(new icon("http://k.png"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onBookAction(ActionEvent event) throws IOException {
          ((Node)event.getSource()).getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("addBooks.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setTitle("Admin Panel - Library Management System");
       // stage.setIconified(new icon("http://k.png"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onIssueAction(ActionEvent event) throws IOException {
         ((Node)event.getSource()).getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("issueBook.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setTitle("Admin Panel - Library Management System");
       // stage.setIconified(new icon("http://k.png"));
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    private void onReturnAction(ActionEvent event) throws IOException {
            ((Node)event.getSource()).getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("returnBook.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setTitle("Admin Panel - Library Management System");
       // stage.setIconified(new icon("http://k.png"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onStudentAction(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("studentRecords.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setTitle("Admin Panel - Library Management System");
       // stage.setIconified(new icon("http://k.png"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onSearchAction(ActionEvent event) throws IOException {
 ((Node)event.getSource()).getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("searchBooks.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setTitle("Admin Panel - Library Management System");
       // stage.setIconified(new icon("http://k.png"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onTransAction(ActionEvent event) {
    }
    
}
