package pageb.Customer;

import StartPage.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class CustomerRequestController implements Initializable {

    public TextArea request;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    //method to call when the customer sends a request to the shop
    public void CONFRIMATION(ActionEvent event) {
        sendmail();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("CONFIRMED");
        alert.setContentText("YOUR REQUEST HAS BEEN CONFIRMED AND THE BALANCE WILL BE SENT TO YOUR MAIL");
        alert.showAndWait();
        request.clear();

    }

    //method to send the mail
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
            message.setRecipients( Message.RecipientType.TO, InternetAddress.parse( "jefffishingshack018@gmail.com") );
        } catch (javax.mail.MessagingException e1) {
            e1.printStackTrace();
        }

        // Set Subject: header field
        try {
            message.setSubject( "REQUEST TO CHECK BALANCE OF "+User.getUserIdcommon()+" ");
        } catch (javax.mail.MessagingException e1) {
            e1.printStackTrace();
        }

        // Now set the actual message
        try {
            message.setText( request.getText() );
        } catch (javax.mail.MessagingException e1) {
            e1.printStackTrace();
        }

        // Send message
        try {
            Transport.send( message );
            System.out.println( "Sent message successfully...." );

        }catch (Exception e) {
            e.printStackTrace();
        }


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

    //method to go back to the last page to the current pqge
    public void back(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow( "/pageb/Customer/Customer.fxml",User.getUsernamecommon() );

    }


}
