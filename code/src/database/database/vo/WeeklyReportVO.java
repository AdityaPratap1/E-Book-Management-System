package database.database.vo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WeeklyReportVO {

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getIssueDate() {
        return issueDate.get();
    }

    public StringProperty issueDateProperty() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate.set(issueDate);
    }


    public String getFullName() {
        return fullName.get();
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }



    public String getBookTitle() {
        return bookTitle.get();
    }

    public StringProperty bookTitleProperty() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle.set(bookTitle);
    }

    private StringProperty fullName;

    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty bookTitle;
    private StringProperty issueDate;


    public WeeklyReportVO(){
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.issueDate = new SimpleStringProperty();
        this.fullName = new SimpleStringProperty();
        this.bookTitle = new SimpleStringProperty();
    }


}
