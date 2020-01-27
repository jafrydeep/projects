/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ResultController implements Initializable {
    private final String DB_USER = "root";
    private final String DB_DB = "db";
    private final String DB_PASS = "jafry";
    private final String DB_HOST = "127.0.0.1";
    private final String DB_URL = "jdbc:mysql://"+DB_HOST+"/"+DB_DB;

    @FXML
    private TableView<Cregister> rTable;
    @FXML
    private TableColumn<Cregister, String> rSem;
    @FXML
    private TableColumn<Cregister, Number> rId;
    @FXML
    private TableColumn<Cregister, String> rCourse;
    @FXML
    private TableColumn<Cregister, Number> rSec;
    private ObservableList <Cregister> registerList;
    private ObservableList <String> List;
    private ObservableList <result> resultList;

    @FXML
    private ComboBox<String> gradeBox;
    @FXML
    private TextField s;
    @FXML
    private TextField sId;
    @FXML
    private TextField sCourse;
    @FXML
    private TextField sSection;
    String g;
    @FXML
    private Text addSuccess;
    @FXML
    private TableView<result> resultTable;
    @FXML
    private TableColumn<result, String> sm;
    @FXML
    private TableColumn<result, Number> stid;
    @FXML
    private TableColumn<result, String> cour;
    @FXML
    private TableColumn<result, Number> secw;
    @FXML
    private TableColumn<result, String> gradew;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        resultList = FXCollections.observableArrayList(); 
        resultList.addAll(getResultList());
        resultTable.setItems(resultList);
        
        sm.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSemester()));  
        cour.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCourse()));   
        stid.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getStId()));
        secw.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getSec()));
        gradew.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGrade()));
        List = FXCollections.observableArrayList(); 
        gradeBox.setItems(List);
        
        
        try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query = "SELECT * FROM grade;";
            ResultSet  resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                
                String dName = resultSet.getString("grade");
                List.add(dName);
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
        stage.setTitle("SE System - HomePage");
        stage.setScene(scene);
        stage.show();
    }
    private List<Cregister> getRegisterList(){
        List<Cregister> allst = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            Statement ds = connection.createStatement();
            String query = "SELECT * FROM register;";
            ResultSet r = ds.executeQuery(query);
            
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

    @FXML
    private void gradeBoxAction(ActionEvent event) {
        g =  gradeBox.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void publishAction(ActionEvent event) {
        
        String sem = s.getText();
        int stId = Integer.parseInt(sId.getText());
        String sC = sCourse.getText();
        int ssecc = Integer.parseInt(sSection.getText());
        String cc = g;
        
        result c = new result (sem,stId,sC,ssecc,cc);
        insertResult(c);
        addSuccess.setText("Published !!");
        reset();
    }
       
    private void insertResult(result i){
        String sems = i.getSemester();
        int stIds = i.getStId();
        String sCs = i.getCourse();
        int sseccs = i.getSec();
        String ccs = i.getGrade();
        
        String query = "INSERT INTO result VALUES('" + sems + "'," + stIds + ",'" + sCs + "'," + sseccs + 
                ",'" + ccs + "');";
        try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        Statement sr = connection.createStatement();
        sr.executeUpdate(query);
        resultList.add(i);
 
        }catch(SQLException sqle){
        System.err.println(sqle);
         }
    }
 private List<result> getResultList(){
        List<result> allst = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            Statement sw = connection.createStatement();
            String query = "SELECT * FROM result;";
            ResultSet r = sw.executeQuery(query);
            
            while(r.next()){
                String sems = r.getString("semester");
                int st = r.getInt("studentId");
                String courss = r.getString("course");               
                int sect = r.getInt("section");
                String grades = r.getString("grade");
                
                result d = new result(sems,st,courss,sect,grades);
                resultList.add(d);
            }
        }catch(SQLException sqle){
            System.err.println(sqle);
        }
        return allst;     
    }

    @FXML
    private void resetAction(ActionEvent event) {
        s.setText(" ");
        sId.setText(" ");
        sCourse.setText(" ");
        sSection.setText(" ");
    }

    @FXML
    private void tClickAction(MouseEvent event) {
      
     try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query = "SELECT * FROM register WHERE studentId=" + rTable.getSelectionModel().getSelectedItem().getsId() +";";
            ResultSet  r = statement.executeQuery(query);
            
            while(r.next()){
                String sss = r.getString("semesterId");
                String sIdss = Integer.toString(r.getInt("studentId"));
                String sCoursess = r.getString("courseCode");
                String sSectionss = Integer.toString(r.getInt("courseSec"));
              
                s.setText(sss);
                sId.setText(sIdss);
                sCourse.setText(sCoursess);
                sSection.setText(sSectionss);
              }
            System.out.println("done");
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        
        //reset();
      
    }
    void reset(){
        s.setText(" ");
        sId.setText(" ");
        sCourse.setText(" ");
        sSection.setText(" ");
    }
}