package com.epam.brest.model;

public class Department {
    private Integer departmentId;
    private String departmentName;
    private  String descririon;
    /**
     * Get Department id
     * @return DepartmentId
     */
    public Integer getDepartmentId() {
        return departmentId;
    }
    /**
     * Get Department Name
     * @return DepartmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }
    /**
     * Get Department Descririon
     * @return Descririon
     */
    public String getDescririon() {
        return descririon;
    }
    /**
     * set Department Id
     * @return
     */
    public void setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
    }
    /**
     * set Department Name
     * @return
     */
    public void setDepartmentName(final String departmentName) {
        this.departmentName = departmentName;
    }
    /**
     * set Department Descririon
     * @return
     */
    public void setDescririon(final String descririon) {
        this.descririon = descririon;
    }

    @Override
    public String toString() {
        return "Department{"
                + "departmentId=" + departmentId
                + ", departmentName='" + departmentName + '\''
                + ", descririon='" + descririon + '\''
                + '}';
    }
}
