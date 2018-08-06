package pageb.Staff;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class StaffController {

    //To load a window when a method is called
    private void loadWindow(String location, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    //method to go to the staff login
    public void logout(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow("/StartPage/Stafflogin.fxml","Staff Login");
    }

    //method to go to the desktop app
    public void exit(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow("/StartPage/StartPage.fxml","Desktop App ");
    }

    //method to go to check the transaction
    public void checktransaction(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow( "/pageb/Staff/CustomerDetail.fxml","Check Transaction" );
    }

    //method to go to update product
    public void updateproduct(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow( "/pageb/Staff/UpdateProduct.fxml","Update Product" );
    }
}
