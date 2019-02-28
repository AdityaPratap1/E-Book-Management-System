package Student;

import LogIn.session.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import database.DBStudentProfileController;
import database.database.vo.StudentProfileVO;
import database.database.vo.UserLoginVO;
import database.database.vo.UserSignUpVO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentProfileController implements Initializable {

    @FXML
    private JFXButton btnBookScreen;

    @FXML
    private JFXTextField txtFname;

    @FXML
    private JFXTextField txtLname;

    @FXML
    private JFXTextField txtDOB;

    @FXML
    private JFXTextField txtGrade;

    @FXML
    private JFXTextField txtStudentId;

    @FXML
    private JFXTextField txtLoginId;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtRedemptionCode;

    @FXML
    private JFXToggleButton tglbtnEditProfile;

    @FXML
    private Label lblSaveSuccess;

    @FXML Label lblWarn;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DBStudentProfileController studentProfileController = new DBStudentProfileController();
        StudentProfileVO studentProfileVO = studentProfileController.getStudentProfile(UserSession.getUserLoginVO().getUserId());
        populateUserDetails(studentProfileVO);

        tglbtnEditProfile.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(tglbtnEditProfile.isSelected() == true){
                    txtFname.setEditable(true);
                    txtLname.setEditable(true);
                    txtGrade.setEditable(true);
                    txtStudentId.setEditable(true);
                    txtPassword.setEditable(true);
                }

                if(tglbtnEditProfile.isSelected() == false){
                    txtFname.setEditable(false);
                    txtLname.setEditable(false);
                    txtGrade.setEditable(false);
                    txtStudentId.setEditable(false);
                    txtPassword.setEditable(false);

                }
            }
        });

    }
    public void onBookScreenAction(javafx.event.ActionEvent event){
        try {
            System.out.println("In openStudentWindow");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Student/student_screen.fxml"));
            AnchorPane root2 = null;
            root2 = (AnchorPane) fxmlLoader.load();
            System.out.println(root2.getChildren().get(1).toString());
            Stage stage = new Stage();
            stage.setTitle("Student Dashboard");
            Scene scene = new Scene(root2, 600, 400);
            stage.setScene(scene);
            //setTableColumn((TableView<BookVO>) root2.getChildren().get(3));
            stage.setResizable(false);
            stage.show();
            Stage prevStage = (Stage) this.btnBookScreen.getScene().getWindow();
            prevStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    StudentScreenController studentScreenController = new StudentScreenController();


    public void populateUserDetails(StudentProfileVO studentProfileVO){


        txtFname.setText(studentProfileVO.getUser_first_name());
        txtLname.setText(studentProfileVO.getUser_last_name());
        txtDOB.setText(studentProfileVO.getUser_dob());
        txtGrade.setText(String.valueOf(studentProfileVO.getStudent_grade()));
        txtStudentId.setText(String.valueOf(studentProfileVO.getStudent_code()));
        txtLoginId.setText(UserSession.getUserLoginVO().getUsername());
        txtPassword.setText(UserSession.getUserLoginVO().getPassword());
        txtRedemptionCode.setText(String.valueOf(UserSession.getUserLoginVO().getRedemptionCode()));

    }

    //This method
    public void onStudentProfileSaveInformation(javafx.event.ActionEvent event){

        if(txtPassword.getText().equals("")){
            lblWarn.setText("Missing Text Fields");
        }

        else {
            StudentProfileVO studentProfileVO = new StudentProfileVO();
            DBStudentProfileController studentProfileController = new DBStudentProfileController();

            studentProfileVO.setUser_first_name(txtFname.getText());
            studentProfileVO.setUser_last_name(txtLname.getText());
            studentProfileVO.setStudent_grade(Integer.valueOf(txtGrade.getText()));
            studentProfileVO.setStudent_code(Integer.valueOf(txtStudentId.getText()));
            UserSession.getUserLoginVO().setPassword(txtPassword.getText());
            studentProfileController.updateStudentProfile(studentProfileVO);
            lblWarn.setVisible(false);
            lblSaveSuccess.setText("Information Saved");
        }
    }
}
