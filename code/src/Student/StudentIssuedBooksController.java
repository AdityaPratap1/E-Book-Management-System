package Student;

import LogIn.session.UserSession;
import com.jfoenix.controls.JFXButton;
import database.DBBookController;
import database.database.vo.BookVO;
import database.database.vo.UserBookVO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.table.TableColumn;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentIssuedBooksController implements Initializable {
    @FXML
    private JFXButton btnViewBookList;

    @FXML
    private javafx.scene.control.TableColumn<UserBookVO, String> bookTitleColumn;

    @FXML
    private javafx.scene.control.TableColumn<UserBookVO, String> authorNameColumn;

    @FXML
    private TableView<UserBookVO> issuedBooksTableView;

    public void onViewBookListAction(javafx.event.ActionEvent event){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Student/student_screen.fxml"));
            AnchorPane root2 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Student DashBoard");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBBookController object = new DBBookController();
        ObservableList<UserBookVO> userBookList =  object.getUserBookList();
        bookTitleColumn.setCellValueFactory(cellData -> cellData.getValue().getBookTitle());
        authorNameColumn.setCellValueFactory(cellData -> cellData.getValue().getAuthorFirstName());
        populateTable(userBookList);
        issuedBooksTableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                displayReturnBookDetailWindow(issuedBooksTableView.getSelectionModel().getSelectedItem());
            }
        });
    }

    private void populateTable(ObservableList<UserBookVO> bookList){
        issuedBooksTableView.setItems(bookList);

    }

    public void displayReturnBookDetailWindow(UserBookVO selectedBook){

          UserSession.setUserBookVO(selectedBook);
          openReturnBookScreen();

    }

    public void openReturnBookScreen(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Student/student_return_book.fxml"));
            AnchorPane root2 = null;
            root2 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Return Book Screen");
            Scene scene = new Scene(root2, 600, 450);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Stage prevStage = (Stage) this.btnViewBookList.getScene().getWindow();
            prevStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
