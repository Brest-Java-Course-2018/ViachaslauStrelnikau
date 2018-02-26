package com.epam.brest;

import static org.junit.Assert.*;

public class EmployeeTest {

    public static final String VASIA = "Vasia";

    @org.junit.Test
    public void getEmployeeId() {
        Employee employee =new Employee();
        employee.setEmployeeName(VASIA);
        assertEquals(VASIA,employee.getEmployeeName());
        assertNotEquals("Petia",employee.getEmployeeName());
    }
}