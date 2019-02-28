package database;

import database.database.vo.UserSignUpVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static database.DbConnectionManager.getDbConnection;


public class DBSignUpController {

    private static Connection dbConn;
    private String userQuery = "INSERT INTO ebm_user(user_first_name, user_last_name, user_type, user_email, user_dob) VALUES(?, ?, ?, ?, ?)";
    private String userStudentQuery = "INSERT INTO ebm_student(student_grade, student_code, user_id, student_redemption_code) VALUES(";
    private String userManagerQuery = "INSERT INTO ebm_manager(manager_fname, manager_lname, user_id) VALUES(";
    private String userIdSelecttQuery = "SELECT a.user_id FROM ebm_user a WHERE a.user_first_name = ";
    private String userLoginQuery = "INSERT INTO ebm_userlogin(user_login_name, user_login_password, user_id) VALUES(";
    public static PreparedStatement preStatement;
    public static ResultSet resultSet;



    public UserSignUpVO signUp(UserSignUpVO object) { //Comment

        getConnection();
        saveSignUpInformation(object);
        return object;
    }

    public void getConnection() {
        dbConn = getDbConnection();
    }

    public UserSignUpVO saveSignUpInformation(UserSignUpVO object){


       //String userQuery2 = userQuery + "\'" + object.getFname() + "\'" + ",\'" + object.getLname() + "\'" + ",\'" + object.getRole() + "\'" + ",\'" + object.getEmailAdderss() + "\')";
      // System.out.println(userQuery2);

       String userIdSelectQuery2 = userIdSelecttQuery  + "\'" + object.getFname() + "\'";


        try {
            preStatement= dbConn.prepareStatement(userQuery);
            preStatement.setString(1, object.getFname());
            preStatement.setString(2, object.getLname());
            preStatement.setString(3, object.getRole());
            preStatement.setString(4, object.getEmailAdderss());
            preStatement.setString(5, object.getBirthDate());
            int x = preStatement.executeUpdate();

            preStatement = dbConn.prepareStatement(userIdSelectQuery2);
            resultSet = preStatement.executeQuery();

            int y = 0;

            while(resultSet.next()){
                 y = resultSet.getInt(1);
                 System.out.println(y);
            }

            System.out.println(y);

            object.setUserID(y);

            String userLoginQuery2 = userLoginQuery + "\'" + object.getSignUsername() + "\'" + ",\'" + object.getSignPassword() + "\'" + ",\'" + object.getUserID() + "\')";


            preStatement = dbConn.prepareStatement(userLoginQuery2);
            x = preStatement.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }

       // System.out.println(userQuery2);

       return object;
    }

    public void saveStudentSignUpInformation(UserSignUpVO object){

        String userStudentQuery2 = userStudentQuery + "\'" + object.getGrade() + "\'" + ",\'" + object.getStudentID() + "\'" + ",\'" + object.getUserID() + "\'" +",\'" + object.getRedemptionCode() + "\')";
        try {
            preStatement= dbConn.prepareStatement(userStudentQuery2);
            int x = preStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveManagerSignUpInformation(UserSignUpVO object){

        String userManagerQuery2 = userManagerQuery + "\'" + object.getFname() + "\'" + ",\'" + object.getLname() + "\'" + ",\'" + object.getUserID() +  "\')";
        try {
            preStatement= dbConn.prepareStatement(userManagerQuery2);
            int x = preStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public boolean userExists(String object){
        getConnection();
        String UserNameQuery = "select user_login_name from ebm_userlogin where user_login_name=?";
        boolean userExistFlag=false;
        try {
            preStatement = dbConn.prepareStatement(UserNameQuery);
            preStatement.setString(1, object);

            resultSet = preStatement.executeQuery();

           while (resultSet.next()) {
               String name=resultSet.getString(1);
                if(object.equalsIgnoreCase(name)) userExistFlag=true;

           }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return userExistFlag;
    }
}