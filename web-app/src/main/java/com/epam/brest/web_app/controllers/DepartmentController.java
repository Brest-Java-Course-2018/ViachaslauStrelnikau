package com.epam.brest.web_app.controllers;

import com.epam.brest.model.Department;
import com.epam.brest.model.DepartmentAVGsalary;
import com.epam.brest.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * DepartmentController class.
 * Designed to Map department reqests
 */
@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;
    /**
     * method departments mapping /departments reqest and get list  departments with average salary and send to view.
     *
     * @param model attributes map
     * @return template name
     */
    @GetMapping(value = "/departments")
    public String departments( final Model model) {
        Collection<DepartmentAVGsalary> departmentAVGsalaries = departmentService.getDepartmentsAVGSalary();
        model.addAttribute  ("departments",departmentAVGsalaries);
        return "departments";
    }
    /**
     * method editDepartment mapping /editDepartment reqest and get department by Id and send to view.
     *
     * @param model attributes map
     * @param id id of department to edit
     * @return template name
     */
    @GetMapping(value = "/editDepartment/{id}")
    public String editDepartment(@PathVariable Integer id, final Model model) {
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute  ("department",department);
        model.addAttribute  ("Title","Edit Department");
        return "editDepartment";
    }
    /**
     * method removeDepartment mapping /removeDepartment reqest and remove department by Id list  departments with average salary and send to view .
     *
     * @param model attributes map
     * @param id id of department to edit
     * @return template name
     */
    @GetMapping(value = "/removeDepartment/{id}")
    public String removeDepartment(@PathVariable Integer id, final Model model) {
        departmentService.removeDepartmentById(id);
        Collection<DepartmentAVGsalary> departmentAVGsalaries = departmentService.getDepartmentsAVGSalary();
        model.addAttribute  ("departments",departmentAVGsalaries);
        return "departments";


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
    public String saveEditedDepartment(@ModelAttribute("SpringWeb") Department department, final Model model) {
     //   Department department = departmentService.getDepartmentById(id);
        model.addAttribute  ("department",new Department());
        return "editDepartment";
    }

}
