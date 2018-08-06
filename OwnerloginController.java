package StartPage;

import Database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class OwnerloginController extends User {
    User owner= new User();
    @FXML
    TextField username;
    @FXML
    PasswordField pass;
String Ownername="Jeff";

String Password="pass";

    //method to check the user id and password and also validating if the pass word is incoorect
    public void login(ActionEvent event) throws IOException {
        if(username.getText().isEmpty()||pass.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Fill all required fields!");
            alert.showAndWait();
        }
        else {
            if(Ownername.equals(username.getText())||Password.equals(pass.getText())){
                User.setUserIdcommon( 1);
                User.setUsernamecommon( "Jeff" );
                User.setEmailcommon("jefffishingshack018@gmail.com");
                User.setPasswordcommon( "pass" );
                User.setAddresscommon( "colombo" );

                    ((Node)event.getSource()).getScene().getWindow().hide();
                    DBConnection.loadWindow( "/Owner/Owner.fxml",username.getText());

                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Enter the Corret Credentials!");
                    alert.showAndWait();

                }


        }


    }

    //method to go to the last page to the current
    public void back(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow("StartPage.fxml","Desktop App");
    }


   // To load a window when a method is called
    private void loadWindow(String location, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }


}
