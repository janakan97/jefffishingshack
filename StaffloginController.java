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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StaffloginController extends User {
    User staff = new User();
    @FXML
    TextField username;
    @FXML
    PasswordField pass;

    //method to check the user id and password and also validating if the pass word is incoorect
    public void login(ActionEvent event) {
        if(username.getText().isEmpty()||pass.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Fill all required fields!");
            alert.showAndWait();
        }
        else {
            try {
                Connection con = DBConnection.getConnection();
                String sql="SELECT * FROM `Employee` WHERE Email='"+username.getText()+"' AND Password ='"+pass.getText()+"';";
                ResultSet resultSet=  con.prepareStatement(sql).executeQuery();
                if(resultSet.next()){
                    ((Node)event.getSource()).getScene().getWindow().hide();
                    String location="/pageb/Staff/Staff.fxml";
                    DBConnection.loadWindow(location,username.getText());
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Enter the Corret Credentials!");
                    alert.showAndWait();

                }
            }catch (Exception e){
                System.out.println(e);
            }
        }


    }

    //method to go to the last page to the current
    public void back(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
     loadWindow("StartPage.fxml","Desktop App");

    }

    //To load a window when a method is called
    public void loadWindow(String location, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

}
