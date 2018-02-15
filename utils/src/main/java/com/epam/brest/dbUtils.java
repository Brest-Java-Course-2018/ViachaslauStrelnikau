package com.epam.brest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbUtils {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String batabaseURL="jdbc:h2:mem:test_db;DB_CLOSE_DELAY=-1";
        Class.forName("org.h2.Driver");
        Connection connection= DriverManager.getConnection(batabaseURL,"sa","");
        return connection;
    }
}
