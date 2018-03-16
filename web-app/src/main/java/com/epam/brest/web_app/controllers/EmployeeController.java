package com.epam.brest.web_app.controllers;

import com.epam.brest.model.Department;
import com.epam.brest.model.Employee;
import com.epam.brest.service.DepartmentService;
import com.epam.brest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;

/**
 * EmployeeController class.
 * Designed to Map employee reqests
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;
    /**
     * method employees mapping /employees reqest and get list  employees.
     *
     * @param model attributes map
     * @return template name
     */
    @GetMapping(value = "/employees")
    public String employees(final Model model) {

        List<Employee> employees =employeeService.getEmployees();
        model.addAttribute ("employees",employees);
        return "employees";
    }

    @GetMapping(value = "/editEmployee/{id}")
    public String editEmployee(@PathVariable Integer id, final Model model) {
        Employee employee= employeeService.getEmployeeById(id);
        Collection<Department> departments=departmentService.getDepartments();
        model.addAttribute  ("employee",employee);
        model.addAttribute  ("Title","Edit Employee");
        model.addAttribute  ("departments",departments);
        return "editEmployee";
    }

    @GetMapping(value = "/removeEmployee/{id}")
    public String removeEmployee(@PathVariable Integer id, final Model model) {
        employeeService.removeEmployeeById(id);
        List<Employee> employees =employeeService.getEmployees();
        model.addAttribute ("employees",employees);
        return "employees";
    }

}
