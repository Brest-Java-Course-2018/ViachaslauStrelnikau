package com.epam.brest.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * EmployeeController class.
 * Designed to Map employee reqests
 */
@Controller
public class EmployeeController {
    @GetMapping(value = "/employees")
    public String employees(final Model model) {

        return "employees";
    }

    @GetMapping(value = "/editEmployee")
    public String employee(final Model model) {

        return "editEmployee";
    }
}
