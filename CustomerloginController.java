package StartPage;

import Database.DBConnection;
import StartPage.User;
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
import java.sql.ResultSet;
import java.sql.SQLException;

;
;

//inherited from user class
public class CustomerloginController extends User   {

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
                String sql="SELECT * FROM `Customer` WHERE Email='"+username.getText()+"' AND Password ='"+pass.getText()+"';";
                ResultSet resultSet=  con.prepareStatement(sql).executeQuery();

                if(resultSet.next()){
    //calling the set value method to set the values
                    setvalues();
                    ((Node)event.getSource()).getScene().getWindow().hide();
                    loadWindow( "/pageb/Customer/Customer.fxml",username.getText());


                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Enter the Correct Credentials!");
                    alert.showAndWait();

                }

             }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    //if the login is verified setting the matching value to the
    public void setvalues() {
    try {
    Connection con = DBConnection.getConnection();
    String sql = "select * from Customer where email='" + username.getText() + "';";
    ResultSet resultSet = con.prepareStatement( sql ).executeQuery();
    while (resultSet.next()) {
        int cid =resultSet.getInt( "customerid" );
        String name = resultSet.getString( "name" );
        String email =resultSet.getString( "Email" );
        String pass = resultSet.getString( "password" );
        String add = resultSet.getString( "address" );
        User.setUserIdcommon( cid );
        User.setUsernamecommon( name);
        User.setEmailcommon( email );
        User.setPasswordcommon( pass );
        User.setAddresscommon( add );

    }
}catch (Exception e){
    System.out.println(e);
}
    }

   //method to go to the last page to the current
    public void back(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow("StartPage.fxml","Desktop App");

   }

    //To load a window when a method is called
    public   void loadWindow(String location, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }


}
