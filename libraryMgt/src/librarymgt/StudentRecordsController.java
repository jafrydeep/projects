/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymgt;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author User
 */
public class StudentRecordsController implements Initializable {
    String dcat,dcat2;
    @FXML
    private Text addSuccess;

    private final String DB_USER = "root";
    private final String DB_DB = "libraryMgt";
    private final String DB_PASS = "102030";
    private final String DB_HOST = "127.0.0.1";
    private final String DB_URL = "jdbc:mysql://"+DB_HOST+"/"+DB_DB;

    @FXML
    private TextField sid;
    @FXML
    private TextField sname;
    @FXML
    private TextField sadd;
    @FXML
    private ComboBox<String> sdept;
    @FXML
    private ComboBox<String> sbatch;
    private ObservableList <String> deptList;
    private ObservableList <String> batchList;
    private ObservableList <students> studentList;
    private ObservableList <String> sList;
    @FXML
    private TableView<students> sTable;
    @FXML
    private TableColumn<students, Number> sid2;
    @FXML
    private TableColumn<students, String> sname2;
    @FXML
    private TableColumn<students, String> sadd2;
    @FXML
    private TableColumn<students, String> sdept2;
    @FXML
    private TableColumn<students, String> sbatch2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        deptList = FXCollections.observableArrayList(); 
        sdept.setItems(deptList);
        sid2.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()));  
        sname2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));   
        sadd2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLoc()));
        sdept2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDept()));
        sbatch2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBatch()));  
        
        
        
        try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query = "SELECT * FROM dept;";
            ResultSet  resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                
                String dName = resultSet.getString("Dname");
                deptList.add(dName);
              }
            System.out.println("done");
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    batchList = FXCollections.observableArrayList(); 
    sbatch.setItems(batchList);    
     
        try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query = "SELECT * FROM batch;";
            ResultSet  resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                
                String dName = resultSet.getString("Bid");
                batchList.add(dName);
              }
            System.out.println("done");
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        
    sList = FXCollections.observableArrayList(); 
//    selectStdnt.setItems(sList);    
    try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query = "SELECT * FROM students;";
            ResultSet  resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                
                String sIdd = resultSet.getString("StudentName");
                sList.add(sIdd);
              }
            System.out.println("done");
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }    
    studentList = FXCollections.observableArrayList();   
    studentList.addAll(getStudentList());     
    sTable.setItems(studentList);   
        // TODO
    }    

    @FXML
    private void onLogoutAction(ActionEvent event) throws IOException {
       ((Node)event.getSource()).getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setTitle("Admin Panel - Library Management System");
       // stage.setIconified(new icon("http://k.png"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onRegisterAction(ActionEvent event) {
        int ID = Integer.parseInt(sid.getText());
        String NAME = sname.getText();
        String LOC = sadd.getText();
        String DEPT = dcat;
        String BATCH = dcat2;
        
        students c = new students (ID, NAME, LOC,DEPT, BATCH);
        insertStudent(c);
        addSuccess.setText("Registered SuccessFull :D");
        reset();
    }
    void reset (){
        sid.clear();
        sname.clear();
        sadd.clear();
    }
    @FXML
    private void onResetAction(ActionEvent event) {
         sid.clear();
        sname.clear();
        sadd.clear();
    }
    private void insertStudent(students i){
        int Id = i.getId();
        String Name = i.getName();
        String Add = i.getLoc();
        String Dept = i.getDept();
        String Batch = i.getBatch();
       
        
        String query = "INSERT INTO students VALUES(" + Id + ",'" + Name + "','" + Add + "','" + Dept + 
                "','" + Batch + "');";
        try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        Statement s = connection.createStatement();
        s.executeUpdate(query);
        studentList.add(i);
 
        }catch(SQLException sqle){
        System.err.println(sqle);
         }
    }
    private List<students> getStudentList(){
        List<students> allst = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            Statement s = connection.createStatement();
            String query = "SELECT * FROM students;";
            ResultSet r = s.executeQuery(query);
            
            while(r.next()){
                int id = r.getInt("StudentID");
                String name = r.getString("StudentName");
                String adds = r.getString("Address");
                String dept = r.getString("Department");
                String batch = r.getString("Batch");
                
                
                students d = new students(id,name,adds, dept,batch);
                studentList.add(d);
            }
        }catch(SQLException sqle){
            System.err.println(sqle);
        }
        return allst;     
    }

    @FXML
    private void onDeptAction(ActionEvent event) {
        dcat = sdept.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void onBatchAction(ActionEvent event) {
        dcat2 = sbatch.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void onHomeAction(ActionEvent event) throws IOException {
       ((Node)event.getSource()).getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("adminPanel.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setTitle("Admin Panel - Library Management System");
       // stage.setIconified(new icon("http://k.png"));
        stage.setScene(scene);
        stage.show();
    }
}
