package com.epam.brest.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

/**
 * Test Group class.
 */
public class IntervalTest {

    private static Date dateFrom;
    private static Date dateTo;
    private static String string;
    /**
     * testSetUp - test setUp method.
     */
    @Before
    public void testSetUp() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date dateF = simpleDateFormat.parse("31.05.1990");
        java.util.Date dateT = simpleDateFormat.parse("31.05.1999");
        dateFrom=new java.sql.Date(dateF.getTime());
        dateTo=new java.sql.Date(dateT.getTime());
        string="Interval{" +
                "dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';

    }
    /**
     * Method grouptest tests all methods of Interval class.
     */
    @Test
    public void intervaltest()
    {
        Interval interval = new Interval();
        Interval interval1 = new Interval(dateFrom,dateTo);
        interval.setDateFrom(dateFrom);
        interval.setDateTo(dateTo);

        Assert.assertEquals(interval1.getDateFrom(),dateFrom);
        Assert.assertEquals(interval1.getDateTo(),dateTo);

        Assert.assertEquals(interval.getDateFrom(),dateFrom);
        Assert.assertEquals(interval.getDateTo(),dateTo);
        Assert.assertEquals(interval.toString(),string);
    }

}