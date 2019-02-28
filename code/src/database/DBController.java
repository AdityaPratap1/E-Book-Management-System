package database;

import LogIn.session.UserSession;
import database.database.vo.UserLoginVO;

import java.sql.*;

import static database.DbConnectionManager.*;

public class DBController {

    private Connection dBConn = null;
    private String loginQuery = "SELECT a.user_login_name, a.user_login_password, b.user_type , a.user_id FROM ebm_userlogin a, ebm_user b WHERE a.user_id = b.user_id AND a.user_login_name = ";
    public static PreparedStatement preStatement;
    public static ResultSet resultSet;
    public UserLoginVO validateLogin(String username, String password){

        getConnection();
        UserLoginVO objectULVO =  executeLogInValidation(username);
        return objectULVO;
    }



    public void getConnection(){
        dBConn = getDbConnection();
    }

        public UserLoginVO executeLogInValidation(String username){
            UserLoginVO objectULVO = null;
            try {
                String query= loginQuery+"\'"+username+"\'";
                System.out.println("LoginQuery ="+query);
                preStatement= dBConn.prepareStatement(query);
                resultSet = preStatement.executeQuery();
                System.out.println(resultSet);

                if(resultSet.isBeforeFirst()){
                    System.out.println("in resultset");
                    objectULVO = new UserLoginVO();

                     ResultSetMetaData rmd = resultSet.getMetaData();

                        while(resultSet.next()){

                        int columnCount= rmd.getColumnCount();
                         objectULVO.setUsername(resultSet.getString(1));
                         objectULVO.setPassword(resultSet.getString(2));
                         objectULVO.setUserType(resultSet.getString(3));
                         objectULVO.setUserId(resultSet.getInt("user_id"));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objectULVO;

    }

    public int retriveStudentRedemptionCode(int userId)  {
        getConnection();
        int code = 0;
        String redemptionCodeQuery = "SELECT student_redemption_code From ebm_student WHERE user_id = ?";
        try {
            preStatement = dBConn.prepareStatement(redemptionCodeQuery);
            preStatement.setInt(1, userId);
            resultSet = preStatement.executeQuery();
            while (resultSet.next()){
                try {
                    code = resultSet.getInt("student_redemption_code");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return code;
    }
}
