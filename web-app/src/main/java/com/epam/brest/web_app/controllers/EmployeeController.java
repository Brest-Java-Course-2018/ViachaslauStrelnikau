package com.epam.brest.web_app.controllers;

import com.epam.brest.model.Employee;
import com.epam.brest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * EmployeeController class.
 * Designed to Map employee reqests
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

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
    public String employee(@PathVariable Integer id, final Model model) {
        Employee employee= employeeService.getEmployeeById(id);
        model.addAttribute  ("employee",employee);
        model.addAttribute  ("Title","Edit Employee");
        return "editEmployee";
    }
}
