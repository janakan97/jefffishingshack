package Owner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddProduct implements Initializable {

    //To load a window when a method is called
    private void loadWindow(String location, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
    public void initialize(URL url, ResourceBundle rb) {

    }
    @FXML
    private TextField productidadd;
    @FXML
    private TextField quantityadd;
    @FXML
    private TextField productname;
    @FXML
    private TextField unitprice;
    @FXML
    private TextField size;
    @FXML
    private TextField productiddup;
    @FXML
    private TextField quantityup;

    // add the new product to the catalogue
    public void addproduct(ActionEvent event)  {
        if(productidadd.getText().isEmpty()|| productname.getText().isEmpty()){
            alert(null, "Fill all the required fiels");

        }if (true){
            try {

                //connecting to the database of the customer
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff","root","");
                PreparedStatement stmt1 = con.prepareStatement("insert into stock(Productcode,productname,Quantity,size,unitprice) values(?,?,?,?,?)");
                stmt1.setString(1,  productidadd.getText() );
                stmt1.setString(2,productname.getText() );
                stmt1.setString(3, quantityadd.getText());
                stmt1.setString(4, size.getText());
                stmt1.setString(5, unitprice.getText());
                   int i = stmt1.executeUpdate();


                con.close();
                Alert confirmation  =new Alert(Alert.AlertType.INFORMATION);
                confirmation.setHeaderText("CONFIRMED");
                confirmation.setContentText("You have been added the good product to the stock ,Now you can see in the catalogue ");
                confirmation.show();
                cleartabs();

            }catch (Exception e) {
               alert("ALREADY VERIFIED","THE PRODUCT ID IS ALREADY TAKEN");
            }

        }
    }

    //updating the exiting product quantity
    public void update(ActionEvent event) {
        try {

            //connecting to the database of the customer
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeff","root","");
            PreparedStatement stmt1 = con.prepareStatement("update Stock set quantity = quantity + ? WHERE Productid=?");
            stmt1.setString(1,  quantityup.getText() );
            stmt1.setString(2,productiddup.getText() );

            int i = stmt1.executeUpdate();
            con.close();
            Alert confirmation  =new Alert(Alert.AlertType.INFORMATION);
            confirmation.setHeaderText("CONFIRMED");
            confirmation.setContentText("You have been updated the product  ");
            cleartabs();
            confirmation.show();

        }catch (Exception e) {
               alert("ALREADY VERIFIED","THE PRODUCT ID IS ALREADY TAKEN");
        }

    }

    //method call the alert box when necessary
    private void alert(String headertext,String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(headertext);
        alert.setContentText(content);
        alert.showAndWait();

    }

    //method to clear the tabs
    private void cleartabs() {
        productidadd.clear();
        quantityadd.clear();
        size.clear();
        unitprice.clear();
        productname.clear();
    }

    //to go to the main of the owner
    public void back(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow( "/Owner/Owner.fxml","JEFF" );
    }
}
