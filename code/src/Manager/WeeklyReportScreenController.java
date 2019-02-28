package Manager;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import database.DBManagerController;
import database.DBStudentProfileController;
import database.database.vo.BookVO;
import database.database.vo.StudentProfileVO;
import database.database.vo.WeeklyReportVO;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WeeklyReportScreenController implements Initializable {

    @FXML
    private JFXButton btnUserList;
    @FXML
    private TableColumn<WeeklyReportVO, String> studentNameCol;

    @FXML
    private TableColumn<WeeklyReportVO, String> bookTitleCol;

    @FXML
    private TableColumn<WeeklyReportVO, String> issueDateCol;

    @FXML
    private TableView<WeeklyReportVO> weeklyReportTableView;
    @FXML
    private JFXTextField dateFilter;


    public void onViewUserList(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Manager/manager_screen.fxml"));
            AnchorPane root2 = null;
            root2 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("User List");
            Scene scene = new Scene(root2, 600, 400);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Stage prevStage = (Stage) this.btnUserList.getScene().getWindow();
            prevStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DBManagerController dbManagerController = new DBManagerController();// DB Student Profile Controller has all infor related to a student
        ObservableList<WeeklyReportVO> studentsList = dbManagerController.getWeeklyStudentBookIssueInformation();
        studentNameCol.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
        bookTitleCol.setCellValueFactory(cellData -> cellData.getValue().bookTitleProperty());
        issueDateCol.setCellValueFactory(cellData -> cellData.getValue().issueDateProperty());
        populateTable(studentsList);
        searchData();

    }

    private void searchData(){

        ObservableList data =  weeklyReportTableView.getItems();
        dateFilter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                weeklyReportTableView.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<WeeklyReportVO> subentries = FXCollections.observableArrayList();

            long count = weeklyReportTableView.getColumns().stream().count();
            for (int i = 0; i < weeklyReportTableView.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + weeklyReportTableView.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(weeklyReportTableView.getItems().get(i));
                        break;
                    }
                }
            }
            weeklyReportTableView.setItems(subentries);
        });
    }



    private void populateTable(ObservableList<WeeklyReportVO> studentsList){
        weeklyReportTableView.setItems(studentsList);

    }
    }

