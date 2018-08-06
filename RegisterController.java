package Register;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import Database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RegisterController implements Initializable{




    public void initialize(URL url, ResourceBundle rb) {


    }


    private Text names;

    @FXML
    private TextField fulname;
    @FXML
    private TextField email;
    @FXML
    private TextField pass;
    @FXML
    private TextArea address;

    //when clicking the register button it works and with validation of all the necessary
    public void registerdetail (ActionEvent actionEvent) throws SQLException {
        boolean status=true;
        //in password alphabetic counts
        int pwalpha=0;
        //in password  non-alphabetic counts
        int pwnalpha=0;
        char c;
        String pw= pass.getText();
        int pwl=pw.length();
        //the empty textbox validation
        if (fulname.getText().isEmpty() || email.getText().isEmpty() || pass.getText().isEmpty() || address.getText().isEmpty()) {
            alert(null, "Fill all the required fiels");
            //length of the password
        }
        if(pwl <=8) {
            alert(null, "Length of the password is not Enough ");
            status=false;
        } if(true){

            for(int i=0;i<pwl;i++) {
                  c = pw.charAt(i);
                  //the alphabetic atleast two
                 if((c>='a' && c<='z') || (c>='A' && c<='Z')){
                    pwalpha++;
                 }else{
                     pwnalpha++;
                 }
            }if(pwalpha<2){
                alert(null,"You need to have atleast 2 alphabets" );
            }if(pwnalpha<2){
                alert( null,"You need to have two non-Alphabet Value" );

            }
            }
            if(status) {
            try {
                //connecting to the database of the customer
                Connection con = DBConnection.getConnection();
               System.out.println("connected to db");
                PreparedStatement stmt = con.prepareStatement("insert into customer (Name,Email,Password,Address)values(?,?,?,?)");

                stmt.setString(1, fulname.getText());
                stmt.setString(2, email.getText());
                stmt.setString(3, pass.getText());
                stmt.setString(4, address.getText());
                stmt.executeUpdate();

                Alert confirmation  =new Alert(Alert.AlertType.INFORMATION);
                confirmation.setHeaderText("CONFIRMED");
                confirmation.setContentText("You have been registered Successfully ,Now you can login ");
                confirmation.show();
                sendmail(  );
                Alert confirmation2  =new Alert(Alert.AlertType.INFORMATION);
                confirmation2.setHeaderText("SUCCESSFUL");
                confirmation2.setContentText("Your mail ID is accepted and monthly News Letter will be send to your mail");
                confirmation2.show();
                cleartabs();

            } catch (Exception e) {
               // System.out.println(e);
               alert("ALREADY VERIFIED","THE EMAIL ID IS ALREADY TAKEN");
            }


        }
    }
    //method go to the last page to the current page
    public void back(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow("/StartPage/StartPage.fxml","Desktop App");

    }
    private void cleartabs() {
        fulname.clear();
        email.clear();
        pass.clear();
        address.clear();
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

    //method call the alert box when necessary
    private void alert(String headertext,String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(headertext);
        alert.setContentText(content);
        alert.showAndWait();

    }

    //To send the mail confirmation after the customer registered successfully
    private void sendmail(){

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.setProperty( "mail.smtp.ssl.enable", "true" );
        props.put( "mail.smtp.auth", "true" );
        props.put( "mail.smtp.starttls.enable", "true" );
        props.put( "mail.smtp.host", host );
        props.put( "mail.smtp.port", "587" );

        // Get the Session object.
        Session session = Session.getInstance( props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication(){

                        return new PasswordAuthentication( "jefffishingshack018@gmail.com", "123456jeff" );
                    }
                } );


        // Create a default MimeMessage object.
        Message message = new MimeMessage( session );

        // Set From: header field of the header.
        try {
            message.setFrom( new InternetAddress( "jefffishingshack018@gmail.com" ) );
        } catch (javax.mail.MessagingException e1) {
            e1.printStackTrace();
        }

        // Set To: header field of the header.
        try {
            message.setRecipients( Message.RecipientType.TO, InternetAddress.parse( email.getText()) );
        } catch (javax.mail.MessagingException e1) {
            e1.printStackTrace();
        }

        // Set Subject: header field
        try {
            message.setSubject( "Succesfully registered!" );
        } catch (javax.mail.MessagingException e1) {
            e1.printStackTrace();
        }

        // Now set the actual message
        try {
            message.setText( ",\n\tYou have been succesfully registerd. You will be sent " +
                    "newsletter and your payment invoices.\n\tThank You. " );
        } catch (javax.mail.MessagingException e1) {
            e1.printStackTrace();
        }

        // Send message
        try {
            Transport.send( message );
            System.out.println( "Sent message successfully...." );

        }catch (Exception e) {
           alert( "Email is not valid","You have Entered an invalid mail id modify it " );
            // e.printStackTrace();
        }


    }


}



