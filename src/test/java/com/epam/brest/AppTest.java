package com.epam.brest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */

    public void testApp()
    {
        String [] mas={""};
        //save console out
        PrintStream sysOut = System.out;
       // create and setup own output stream as a System.out stream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
// run App
        App.main(mas);
        // get output from main class
        String res =outContent.toString();
        // restore System.out
        System.setOut(sysOut);
// testing result

        assertEquals ("Hello World!\n",res);
    }

}
