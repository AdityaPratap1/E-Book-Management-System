package Student;

import LogIn.Main;
import LogIn.session.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import database.DBBookController;
import database.DBController;
import database.database.vo.BookVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.event.ActionEvent;
import java.awt.print.Book;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class StudentScreenController implements Initializable {

    @FXML
    private JFXTextField txtSearchBook;
    @FXML
    private JFXComboBox searchBy;

    @FXML
    private TableView<BookVO> tableView;
    @FXML
    private TableColumn<BookVO, String> bookTitleCol;
    @FXML
    private TableColumn<BookVO, String> authorFirstNameCol;
    @FXML
    private TableColumn<BookVO, String> authorLastNameCol;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnProfile;
    @FXML
    private Label lblRedemptionCode;

    public String Lname;
    private static StudentScreenController globalController;
    private static BookVO objectBVO;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblRedemptionCode.setText(String.valueOf(getStudentRedemptionCode(UserSession.getUserLoginVO().getUserId())));
        lblRedemptionCode.setStyle("-fx-text-fill: GREEN");
        DBBookController object = new DBBookController();
        ObservableList<BookVO> bookList =  object.getBookList();
        bookTitleCol.setCellValueFactory(cellData -> cellData.getValue().getBookTitle());
        authorFirstNameCol.setCellValueFactory(cellData -> cellData.getValue().getAuthorFirstName());
        authorLastNameCol.setCellValueFactory(cellData -> cellData.getValue().getAuthorLastName());
        populateTable(bookList); //populates data in tableview
        tableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                displayBookDetailWindow(tableView.getSelectionModel().getSelectedItem());
            }
        });

        searchBook();

    }

    private void populateTable(ObservableList<BookVO> bookList){
        tableView.setItems(bookList);

    }

    private void searchBook(){ // Filter Function

        ObservableList data =  tableView.getItems();
        txtSearchBook.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                tableView.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<BookVO> subentries = FXCollections.observableArrayList();

            long count = tableView.getColumns().stream().count();
            for (int i = 0; i < tableView.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + tableView.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(tableView.getItems().get(i));
                        break;
                    }
                }
            }
            tableView.setItems(subentries);
        });
    }

    public void onMyProfileAction(javafx.event.ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Student/student_profile.fxml"));
            AnchorPane root = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("My Profile");
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Stage prevStage = (Stage) this.btnProfile.getScene().getWindow();
            prevStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void displayBookDetailWindow(BookVO selectedBook){

     Lname = selectedBook.getLname();
     System.out.println("display method " + Lname);
     objectBVO = selectedBook;
     UserSession.setBookVO(selectedBook);
     openIssueBookScreen();

     }



     public static BookVO getSelectedBook(){
        return objectBVO;
     }

     public void openIssueBookScreen(){
         try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Student/student_issue_book.fxml"));
             AnchorPane root2 = null;
             root2 = (AnchorPane) fxmlLoader.load();
             Stage stage = new Stage();
             stage.setTitle("Student Dashboard");
             Scene scene = new Scene(root2, 600, 450);
             stage.setScene(scene);
             stage.setResizable(false);
             stage.show();
             Stage prevStage = (Stage) this.btnLogOut.getScene().getWindow();
             prevStage.close();

         } catch (IOException e) {
             e.printStackTrace();
         }
     }

    public void onLogoutAction(javafx.event.ActionEvent event){

            Stage prevStage = (Stage) this.btnLogOut.getScene().getWindow();
            prevStage.close();

    }


    public void onIssuedBookAction(javafx.event.ActionEvent event){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Student/student_issued_books.fxml"));
            AnchorPane root2 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Issued Books Screen");
            Scene scene = new Scene(root2, 600, 400);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Stage prevStage = (Stage) this.btnLogOut.getScene().getWindow();
            prevStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
     }

     public int getStudentRedemptionCode(int userId) {

         DBController dbController = new DBController();
         int code = dbController.retriveStudentRedemptionCode(userId);
         UserSession.getUserLoginVO().setRedemptionCode(code);
         return code;

     }



    }

