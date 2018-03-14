package com.epam.brest.web_app.controllers;

import com.epam.brest.model.Department;
import com.epam.brest.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;

/**
 * DepartmentController class.
 * Designed to Map department reqests
 */
@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping(value = "/departments")
    public String departments( final Model model) {
        Collection<Department> getDepartments = departmentService.getDepartments();
        model.addAttribute  ("departmenta",getDepartments);
        return "departments";
    }
//    @GetMapping(value = "/editDepartment")
//    public String editDepartment(final Model model) {
//
//        return "editDepartment";
//    }
    @GetMapping(value = "/editDepartment/{id}")
    public String getDepartmentById(@PathVariable Integer id, final Model model) {
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute  ("department",department);
        return "editDepartment";
    }
}
