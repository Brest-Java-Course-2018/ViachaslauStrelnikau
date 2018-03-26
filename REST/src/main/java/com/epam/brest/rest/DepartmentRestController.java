package com.epam.brest.rest;

import com.epam.brest.model.Department;
import com.epam.brest.model.DepartmentAVGsalary;
import com.epam.brest.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class DepartmentRestController {
    private static final Logger LOGGER= LogManager.getLogger();
    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/departments")
    public Collection<DepartmentAVGsalary> getDepartments()
    {
        LOGGER.debug("REST getDepartments");
        return departmentService.getDepartmentsAVGSalary();
    }

    @GetMapping(value = "/departments/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Department getdepartmentById(@PathVariable(value = "id") Integer id)
    {
        LOGGER.debug("REST getdepartmentById {}",id);
        return departmentService.getDepartmentById(id);
    }

    @PostMapping(value = "/departments")
    @ResponseStatus(HttpStatus.CREATED)
    public Department addDepartment(@RequestBody Department department)
    {
        LOGGER.debug("REST addDepartment {}",department);
        Department department_res=departmentService.addDepartment(department);
        return department_res;
    }

    @PostMapping(value = "/departments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDepartment(@PathVariable(value = "id") Integer id, @RequestBody Department department)
    {
        LOGGER.debug("REST updateDepartment {}",id);
        departmentService.updateDepartment(department);
    }

    @DeleteMapping(value = "/departments/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public void deleteDepartmentById(@PathVariable(value = "id") Integer id)
    {
        LOGGER.debug("REST deleteDepartmentById {}",id);
        departmentService.removeDepartmentById(id);
    }
}
