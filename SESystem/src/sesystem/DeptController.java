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
 * @author Lenovo
 */
public class DeptController implements Initializable {
    private final String DB_USER = "root";
    private final String DB_DB = "db";
    private final String DB_PASS = "jafry";
    private final String DB_HOST = "127.0.0.1";
    private final String DB_URL = "jdbc:mysql://"+DB_HOST+"/"+DB_DB;
    
    private ObservableList deptList;


    @FXML
    private TextField did;
    @FXML
    private TextField dname;
    @FXML
    private TextField dloc;
    @FXML
    private TextField dphn;
    @FXML
    private TableView<dept> dTableView;
    @FXML
    private TableColumn<dept, Number> dIdd;
    @FXML
    private TableColumn<dept, String> dNamee;
    @FXML
    private TableColumn<dept, String> dLocc;
    @FXML
    private TableColumn<dept, Number> dPhnn;
    @FXML
    private Text scAdd;
    @FXML
    private Text scErr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        deptList = FXCollections.observableArrayList();
        deptList.addAll(getDeptList());       
        dTableView.setItems(deptList);
        
        dIdd.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getdId()));
         dNamee.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getdName()));   
        dLocc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getdLoc()));   
        dPhnn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getdPhn()));

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
    
    private List<dept> getDeptList(){
        List<dept> allDept = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            Statement s = connection.createStatement();
            String query = "SELECT * FROM dept;";
            ResultSet r = s.executeQuery(query);
            
            while(r.next()){
                int dId = r.getInt("dId");
                String dName = r.getString("dName");
                String dLoc = r.getString("dLoc");
                int dPhn = r.getInt("dPhn");
                
                dept d = new dept(dId, dName, dLoc, dPhn);
                deptList.add(d);
            }
        }catch(SQLException sqle){
            System.err.println(sqle);
        }
        return allDept;     
    }
    private void insertDept(dept i){
        int dId = i.getdId();
        String dName = i.getdName();
        String dLoc = i.getdLoc();
        int dPhn = i.getdPhn();
        
        String query = "INSERT INTO dept VALUES(" + dId + ",'" + dName + "','" + dLoc + "'," + dPhn + ");";
        
        try{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        Statement s = connection.createStatement();
        s.executeUpdate(query);
        deptList.add(i);
 
    }catch(SQLException sqle){
        System.err.println(sqle);
    }
    
    }
    @FXML
    private void addAction(ActionEvent event) {
        int dId = Integer.parseInt(did.getText());
        String dName = dname.getText();
        String dLoc = dloc.getText();
        int dPhn = Integer.parseInt(dphn.getText());
        
        dept i = new dept (dId , dName , dLoc, dPhn);
        insertDept(i);
        scAdd.setText("Successfully Added !!!");
    }

    @FXML
    private void resetAction(ActionEvent event) {
        did.clear();
        dname.clear();
        dloc.clear();
        dphn.clear();
    }
    
}
