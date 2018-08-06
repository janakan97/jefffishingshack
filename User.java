package StartPage;


//user  a commomn super class to all the sub class such as owner,customer,staff
public class User {
    static String Usernamecommon;
    static String passwordcommon;
    static String emailcommon;
    static String addresscommon;
    static int userIdcommon;

//getting the current value of the logged in user
    public static String getUsernamecommon() {
        return Usernamecommon;
    }
    //setting the current value of the logged in user
    public static void setUsernamecommon(String usernamecommon) {
        Usernamecommon = usernamecommon;
    }
    //getting the current value of the logged in user
    public static String getPasswordcommon() {
        return passwordcommon;
    }

    //setting the current value of the logged in user
    public static void setPasswordcommon(String passwordcommon) {
        User.passwordcommon = passwordcommon;
    }
    //getting the current value of the logged in user
    public static String getEmailcommon() {
        return emailcommon;
    }

    //setting the current value of the logged in user
    public static void setEmailcommon(String emailcommon) {
        User.emailcommon = emailcommon;
    }

    //getting the current value of the logged in user
    public static String getAddresscommon() {
        return addresscommon;
    }

    //setting the current value of the logged in user
    public static void setAddresscommon(String addresscommon) {
        User.addresscommon = addresscommon;
    }

    //getting the current value of the logged in user
    public static int getUserIdcommon() {
        return userIdcommon;
    }

    //setting the current value of the logged in user
    public static void setUserIdcommon(int userIdcommon) {
        User.userIdcommon = userIdcommon;
    }


}
