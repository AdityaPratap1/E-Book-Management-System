package signUp;

import LogIn.session.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import database.DBSignUpController;
import database.database.vo.UserSignUpVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;
import java.sql.Date;
import java.time.ZoneId;


public class SignupScreenController implements Initializable {

    @FXML
    private JFXTextField signupUsername;

    @FXML
    private JFXTextField firstNameText, lastNameText, gradeText, studentIdText, usernameText, passwordText, confirmPasswordText, emaddressText;

    @FXML
    private JFXButton signUpButton;

    @FXML
    private JFXComboBox chooseRoleText;

    @FXML
    private JFXDatePicker datePicker;

    @FXML private JFXButton cancelbutton;

    @FXML private Label lblUsernameWarning;

    String fname, lname, grade, studentId, username, password, email, role;
    int redemptionCode;
    String birthDate;


    public void signUpUserDatabase(javafx.event.ActionEvent event) {

        if(checkNullFields()){
            lblUsernameWarning.setText("Check Empty Fields");
        }

        else {
                System.out.println("Date" + datePicker.getValue());
                fname = firstNameText.getText();
                lname = lastNameText.getText();
                grade = gradeText.getText();
                studentId = studentIdText.getText();
                username = usernameText.getText();
                password = passwordText.getText();
                email = emaddressText.getText();
                role = chooseRoleText.getValue().toString();
                redemptionCode = generateRedemptionCode();
                birthDate = datePicker.getValue().toString();

                UserSignUpVO object = new UserSignUpVO(fname, lname, grade, studentId, username, password, role, email, redemptionCode, birthDate);
                System.out.println(object.getSignUsername());


                DBSignUpController objectdbsc = new DBSignUpController();
                if (objectdbsc.userExists(username)) {

                    usernameText.setFocusColor(Color.RED);
                    usernameText.setUnFocusColor(Color.RED);
                    usernameText.setStyle("-fx-prompt-text-fill: RED");

                    usernameText.textProperty().addListener(((observableValue, s, t1) -> {
                        usernameText.setStyle("-fx-prompt-text-fill: WHITE");
                        usernameText.setStyle("-fx-prompt-text-fill: WHITE");
                        usernameText.setUnFocusColor(Color.WHITE);
                        usernameText.setUnFocusColor(Color.WHITE);
                    }));
                    lblUsernameWarning.setText("Username Taken");

                } else if (!passwordText.getText().equals(confirmPasswordText.getText())) {

                    passwordText.setFocusColor(Color.RED);
                    passwordText.setUnFocusColor(Color.RED);

                    confirmPasswordText.setFocusColor(Color.RED);
                    confirmPasswordText.setUnFocusColor(Color.RED);

                    confirmPasswordText.textProperty().addListener(((observableValue, oldValue, newValue) -> {
                        confirmPasswordText.setStyle("-fx-prompt-text-fill: WHITE");
                        confirmPasswordText.setStyle("-fx-prompt-text-fill: WHITE");
                        confirmPasswordText.setUnFocusColor(Color.WHITE);
                        confirmPasswordText.setUnFocusColor(Color.WHITE);
                        passwordText.setStyle("-fx-prompt-text-fill: WHITE");
                        passwordText.setStyle("-fx-prompt-text-fill: WHITE");
                        passwordText.setUnFocusColor(Color.WHITE);
                        passwordText.setUnFocusColor(Color.WHITE);

                    }));


                    lblUsernameWarning.setText("Password Mismatch");
                } else {
                    objectdbsc.signUp(object);

                    if (role.equals("Manager")) {
                        objectdbsc.saveManagerSignUpInformation(object);
                    }

                    if (role.equals("Student")) {
                        objectdbsc.saveStudentSignUpInformation(object);

                    }

                    returnToLoginScreen();

                }
        }
        }
    public Date getBirthDate(JFXDatePicker datePicker){

        java.util.Date dt= java.util.Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
        return sqlDate;
    }
    public void cancelReturn(javafx.event.ActionEvent event){

        returnToLoginScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseRoleText.setItems(FXCollections.observableArrayList("Manager", "Student"));

    }

    public void returnToLoginScreen(){

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/LogIn/login_screen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage prevStage = (Stage) this.signUpButton.getScene().getWindow();
        prevStage.close();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("LogIn Screen");
        Scene scene = new Scene(root, 600, 320);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public int generateRedemptionCode() {
        Random rnd = new Random();
        int redemptionCode = 100000 + rnd.nextInt(900000);
        System.out.println("*********** Redemption code " + redemptionCode);
        return redemptionCode;
    }

    public boolean checkNullFields() {

        if (firstNameText.getText() == null || lastNameText.getText() == null || datePicker.getValue() == null || usernameText.getText() == null || passwordText.getText() == null || confirmPasswordText.getText() == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkRequiredFields(){

        boolean bool = false;

        if((chooseRoleText.getValue().toString().equals("Student") && (gradeText.getText() == null || studentIdText.getText() == null))||(chooseRoleText.getValue().toString().equals("Student") &&(gradeText.getText() == null && studentIdText.getText() == null))){
            bool = false;        }

        else {
            bool =  true;
        }

        return bool;
    }

}
