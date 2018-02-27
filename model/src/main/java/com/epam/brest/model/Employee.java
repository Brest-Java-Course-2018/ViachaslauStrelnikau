package com.epam.brest.model;
/**
 * *
 *Employee class dao
 */
public class Employee {
    private Integer employeeId;
    private String employeeName;
    private Integer employeeSalary;
    private Integer departmenyId;


    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * Get Employee Salary
     * @return salary amount
     */
    public Integer getEmployeeSalary() {
        return employeeSalary;
    }

    public Integer getDepartmenyId() {
        return departmenyId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmployeeSalary(Integer employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public void setDepartmenyId(Integer departmenyId) {
        this.departmenyId = departmenyId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeSalary=" + employeeSalary +
                ", departmenyId=" + departmenyId +
                '}';
    }
}
