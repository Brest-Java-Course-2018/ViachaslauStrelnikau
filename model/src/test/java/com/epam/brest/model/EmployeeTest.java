package com.epam.brest.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EmployeeTest {

    public static final String VASIA = "Vasia";

    @Test
    public void getEmployeeId() {
        Employee employee =new Employee();
        employee.setEmployeeName(VASIA);
        assertEquals(VASIA,employee.getEmployeeName());
        assertNotEquals("Petia",employee.getEmployeeName());
    }
}