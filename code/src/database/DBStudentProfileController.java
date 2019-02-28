package database;

import LogIn.session.UserSession;
import database.database.vo.StudentProfileVO;
import database.database.vo.UserBookVO;
import database.database.vo.UserLoginVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static database.DbConnectionManager.getDbConnection;

public class DBStudentProfileController {
    private static Connection dbConn;
    private String studentProfileQuery = "SELECT a.user_first_name, a.user_last_name, a.user_dob, b.student_grade, b.student_redemption_code, b.student_code FROM ebm_user a, ebm_student b WHERE a.user_id = b.user_id AND a.user_id = ";
    private String userProfileUpdateQuery = "UPDATE ebm_user set user_first_name = ?, user_last_name = ? WHERE ebm_user.user_id = ?";
    private String studentProfileUpdateQuery = "UPDATE ebm_student set student_grade = ? , student_code = ? WHERE ebm_student.user_id = ?";
    private String userLoginUpdateQuery = "UPDATE ebm_userlogin set user_login_password = ? WHERE ebm_userlogin.user_id = ?";
    private String studentProfileListSelectQuery = "SELECT a.user_first_name, a.user_last_name, b.student_redemption_code FROM ebm_user a, ebm_student b WHERE a.user_id = b.user_id";
    public static PreparedStatement preStatement;
    public static ResultSet resultSet;

    public StudentProfileVO getStudentProfile(int userId) {

        StudentProfileVO studentProfileVO = null;
        getConnection();
        try {
            studentProfileVO = getStudentInformation(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(studentProfileVO.getUser_first_name());
        return studentProfileVO;

    }

    public void getConnection() {
        dbConn = getDbConnection();
    }

    public StudentProfileVO getStudentInformation(int userId) throws SQLException {

        String studentProfileQueryWithId = studentProfileQuery + "\'" + userId + "\'";

        System.out.println(studentProfileQueryWithId);

        try {
            preStatement = dbConn.prepareStatement(studentProfileQueryWithId);
            resultSet = preStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        StudentProfileVO studentProfileVO = new StudentProfileVO();

        while (resultSet.next()) {

            studentProfileVO.setStudent_grade(resultSet.getInt("student_grade"));
            studentProfileVO.setStudent_code(resultSet.getInt("student_code"));
            studentProfileVO.setRedemption_code(resultSet.getString("student_redemption_code"));
            studentProfileVO.setUser_first_name(resultSet.getString("user_first_name"));
            studentProfileVO.setUser_last_name(resultSet.getString("user_last_name"));
            studentProfileVO.setUser_dob(resultSet.getString("user_dob"));

        }

        return studentProfileVO;

    }

    public void updateStudentProfile(StudentProfileVO studentProfileVO) {


        getConnection();

        UserLoginVO userLoginVO = new UserLoginVO();

        try {
            preStatement = dbConn.prepareStatement(userProfileUpdateQuery);
            preStatement.setString(1, studentProfileVO.getUser_first_name());
            preStatement.setString(2, studentProfileVO.getUser_last_name());
            preStatement.setInt(3, UserSession.getUserLoginVO().getUserId());
            preStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

        try {
            preStatement = dbConn.prepareStatement(studentProfileUpdateQuery);
            preStatement.setInt(1, studentProfileVO.getStudent_grade());
            preStatement.setInt(2, studentProfileVO.getStudent_code());
            preStatement.setInt(3, UserSession.getUserLoginVO().getUserId());
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            preStatement = dbConn.prepareStatement(userLoginUpdateQuery);
            preStatement.setString(1, UserSession.getUserLoginVO().getPassword());
            preStatement.setInt(2, UserSession.getUserLoginVO().getUserId());
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public ObservableList<StudentProfileVO> getAllStudentsList() {
        getConnection();
        ObservableList<StudentProfileVO> allStudentsList = FXCollections.observableArrayList();

        try {
            preStatement = dbConn.prepareStatement(studentProfileListSelectQuery);
            resultSet = preStatement.executeQuery();

            while (resultSet.next()) {
                StudentProfileVO studentProfileVO = new StudentProfileVO();
                studentProfileVO.setUser_first_name(resultSet.getString("user_first_name"));
                studentProfileVO.setUser_last_name(resultSet.getString("user_last_name"));
                studentProfileVO.setRedemption_code(resultSet.getString("student_redemption_code"));
                allStudentsList.add(studentProfileVO);
            }
            }catch(SQLException e){
                e.printStackTrace();
            }
            return allStudentsList;
        }
    }
