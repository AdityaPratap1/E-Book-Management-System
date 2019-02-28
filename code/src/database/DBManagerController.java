package database;

import database.database.vo.StudentProfileVO;
import database.database.vo.WeeklyReportVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static database.DbConnectionManager.getDbConnection;


public class DBManagerController {
    private static Connection dbConn;
    public static PreparedStatement preStatement;
    public static ResultSet resultSet;
    private static String weeklyBookIssueSelectQuery = "SELECT a.user_first_name, a.user_last_name, a.user_id, b.book_title, c.book_issue_date FROM ebm_user a, ebm_book b, ebm_bookissue c WHERE  a.user_id = c.user_id AND b.book_id = c.book_id";


    public void getConnection() {
        dbConn = getDbConnection();
    }

    public ObservableList<WeeklyReportVO> getWeeklyStudentBookIssueInformation() {
        getConnection();
        ObservableList<WeeklyReportVO> studentBookIssueList = FXCollections.observableArrayList();

        try {
            preStatement = dbConn.prepareStatement(weeklyBookIssueSelectQuery);
            resultSet = preStatement.executeQuery();

            while (resultSet.next()) {
                WeeklyReportVO weeklyReportVO1 = new WeeklyReportVO();
                weeklyReportVO1.setFirstName(resultSet.getString("user_first_name"));
                weeklyReportVO1.setLastName(resultSet.getString("user_last_name"));
                weeklyReportVO1.setBookTitle(resultSet.getString("book_title"));
                weeklyReportVO1.setIssueDate(resultSet.getString("book_issue_date"));
                weeklyReportVO1.setFullName(weeklyReportVO1.getFirstName() + ", " + weeklyReportVO1.getLastName());
                studentBookIssueList.add(weeklyReportVO1);

            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return studentBookIssueList;
    }
}


