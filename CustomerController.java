package pageb.Customer;

import java.io.IOException;

import StartPage.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class CustomerController {


    //To load a window when a method is called
    private void loadWindow(String location, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();

    }

    //method to go to the Purchase page
    @FXML
    private void purchase(ActionEvent event)throws IOException{
        ((Node)event.getSource()).getScene().getWindow().hide();
     loadWindow("/pageb/Customer/Purchase.fxml","Purchase");
    }

    //method to go to the Balance Request
    public void reqestCheckBalance(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
      loadWindow("/pageb/Customer/CustomerRequest.fxml","Balance Request");
    }

    //method to go to the Modify account
    public void modify(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow("/pageb/Customer/ModifyAccount.fxml","Modify Account");
    }

    //method to go to the customer login
    public void logout(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow("/StartPage/Customerlogin.fxml","Customer Login");

    }

    //method to go back to the intial page
    public void exit(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow("/StartPage/StartPage.fxml","Desktop App ");

    }

}