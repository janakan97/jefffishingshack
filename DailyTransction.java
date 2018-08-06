package Owner;
import Database.DBConnection;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DailyTransction {

    @FXML
    private Label label;
    @FXML
    private TableView<daily> tableUser;
    @FXML
    private TableColumn<daily, String> date;
    @FXML
    private TableColumn<daily,Integer> customerid;
    @FXML
    private TableColumn<daily,Double> amount;
    @FXML
    private Button btnLoad;
    //Initialise observable list to hold out database data
    private ObservableList<daily> data;

    //To load a window when a method is called
    private void loadWindow(String location, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    public void loadDataFromDatabase(ActionEvent event) {
        try {
            Connection con = DBConnection.getConnection();
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM DAILYTRANSACTION ");
            while (rs.next()) {
                data.add(new daily(rs.getString(2), rs.getInt(3),rs.getDouble(4) ));
            }
        }catch(SQLException ex){
            System.out.println("Error"+ex);
        }
    //set cell value factory to tableview
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        customerid.setCellValueFactory(new PropertyValueFactory<>("customerid"));
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
