package com.epam.brest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest 

{
   private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
   // setup out stream to new created stream
    @Before
    public void testSetup() {

        System.setOut(new PrintStream(outContent));
    }
    // restore out stream to console
    @After
    public void RestoreStream()
    {
        System.setOut(System.out);
    }

    // run test that checked equlity of out text and expected text
    @Test
    public void testApp() throws SQLException, ClassNotFoundException {
        String [] mas={""};
        App.main(mas);
        // get output from main class
        String res =outContent.toString();
        assertEquals ("Hello World!\n",res);

    }
}
