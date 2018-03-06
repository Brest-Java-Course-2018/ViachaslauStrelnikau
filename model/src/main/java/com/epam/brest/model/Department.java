package com.epam.brest.model;
/**
 * Department class.
 */
public class Department {
    /**Property departmentId.*/
    private int departmentId;
    /**Property departmentName.*/
    private String departmentName;
    /**Property description.*/
    private  String description;

    public Department( String departmentName, String description) {
        this.departmentName = departmentName;
        this.description = description;
    }
    public Department(){}

    /**
     * Get Department id.
     * @return DepartmentId
     */
    public int getDepartmentId() {
        return departmentId;
    }
    /**
     * Get Department Name.
     * @return DepartmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }
    /**
     * Get Department description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set Department Id.
     * @param departmentId new Department Id
     */
    public void setDepartmentId(final int departmentId) {
        this.departmentId = departmentId;
    }
    /**
     * set Department Name.
     * @param departmentname new Department Name
     */
    public void setDepartmentName(final String departmentname) {
        this.departmentName = departmentname;
    }
    /**
     * set Department Descririon.
     * @param description new department description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Department{"
                + "departmentId=" + departmentId
                + ", departmentName='" + departmentName + '\''
                + ", description='" + description + '\''
                + '}';
    }
}
