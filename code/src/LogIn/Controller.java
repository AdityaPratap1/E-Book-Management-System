package LogIn;

import LogIn.session.UserSession;
import com.jfoenix.controls.*;
import database.DBBookController;
import database.DBController;
import database.database.vo.BookVO;
import database.database.vo.UserLoginVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.scene.control.TableView;
import javafx.util.Callback;


public class Controller implements Initializable {

    @FXML
    private JFXButton signupbutton;

    @FXML
    private JFXButton loginbutton;

    @FXML
    private JFXTextField loginUsername;

    @FXML
    private JFXTextField signupUsername;

    @FXML
    private JFXPasswordField loginPassword;


    UserLoginVO objectULVO;
    int redemptionCode;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void signUpUser(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/signUp/signup_screen.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Sign Up");
            stage.setScene(new Scene(root1, 600, 450));
            stage.setResizable(false);
            String username = this.loginUsername.getText();
            signupUsername = (JFXTextField) root1.getChildren().get(5);
            signupUsername.setText(username);
            stage.show();

            Stage prevStage = (Stage) this.signupbutton.getScene().getWindow();
            prevStage.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void textEnterUser(ActionEvent event) {
        loginUsername.setUnFocusColor(Color.WHITE);
        loginPassword.setUnFocusColor(Color.WHITE);

        loginUsername.setFocusColor(Color.WHITE);
        loginPassword.setFocusColor(Color.WHITE);
    }

    public void loginUser(ActionEvent event) {

        if (validateLogin(loginUsername.getText(), loginPassword.getText())) {
            //setting the UserLoginVO object in the userSession

            UserSession.setUserLoginVO(objectULVO);
            loginUsername.setUnFocusColor(Color.GREEN);
            loginPassword.setUnFocusColor(Color.GREEN);
            loginUsername.setFocusColor(Color.GREEN);
            loginPassword.setFocusColor(Color.GREEN);

            if (objectULVO.getUserType().equals("Student")) {
                openStudentWindow();
            }

            if (objectULVO.getUserType().equals("Manager")) {
                openManagerWindow();
            }

        } else {
            loginUsername.setUnFocusColor(Color.RED);
            loginPassword.setUnFocusColor(Color.RED);

            loginUsername.setFocusColor(Color.RED);
            loginPassword.setFocusColor(Color.RED);

            loginPassword.setStyle("-fx-prompt-text-fill: RED");
            loginUsername.setStyle("-fx-prompt-text-fill: RED");

            loginUsername.textProperty().addListener((observable, oldValue, newValue) -> {
                loginPassword.setStyle("-fx-prompt-text-fill: WHITE");
                loginUsername.setStyle("-fx-prompt-text-fill: WHITE");
                loginUsername.setUnFocusColor(Color.WHITE);
                loginPassword.setUnFocusColor(Color.WHITE);
            });
        }
    }


    public boolean validateLogin(String username, String password) {
        boolean bool = true;
        DBController object = new DBController();
        objectULVO = object.validateLogin(username, password);
        if (objectULVO == null || (!(objectULVO.getPassword().equals(loginPassword.getText())))) {
            System.out.println("INVALID CREDENTIALS");
            bool = false;
        }
        return bool;
    }

    public void openManagerWindow() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Manager/manager_screen.fxml"));
            AnchorPane root2 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Manager Dashboard");
            stage.setScene(new Scene(root2, 600, 400));
            //this.ttview = (JFXTreeTableView) root2.getChildren().get(0);
            // String x = ttview.getTreeItem(0).toString();
            stage.setResizable(false);
            stage.show();
            Stage prevStage = (Stage) this.loginbutton.getScene().getWindow();
            prevStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openStudentWindow() {


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
            Stage prevStage = (Stage) this.loginbutton.getScene().getWindow();
            prevStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setTableColumn(TableView<BookVO> table) {
        System.out.println("SET BOOK TITLE METHOD");
        TableColumn<BookVO, String> bookTitleCol = new TableColumn<BookVO, String>("Books Title");
        bookTitleCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BookVO, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<BookVO, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row

                //System.out.println("*******" + p.getValue().getBookTitle());
                return p.getValue().getBookTitle();
            }
        });


        table.getColumns().setAll(bookTitleCol);
    }



}
