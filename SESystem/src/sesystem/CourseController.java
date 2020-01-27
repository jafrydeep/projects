/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JAFRY
 */
public class CourseController implements Initializable {
    private final String DB_USER = "root";
    private final String DB_DB = "db";
    private final String DB_PASS = "jafry";
    private final String DB_HOST = "127.0.0.1";
    private final String DB_URL = "jdbc:mysql://"+DB_HOST+"/"+DB_DB;
    private ObservableList courseList;
    @FXML
    private TextField cCode;
    @FXML
    private TextField cName;
    @FXML
    private TextField cSec;
    @FXML
    private TextField cCredit;
    @FXML
    private TableView<course> courseView;
    @FXML
    private Text scAdd;
    @FXML
    private TableColumn<course, String> cCodd;
    @FXML
    private TableColumn<course, String> cTit;
    @FXML
    private TableColumn<course, Number> cSeec;
    @FXML
    private TableColumn<course, Number> cCrd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        courseList = FXCollections.observableArrayList();
        courseList.addAll(getCourseList());       
        courseView.setItems(courseList);
        cCodd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getcCode()));  
        cTit.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getcName()));   
        cSeec.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getcSec()));
        cCrd.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getcCredit()));


        
        // TODO
    }    

    @FXML
    private void homeAction(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("main_page.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("SE System By JAFRY");
        stage.setScene(scene);
        stage.show();  
    }
    
    private void insertCourse(course i){
        String cCodee = i.getcCode();
        String cNamee = i.getcName();
        int cSecc = i.getcSec();
        int cCreditt = i.getcCredit();
        
        String query = "INSERT INTO course VALUES('" +  cCodee + "','" + cNamee + "'," + cSecc + "," + cCreditt + ");";
        try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        Statement s = connection.createStatement();
        s.executeUpdate(query);
        courseList.add(i);
 
        }catch(SQLException sqle){
        System.err.println(sqle);
         }
    }
    
    private List<course> getCourseList(){
        List<course> allCourse = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            Statement s = connection.createStatement();
            String query = "SELECT * FROM course;";
            ResultSet r = s.executeQuery(query);
            
            while(r.next()){
                String cCodee = r.getString("cCode");
                String cNamee = r.getString("cName");
                int cSecc = r.getInt("cSec");
                int cCreditt = r.getInt("cCredit");
                
                course d = new course(cCodee, cNamee, cSecc, cCreditt);
                courseList.add(d);
            }
        }catch(SQLException sqle){
            System.err.println(sqle);
        }
        return allCourse;     
    }
    
    @FXML
    private void AddAction(ActionEvent event) {
        String cCodee = cCode.getText();
        String cNamee = cName.getText();
        int cSecc = Integer.parseInt(cSec.getText());
        int cCreditt = Integer.parseInt(cCredit.getText());
        
        course c = new course (cCodee, cNamee, cSecc, cCreditt);
        insertCourse(c);
        scAdd.setText("Successfully Added !!!");
    }

    @FXML
    private void resetAction(ActionEvent event) {
        cCode.clear();
        cName.clear();
        cSec.clear();
        cCredit.clear();
    }

    @FXML
    private void CRAction(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("CRegister.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("SE System By JAFRY");
        stage.setScene(scene);
        stage.show();  
    }
    
}
