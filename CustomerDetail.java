package pageb.Staff;

import Database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDetail {
    @FXML
    private TextField cusid;
    @FXML
    private TextField date;
    @FXML
    private TextArea output;

        //to get the detail from daily transactin database using the customer id and the date he want to see his transaction
    public void getdetail(ActionEvent event) throws SQLException {
        Connection con=DBConnection.getConnection();
        String sql="SELECT Amount FROM `dailytransaction` WHERE customerID="+cusid.getText()+" AND date='"+date.getText()+" ';";
        PreparedStatement stmt1 = con.prepareStatement(sql);
          ResultSet resultSet = stmt1.executeQuery();
        System.out.println("done");
        double amount=0;
        while (resultSet.next()){
            amount=resultSet.getDouble( "Amount" );
        }
        output.setText( Double.toString(  amount) );

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
