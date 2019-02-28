package Manager;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerProfileScreenController {

    @FXML
    private JFXButton btnUserScreen;


public void onSaveInfoAction(ActionEvent event){


}

public void onUserScreenAction(ActionEvent event){

    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Manager/manager_screen.fxml"));
        AnchorPane root2 = null;
        root2 = (AnchorPane) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Manager DashBoard");
        Scene scene = new Scene(root2, 600, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Stage prevStage = (Stage) this.btnUserScreen.getScene().getWindow();
        prevStage.close();

    } catch (IOException e) {
        e.printStackTrace();
    }

}

}