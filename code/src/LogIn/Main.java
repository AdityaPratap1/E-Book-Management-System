package LogIn;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;

public class Main extends Application {

    @FXML
    JFXButton signUpUser;


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/LogIn/login_screen.fxml"));
        primaryStage.setTitle("Sign Up Screen");
        Scene scene = new Scene(root, 600, 320);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
