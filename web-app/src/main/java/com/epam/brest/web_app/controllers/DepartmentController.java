package com.epam.brest.web_app.controllers;

import com.epam.brest.model.Department;
import com.epam.brest.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

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
        model.addAttribute  ("departments",getDepartments);
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
        model.addAttribute  ("Title","Edit Department");
        return "editDepartment";
    }
    @GetMapping(value = "/addDepartment")
    public String addDepartment(final Model model)
    {
        Department department =new Department();
        model.addAttribute  ("department",department);
        model.addAttribute  ("Title","Add Department");
        return "editDepartment";
    }
    @PostMapping(value = "/saveEditedDepartment")
    public String saveEditedDepartment(@RequestParam String department, final Model model) {
     //   Department department = departmentService.getDepartmentById(id);
        model.addAttribute  ("department",new Department());
        return "editDepartment";
    }

}
