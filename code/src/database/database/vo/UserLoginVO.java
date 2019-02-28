package database.database.vo;

public class UserLoginVO {

    String username;
    String password;
    String userType;
    String userStatus;
    int userLoginId;
    int userId;

    public int getRedemptionCode() {
        return redemptionCode;
    }

    public void setRedemptionCode(int redemptionCode) {
        this.redemptionCode = redemptionCode;
    }

    int redemptionCode;

    public UserLoginVO(String username, String password, String userType, String userStatus, int userLoginId, int userId) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.userStatus = userStatus;
        this.userLoginId = userLoginId;
        this.userId = userId;
    }

    public UserLoginVO() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public int getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(int userLoginId) {
        this.userLoginId = userLoginId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
