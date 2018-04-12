package com.epam.brest.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class IntervalValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
       return Interval.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Interval interval = (Interval)o;
        if(interval.getDateFrom()!=null
                &&interval.getDateTo()!=null
                &&interval.getDateFrom().compareTo(interval.getDateTo())>0)
                errors.reject("dateFrom","DateFrom is after the DateTo");
    }
}
