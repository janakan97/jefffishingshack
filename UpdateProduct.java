package pageb.Staff;

import Database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateProduct {


    @FXML
    private TextField productidup;
    @FXML
    private TextField quantityup;

    public void update(ActionEvent event) {
        try {
            //connecting to the database of the jeff and the table name stock
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt1 = con.prepareStatement("update Stock set quantity = quantity + ? WHERE Productid=?");
            stmt1.setString(1,  quantityup.getText() );
            stmt1.setString(2,productidup.getText() );
            int i = stmt1.executeUpdate();
            con.close();
            Alert confirmation  =new Alert(Alert.AlertType.INFORMATION);
            confirmation.setHeaderText("CONFIRMED");
            confirmation.setContentText("You have been updated the product  ");
            confirmation.show();
            cleartabs();

        }catch (Exception e) {
            System.out.println(e);
        }

    }

    //method to clear the tabs
    private void cleartabs() {
        quantityup.clear();
        productidup.clear();

    }

    //To load a window when a method is called
    private void loadWindow(String location, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();

    }

    //method to go back to the last page to the current page
    public void back(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow( "/pageb/Staff/Staff.fxml","Staff" );
    }
}
