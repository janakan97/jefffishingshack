package pageb.Customer;

import Database.DBConnection;
import StartPage.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

public class PurchaseController implements Initializable {

    public  String size1;
    public  Integer size2;
    public  Integer size3;
    public  Integer size4;
    public  Integer size5;
    public  String size6;
    public double total=0;
    public  int commonquantity1;
    public  int commonquantity2;
    public  int commonquantity3;
    public  int commonquantity4;
    public  int commonquantity5;
    public  int commonquantity6;
    public ComboBox cmbobox1;
    public ComboBox cmbobox2;
    public ComboBox cmbobox3;
    public ComboBox cmbobox4;
    public ComboBox cmbobox5;
    public ComboBox cmbobox6;
    public TextField qty1;
    public TextField qty2;
    public TextField qty3;
    public TextField qty4;
    public TextField qty5;
    public TextField qty6;
    public Label tp1;
    public Label tp2;
    public Label tp3;
    public Label tp4;
    public Label tp5;
    public Label tp6;
    public Label tp10;
    public String day;
    public String month;
    public String year;


    //intializing the the variable and adding them to the combobox
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //creating of arraylist to store the sizes of each product
        ArrayList<String> FISHINGREELS=new ArrayList<String>();
        ArrayList<Integer> FISHYHOOKS= new ArrayList<>();
        ArrayList<Integer> FISHINGLINE=new ArrayList<Integer>();
        ArrayList<Integer> FISHINGROD=new ArrayList<Integer>();
        ArrayList<Integer> FISHINGSINKER= new ArrayList<>();
        ArrayList<String> SWIVELS=new ArrayList<String>();

        //adding the values to array list
        FISHINGREELS.add( "SMALL" );FISHINGREELS.add( "MEDIUM" );FISHINGREELS.add( "LARGE" );
        FISHYHOOKS.add( 3 );FISHYHOOKS.add( 7 );FISHYHOOKS.add( 11 );
        FISHINGLINE.add( 100 );FISHINGLINE.add( 200 );FISHINGLINE.add( 300 );
        FISHINGROD.add( 1 );FISHINGROD.add( 3 );FISHINGROD.add( 5 );
        FISHINGSINKER.add( 100 );FISHINGSINKER.add(200);FISHINGSINKER.add( 300 );
        SWIVELS.add( "SMALL" );SWIVELS.add( "MEDIUM" );SWIVELS.add( "LARGE" );

        //adding the arraylist to observable list to the value to combobox
        ObservableList<String> list1 = FXCollections.observableArrayList(FISHINGREELS);
        ObservableList<Integer> list2 = FXCollections.observableArrayList(FISHYHOOKS);
        ObservableList<Integer> list3 = FXCollections.observableArrayList(FISHINGLINE);
        ObservableList<Integer> list4 = FXCollections.observableArrayList(FISHINGROD);
        ObservableList<Integer> list5 = FXCollections.observableArrayList(FISHINGSINKER);
        ObservableList<String> list6 = FXCollections.observableArrayList(SWIVELS);

        //setting the values to the combobox
        cmbobox1.setItems( list1 );cmbobox2.setItems( list2 );cmbobox3.setItems( list3 );cmbobox4.setItems( list4 );cmbobox5.setItems( list5 );cmbobox6.setItems( list6 );

