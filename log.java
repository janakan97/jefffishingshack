package Owner;

import javafx.beans.property.*;

//class to hold the all product transaction detail of the data base until it is is loaded
public class log {

    private final DoubleProperty amount;
    private final IntegerProperty customerid;
    private final StringProperty date;
    private final IntegerProperty productid;

    public log(String date, int customerid,int productid, double amount) {
        this.date = new SimpleStringProperty(date);
        this.customerid = new SimpleIntegerProperty(customerid);
        this.productid = new SimpleIntegerProperty(productid);
        this.amount = new SimpleDoubleProperty(amount);


    }

    public int getProductid() {
        return productid.get();
    }

    public IntegerProperty productidProperty() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid.set( productid );
    }

    public double getAmount() {
        return amount.get();
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set( amount );
    }

    public int getCustomerid() {
        return customerid.get();
    }

    public IntegerProperty customeridProperty() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid.set( customerid );
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set( date );
    }
}
