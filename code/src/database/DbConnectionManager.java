package database;

import java.sql.*;

public class DbConnectionManager {

        public static Connection connObj;
        public static PreparedStatement preStatement;
        public static ResultSet resultSet;
        //public static String JDBC_URL = "jdbc:sqlite:C:\\Users\\Aditya Pratap\\Desktop\\EbookMSystem\\src\\ebook_database.db";
        public static String JDBC_URL = "jdbc:sqlite::resource:ebook_database.db";

    public static Connection getDbConnection() {
        Connection conn = null;
        try {
            // db parameters
            conn = DriverManager.getConnection(JDBC_URL);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return conn;
    }
    }

