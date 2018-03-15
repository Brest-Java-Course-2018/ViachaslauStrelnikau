package com.epam.brest.model;

public class DepartmentAVGsalary {
    /**Property departmentId.*/
    private int departmentId;
    /**Property departmentName.*/
    private String departmentName;
    /** Property avgSalary.*/
    private int avgSalary;

    public DepartmentAVGsalary(int departmentId, String departmentName, int avgSalary) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.avgSalary = avgSalary;
    }

    public DepartmentAVGsalary() {
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getAvgSalary() {
        return avgSalary;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setAvgSalary(int avgSalary) {
        this.avgSalary = avgSalary;
    }

    @Override
    public String toString() {
        return "DepartmentAVGsalary{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", avgSalary=" + avgSalary +
                '}';
    }
}
