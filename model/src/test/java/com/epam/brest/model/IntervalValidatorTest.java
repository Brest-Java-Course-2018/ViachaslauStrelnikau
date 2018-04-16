package com.epam.brest.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import javax.validation.constraints.AssertTrue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Test IntervalValidator class
 */
public class IntervalValidatorTest {


    private IntervalValidator intervalValidator;
    private Interval interval;
    private Errors errors;

    /**
     * testSetUp - test setUp method.
     */
    @Before
    public void testSetUp() throws ParseException {

        intervalValidator = new IntervalValidator();
        interval = new Interval();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date dateF = simpleDateFormat.parse("31.05.1990");
        Date dateT = simpleDateFormat.parse("31.05.1980");
        java.sql.Date dateFrom = new java.sql.Date(dateF.getTime());
        java.sql.Date dateTo = new java.sql.Date(dateT.getTime());
        interval.setDateFrom(dateFrom);
        interval.setDateTo(dateTo);
        errors = new BeanPropertyBindingResult(interval, "interval");
    }

    /**
     * Method validate tests validate method.
     */
    @Test
    public void validate() {

        intervalValidator.validate(interval, errors);
        Assert.assertTrue(errors.hasErrors());
        Assert.assertTrue(errors.getErrorCount() == 1);
        Assert.assertTrue(errors.getGlobalError().getDefaultMessage().equals("DateFrom is after the DateTo"));
    }

    /**
     * Method supporttest tests support method.
     */
    @Test
    public void supporttest() {

        Assert.assertTrue(intervalValidator.supports(Interval.class));
    }

    /**
     * Method supporttest tests support method.
     */
    @Test
    public void supporttest2() {

        Assert.assertFalse(intervalValidator.supports(Group.class));
    }
}