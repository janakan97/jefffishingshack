package Owner;

import javafx.beans.property.*;


//class to hold the all daily transaction detail of the data base until it is is loaded
public class daily {
    private final DoubleProperty amount;
    private final IntegerProperty customerid;
    private final StringProperty date;


    public daily(String date, int customerid, double amount) {
        this.date = new SimpleStringProperty(date);
        this.customerid = new SimpleIntegerProperty(customerid);
        this.amount = new SimpleDoubleProperty(amount);

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
