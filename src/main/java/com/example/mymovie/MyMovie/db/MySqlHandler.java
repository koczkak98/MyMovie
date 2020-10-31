package com.example.mymovie.MyMovie.db;

import com.example.mymovie.MyMovie.model.User;

import java.sql.*;

public class MySqlHandler {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/movies?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PWD = "root";


    public User getUserById (int userID) throws SQLException {
        User user = new User();

        // Create DB Connection
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);

        // Prepare SQL Execution
        Statement stmt = conn.createStatement();

        String sqlSelectAll = "SELECT * FROM users";
        ResultSet rs = stmt.executeQuery(sqlSelectAll);

        // ON-DEMAND: Iterate over the result
        while(rs.next())
        {
            // userId Column
            int id = rs.getInt("userId");

            if (id == user.getUserID())
            {
                user.setUserName(rs.getNString("name"));
                user.addMovieID(rs.getInt("movieId"));

            }
        }

        // Close the ResultSet
        rs.close();
        // Close the Statement
        stmt.close();
        // Close the DB Connection
        conn.close();

        return user;
    }
}
