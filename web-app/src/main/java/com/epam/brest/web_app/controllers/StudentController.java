package com.epam.brest.web_app.controllers;

import com.epam.brest.dto.StudentDTO;
import com.epam.brest.service.GroupService;
import com.epam.brest.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class StudentController {
    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    StudentService studentService;

    @Autowired
    GroupService groupService;

    @GetMapping(value = "/students")
    public String showStudents(Model model)
    {
        LOGGER.debug("StudentController - showStudents");
        Collection<StudentDTO> studentDTOS =studentService.getallStudentsDTO();
        model.addAttribute("students",studentDTOS);
        return "students";
    }

}
