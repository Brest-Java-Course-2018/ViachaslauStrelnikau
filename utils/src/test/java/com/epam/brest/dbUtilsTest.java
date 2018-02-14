package com.epam.brest;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class dbUtilsTest {

    @org.junit.Test
    public void getConnection() throws SQLException, ClassNotFoundException {
    dbUtils conn= new dbUtils();
    assertNotNull(conn.getConnection());
    }
}