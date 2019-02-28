package database.database.vo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserVO {

    private IntegerProperty user_id;
    private StringProperty user_first_name;
    private StringProperty user_last_name;
    private StringProperty user_dob;
    private StringProperty user_type;
    private StringProperty user_email;

    public UserVO(){
        this.user_id = new SimpleIntegerProperty();
        this.user_first_name = new SimpleStringProperty();
        this.user_last_name = new SimpleStringProperty();
        this.user_dob = new SimpleStringProperty();
        this.user_type = new SimpleStringProperty();
        this.user_email = new SimpleStringProperty();

    }

    public int getUser_id() {
        return user_id.get();
    }

    public IntegerProperty get_user_idProperty() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id.set(user_id);
    }

    public String getUser_first_name() {
        return user_first_name.get();
    }

    public StringProperty get_user_first_nameProperty() {
        return user_first_name;
    }

    public void setUser_first_name(String user_first_name) {
        this.user_first_name.set(user_first_name);
    }

    public String getUser_last_name() {
        return user_last_name.get();
    }

    public StringProperty get_user_last_nameProperty() {
        return user_last_name;
    }

    public void setUser_last_name(String user_last_name) {
        this.user_last_name.set(user_last_name);
    }

    public String getUser_dob() {
        return user_dob.get();
    }

    public StringProperty get_user_dobProperty() {
        return user_dob;
    }

    public void setUser_dob(String user_dob) {
        this.user_dob.set(user_dob);
    }

    public String getUser_type() {
        return user_type.get();
    }

    public StringProperty get_user_typeProperty() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type.set(user_type);
    }

    public String getUser_email() {
        return user_email.get();
    }

    public StringProperty get_user_emailProperty() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email.set(user_email);
    }
}
