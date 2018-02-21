package com.epam.brest;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {

        System.out.println("Hello World!");

        dbUtils dbutils = new dbUtils();
        Connection con;
        con = dbutils.getConnection();
        dbutils.createUsertable(con);
        dbutils.addUser(con, "admin", "password", "cool admin");
        dbutils.addUser(con, "admin2", "password", "cool admin");
        dbutils.addUser(con, "admin3", "password", "cool admin");
        dbutils.getUsers(con);
        dbutils.deleteUser(con, 10);

    }
}
