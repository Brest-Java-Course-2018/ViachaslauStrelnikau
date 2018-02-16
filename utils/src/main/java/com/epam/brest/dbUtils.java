package com.epam.brest;

import java.sql.*;

public class dbUtils {
    //connect to DB
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String batabaseURL="jdbc:h2:mem:test_db;DB_CLOSE_DELAY=-1";
        Class.forName("org.h2.Driver");
        Connection connection= DriverManager.getConnection(batabaseURL,"sa","");
        return connection;
    }
    //create table users
    public void createUsertable(Connection connection) throws SQLException {
        String create_table=
                "CREATE TABLE app_user(" +
                        "user_id INT NOT NULL AUTO_INCREMENT," +
                        "login VARCHAR (255) NOT NULL ," +
                        "password VARCHAR (255) NOT NULL ," +
                        "description VARCHAR (255) NULL," +
                        "PRIMARY KEY (user_id))";
        try(
        Statement statement=connection.createStatement()) {
            statement.executeUpdate(create_table);
        }
        System.out.println("Table app_user seccesfuly created");
    }
    // Addes new user
    public void addUser(Connection connection, String LOGIN, String PASSWORD, String DESCRIPTION) throws SQLException
    {
        String new_user="insert into app_user (login,password,description) values(?,?,?)";

        PreparedStatement statement =connection.prepareStatement(new_user);
        statement.setString(1,LOGIN);
        statement.setString(2,PASSWORD);
        statement.setString(3,DESCRIPTION);
        statement.executeUpdate();
        System.out.println( String.format( "User seccesfuly added :%s %s",LOGIN,DESCRIPTION));
    }
// Shows all users
    public void getUsers(Connection connection) throws SQLException {
        String select_all_user=
                "select user_id, login, description from app_user ";
        Statement statement=connection.createStatement();
        ResultSet records;
        records=statement.executeQuery (select_all_user);
        while(records.next())
        {
            System.out.println(String.format("User:%s Login:%s Discription:%s",
                    records.getString("user_id"),records.getString("login"),records.getString("description")));
        }
    }
}
