package Database;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String Username ="root";
    private static final String Pass="";
    private static final String CONN="jdbc:mysql://localhost:3306/jeff";

public static Connection getConnection() throws SQLException {


    return DriverManager.getConnection(CONN , Username, Pass);
}
    //To load a window when a method is called
    public static void loadWindow(String location, String title) throws IOException {
        Parent root = FXMLLoader.load(DBConnection.class.getResource(location));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

}
