package com.epam.brest.model;
/**
 * *
 *Employee class dao.
 */
public class Employee {
    /**
     * @
     */
    private Integer employeeId;
    private String employeeName;
    private Integer employeeSalary;
    private Integer departmenyId;

    /**
     * Get Employee id.
     * @return EmployeeId
     */
    public Integer getEmployeeId() {
        return employeeId;
    }
    /**
     * Get Employee Name.
     * @return EmployeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * Get Employee Salary.
     * @return salary amount
     */
    public Integer getEmployeeSalary() {
        return employeeSalary;
    }
    /**
     * Get Employees Department id.
     * @return departentId
     */
    public Integer getDepartmenyId() {
        return departmenyId;
    }
    /**
     * Set Employees  id.
     * @param employeeId new Employees  id
     */
    public void setEmployeeId(final Integer employeeId) {
        this.employeeId = employeeId;
    }
    /**
     * Set Employees  name.
     * @param employeeName new Employees  name
     */
    public void setEmployeeName(final String employeeName) {
        this.employeeName = employeeName;
    }
    /**
     * Set Employees  salary.
     * @param employeeSalary new Employees  salary
     */
    public void setEmployeeSalary(final Integer employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
    /**
     * Set Employees  DepartmenyId.
     * @param departmenyId new Departmeny Id
     */
    public void setDepartmenyId(final Integer departmenyId) {
        this.departmenyId = departmenyId;
    }
    /**
     * Set Generates string with all employee fields.
     * @return String with all employee class propertys
     */
    @Override
    public String toString() {
        return "Employee{"
                + "employeeId=" + employeeId
                + ", employeeName='" + employeeName + '\''
                + ", employeeSalary=" + employeeSalary
                + ", departmenyId=" + departmenyId
                + '}';
    }
}
