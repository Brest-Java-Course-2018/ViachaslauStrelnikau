package com.epam.brest.web_app.controllers;

import com.epam.brest.dto.GroupDTOlite;
import com.epam.brest.dto.StudentDTO;
import com.epam.brest.model.Student;
import com.epam.brest.service.GroupService;
import com.epam.brest.service.StudentService;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-webapp-test.xml")
public class StudentControllerTest {

    @Autowired
    private StudentService studenCounsumerRestMock;

    @Autowired
    private GroupService groupCounsumerRestMock;

    @Autowired
    private StudentController studentController;

    private MockMvc mockMvc;

    private static GroupDTOlite groupDTOlite;
    private static GroupDTOlite groupDTOlite2;

    private StudentDTO studentDTO;
    private StudentDTO studentDTO2;

    private Student student;
    private Student student_in;
    private Student student_empty;
    private static final int ID=1;

    @Before
    public void testSetUp() throws ParseException {
     mockMvc= MockMvcBuilders.standaloneSetup(studentController)
             .setMessageConverters(new MappingJackson2HttpMessageConverter())
             .build();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

        studentDTO = new StudentDTO();
        studentDTO.setStudentId(1);
        studentDTO.setFullName("Test1");
        Date date = simpleDateFormat.parse("31.05.1990");
        studentDTO.setStudentBirth(new java.sql.Date(date.getTime()));
        studentDTO.setStudentAvgMarks(1);
        studentDTO.setStudentName("Student1");

        studentDTO2 = new StudentDTO();
        studentDTO2.setStudentId(2);
        studentDTO2.setFullName("Test2");
        date = simpleDateFormat.parse("31.05.1990");
        studentDTO2.setStudentBirth(new java.sql.Date(date.getTime()));
        studentDTO2.setStudentAvgMarks(2);
        studentDTO2.setStudentName("Student2");

        student = new Student();
        student.setStudentId(1);
        student.setGroupId(2);
        date = simpleDateFormat.parse("31.05.1990");
        student.setStudentBirth(new java.sql.Date(date.getTime()));
        student.setStudentAvgMarks(2);
        student.setStudentName("Student2");

        student_in = new Student();
        student_in.setGroupId(2);
        date = simpleDateFormat.parse("31.05.1990");
        student_in.setStudentBirth(new java.sql.Date(date.getTime()));
        student_in.setStudentAvgMarks(2);
        student_in.setStudentName("Student2");

        student_empty= new Student();

        groupDTOlite = new GroupDTOlite();
        groupDTOlite.setGroupId(1);
        groupDTOlite.setFullName("Test1");

        groupDTOlite2 = new GroupDTOlite();
        groupDTOlite2.setGroupId(2);
        groupDTOlite2.setFullName("Test2");

    }
    @Test
    public void showStudents() throws Exception {
        Collection<StudentDTO> studentDTOS = Arrays.asList(studentDTO,studentDTO2);
        EasyMock.expect(studenCounsumerRestMock.getallStudentsDTO()).andReturn(studentDTOS);
        EasyMock.replay(studenCounsumerRestMock);
        mockMvc.perform(get("/students/"))
                .andExpect(status().isOk())
                .andExpect(view().name("students"))
                .andExpect(model().attribute("students",studentDTOS));
        EasyMock.verify(studenCounsumerRestMock);
        EasyMock.reset(studenCounsumerRestMock);
    }

    @Test
    public void editStudent() throws Exception {
        Collection<GroupDTOlite> groupDTOlites = Arrays.asList(groupDTOlite,groupDTOlite2);
        EasyMock.expect(groupCounsumerRestMock.getallGroupsDTOlite()).andReturn(groupDTOlites);
        EasyMock.expect(studenCounsumerRestMock.getStudentById(ID)).andReturn(student);
        EasyMock.replay(studenCounsumerRestMock);
        EasyMock.replay(groupCounsumerRestMock);
        mockMvc.perform(get("/students/"+ID))
                .andExpect(status().isFound())
                .andExpect(view().name("editstudents"))
                .andExpect(model().attribute("isNew",false ))
                .andExpect(model().attribute("student",student ))
                .andExpect(model().attribute("groups",groupDTOlites ));
        EasyMock.verify(groupCounsumerRestMock);
        EasyMock.verify(studenCounsumerRestMock);
        EasyMock.reset(studenCounsumerRestMock);
        EasyMock.reset(groupCounsumerRestMock);
    }

    @Test
    public void updateStudent() throws Exception {
        studenCounsumerRestMock.updateStudent(student);
        EasyMock.replay(studenCounsumerRestMock);
        mockMvc.perform(post("/students/"+ID)
                .param("studentId",Integer.toString( student.getStudentId()))
                .param("studentName",student.getStudentName())
                .param("studentBirth",student.getStudentBirth().toString())
                .param("studentAvgMarks",Double.toString( student.getStudentAvgMarks()))
                .param("groupId",Integer.toString( student.getGroupId())))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/students"));
        EasyMock.verify(studenCounsumerRestMock);
        EasyMock.reset(studenCounsumerRestMock);
    }

    @Test
    public void newStudent() throws Exception {
//        Collection<GroupDTOlite> groupDTOlites = Arrays.asList(groupDTOlite,groupDTOlite2);
//        EasyMock.expect(groupCounsumerRestMock.getallGroupsDTOlite()).andReturn(groupDTOlites);
//        EasyMock.replay(groupCounsumerRestMock);
//        mockMvc.perform(get("/addStudent"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("editstudents"))
//                .andExpect(model().attribute("isNew",true ))
//                .andExpect(model().attribute("student",student_empty ))
//                .andExpect(model().attribute("groups",groupDTOlites ));
//        EasyMock.verify(groupCounsumerRestMock);
//        EasyMock.reset(groupCounsumerRestMock);

    }

    @Test
    public void addStudent() throws Exception {
        EasyMock.expect(studenCounsumerRestMock.addStudent(student_in)).andReturn(student);
        EasyMock.replay(studenCounsumerRestMock);
        mockMvc.perform(post("/addStudent")
                .param("studentId",Integer.toString( student_in.getStudentId()))
                .param("studentName",student_in.getStudentName())
                .param("studentBirth",student_in.getStudentBirth().toString())
                .param("studentAvgMarks",Double.toString( student_in.getStudentAvgMarks()))
                .param("groupId",Integer.toString( student_in.getGroupId())))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(view().name("redirect:/students"));
        EasyMock.verify(studenCounsumerRestMock);
        EasyMock.reset(studenCounsumerRestMock);
    }

    @Test
    public void removeStudent() throws Exception {
        studenCounsumerRestMock.removeStudent(ID);
        EasyMock.replay(studenCounsumerRestMock);
        mockMvc.perform(get("/students/{id}/delete",ID))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/students"));
        EasyMock.verify(studenCounsumerRestMock);
        EasyMock.reset(studenCounsumerRestMock);
    }

//    @Test
//    public void filtrStudents() {
//
//    }
}