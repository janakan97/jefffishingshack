package Owner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewLogFile {
    @FXML
    private Label label;
    @FXML
    private TableView<log> tableUser;
    @FXML
    private TableColumn<log, String> date;
    @FXML
    private TableColumn<log,Integer> customerid;
    @FXML
    private TableColumn<log,Integer> productid;
    @FXML
    private TableColumn<log,Double> amount;
    @FXML
    private Button btnLoad;
    //Initialise observable list to hold out database data
    private ObservableList<log> data;

    //To load a window when a method is called form  the log class
    private void loadWindow(String location, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    //method to load data from the data t
    public void loadDataFromDatabase(ActionEvent event) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff", "root", "");
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM PRODUCTTRANSACTION ");
            while (rs.next()) {
                data.add(new log(rs.getString(2), rs.getInt(3),rs.getInt( 4 ),rs.getDouble(5) ));
            }
        }catch(SQLException ex){
            System.out.println("Error"+ex);
        }
//set cell value factory to tableview
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        customerid.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        productid.setCellValueFactory(new PropertyValueFactory<>("productid"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        //tableUser.setItems(null);
        tableUser.setItems(data);
    }

    //to go to the main of the owner
    public void back(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow( "/Owner/Owner.fxml","JEFF" );
    }
}


