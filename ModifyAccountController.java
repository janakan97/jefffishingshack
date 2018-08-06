package pageb.Customer;

import Database.DBConnection;
import StartPage.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifyAccountController implements Initializable {
    public TextField name;
    public TextField email;
    public TextField password;
    public TextArea address;
    public  int customerid;


    @Override // intializing with values that are stored in user class using the getters
    public void initialize(URL location, ResourceBundle resources) {
        customerid=User.getUserIdcommon();
        String namel=User.getUsernamecommon();
        String emaill=User.getEmailcommon();
               String passwordl=User.getPasswordcommon();
               String addressl=User.getPasswordcommon();

            name.setText( namel );
            email.setText( emaill );
            password.setText( passwordl );
            address.setText( addressl );


    }


    //methdod calls when the confirm button is clicke and it receive the new detail and it get added to database
    public void updatedb()  {
        String NAME=name.getText();
        String EMAIL =email.getText();
        String PASSWORD =password.getText();
        String ADDRESS=address.getText();
        try {

            Connection con = DBConnection.getConnection();
            String sql ="update Customer  SET Name='"+NAME+"',Email='"+EMAIL+"',Password='"+PASSWORD+"',Address='"+ADDRESS+"' WHERE customerid = "+customerid+";";
            con.prepareStatement(sql).executeUpdate();

        }catch (Exception e){
            System.out.println(e);
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("CONFIRMED");
        alert.setContentText("YOUR NEW CHANGES HAS BEEN UPDATED TO THE DATABASE NOW YOU CAN USE THEM  ");
        alert.showAndWait();
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

    //Method to go back to the current page
    public void back(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow( "/pageb/Customer/Customer.fxml",User.getUsernamecommon() );
    }
}
