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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
 * @author User
 */
public class IssueBookController implements Initializable {
    private final String DB_USER = "root";
    private final String DB_DB = "libraryMgt";
    private final String DB_PASS = "102030";
    private final String DB_HOST = "127.0.0.1";
    private final String DB_URL = "jdbc:mysql://"+DB_HOST+"/"+DB_DB;
    @FXML
    private TableView<students> sTable;
    private ObservableList <String> sList;
    @FXML
    private TextField search;
    @FXML
    private Text s1;
    @FXML
    private Text s2;
    @FXML
    private Text s3;
    @FXML
    private Text s4;
    @FXML
    private Text s5;
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
    
    private ObservableList <students> studentList;
    private ObservableList <transaction> transactionList;
    @FXML
    private TextField search7;
    @FXML
    private Text b1;
    @FXML
    private Text b2;
    @FXML
    private Text b3;
    @FXML
    private Text b4;
    @FXML
    private Text bnf;
    @FXML
    private Text scstrans;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
     setMouseClicked();        
    studentList = FXCollections.observableArrayList();   
    studentList.addAll(getStudentList());     
    sTable.setItems(studentList); 
    
    sid2.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()));  
    sname2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));   
    sadd2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLoc()));
    sdept2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDept()));
    sbatch2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBatch()));    
//   try{
//        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//            Statement statement = connection.createStatement();
//            
//            String query = "SELECT * FROM students;";
//            ResultSet  resultSet = statement.executeQuery(query);
//            
//            while(resultSet.next()){
//                
//                String sIdd = resultSet.getString("StudentName");
//                sList.add(sIdd);
//              }
//            System.out.println("done");
//        } catch (SQLException sqle) {
//            System.out.println(sqle);
//        }    

 
    // TODO
    
        
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
    private void onLogoutAction(ActionEvent event) throws IOException {
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

    @FXML
    private void searchType(KeyEvent event) {
            FilteredList<students> filterData = new FilteredList<>(studentList, p -> true);
        search.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(pers -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (pers.getName().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (pers.getDept().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (pers.getLoc().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }
                if (pers.getBatch().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }
//                if (pers.getId().toLowerCase.indexOf(typedText) != -1) {
//                    return true;
//                }

                return false;
            });
            SortedList<students> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(sTable.comparatorProperty());
            sTable.setItems(sortedList);
                       
            
        });

}

  
    @FXML
    private void mouseClick(MouseEvent event) {
        
    }
    
    private void setMouseClicked(){
        sTable.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle (MouseEvent event) {
            students p1 = sTable.getItems().get(sTable.getSelectionModel().getSelectedIndex());
            int i = p1.getId();
            String s = String.valueOf(i); 
            s1.setText(s);
            s2.setText(p1.getName());
            s3.setText(p1.getLoc());
            s4.setText(p1.getDept());
            s5.setText(p1.getBatch());
       }
        });
        
    }

    @FXML
    private void issueBookClick(KeyEvent event) {
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            Statement s = connection.createStatement();
            String query = "SELECT * FROM bookList where bookId='"+search7.getText()+"';";
            ResultSet r = s.executeQuery(query);
            
            if(r.next()){
                bnf.setText("");
                String id = r.getString("BookId");
                b1.setText(id);
                String name = r.getString("BookName");
                b2.setText(name);
                String adds = r.getString("Catagory");
                b3.setText(adds);
                int dept = r.getInt("Copies");
                String s9 = String.valueOf(dept);
                b4.setText(s9);
            }
            else{
                bnf.setText("Book Not Found !");
                b1.setText("");
                b2.setText("");
                b3.setText("");
                b4.setText("");
                
            }
        }catch(SQLException sqle){
            System.err.println(sqle);
        }
    }

    @FXML
    private void onSearchAction(ActionEvent event) {
//        try{
//            Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
//            Statement s = connection.createStatement();
//            String query = "SELECT * FROM bookList where bookId='"+search7.getText()+"';";
//            ResultSet r = s.executeQuery(query);
//            
//            if(r.next()){
//                String id = r.getString("BookId");
//                b1.setText(id);
//                String name = r.getString("BookName");
//                b2.setText(name);
//                String adds = r.getString("Catagory");
//                b3.setText(adds);
//                int dept = r.getInt("Copies");
//                String s9 = String.valueOf(dept);
//                b4.setText(s9);
//            }
//            else{
//                bnf.setText("Book Not Found !");
//                b1.setText("");
//                b2.setText("");
//                b3.setText("");
//                b4.setText("");
//                
//            }
//        }catch(SQLException sqle){
//            System.err.println(sqle);
//        }
    }

    @FXML
    private void onBorrowAction(ActionEvent event) {
       
        int ssId = Integer.parseInt(s1.getText());
        String ssname = s2.getText();
        String ssdept = s3.getText();
        String bbid = b1.getText();
        String bbname = b2.getText();
        String bbcat = b3.getText();
        int bbcp = Integer.parseInt(b4.getText());
        String type = "Borrowed";
        LocalDate locald = LocalDate.now();
       Date date = Date.valueOf(locald); 
        
        transaction c = new transaction (ssId, ssname, ssdept, bbid,bbname,bbcat,type,date);
        insertTrans(c);
        scstrans.setText("Successfully Added !");
        reset();
    }
    
     private void insertTrans(transaction i){
//        if (s1.getText()!= " " && search7.getText()!= " "){
        int Id3 = i.getSid();
        String Name3 = i.getSname();
        String dept3 = i.getSdept();
        String bid3 = i.getBid();
        String bname3 = i.getBname();
        String bcat3 = i.getBcat();
        String type3 = "Borrowed";
        Date date3 = i.getDate();
        
       
        
        String query = "INSERT INTO issueBook VALUES(" + Id3 + ",'" + Name3 + "','" + dept3 + "','" + bid3 + 
                "','" + bname3 +"','"+ bcat3+ "','"+type3+ "','"+ date3 +"');";
        String queryy = "UPDATE bookList SET Copies=Copies-1 where BookId='" + bid3 + "';";
        try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        Statement s = connection.createStatement();
        s.executeUpdate(query);
        s.executeUpdate(queryy);
        transactionList.add(i);
 
        }catch(SQLException sqle){
        System.err.println(sqle);
         }
//        } 
//        else {
//            reset();
//        }
        
    }
     
     public void reset(){
         s1.setText("");
         s2.setText("");
         s3.setText("");
         s4.setText("");
         
         b1.setText("");
         b2.setText("");
         b3.setText("");
         b4.setText("");
     }
}