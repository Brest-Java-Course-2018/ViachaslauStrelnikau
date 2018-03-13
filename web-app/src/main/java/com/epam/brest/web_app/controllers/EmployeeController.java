package com.epam.brest.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {
    @GetMapping(value = "/employees")
    public String employees( Model model) {

        return "employees";
    }
    @GetMapping(value = "/editEmployee")
    public String employee( Model model) {

        return "editEmployee";
    }
}
