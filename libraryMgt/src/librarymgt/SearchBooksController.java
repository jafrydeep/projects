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
import java.util.function.Predicate;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SearchBooksController implements Initializable {
    private final String DB_USER = "root";
    private final String DB_DB = "libraryMgt";
    private final String DB_PASS = "102030";
    private final String DB_HOST = "127.0.0.1";
    private final String DB_URL = "jdbc:mysql://"+DB_HOST+"/"+DB_DB;
    @FXML
    private TextField searchId;
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

    private ObservableList <books> booksList;
    private ObservableList <books> booksList2;
    String search;
    
    /**
     * Initializes the controller class.
     */
//    FilteredList<books> filter =new FilteredList (booksList2, e -> true );
      

    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        
        booksList2 = FXCollections.observableArrayList();
        booksList2.addAll(getBooksList());     
        bTable.setItems(booksList2);
//        booksList = FXCollections.observableArrayList();
//        booksList.addAll(getBooksList());     
//        bTable.setItems(booksList);
        // TODO
        bid2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBid()));  
        bname2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getbName()));   
        bcat2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getbCat()));
        bcp2.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getbCopy()));
        
//        try{
//            Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
//            Statement s = connection.createStatement();
//            String query = "SELECT * FROM bookList;";
//            ResultSet r = s.executeQuery(query);
//            
//            while(r.next()){
//                String id = r.getString("BookId");
//                String name = r.getString("BookName");
//                String adds = r.getString("Catagory");
//                int dept = r.getInt("Copies");
//                
//                books d = new books(id,name,adds, dept);
//                booksList2.add(d);
//            }
//        }catch(SQLException sqle){
//            System.err.println(sqle);
//        }
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
                booksList2.add(d);
            }
        }catch(SQLException sqle){
            System.err.println(sqle);
        }
        return allst;     
    }

//    private void onSearchAction(ActionEvent event) {
//        search = searchId.getText();
//         try{
//            Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
//            Statement s = connection.createStatement();
//            String query = "SELECT * FROM bookList where BookId='" + search + "';";
//            ResultSet r = s.executeQuery(query);
//            
//            while(r.next()){
//                String id = r.getString("BookId");
//                String name = r.getString("BookName");
//                String adds = r.getString("Catagory");
//                int dept = r.getInt("Copies");
//                
//                books d = new books(id,name,adds, dept);
//                booksList.add(d);
//            }
//        }catch(SQLException sqle){
//            System.err.println(sqle);
//        }
//    }

//    private void onSearchAction(KeyEvent event) {
//        
//        search = searchId.getText();
////        onSearchAction.textProperty().addListener((observable,oldValue,newValue)-> {
//        filter.setPredicate((Predicate <? super Std>)(Std std) ->{
//            if(newValue.isEmpty() || newValue == null){
//                return true;
//            }
//            else if (std.getId().contains(newValue)){
//                return true;
//            }
//            return false;
//        });
//    });
//        
  //  }

//    @FXML
//    private void search2(KeyEvent event) {
//        FilteredList<books> filterr =new FilteredList (booksList2, e -> true );
//        searchId.textProperty().addListener((observableValue,oldValue,newValue)-> {
//        filterr.setPredicate((Predicate <? super books>)(books b) ->{
//            if(newValue.isEmpty() || newValue == null){
//                return true;
//            }
//            String lowerCaseFilter = newValue.toLowerCase();
//            if(b.getBid().contains(newValue)){
//                return true;
//            }
//            return false;
//        });
//        
//    });
//        SortedList<books> sorted = new SortedList<> (filterr);
//        sorted.comparatorProperty().bind(bTable.comparatorProperty());
//        bTable.setItems(booksList2);
//    }
//        
//        Parent root = FXMLLoader.load(getClass().getResource("searchBooks.fxml"));
//        Scene scene = new Scene(root);
//        Stage primaryStage = new Stage();
//        primaryStage.setScene(scene);
//        primaryStage.show();
    

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
    private void search2(KeyEvent event) {
            //personList is table setter getter
        FilteredList<books> filterData = new FilteredList<>(booksList2, p -> true);
        searchId.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(pers -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (pers.getBid().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (pers.getbName().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (pers.getbCat().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }
              

                return false;
            });
            SortedList<books> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(bTable.comparatorProperty());
            bTable.setItems(sortedList);
                       
            
        });

    }
    
    
    
}
