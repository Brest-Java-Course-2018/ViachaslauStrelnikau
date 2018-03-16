package com.epam.brest.model;
/**
 * *
 *Employee class dao.
 */
public class Employee {
    /**Property employeeId.*/
    private int employeeId;
    /** Property employeeName.*/
    private String employeeName;
    /** Property employeeEmail.*/
    private String employeeEmail;
    /** Property employeeSalary.*/
    private int employeeSalary;
    /** Property departmenyId.*/
    private int departmentId;



    public Employee(String employeeName, String employeeEmail, int employeeSalary, int departmentId) {
        this.employeeName = employeeName;
        this.employeeEmail=employeeEmail;
        this.employeeSalary = employeeSalary;
        this.departmentId = departmentId;

    }
    public Employee()
    {}

    /**
     * Get Employee id.
     * @return EmployeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * get Employee Email .
     * @return employeeEmail

     */
    public String getEmployeeEmail() {
        return employeeEmail;
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
    public int getEmployeeSalary() {
        return employeeSalary;
    }
    /**
     * Get Employees Department id.
     * @return departentId
     */
    public int getDepartmentId() {
        return departmentId;
    }
    /**
     * Set Employees  id.
     * @param employeeid new Employees  id
     */
    public void setEmployeeId(final int employeeid) {
        this.employeeId = employeeid;
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
     * @param employeesalary new Employees  salary
     */
    public void setEmployeeSalary(final int employeesalary) {
        this.employeeSalary = employeesalary;
    }
    /**
     * Set Employees  DepartmenyId.
     * @param departmentId new Departmeny Id
     */
    public void setDepartmentId(final int departmentId) {
        this.departmentId = departmentId;
    }
    /**
     * Set Employees  employee Email.
     * @param employeeEmail new Employees Email
     */
    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    /**
     * Set Generates string with all employee fields.
     *
     * @return String with all employee class propertys
     */
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeeSalary=" + employeeSalary +
                ", departmentId=" + departmentId +
                '}';
    }
}
