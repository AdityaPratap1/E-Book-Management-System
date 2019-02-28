package Student;

import LogIn.session.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DBBookController;
import database.database.vo.BookVO;
import database.database.vo.UserBookVO;
import database.database.vo.UserLoginVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class StudentIssueBookController implements Initializable {

    @FXML
    private JFXButton btnViewBookList;

    @FXML
    private  JFXButton btnIssueBook;

    @FXML private Label lblTitle;

    @FXML private JFXTextField lblFName;

    @FXML private JFXTextField lblLName;

    @FXML private JFXTextField lblISBN;

    @FXML private JFXTextField lblPublisher;

    @FXML private JFXTextField lblGenre;

    @FXML private Label lblBookStatus;
    @FXML private JFXTextField lblRedemption;
    @FXML private Label lblBookIssued;




    StudentScreenController object = new StudentScreenController();
    DBBookController dbBookController = new DBBookController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BookVO bookVO = StudentScreenController.getSelectedBook();
        System.out.println(bookVO.getLname());
        populateBookDetails(bookVO);
    }

    public void onIssueBookAction(ActionEvent event) {

        UserBookVO userBookVO = new UserBookVO();
        userBookVO.setUserId(UserSession.getUserLoginVO().getUserId());
        userBookVO.setBookId(UserSession.getBookVO().getBId());
        userBookVO.setIssueDate(LocalDate.now().toString());
        dbBookController.insertStudentbookIssue(userBookVO);
        lblBookIssued.setText("BOOK SUCCESSFULLY ISSUED");


    }

    public void onBookScreenAction(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Student/student_screen.fxml"));
            AnchorPane root2 =  (AnchorPane) fxmlLoader.load();
            System.out.println(root2.getChildren().get(1).toString());
            Stage stage = new Stage();
            stage.setTitle("Student Dashboard");
            Scene scene = new Scene(root2, 600, 400);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Stage prevStage = (Stage) this.btnViewBookList.getScene().getWindow();
            prevStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void populateBookDetails(BookVO objectBVO){
        lblTitle.setText(objectBVO.getBook());
        lblFName.setText(objectBVO.getFname());
        lblLName.setText(objectBVO.getLname());
        lblISBN.setText(objectBVO.getBIsbnNumber());
        lblPublisher.setText(objectBVO.getBPublisher());
        lblGenre.setText(objectBVO.getBGenre());
        lblRedemption.setText(String.valueOf(UserSession.getUserLoginVO().getRedemptionCode()));

        if(objectBVO.getBIssueStatus().equalsIgnoreCase("issued")){
            btnIssueBook.setVisible(false);
            lblBookStatus.setText("Book Not Available (Already issued by you or someone else)");
        }


    }

}
