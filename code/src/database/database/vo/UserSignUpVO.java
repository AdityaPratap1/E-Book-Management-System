package database.database.vo;
import java.sql.Date;
public class UserSignUpVO {

    String fname, lname, grade, studentID, signUsername, signPassword, role, emailAdderss, birthDate;
    int userID, redemptionCode;

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }


    public int getRedemptionCode() {
        return redemptionCode;
    }

    public void setRedemptionCode(int redemptionCode) {
        this.redemptionCode = redemptionCode;
    }

    public UserSignUpVO(String fname, String lname, String grade, String studentID, String signUsername, String signPassword, String role, String emailAdderss, int redemptionCode, String birthDate) {
        this.fname = fname;
        this.lname = lname;
        this.grade = grade;
        this.studentID = studentID;
        this.signUsername = signUsername;
        this.signPassword = signPassword;
        this.role = role;
        this.emailAdderss = emailAdderss;
        this.userID = userID;
        this.redemptionCode = redemptionCode;
        this.birthDate=birthDate;
    }

    public UserSignUpVO(){

    }


    public  String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getSignUsername() {
        return signUsername;
    }

    public void setSignUsername(String signUsername) {
        this.signUsername = signUsername;
    }

    public String getSignPassword() {
        return signPassword;
    }

    public void setSignPassword(String signPassword) {
        this.signPassword = signPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmailAdderss() {
        return emailAdderss;
    }

    public void setEmailAdderss(String emailAdderss) {
        this.emailAdderss = emailAdderss;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
