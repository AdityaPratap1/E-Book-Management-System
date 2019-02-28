package database.database.vo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class BookVO {

    private StringProperty bookTitle;
    private StringProperty authorFirstName;
    private StringProperty authorLastName;
    private StringProperty bookPublisher;
    private StringProperty bookIsbnNumber;
    private StringProperty bookGenre;
    private StringProperty bookIssueStatus;
    private IntegerProperty bookId;

    public BookVO() {
        this.bookTitle= new SimpleStringProperty();
        this.authorFirstName = new SimpleStringProperty();
        this.authorLastName = new SimpleStringProperty();
        this.bookPublisher = new SimpleStringProperty();
        this.bookIsbnNumber = new SimpleStringProperty();
        this.bookGenre = new SimpleStringProperty();
        this.bookIssueStatus = new SimpleStringProperty();
        this.bookId = new SimpleIntegerProperty();

    }

    //public void setBookTitle(String value) { bookTitleProperty().set(value); }
    public void setBookTitle(String title) {this.bookTitle.set(title); }
    public StringProperty getBookTitle() { return this.bookTitle; }
    public String getBook(){return bookTitle.get();}

    public void setAuthorFirstName(String fname){authorFirstName.set(fname);}
    public StringProperty getAuthorFirstName() {return this.authorFirstName;}
    public String getFname(){return authorFirstName.get();}

    public void setAuthorLastName(String lname){authorLastName.set(lname);}
    public StringProperty getAuthorLastName() {return this.authorLastName;}
    public String getLname(){return authorLastName.get();}


    public void setBookPublisher(String bPublisher){bookPublisher.set(bPublisher);}
    public StringProperty getBookPublisher() {return this.bookPublisher;}
    public String getBPublisher(){return bookPublisher.get();}


    public void setBookIsbnNumber(String bIsbnNumber){bookIsbnNumber.set(bIsbnNumber);}
    public StringProperty getBookIsbnNumber() {return this.bookIsbnNumber;}
    public String getBIsbnNumber(){return bookIsbnNumber.get();}


    public void setBookGenre(String bGenre){bookGenre.set(bGenre);}
    public StringProperty getBookGenre() {return this.bookGenre;}
    public String getBGenre(){return bookGenre.get();}

    public void setBookIssueStatus(String bStatus){bookIssueStatus.set(bStatus);}
    public StringProperty getBookIssueStatus() {return this.bookIssueStatus;}
    public String getBIssueStatus(){return bookIssueStatus.get();}

    public void setBookId(int bId){bookId.set(bId);}
    public IntegerProperty getBookId() {return this.bookId;}
    public int getBId(){return bookId.get();}




}
