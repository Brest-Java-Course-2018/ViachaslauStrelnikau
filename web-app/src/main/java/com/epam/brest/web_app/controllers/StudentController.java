package com.epam.brest.web_app.controllers;

import com.epam.brest.dto.GroupDTOlite;
import com.epam.brest.dto.StudentDTO;
import com.epam.brest.model.Interval;
import com.epam.brest.model.IntervalValidator;
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
import java.sql.Date;
import java.text.ParseException;
import java.util.Collection;

/**
 * Student MVC controller.
 */
@Controller
public class StudentController {
    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    StudentService studentService;

    @Autowired
    GroupService groupService;

    /**
     * Method showStudents maps /students get request to display StudentDTO records.
     *
     * @param model model data
     * @return view string
     */
    @GetMapping(value = "/students")
    public String showStudents(Model model) {
        LOGGER.debug("StudentController - showStudents");
        Collection<StudentDTO> studentDTOS = studentService.getallStudentsDTO();
        Interval datesInterval = new Interval();
        model.addAttribute("students", studentDTOS);
        model.addAttribute("datesInterval", datesInterval);
        return "students";
    }

    /**
     * Method editStudent maps /students/{id} get request and display student edit window.
     *
     * @param id    of editing record
     * @param model model data
     * @return view string
     */
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

    /**
     * Method updateStudent maps /students/{id} post request and save edited record to database.
     *
     * @param id            id of editing record
     * @param model         model data
     * @param student       edited record
     * @param bindingResult validation results
     * @return view string
     */
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

    /**
     * Method newStudent maps /addStudent get request and display student add window.
     *
     * @param model model data
     * @return view string
     */
    @GetMapping(value = "/addStudent")
    public String newStudent(Model model) {
        LOGGER.debug("StudentController - newStudent");
        Student student = new Student();
        Collection<GroupDTOlite> groupDTOlites = groupService.getallGroupsDTOlite();
        model.addAttribute("groups", groupDTOlites);
        model.addAttribute("isNew", true);
        model.addAttribute("student", student);
        return "editstudents";
    }

    /**
     * Method addStudent maps /addStudent post request and save new students record to database.
     *
     * @param model         model data
     * @param student       edited record
     * @param bindingResult validation results
     * @return view string
     */
    @PostMapping(value = "/addStudent")
    public String addStudent(Model model, @Valid Student student, BindingResult bindingResult) {
        LOGGER.debug("StudentController - addStudent - {}", student);
        if (bindingResult.hasErrors()) {
            LOGGER.debug("StudentController - addStudent");
            Collection<GroupDTOlite> groupDTOlites = groupService.getallGroupsDTOlite();
            model.addAttribute("groups", groupDTOlites);
            model.addAttribute("isNew", true);
            model.addAttribute("student", student);
            return "editstudents";
        } else {
            studentService.addStudent(student);
            return "redirect:/students";
        }
    }

    /**
     * Method removeStudent maps /students/{id}/delete request to delete record from database.
     *
     * @param id id of removing record
     * @return view string
     */
    @GetMapping(value = "/students/{id}/delete")
    public String removeStudent(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("StudentController - removeStudent - {}", id);
        studentService.removeStudent(id);
        return "redirect:/students";
    }

    @PostMapping(value = "/filtrStudents")
    public String filtrStudents(Model model, Interval datesInterval, BindingResult bindingResult) throws ParseException {

        LOGGER.debug("StudentController - filtrStudents - {}", datesInterval);
        Date dateFrom = datesInterval.getDateFrom();
        Date dateTo = datesInterval.getDateTo();
        IntervalValidator intervalValidator = new IntervalValidator();
        intervalValidator.validate(datesInterval, bindingResult);
        Collection<StudentDTO> studentDTOS;
        if (bindingResult.hasErrors()) {
            LOGGER.error("StudentController - filtrStudents - dateFrom less then dateTo {}", datesInterval);
            studentDTOS = studentService.getallStudentsDTO();
        } else {
            studentDTOS = studentService.getFilteredStudentsDTO(dateFrom, dateTo);
        }
        model.addAttribute("students", studentDTOS);
        model.addAttribute("datesInterval", datesInterval);
        return "students";

    }

}
