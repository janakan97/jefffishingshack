package StartPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartPageController implements Initializable {

    // To load a window when a method is called
    private void loadWindow(String location, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();

    }

    //method to go to the customer login
    public void loginasCustomer(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
       loadWindow("CustomerLogin.fxml","Customer");
    }

    //method to go to the ownerer login
    public void loginasOwner(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
      loadWindow("Ownerlogin.fxml", "Owner" );
    }

    //method to go to the staff login
    public void loginasStaff(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow("Stafflogin.fxml","Staff");

    }

    //method to go to register page
    public void register(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow("/Register/Register.fxml","Registration");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    }





