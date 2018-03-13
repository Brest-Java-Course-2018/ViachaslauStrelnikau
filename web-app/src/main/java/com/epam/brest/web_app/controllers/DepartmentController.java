package com.epam.brest.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartmentController {
    @GetMapping(value = "/departments")
    public String departments( Model model) {

        return "departments";
    }
    @GetMapping(value = "/editDepartment")
    public String editDepartment( Model model) {

        return "editDepartment";
    }
}
