package com.epam.brest;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class dbUtilsTest {

    @Test
    public void getConnection() throws SQLException, ClassNotFoundException {
    dbUtils conn= new dbUtils();
    assertNotNull(conn.getConnection());
    }
}