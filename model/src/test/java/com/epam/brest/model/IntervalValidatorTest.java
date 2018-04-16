package com.epam.brest.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;


public class IntervalValidatorTest {


    private IntervalValidator intervalValidator;
    private Interval interval;
    private Errors errors;

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

    @Test
    public void validate() {

        intervalValidator.validate(interval, errors);
        Assert.assertTrue(errors.hasErrors());
        Assert.assertTrue(errors.getErrorCount() == 1);
        //   Assert.assertTrue(errors.getGlobalError().equals("DateFrom is after the DateTo"));

    }
}