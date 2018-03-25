package com.epam.brest.client_rest;

import com.epam.brest.model.Employee;
import com.epam.brest.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class EmployeeConsumerRest implements EmployeeService {
    private static final Logger LOGGER = LogManager.getLogger();

    private RestTemplate restTemplate;
    private String url;

    public EmployeeConsumerRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Employee> getEmployees() {
        LOGGER.debug("EmployeeConsumerRest.getEmployees");
        ResponseEntity responseEntity = restTemplate.getForEntity(url,List.class);
        List<Employee> employees = (List<Employee>)responseEntity.getBody();
        return employees;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        LOGGER.debug("EmployeeConsumerRest.getEmployeeById {}",employeeId);
        ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(url+"/"+employeeId,Employee.class);
        Employee employee = responseEntity.getBody();
        return  employee;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        LOGGER.debug("EmployeeConsumerRest.addEmployee {}",employee);
        ResponseEntity<Employee> responseEntity = restTemplate.postForEntity(url,employee,Employee.class);
        Employee resultRmployee = responseEntity.getBody();
        return  resultRmployee;
    }

    @Override
    public void removeEmployeeById(int employeeId) {
        LOGGER.debug("EmployeeConsumerRest.removeEmployeeById {}",employeeId);
        restTemplate.delete(url+"/"+employeeId);
    }


    @Override
    public void updateEmployee(Employee employee) {
        LOGGER.debug("EmployeeConsumerRest.updateEmployee {}",employee);
        restTemplate.postForEntity(url+"/"+employee.getDepartmentId(),employee,Employee.class);
    }

//----------------------------------
    @Override
    public void riseAllSallerysByPercent(int percent) {

    }
}
