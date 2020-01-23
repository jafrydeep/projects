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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ReturnBookController implements Initializable {
    private final String DB_USER = "root";
    private final String DB_DB = "libraryMgt";
    private final String DB_PASS = "102030";
    private final String DB_HOST = "127.0.0.1";
    private final String DB_URL = "jdbc:mysql://"+DB_HOST+"/"+DB_DB;
    private ObservableList <issueBooks> issueList;
    private ObservableList <returnbook> transactionList;
    @FXML
    private TableView<issueBooks> iTable;
    @FXML
    private TextField searchh;
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
    private TableColumn<issueBooks, Number> sId;
    @FXML
    private TableColumn<issueBooks, String> sName;
    @FXML
    private TableColumn<issueBooks, String> bId;
    @FXML
    private TableColumn<issueBooks, String> bname;
    @FXML
    private TableColumn<issueBooks, Date> iDate;
    @FXML
    private Text ret;
    @FXML
    private TableColumn<returnbook, String> sName11;
    @FXML
    private TableColumn<returnbook, String> bId11;
    @FXML
    private TableColumn<returnbook, String> bname11;
    @FXML
    private TableColumn<returnbook, String> iDate11;
    @FXML
    private TableColumn<returnbook, String> ii1;
    @FXML
    private TableColumn<returnbook, Date> rr1;
    @FXML
    private TableView<returnbook> iTable111;
    @FXML
    private TableColumn<returnbook, Number> sId11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    issueList = FXCollections.observableArrayList();   
    issueList.addAll(getStudentList());     
    iTable.setItems(issueList); 
    
    transactionList = FXCollections.observableArrayList();   
    transactionList.addAll(getrrList());     
    iTable111.setItems(transactionList); 
        // TODO
        
    sId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getsId()));  
    sName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getsName()));   
    bId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getbId()));
    bname.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getbName()));
//    iDate.setCellValueFactory(data -> new SimpleDateProperty(data.getValue().getIssueDate())); 
    iDate.setCellValueFactory(new PropertyValueFactory<issueBooks, Date>("issueDate"));
    
    sId11.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getSid()));  
    sName11.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSname()));   
    bId11.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBid()));
    bname11.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBname()));
    iDate11.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getType()));
    ii1.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIdate()));
    rr1.setCellValueFactory(new PropertyValueFactory<returnbook, Date>("rdate"));
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
    private void onReturnAction(ActionEvent event) {
        int ssid = Integer.parseInt(s1.getText());
        String ssname = s2.getText();
        String bbid = s3.getText();
        String bbname = s4.getText();
        String iidate = s5.getText();
        LocalDate locald = LocalDate.now();
        Date date = Date.valueOf(locald); 
        String type = "Returned";
        returnbook c = new returnbook (ssid, ssname, bbid, bbname,type,iidate,date);
        returnbooks(c);
        ret.setText("Successfully returned !");
        reset();
        
        
    }
    
    private void returnbooks(returnbook i){
//        if (s1.getText()!= " " && search7.getText()!= " "){
        int Id3 = i.getSid();
        String Name3 = i.getSname();
        String biId = i.getBid();
        String bName = i.getBname();
//        String bname3 = i.getType();
        String iiidate = i.getIdate();
        String type3 = "Borrowed";
        Date date3 = i.getRdate();
        
       
        
        String query = "INSERT INTO returnbook VALUES(" + Id3 + ",'" + Name3 + "','" + biId + "','" + bName + "','" + type3 + 
                "','" + iiidate +"','"+ date3+ "');";
//        String queryy = "UPDATE bookList SET Copies=Copies-1 where BookId='" + bid3 + "';";
        try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        Statement s = connection.createStatement();
        s.executeUpdate(query);
//        s.executeUpdate(queryy);
        transactionList.add(i);
 
        }catch(SQLException sqle){
        System.err.println(sqle);
         }
        
    }

    @FXML
    private void onResetAction(ActionEvent event) {
         s1.setText("");
         s2.setText("");
         s3.setText("");
         s4.setText("");
         
         s5.setText("");
        
    }
    
    private List<issueBooks> getStudentList(){
        List<issueBooks> allst = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            Statement s = connection.createStatement();
            String query = "SELECT * FROM issuebook;";
            ResultSet r = s.executeQuery(query);
            
            while(r.next()){
                int id = r.getInt("sId");
                String name = r.getString("sName");
                String dept = r.getString("sDept");
                String bid = r.getString("bId");
                String bname = r.getString("bName");
                String bcat = r.getString("bCat");
                String type = r.getString("type");
                Date idate = r.getDate("date");
                
                
                issueBooks d = new issueBooks(id,name,dept,bid,bname,bcat,type,idate);
                issueList.add(d);
            }
        }catch(SQLException sqle){
            System.err.println(sqle);
        }
        return allst;     
    }
     private List<returnbook> getrrList(){
        List<returnbook> allst = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            Statement s = connection.createStatement();
            String query = "SELECT * FROM returnbook;";
            ResultSet r = s.executeQuery(query);
            
            while(r.next()){
                int id = r.getInt("sId");
                String name = r.getString("sName");
                String dept = r.getString("bId");
                String bid = r.getString("bname");
                String bname = r.getString("type");
                String bcat = r.getString("date");
                Date type = r.getDate("rDate");
                returnbook d = new returnbook(id,name,dept,bid,bname,bcat,type);
                transactionList.add(d);
            }
        }catch(SQLException sqle){
            System.err.println(sqle);
        }
        return allst;     
    }

    @FXML
    private void clickAction(MouseEvent event) {
     iTable.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle (MouseEvent event) {
            issueBooks p1 = iTable.getItems().get(iTable.getSelectionModel().getSelectedIndex());
            int i = p1.getsId();
            String s = String.valueOf(i); 
            s1.setText(s);
            s2.setText(p1.getsName());
            s3.setText(p1.getbId());
            s4.setText(p1.getbName());
            Date date = p1.getIssueDate();  
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
            String strDate = dateFormat.format(date);  
            
            s5.setText(strDate);       
       }
    });
        
    }

    @FXML
    private void typeAction(KeyEvent event) {
          FilteredList<issueBooks> filterData = new FilteredList<>(issueList, p -> true);
        searchh.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(pers -> {
                
                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (pers.getsName().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (pers.getbId().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (pers.getbName().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }
                if (pers.getIssueDate().toString().indexOf(typedText) != -1) {
                    return true;
                }
//                if (pers.getId().toLowerCase.indexOf(typedText) != -1) {
//                    return true;
//                }

                return false;
            });
            SortedList<issueBooks> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(iTable.comparatorProperty());
            iTable.setItems(sortedList);
                       
            
          
        });

}
      public void reset(){
         s1.setText("");
         s2.setText("");
         s3.setText("");
         s4.setText("");
         
         s5.setText("");
        
     }
    
}
