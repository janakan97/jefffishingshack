package Owner;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class OwnerController {

    //To load a window when a method is called
    private void loadWindow(String location, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
 }

    //method to go to the register staff
    public void register(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
         loadWindow( "/Owner/RegisterStaffDetail.fxml","Register Staff");
    }

    //method to go to the add product
    public void addProduct(ActionEvent event)throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow( "/Owner/AddProduct.fxml","Add Product");
    }

    //method to go to the daily transaction
    public void dailytransaction(ActionEvent event)throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow( "/Owner/DailyTransction.fxml","Daily Transaction");
    }

    //method to go to the logfile
    public void viewlog(ActionEvent event) throws IOException{
        ((Node)event.getSource()).getScene().getWindow().hide();
         loadWindow( "/Owner/ViewLogFile.fxml","Log File");
    }

    //method to go to the customer detail
    public void customerdetail(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow( "/Owner/CustomerDetail.fxml","Customer Detail");
    }

    //method to go to the desktop app
    public void exit(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow("/StartPage/StartPage.fxml","Desktop App ");

    }

}
