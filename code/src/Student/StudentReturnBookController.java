package Student;

import LogIn.session.UserSession;
import com.jfoenix.controls.JFXTextField;
import database.DBBookController;
import database.database.vo.BookVO;
import database.database.vo.UserBookVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.sqlite.core.DB;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class StudentReturnBookController implements Initializable {

    @FXML
    private Label lblTitle;

    @FXML
    private JFXTextField lblFName;

    @FXML
    private JFXTextField lblLName;

    @FXML
    private JFXTextField lblISBN;

    @FXML
    private JFXTextField lblPublisher;

    @FXML JFXTextField lblGenre;

    @FXML Label lblReturned;



    public void onReturnBookAction(javafx.event.ActionEvent event){
     DBBookController dbBookController = new DBBookController();
     UserBookVO userBookVO = new UserBookVO();
    userBookVO.setUserId(UserSession.getUserLoginVO().getUserId());
    userBookVO.setBookId(UserSession.getUserBookVO().getBId());
    userBookVO.setReturnDate(LocalDate.now().toString());
    dbBookController.updateStudentBookReturn(userBookVO);
    lblReturned.setText("Book Returned");


    }

    public void onBookScreenAction(javafx.event.ActionEvent event){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Student/student_screen.fxml"));
            AnchorPane root2 = null;
            root2 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Student Dashboard");
            Scene scene = new Scene(root2, 600, 400);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Stage prevStage = (Stage) this.lblTitle.getScene().getWindow();
            prevStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        populateBookDetails(UserSession.getUserBookVO());

    }

    public void populateBookDetails(UserBookVO objectUBVO){
        lblTitle.setText(String.valueOf(objectUBVO.getBook()));
        lblFName.setText(objectUBVO.getFname());
       lblLName.setText((objectUBVO.getLname()));
       lblISBN.setText(objectUBVO.getBIsbnNumber());
       lblPublisher.setText(objectUBVO.getBPublisher());
       lblGenre.setText(objectUBVO.getBGenre());


    }
}
