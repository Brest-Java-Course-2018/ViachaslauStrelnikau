package com.epam.brest.web_app.controllers;

import com.epam.brest.dto.GroupDTOlite;
import com.epam.brest.dto.StudentDTO;
import com.epam.brest.model.Student;
import com.epam.brest.service.GroupService;
import com.epam.brest.service.StudentService;
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

@Controller
public class StudentController {
    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    StudentService studentService;

    @Autowired
    GroupService groupService;

    @GetMapping(value = "/students")
    public String showStudents(Model model) {
        LOGGER.debug("StudentController - showStudents");
        Collection<StudentDTO> studentDTOS = studentService.getallStudentsDTO();
        model.addAttribute("students", studentDTOS);
        return "students";
    }

    @GetMapping(value = "/students/{id}")
    public String editStudent(@PathVariable(value = "id") Integer id, Model model) {
        LOGGER.debug("StudentController - editStudent:{}", id);
        Student student = studentService.getStudentById(id);
        Collection<GroupDTOlite> groupDTOlites = groupService.getallGroupsDTOlite();
        model.addAttribute("isNew", false);
        model.addAttribute("student", student);
        model.addAttribute("groups", groupDTOlites);
        return "editstudents";
    }

    @PostMapping(value = "/students/{id}")
    public String updateStudent(@PathVariable(value = "id") Integer id, Model model, @Valid Student student, BindingResult bindingResult) {
        LOGGER.debug("StudentController - updateStudent:{}", id);
        if (bindingResult.hasErrors()) {
            LOGGER.error("StudentController - updateStudent - validation error -{}", student);
            Collection<GroupDTOlite> groupDTOlites = groupService.getallGroupsDTOlite();
            model.addAttribute("isNew", false);
            model.addAttribute("student", student);
            model.addAttribute("groups", groupDTOlites);
            return "editstudents";

        } else {

            studentService.updateStudent(student);
            return "redirect:/students";
        }
    }
    @GetMapping(value = "/addStudent")
    public String newStudent(Model model)
    {
        LOGGER.debug("StudentController - newStudent");
        Student student = new Student();
        Collection<GroupDTOlite> groupDTOlites = groupService.getallGroupsDTOlite();
        model.addAttribute("groups", groupDTOlites);
        model.addAttribute("isNew",true);
        model.addAttribute("student",student);
        return "editstudents";
    }

    @PostMapping(value = "/addStudent")
    public String addStudent(Model model,@Valid Student student, BindingResult bindingResult)
    {
        LOGGER.debug("StudentController - addStudent - {}",student);
        if(bindingResult.hasErrors())
        {
            LOGGER.debug("StudentController - addStudent");
            Collection<GroupDTOlite> groupDTOlites = groupService.getallGroupsDTOlite();
            model.addAttribute("groups", groupDTOlites);
            model.addAttribute("isNew",true);
            model.addAttribute("student",student);
            return "editstudents";
        }
        else
        {
            studentService.addStudent(student);
            return "redirect:/students";
        }
    }
    @GetMapping(value = "/students/{id}/delete")
    public String removeStudent(@PathVariable(value = "id")Integer id)
    {
        LOGGER.debug("StudentController - removeStudent - {}",id);
        studentService.removeStudent(id);
        return "redirect:/students";
    }

}
