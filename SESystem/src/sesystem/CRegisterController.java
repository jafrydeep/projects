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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class CRegisterController implements Initializable {

    @FXML
    private TextField sSemister;
    @FXML
    private ComboBox<String> sCourse;
    @FXML
    private ComboBox<Number> sSid;
    private final String DB_USER = "root";
    private final String DB_DB = "db";
    private final String DB_PASS = "jafry";
    private final String DB_HOST = "127.0.0.1";
    private final String DB_URL = "jdbc:mysql://"+DB_HOST+"/"+DB_DB;
    
    private ObservableList <Number> sList;
    private ObservableList <String> cList;
    private ObservableList <Number> sSecs;
    private ObservableList <Cregister> registerList;
    @FXML
    private ComboBox<Number> sSec;
    String cCombo;
    int sCombo;
    int idCombo;
    @FXML
    private Text succst;
    @FXML
    private TableView<Cregister> rTable;
    @FXML
    private TableColumn<Cregister, Number> rId;
    @FXML
    private TableColumn<Cregister, String> rSem;
    @FXML
    private TableColumn<Cregister, String> rCourse;
    @FXML
    private TableColumn<Cregister, Number> rSec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        sList = FXCollections.observableArrayList(); 
        sSid.setItems(sList);
        
        cList = FXCollections.observableArrayList(); 
        sCourse.setItems(cList);
        
        sSecs = FXCollections.observableArrayList(); 
        sSec.setItems(sSecs);
        
        try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query = "SELECT * FROM student;";
            ResultSet  resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                
                int sId = resultSet.getInt("sId");
                sList.add(sId);
              }
            System.out.println("done");
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        
        
        try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query = "SELECT * FROM course;";
            ResultSet  resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                
                String sId = resultSet.getString("cCode");
                cList.add(sId);
              }
            System.out.println("done");
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        
        try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query = "SELECT * FROM course;";
            ResultSet  resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                
                int cSec = resultSet.getInt("cSec");
                sSecs.add(cSec);
              }
            System.out.println("done");
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    registerList = FXCollections.observableArrayList();   
    registerList.addAll(getRegisterList());     
    rTable.setItems(registerList);    
    
        rSem.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSemId()));  
        rCourse.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getcCode()));   
        rId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getsId()));
        rSec.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getcSec()));
        
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

    @FXML
    private void BackAction(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("course.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("SE System By JAFRY");
        stage.setScene(scene);
        stage.show();  
    }

    @FXML
    private void registerAction(ActionEvent event) {
        int sId = idCombo;
        String seId = sSemister.getText();
        int cSec = sCombo;
        String cCode = cCombo;
        
        Cregister c = new Cregister (seId , sId, cCode, cSec);
        insertData(c);
        succst.setText("Registered Successfully !");
        
        
    }

    @FXML
    private void sCcombo(ActionEvent event) {
        cCombo = sCourse.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void sScombo(ActionEvent event) {
        sCombo = (int) sSec.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void SidCombo(ActionEvent event) {
        idCombo = (int) sSid.getSelectionModel().getSelectedItem();
    }
    private void insertData(Cregister i){
        String sem = i.getSemId();
        int stdnt = i.getsId();
        String course = i.getcCode();
        int sec = i.getcSec();
 
        String query = "INSERT INTO register VALUES('" + sem + "'," + stdnt + ",'" + course + "'," + sec +");";
        try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        Statement s = connection.createStatement();
        s.executeUpdate(query);
        registerList.add(i);
 
        }catch(SQLException sqle){
        System.err.println(sqle);
         }
    }
    private List<Cregister> getRegisterList(){
        List<Cregister> allst = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            Statement s = connection.createStatement();
            String query = "SELECT * FROM register;";
            ResultSet r = s.executeQuery(query);
            
            while(r.next()){
                String sems = r.getString("semesterId");
                int stdnt = r.getInt("studentId");
                String cours = r.getString("courseCode");
                int secc = r.getInt("courseSec");
                
                Cregister d = new Cregister(sems, stdnt, cours, secc);
                registerList.add(d);
            }
        }catch(SQLException sqle){
            System.err.println(sqle);
        }
        return allst;     
    }
}
