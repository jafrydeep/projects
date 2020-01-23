/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymgt;

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
 * @author User
 */
public class AddBooksController implements Initializable {

    private final String DB_USER = "root";
    private final String DB_DB = "libraryMgt";
    private final String DB_PASS = "102030";
    private final String DB_HOST = "127.0.0.1";
    private final String DB_URL = "jdbc:mysql://"+DB_HOST+"/"+DB_DB;
    @FXML
    private TextField bid;
    @FXML
    private TextField bname;
    @FXML
    private ComboBox<String> bcat;
    @FXML
    private TextField bcp;
    @FXML
    private TableView<books> bTable;
    @FXML
    private TableColumn<books, String> bid2;
    @FXML
    private TableColumn<books, String> bname2;
    @FXML
    private TableColumn<books, String> bcat2;
    @FXML
    private TableColumn<books, Number> bcp2;
    private ObservableList <String> deptList;
    private ObservableList <books> booksList;

    @FXML
    private Text addSuccess;
    String dcat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        deptList = FXCollections.observableArrayList(); 
        bcat.setItems(deptList);
        booksList = FXCollections.observableArrayList();   
        booksList.addAll(getBooksList());     
        bTable.setItems(booksList);  
        bid2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBid()));  
        bname2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getbName()));   
        bcat2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getbCat()));
        bcp2.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getbCopy()));
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
    private void onAddBook(ActionEvent event) {
        String id = bid.getText();
        String name = bname.getText();
       
        String catagory = dcat;
        int copy =Integer.parseInt(bcp.getText());
        
        books c = new books (id,name,catagory,copy);
       insertBooks(c);
        addSuccess.setText("Successfully Added ! !");
        reset();
    }
    void reset (){
        bid.clear();
        bname.clear();
        bcp.clear();
    }

    @FXML
    private void onCatagory(ActionEvent event) {
         dcat = bcat.getSelectionModel().getSelectedItem();

    }
    
    
    private void insertBooks(books i){
        String id = i.getBid();
        String Name = i.getbName();
        String catagory = i.getbCat();
        int copy = i.getbCopy();
       
        
        String query = "INSERT INTO bookList VALUES('" + id + "','" + Name + "','" + catagory + "'," + copy +");";
        try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        Statement s = connection.createStatement();
        s.executeUpdate(query);
        booksList.add(i);
 
        }catch(SQLException sqle){
        System.err.println(sqle);
         }
    }
    private List<books> getBooksList(){
        List<books> allst = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            Statement s = connection.createStatement();
            String query = "SELECT * FROM bookList;";
            ResultSet r = s.executeQuery(query);
            
            while(r.next()){
                String id = r.getString("BookId");
                String name = r.getString("BookName");
                String adds = r.getString("Catagory");
                int dept = r.getInt("Copies");
                
                books d = new books(id,name,adds, dept);
                booksList.add(d);
            }
        }catch(SQLException sqle){
            System.err.println(sqle);
        }
        return allst;     
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
