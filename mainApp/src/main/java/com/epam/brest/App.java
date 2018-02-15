package com.epam.brest;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("Hello World!");
        dbUtils dbutils = new dbUtils();
        Connection con;
        con=dbutils.getConnection();
    }
}
