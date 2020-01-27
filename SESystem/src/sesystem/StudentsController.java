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
 * @author Lenovo
 */
public class StudentsController implements Initializable {
    private final String DB_USER = "root";
    private final String DB_DB = "db";
    private final String DB_PASS = "jafry";
    private final String DB_HOST = "127.0.0.1";
    private final String DB_URL = "jdbc:mysql://"+DB_HOST+"/"+DB_DB;
    String dCat;
    @FXML
    private Text addSuccess;
    @FXML
    private TextField sId;
    @FXML
    private TextField sName;
    @FXML
    private TextField sMail;
    @FXML
    private TextField sAdd;
    @FXML
    private TextField sPhn;
    @FXML
    private ComboBox<String> sDept;
    private ObservableList <String> deptList;
    private ObservableList <String> deptList2;
    private ObservableList <student> studentList;
   // private javax.swing.JComboBox<String> sDept;

    @FXML
    private Text errSt;
    @FXML
    private Text successSt;
    //private ObservableList<dept> list;
    @FXML
    private DatePicker dPicker;
    @FXML
    private TableView<student> sTable;
    @FXML
    private TableColumn<student, Number> id;
    @FXML
    private TableColumn<student, String> name;
    @FXML
    private TableColumn<student, String> mail;
    @FXML
    private TableColumn<student, String> birth;
    @FXML
    private TableColumn<student, String> add;
    @FXML
    private TableColumn<student, Number> cc;
    @FXML
    private TableColumn<student, String> dd;
    @FXML
    private ListView<String> selectStdnt;
    private ObservableList <String> sList;
    @FXML
    private ComboBox<String> ldept;
    @FXML
    private TextField lid;
    @FXML
    private TextField lname;
    @FXML
    private TextField lmail;
    @FXML
    private TextField ladd;
    @FXML
    private TextField lphn;
    @FXML
    private DatePicker lpicker;
    String dCat2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // 
        
        dPicker.setValue(LocalDate.now());
        
        deptList = FXCollections.observableArrayList(); 
        sDept.setItems(deptList);
        
        
        try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query = "SELECT * FROM dept;";
            ResultSet  resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                
                String dName = resultSet.getString("dName");
                deptList.add(dName);
              }
            System.out.println("done");
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    deptList2 = FXCollections.observableArrayList(); 
    ldept.setItems(deptList2);    
     
        try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query = "SELECT * FROM dept;";
            ResultSet  resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                
                String dName = resultSet.getString("dName");
                deptList2.add(dName);
              }
            System.out.println("done");
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    sList = FXCollections.observableArrayList(); 
    selectStdnt.setItems(sList);    
    try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query = "SELECT * FROM student;";
            ResultSet  resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                
                String sIdd = resultSet.getString("sName");
                sList.add(sIdd);
              }
            System.out.println("done");
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }    
    studentList = FXCollections.observableArrayList();   
    studentList.addAll(getStudentList());     
    sTable.setItems(studentList);    
    
        mail.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMail()));  
        name.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));   
        id.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()));
        cc.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPhn()));
        add.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLoc()));  
        dd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDept()));   
        birth.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDob().toString()));
        
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
    private void deptAction(ActionEvent event) {
        dCat = sDept.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void addAction(ActionEvent event) {
        int ID = Integer.parseInt(sId.getText());
        String NAME = sName.getText();
        String MAIL = sMail.getText();
        String LOC = sAdd.getText();
        String DEPT = dCat;
        LocalDate DOB = dPicker.getValue();
        int PHN = Integer.parseInt(sPhn.getText());
        
        student c = new student (ID, NAME, MAIL, DOB, LOC, PHN, DEPT);
        insertStudent(c);
        addSuccess.setText("Registered Successfully !!");
        reset();
    }
    
    void reset (){
        sId.clear();
        sName.clear();
        sMail.clear();
        sPhn.clear();
        sAdd.clear();
        dPicker.setValue(LocalDate.now());
     /*   lid.clear();
        lname.clear();
        lmail.clear();
        lphn.clear();
        ladd.clear();
        lpicker.setValue(LocalDate.now());
    */}

    @FXML
    private void resetAction(ActionEvent event) {
        sId.clear();
        sName.clear();
        sMail.clear();
        sPhn.clear();
        sAdd.clear();
        dPicker.setValue(LocalDate.now());
        
    }
    private void insertStudent(student i){
        int Id = i.getId();
        String Name = i.getName();
        String Mail = i.getMail();
        LocalDate Dob = i.getDob();
        String Loc = i.getLoc();
        int Phn = i.getPhn();
        String Dept = i.getDept();
        
        String query = "INSERT INTO student VALUES(" + Id + ",'" + Name + "','" + Mail + "','" + Dob + 
                "','" + Loc + "'," + Phn + ",'" + Dept + "');";
        try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        Statement s = connection.createStatement();
        s.executeUpdate(query);
        studentList.add(i);
 
        }catch(SQLException sqle){
        System.err.println(sqle);
         }
    }
    
    private List<student> getStudentList(){
        List<student> allst = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            Statement s = connection.createStatement();
            String query = "SELECT * FROM student;";
            ResultSet r = s.executeQuery(query);
            
            while(r.next()){
                String names = r.getString("sName");
                String mails = r.getString("sMail");
                String adds = r.getString("sLoc");
                String dept = r.getString("sDept");
                int ids = r.getInt("sId");
                int phn = r.getInt("sPhn");
                Date date = r.getDate("sDob");
                LocalDate dates = LocalDate.of(date.getYear()+1900, 
                        Month.values()[(date.getMonth())],
                        date.getDate());
                
                student d = new student(ids,names,mails, dates,adds,phn,dept);
                studentList.add(d);
            }
        }catch(SQLException sqle){
            System.err.println(sqle);
        }
        return allst;     
    }

    @FXML
    private void updateInsert(MouseEvent event) {
       try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query = "SELECT * FROM student WHERE sName='" + selectStdnt.getSelectionModel().getSelectedItem() +"';";
            ResultSet  r = statement.executeQuery(query);
            
            while(r.next()){
                
                String names = r.getString("sName");
                String mails = r.getString("sMail");
                String adds = r.getString("sLoc");
                String dept = r.getString("sDept");
                String ids = Integer.toString(r.getInt("sId"));
                String phn = Integer.toString(r.getInt("sPhn"));
                Date date = r.getDate("sDob");
                //DateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
                //String d = simple.format(date);
                LocalDate dates = LocalDate.of(date.getYear()+1900, 
                        Month.values()[(date.getMonth())],
                        date.getDate());
                
                lid.setText(ids);
                lname.setText(names);
                lmail.setText(mails);
                lpicker.setValue(dates);
                ladd.setText(adds);
                lphn.setText(phn);
               // ldept.getSelectionModel().getSelectedItem(dept);
              }
            System.out.println("done");
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        
        reset();
       
    
    }

    @FXML
    private void updateAction(ActionEvent event) {
        //reset();
    }

    @FXML
    private void dept2Action(ActionEvent event) {
        dCat2 = ldept.getSelectionModel().getSelectedItem();
    }

    
}