        //to get the current date of the
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        year  = Integer.toString( localDate.getYear() );
        month = Integer.toString(localDate.getMonthValue());
        day   = Integer.toString(  localDate.getDayOfMonth());


    }


    //Searching for the unit price of the product and find the total price of the product
    public double database(String size ,String code ,int qty) throws SQLException {
        Connection con = DBConnection.getConnection();
        String sql ="SELECT  unitprice,productid FROM `Stock` WHERE Productcode LIKE '%"+code+"%' AND size='"+size+"';";
        ResultSet resultSet=  con.prepareStatement(sql).executeQuery();
        double price=0;
        int pid=0;
        while (resultSet.next()) {
            price = resultSet.getDouble( "unitprice" );
           pid=resultSet.getInt( "productid" );
        }
        System.out.println(price);
        double totalprice = qty*price;
        databasepurchase(totalprice,pid);
        return totalprice;
    }

    //Searching for the unit price of the product and find the total price of the product
    public double database(Integer size ,String code ,int qty) throws SQLException {
        Connection con = DBConnection.getConnection();
        String sql ="SELECT  unitprice ,productid FROM `Stock` WHERE Productcode LIKE '%"+code+"%' AND size='"+size+"';";
        ResultSet resultSet=  con.prepareStatement(sql).executeQuery();
        double price=0;
        int pid=0;
        while (resultSet.next()) {
            price = resultSet.getDouble( "unitprice" );
            pid=resultSet.getInt( "productid" );
        }
      //calling a method to add product to the database
        double totalprice = qty*price;
        databasepurchase(totalprice,pid);
        return totalprice;

    }

    //to fishing reels calculation
    public void FE() throws SQLException {
    size1 = (String) cmbobox1.getValue();
    commonquantity1= Integer.parseInt( (qty1.getText()) );
    double totalprice=database( size1,"FE",commonquantity1 );
    //double tot=totalprice;
    tp1.setText(Double.toString( totalprice ));
    total=total+totalprice;
    tp10.setText(Double.toString( total ));
    }

    //to fishy hooks calculation
    public void FH() throws SQLException {
        size2 = (Integer) cmbobox2.getValue();
        commonquantity2= Integer.parseInt( (qty2.getText()) );
        double totalprice=database( size2,"FH",commonquantity2 );
        //double tot=totalprice;
        tp2.setText(Double.toString( totalprice ));
        total=total+totalprice;
        tp10.setText(Double.toString( total ));
    }

    //to fishing line calculation
    public void FL() throws SQLException {
        size3 = (Integer) cmbobox3.getValue();
        commonquantity3= Integer.parseInt( (qty3.getText()) );
        double totalprice=database( size3,"FL",commonquantity3 );
        //double tot=totalprice;
        tp3.setText(Double.toString( totalprice ));
        total=total+totalprice;
        tp10.setText(Double.toString( total ));
    }

    //to fishing rod calculation
    public void FR() throws SQLException {
        size4 = (Integer) cmbobox4.getValue();
        commonquantity4= Integer.parseInt( (qty4.getText()) );
        double totalprice=database( size4,"FR",commonquantity4 );
        //double tot=totalprice;
        tp4.setText(Double.toString( totalprice ));
        total=total+totalprice;
        tp10.setText(Double.toString( total ));
    }

    //to fishing sinker calculation
    public void FS() throws SQLException {
        size5 = (Integer) cmbobox5.getValue();
        commonquantity5= Integer.parseInt( (qty5.getText()) );
        double totalprice=database( size5,"FS",commonquantity5 );
        //double tot=totalprice;
        tp5.setText(Double.toString( totalprice ));
        total=total+totalprice;
        tp10.setText(Double.toString( total ));
    }

    //to swivels calculation
    public void SW() throws SQLException {

        size6 = (String) cmbobox6.getValue();
        commonquantity6= Integer.parseInt( (qty6.getText()) );
        double totalprice=database( size6,"FE",commonquantity6 );
        //double tot=totalprice;
        tp6.setText(Double.toString( totalprice ));
        total=total+totalprice;
        tp10.setText(Double.toString( total ));
    }

    //accessing the database and inserting the purchase transaction to product transactin table
    public void databasepurchase(double total1,int pid) throws SQLException {
        Connection con =DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement("insert into producttransaction (date,customerid,productid,amount)values(?,?,?,?)");
        stmt.setString(1,year+"-"+month+"-"+day);
        stmt.setInt(2, User.getUserIdcommon() );
        stmt.setInt(3, pid);
        stmt.setDouble(4, total1);
        try {
            stmt.execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //accesing the database and inserting the total amount of transation to the daily transaction table
    public void purchase(ActionEvent event) throws SQLException {
        Connection con =DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement("insert into dailytransaction (date,customerid,amount)values(?,?,?)");
        stmt.setString(1,year+"-"+month+"-"+day);
        stmt.setInt(2, User.getUserIdcommon());
        stmt.setDouble(3, total);
        stmt.execute();
        sendmail();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("CONFIRMED");
        alert.setContentText("YOUR PURCHASE HAS BEEN CONFIRMED AND THE TOTAL AMOUNT WILL BE SENT TO YOUR MAIL");
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
            message.setRecipients( Message.RecipientType.TO, InternetAddress.parse( User.getEmailcommon()) );
        } catch (javax.mail.MessagingException e1) {
            e1.printStackTrace();
        }

        // Set Subject: header field
        try {
            message.setSubject( "INVOICE FOR THE LAST PURCHASE" );
        } catch (javax.mail.MessagingException e1) {
            e1.printStackTrace();
        }

        // Now set the actual message
        //8

        try {
            String str = "<h1 align=\"center\">Jeff's Fishing Shack</h1>\n" +
                    "<h3 align = \"center\"> Tax Invoice</h3>\n" +
                    "<br>\n" +
                    "<p>Jeff's Fishing Shack</p>\n" +
                    "<p>Trading as: Nemo Pvt Ltd</p>\n" +
                    "<p>Shop 5,  Boat Harbour</p>\n" +
                    "<p>Hillarys, Col-6, 10350</p>\n" +
                    "<p>T: 0711214982</p>\n" +
                    "<p>E: jefffishingshack018@gmail.com</p>\n" +
                    "<br>\n" +
                    "<p style=\"text-align:right;\">Date: "+year+"-"+month+"-"+day+"</p>" +
                    "<p>Receipt#: 100</p>\n" +
                    "<br>\n" +
                    "<p>Customer Name: " + User.getUsernamecommon() + "</p>\n" +
                    "<p>Address: " + User.getAddresscommon() + "\n</p>" +
                    "<p>Customer email: " + User.getEmailcommon() + "\n</p>" +
                    "<table cellspacing=\"60\">" +
                    "<caption>Purchases</caption>" +
                    "<tr>" +
                    "<th>No.</th>" +
                    "<th>Desc.</th>" +
                    "<th>Code</th>" +
                    "<th>Size</th>" +
                    "<th>Oty</th>" +
                    "<th>Amount</th></tr><tbody>\n\n\n" ;
            str +=   "<tr><td>" + 1 + "</td><td>" + "FISHINGREELS" + "</td><td>" + "FE" + "</td><td>" + cmbobox1.getValue() + "</td><td>" + qty1.getText() + "</td><td>" + tp1.getText() + "</td></tr>"+
                     "<tr><td>" + 2 + "</td><td>" + "FISHYHOOKS"   + "</td><td>" + "FH" + "</td><td>" + cmbobox2.getValue() + "</td><td>" + qty2.getText() + "</td><td>" + tp2.getText() + "</td></tr>"+
                     "<tr><td>" + 3 + "</td><td>" + "FISHINGLINE"  + "</td><td>" + "FL" + "</td><td>" + cmbobox3.getValue() + "</td><td>" + qty3.getText() + "</td><td>" + tp3.getText() + "</td></tr>"+
                     "<tr><td>" + 4 + "</td><td>" + "FISHINGROD"   + "</td><td>" + "FR" + "</td><td>" + cmbobox4.getValue() + "</td><td>" + qty4.getText() + "</td><td>" + tp4.getText() + "</td></tr>"+
                     "<tr><td>" + 5 + "</td><td>" + "FISHINGSINKER"+ "</td><td>" + "FS" + "</td><td>" + cmbobox5.getValue() + "</td><td>" + qty5.getText() + "</td><td>" + tp5.getText() + "</td></tr>"+
                     "<tr><td>" + 6 + "</td><td>" + "SWIVELS"      + "</td><td>" + "SW" + "</td><td>" + cmbobox6.getValue() + "</td><td>" + qty6.getText() + "</td><td>" + tp6.getText() + "</td></tr></tbody>";;

            str += "</table><p style=\"text-align:right;\">Total Paid:$ " + total + "</p>" +
                    "<p style=\"text-align:center;\">Thank you for your business.</p>" +
                    "<p style=\"text-align:center;\">Jeff's - where the real fishermen shop.</p>";

message.setContent( str,"text/html" );

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

    //method to go back to the last page to the current page
    public void back(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        loadWindow( "/pageb/Customer/Customer.fxml",User.getUsernamecommon() );
    }

}









