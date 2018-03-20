package com.epam.brest.web_app.controllers;

import com.epam.brest.model.Department;
import com.epam.brest.model.Employee;
import com.epam.brest.service.DepartmentService;
import com.epam.brest.service.EmployeeService;
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
import java.util.List;

/**
 * EmployeeController class.
 * Designed to Map employee reqests
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;
    private static final Logger LOGGER= LogManager.getLogger();
    /**
     * method employees mapping /employees reqest and get list  employees.
     *
     * @param model attributes map
     * @return template name
     */
    @GetMapping(value = "/employees")
    public String employees(final Model model) {
        LOGGER.debug("EmployeeController.employees");
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }
    /**
     * method editEmployee mapping /editEmployee reqest make setups to edit Employee record.
     *
     * @param model attributes map
     * @return template name
     */
    @GetMapping(value = "/addEmployee")
    public String addEmployee(final Model model) {
        LOGGER.debug("EmployeeController.addEmployee");
        Employee new_employee = new Employee();
        Collection<Department> departments = departmentService.getDepartments();

        model.addAttribute("employee", new_employee);
//        model.addAttribute("Title", "Add Employee");
        model.addAttribute("isNew", true);
        model.addAttribute("departments", departments);
        return "editEmployee";
    }
    /**
     * method editEmployee mapping /editEmployee reqest make setups to edit Employee record.
     *
     * @param model attributes map
     * @param id id of Employee record
     * @return template name
     */
    @GetMapping(value = "/editEmployee/{id}")
    public String editEmployee(@PathVariable Integer id, final Model model) {
        LOGGER.debug("EmployeeController.editEmployee {}",id);
        String DepartmentName = "";
        Employee employee = employeeService.getEmployeeById(id);
        Collection<Department> departments = departmentService.getDepartments();
        // Getting departmentName for employee
        for (Department department : departments
                ) {
            if (department.getDepartmentId() == employee.getDepartmentId()) {
                DepartmentName = department.getDepartmentName();
            }
        }
        model.addAttribute("employee", employee);
//        model.addAttribute("Title", "Edit Employee");
        model.addAttribute("isNew", false);
        model.addAttribute("departments", departments);
        model.addAttribute("DepartmentName", DepartmentName);
        return "editEmployee";
    }
    /**
     * method removeEmployee mapping /removeEmployee reqest and remoce Employee record from db
     *
     * @param model attributes map
     * @param id id of Employee record
     * @return template name
     */
    @GetMapping(value = "/removeEmployee/{id}")
    public String removeEmployee(@PathVariable Integer id, final Model model) {
        LOGGER.debug("EmployeeController.removeEmployee {}",id);
        employeeService.removeEmployeeById(id);
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);

        return "employees";
    }

    /**
     * method saveEditedEmployee mapping /saveEditedEmployee reqest and save employee record to DB .
     *
     * @param model attributes map
     * @param  employee added/edited employee
     * @param result check errors result
     * @return template name
     */
    @PostMapping(value = "/saveEmployee")
    public String saveEditedEmployee(@Valid Employee employee, BindingResult result, final Model model) {
        LOGGER.debug("EmployeeController.saveEditedEmployee {}",employee.getEmployeeId());
        if (result.hasErrors()){
            Collection<Department> departments = departmentService.getDepartments();
            model.addAttribute  ("employee",employee);
//            model.addAttribute  ("Title","Error in entered values!");
            model.addAttribute("departments", departments);
            return "editEmployee";
        }else {
            if(employee.getEmployeeId()==0)
                employeeService.addEmployee(employee);
            else
                employeeService.updateEmployee(employee);
            return "redirect:/employees";
        }
    }
}
