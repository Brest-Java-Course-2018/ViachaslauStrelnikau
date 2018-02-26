package com.epam.brest;

public class Department {
    private Integer departmentId;
    private String departmentName;
    private  String descririon;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDescririon() {
        return descririon;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setDescririon(String descririon) {
        this.descririon = descririon;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", descririon='" + descririon + '\'' +
                '}';
    }
}
