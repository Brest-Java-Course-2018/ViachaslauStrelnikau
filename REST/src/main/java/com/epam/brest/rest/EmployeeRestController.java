package com.epam.brest.rest;

import com.epam.brest.model.Employee;
import com.epam.brest.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeRestController {
    private static final Logger LOGGER =LogManager.getLogger();

    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/employees")
    List<Employee> getEmployees()
    {
        LOGGER.debug("REST getEmployees");
        return employeeService.getEmployees();
    }

    @GetMapping(value = "employees/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    Employee getEmployeeById(@PathVariable(value = "id") Integer id)
    {
        LOGGER.debug("REST getEmployeeById {}",id);
        return employeeService.getEmployeeById(id);
    }

    @PostMapping(value = "employees")
    @ResponseStatus(HttpStatus.CREATED)
    Employee addEmployee(@RequestBody Employee employee)
    {
        LOGGER.debug("REST addEmployee {}",employee);
        return employeeService.addEmployee(employee);
    }

    @PostMapping(value = "employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    void updateEmployee(@RequestBody Employee employee, @PathVariable(value = "id") Integer id)
    {
        LOGGER.debug("REST updateEmployee {}",employee);
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping(value = "employees/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    void deleteEmployee(@PathVariable(value="id") Integer id)
    {
        employeeService.removeEmployeeById(id);
    }

}
