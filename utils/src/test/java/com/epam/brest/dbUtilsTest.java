package com.epam.brest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class dbUtilsTest {
    dbUtils dbutils = new dbUtils();
    Connection connection;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    //--------------------------------------------------------------------

    @Before
    public void testAddUserSetup() throws SQLException, ClassNotFoundException {
        System.setOut(new PrintStream(outContent));
        connection = dbutils.getConnection();

    }

    // restore out stream to console
    @After
    public void testAddUserSetupEND() throws SQLException {
        System.setOut(System.out);
        connection.close();
    }
    //--------------------------------------------------------------------
    // tesing  getConnection method
    @Test
    public void getConnectionTEST() throws SQLException, ClassNotFoundException {
        //connection test
        connection = dbutils.getConnection();
        assertNotNull(connection);
    }

    //------------------------------------------------------------------------------


    // tesing  addUser method
    @Test
    public void AddUserTESTS() throws SQLException, ClassNotFoundException {


        dbutils.createUsertable(connection);
        String res = outContent.toString();
        outContent.reset();
        // tests Adding record
        dbutils.addUser(connection, "testL", "testP", "testD");
        // get output from addUser class
        res = outContent.toString();
        assertEquals("User seccesfuly added :testL testD\n", res);

        // tests Adding record with null connection
        try {
            dbutils.addUser(null, "testL", "testP", "testD");
        } catch (Exception e) {
            assertEquals(null, e.getMessage());
        }

    }
}