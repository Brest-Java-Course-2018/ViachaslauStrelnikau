package com.epam.brest.client_rest;

import com.epam.brest.model.Department;
import com.epam.brest.model.DepartmentAVGsalary;
import com.epam.brest.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

public class DepartmentConsumerRest implements DepartmentService {
    private String url;

    private RestTemplate restTemplate;

    public DepartmentConsumerRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public void updateDepartmentDescription(int departmentId, String newDescription) {

    }
    @Override
    public Collection<Department> getDepartments() {
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url,List.class);
        Collection<Department> departments = (Collection<Department>)responseEntity.getBody();
        return departments;
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        ResponseEntity responseEntity=restTemplate.getForEntity(url+"/"+departmentId,Department.class);
        Department department=(Department) responseEntity.getBody();
        return department;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<DepartmentAVGsalary> getDepartmentsAVGSalary() {

        ResponseEntity responseEntity=restTemplate.getForEntity(url,List.class);
        List<DepartmentAVGsalary> departmentAVGsalaries=  (List<DepartmentAVGsalary>) responseEntity.getBody();
        return departmentAVGsalaries;
    }

    @Override
    public void removeDepartmentById(int departmentid) {
        restTemplate.delete(url+"/"+departmentid);
    }

    @Override
    public Department addDepartment(Department department) {
        ResponseEntity responseEntity=restTemplate.postForEntity(url,department,Department.class);
        Department department1=(Department) responseEntity.getBody();
        return department1;
    }

    @Override
    public void updateDepartment(Department department) {
        restTemplate.postForEntity(url+"/"+department.getDepartmentId(),department,Department.class);

    }
}
