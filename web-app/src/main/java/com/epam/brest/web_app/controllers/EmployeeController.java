package com.epam.brest.web_app.controllers;

import com.epam.brest.model.Employee;
import com.epam.brest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping(value = "/editEmployee")
    public String employee(final Model model) {

        return "editEmployee";
    }
}
