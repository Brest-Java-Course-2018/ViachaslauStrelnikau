package com.epam.brest.web_app.controllers;

import com.epam.brest.model.Department;
import com.epam.brest.model.DepartmentAVGsalary;
import com.epam.brest.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;

/**
 * DepartmentController class.
 * Designed to Map department reqests
 */
@Controller
public class DepartmentController {

    private static final Logger LOGGER= LogManager.getLogger();

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
        LOGGER.debug("DepartmentController.departments");
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
        LOGGER.debug("DepartmentController.editDepartment {}",id);
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute  ("department",department);
        model.addAttribute  ("Title","EDIT DEPARTMENT");
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
        LOGGER.debug("DepartmentController.removeDepartment {}",id);
        departmentService.removeDepartmentById(id);
        Collection<DepartmentAVGsalary> departmentAVGsalaries = departmentService.getDepartmentsAVGSalary();
        model.addAttribute  ("departments",departmentAVGsalaries);
        return "departments";
    }
    /**
     * method addDepartment mapping /addDepartment reqest and prepares add Department view .
     *
     * @param model attributes map
     * @return template name
     */
    @GetMapping(value = "/addDepartment")
    public String addDepartment(final Model model)
    {
        LOGGER.debug("DepartmentController.addDepartment");
        Department department =new Department();
        model.addAttribute  ("department",department);
        model.addAttribute  ("Title","ADD DEPARTMENT");
        return "editDepartment";
    }
    /**
     * method saveEditedDepartment mapping /saveEditedDepartment reqest and save department record to DB .
     *
     * @param model attributes map
     * @param  department added/edited department
     * @param result check errors result
     * @return template name
     */
    @PostMapping(value = "/saveDepartment")
    public String saveEditedDepartment(@Valid Department department, BindingResult result, final Model model) {
        LOGGER.debug("DepartmentController.removeDepartment {}",department.getDepartmentId());
        if (result.hasErrors()){

            model.addAttribute  ("department",department);
            model.addAttribute  ("Title","Error in entered values!");
            return "editDepartment";
        }else {
            if(department.getDepartmentId()==0) {
                try{
                departmentService.addDepartment(department);}
                catch (IllegalArgumentException e)
                {
                    model.addAttribute  ("department",department);
                    model.addAttribute  ("Title","Record with that name is allready in base!");
                    return "editDepartment";
                }
            }
            else
                departmentService.updateDepartment(department);
            return "redirect:/departments";
        }
    }


}
