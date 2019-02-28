package Manager;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import database.DBBookController;
import database.DBController;
import database.DBStudentProfileController;
import database.database.vo.BookVO;
import database.database.vo.StudentProfileVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerScreenController implements Initializable {

    @FXML
    private JFXButton btnWeeklyIssueReport;
    @FXML
    private JFXButton btnMyProfile;
    @FXML
    private JFXButton btnLogout;

    @FXML
    private TableColumn<StudentProfileVO, String> studentFirstNameCol;

    @FXML
    private TableColumn<StudentProfileVO, String> studentLastNameCol;

    @FXML
    private TableColumn<StudentProfileVO, String> studentRedemptionCodeCol;

    @FXML
    private TableView<StudentProfileVO> tableView;

    @FXML
    private JFXTextField txtSearchStudent;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DBStudentProfileController dbStudentProfileController = new DBStudentProfileController();// DB Student Profile Controller has all infor related to a student
        ObservableList<StudentProfileVO> studentsList = dbStudentProfileController.getAllStudentsList();
        studentFirstNameCol.setCellValueFactory(cellData -> cellData.getValue().get_user_first_nameProperty());
        studentLastNameCol.setCellValueFactory(cellData -> cellData.getValue().get_user_last_nameProperty());
        studentRedemptionCodeCol.setCellValueFactory(cellData -> cellData.getValue().get_redemption_codeProperty());
        populateTable(studentsList);
        searchBook();

    }



    private void populateTable(ObservableList<StudentProfileVO> studentsList){
        tableView.setItems(studentsList);

    }

    private void searchBook(){

        ObservableList data =  tableView.getItems();
        txtSearchStudent.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                tableView.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<StudentProfileVO> subentries = FXCollections.observableArrayList();

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

    public void onWeeklyIssueAction(ActionEvent event){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Manager/weekly_report.fxml"));
            AnchorPane root2 = null;
            root2 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Weekly Issue Report");
            Scene scene = new Scene(root2, 600, 400);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Stage prevStage = (Stage) this.btnWeeklyIssueReport.getScene().getWindow();
            prevStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onLogoutAction(ActionEvent event){

            Stage prevStage = (Stage) this.btnLogout.getScene().getWindow();
            prevStage.close();
        }
    }



