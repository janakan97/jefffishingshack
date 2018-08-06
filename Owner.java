package Owner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Owner extends Application {
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Owner.fxml"));
        primaryStage.setTitle("Owner");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }



    public static void main(String[] args) { launch(args);
    }

}
