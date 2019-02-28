package database;

import LogIn.session.UserSession;
import database.database.vo.BookVO;
import database.database.vo.UserBookVO;
import database.database.vo.UserLoginVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.sqlite.util.StringUtils;


import java.awt.print.Book;
import java.sql.*;

import static database.DbConnectionManager.getDbConnection;

public class DBBookController {

    private static Connection dbConn;
    private static String bookQuery = "SELECT * FROM ebm_book";
    private static String issuedBooksQuery = "SELECT a.book_id,b.user_id, b.book_issue_date , b.book_return_date FROM ebm_book a, ebm_bookissue b WHERE a.book_id = b.book_id AND a.book_id =  ";
    private static String userIssuedBooksQuery = "SELECT a.book_id, a.book_title, a.book_author_fname, a.book_author_lname, a.book_genre, a.book_publisher, a.book_isbn, b.book_issue_date FROM ebm_book a, ebm_bookissue b WHERE a.book_id = b.book_id and book_return_date is null AND b.user_id = ?";
    private static String issueBookInsertQuery = "INSERT INTO ebm_bookissue(user_id, book_id, book_issue_date) VALUES(?, ?, ?)";
    private static String returnBookUpdateQuery = "UPDATE ebm_bookissue set book_return_date = ? WHERE user_id = ? AND book_id = ?";
    public static PreparedStatement preStatement;
    private static ResultSet resultSet;
    private static ResultSetMetaData rsmd;

    public void getConnection() {
        dbConn = getDbConnection();
    }

    public ObservableList<BookVO>  getBookList(){

        ObservableList<BookVO> bookList = null;
        getConnection();
        try {
            bookList =  getBookInformation();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;    }



    public static ObservableList<BookVO> getBookInformation( ) throws ClassNotFoundException, SQLException{
        ObservableList<BookVO> bookList1 = null;

         try {
            preStatement= dbConn.prepareStatement(bookQuery);
            resultSet = preStatement.executeQuery();
            bookList1 = populateBookData(resultSet);

         }catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList1;

    }

    private static ObservableList<BookVO> populateBookData(ResultSet resultSet){

        ObservableList<BookVO> bookList = FXCollections.observableArrayList();

        try {
            while (resultSet.next()) {

                BookVO book = new BookVO();
                book.setBookId(resultSet.getInt("book_id"));
                book.setBookTitle(resultSet.getString("book_title"));
                book.setAuthorFirstName(resultSet.getString("book_author_fname"));
                book.setAuthorLastName(resultSet.getString("book_author_lname"));
                book.setBookPublisher(resultSet.getString("book_publisher"));
                book.setBookIsbnNumber(resultSet.getString("book_isbn"));
                book.setBookGenre(resultSet.getString("book_genre"));
                populateBookIssueStatus(book);
                bookList.add(book);
            }


        }catch (SQLException e){
            e.printStackTrace();       }

        return bookList;

    }

    public static void populateBookIssueStatus(BookVO book){
        String returnDateString = null;
        String issueDateString = null;
        try {
            preStatement= dbConn.prepareStatement(issuedBooksQuery + book.getBId());
            resultSet = preStatement.executeQuery();

            while (resultSet.next()) {
                returnDateString = resultSet.getString("book_return_date");
                issueDateString = resultSet.getString("book_issue_date");
            }


            if((null != issueDateString && (null == returnDateString || returnDateString.trim().length() == 0))){

                book.setBookIssueStatus("Issued");
                System.out.println("#### " + returnDateString);

            }

            else{

                book.setBookIssueStatus("Available");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /** THE FOLLOWING METHODS ARE USED FOR DISPLAYING BOOKS THAT THE STUDENT ISSUED **/

    public ObservableList<UserBookVO>  getUserBookList(){

        ObservableList<UserBookVO> bookList = null;
        getConnection();
        try {
            bookList =  getUserBookInformation();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }



    public static ObservableList<UserBookVO> getUserBookInformation( ) throws ClassNotFoundException, SQLException{

        ObservableList<UserBookVO> userBookList1 = null;

        try {
            preStatement= dbConn.prepareStatement(userIssuedBooksQuery);
            preStatement.setInt(1, UserSession.getUserLoginVO().getUserId());
            resultSet = preStatement.executeQuery();
            userBookList1 = populateUserBookData(resultSet);

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return userBookList1;

    }

    private static ObservableList<UserBookVO> populateUserBookData(ResultSet resultSet){

        ObservableList<UserBookVO> userBookList = FXCollections.observableArrayList();

        try {
            while (resultSet.next()) {

                UserBookVO book = new UserBookVO();
                //book.setUserId(resultSet.getInt("user_id"));
                book.setBookId(resultSet.getInt("book_id"));
                book.setBookTitle(resultSet.getString("book_title"));
                System.out.println("BOOK TITILE = " + resultSet.getString("book_title"));
                book.setAuthorFirstName(resultSet.getString("book_author_fname"));
                book.setAuthorLastName(resultSet.getString("book_author_lname"));
                book.setBookPublisher(resultSet.getString("book_publisher"));
                book.setBookIsbnNumber(resultSet.getString("book_isbn"));
                book.setBookGenre(resultSet.getString("book_genre"));
                //populateBookIssueStatus(book);
                userBookList.add(book);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }



        return userBookList;


    }

    public void insertStudentbookIssue(UserBookVO userBookVO){
        getConnection();
        try {
            preStatement= dbConn.prepareStatement(issueBookInsertQuery);
            preStatement.setInt(1, userBookVO.getUserId());
            preStatement.setInt(2, userBookVO.getBId());
            preStatement.setString(3, userBookVO.getIssueDate());
            preStatement.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateStudentBookReturn(UserBookVO userBookVO){
        getConnection();
        try {
            preStatement= dbConn.prepareStatement(returnBookUpdateQuery);
            preStatement.setString(1, userBookVO.getReturnDate());
            preStatement.setInt(2, userBookVO.getUserId());
            preStatement.setInt(3, userBookVO.getBId());
            preStatement.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
