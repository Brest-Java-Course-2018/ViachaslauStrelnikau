package com.epam.brest.model;
/**
 * Department class.
 */
public class Department {
    /**Property departmentId.*/
    private Integer departmentId;
    /**Property departmentName.*/
    private String departmentName;
    /**Property description.*/
    private  String description;
    /**
     * Get Department id.
     * @return DepartmentId
     */
    public Integer getDepartmentId() {
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
     * @param departmentid new Department Id
     */
    public void setDepartmentId(final Integer departmentid) {
        this.departmentId = departmentid;
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
     * @param descriptioN new department description
     */
    public void setDescririon(final String descriptioN) {
        this.description = descriptioN;
    }

    @Override
    public String toString() {
        return "Department{"
                + "departmentId=" + departmentId
                + ", departmentName='" + departmentName + '\''
                + ", descririon='" + description + '\''
                + '}';
    }
}
