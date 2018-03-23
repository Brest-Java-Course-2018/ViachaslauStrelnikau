package com.epam.brest.model;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

public class DepartmentValidatorTest {

    /**
     * Tests class Validator of Deaprtment class objects.
     */
    @Test(expected = NullPointerException.class)
    public void DepartmentValidatorTest()
    {
        Department department =new Department();
        department.setDepartmentName(null);
        department.setDescription(null);

        DepartmentValidator departmentValidator =new DepartmentValidator();

        Errors errors =new BindException(department,"department");
        departmentValidator.validate(department,errors);
        Assert.assertTrue(errors.hasErrors());
    }
    /**
     * Tests class Validator of Deaprtment class objects.
     */
    @Test
    public void DepartmentValidatorTest2()
    {
        Department department =new Department();
        department.setDepartmentName("1");
        department.setDescription("1");
        DepartmentValidator departmentValidator =new DepartmentValidator();

        Errors errors =new BindException(department,"department");
        departmentValidator.validate(department,errors);
        Assert.assertTrue(errors.hasErrors());
    }

}