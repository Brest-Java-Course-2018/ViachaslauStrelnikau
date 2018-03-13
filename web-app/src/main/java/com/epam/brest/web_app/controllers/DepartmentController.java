package com.epam.brest.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * DepartmentController class.
 * Designed to Map department reqests
 */
@Controller
public class DepartmentController {

    @GetMapping(value = "/departments")
    public String departments(final Model model) {

        return "departments";
    }
    @GetMapping(value = "/editDepartment")
    public String editDepartment(final Model model) {

        return "editDepartment";
    }
}
