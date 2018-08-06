package Owner;

import Database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class RegisterStaffDetail {
    @FXML
    private TextField fulname;
    @FXML
    private TextField email;
    @FXML
    private TextField pass;
    @FXML
    private Button btn;


    //method to update the newly joined customer to the
    @FXML
    public void registerdetail(ActionEvent event) {
        if (fulname.getText().isEmpty() || email.getText().isEmpty() || pass.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Fill all required fields!");
            alert.showAndWait();
        } else {
            try {
                Connection con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement("insert into Employee values(?,?,?,?)");

                stmt.setInt(1, 1);
                stmt.setString(2, fulname.getText());
                stmt.setString(3, email.getText());
                stmt.setString(4, pass.getText());
                stmt.executeUpdate();
                System.out.println( " records inserted");

                con.close();
                Alert confirmation  =new Alert(Alert.AlertType.INFORMATION);
                confirmation.setHeaderText("CONFIRMED");
                confirmation.setContentText("Staff have been registered Successfully  ");
                confirmation.show();
                cleartabs();

            } catch (Exception e) {
                Alert confirmation  =new Alert(Alert.AlertType.INFORMATION);
                confirmation.setHeaderText("NOT VERIFIED");
                confirmation.setContentText("THE EMAIL ID IS ALREADY EXIST  ");
                confirmation.show();
            }

        }
    }

    //method to clear the the tabs after it has registered
    private void cleartabs() {
        fulname.clear();
        email.clear();
        pass.clear();

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

    //to go to the owner main page
    public void backing(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow( "/Owner/Owner.fxml","Jeff" );
    }


}

