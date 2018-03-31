package com.epam.brest.dao;

import com.epam.brest.dto.StudentDTO;
import com.epam.brest.model.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml", "classpath:dao.xml", "classpath:test-dao.xml"})
@Rollback
@Transactional
public class StudentDaoImplTest {

    @Autowired
    StudentDao studentDao;

    /**
     * getallStudentsDTO method test
     */
    @Test
    public void getallStudentstest() {
        Collection<StudentDTO> studentDTOS = studentDao.getallStudentsDTO();
        Assert.assertNotNull(studentDTOS);
    }

    /**
     * addStudent method test
     */
    @Test
    public void addStudent() throws ParseException {
        Student student = new Student();
        student.setStudentName("Ivan Ivanov");
        student.setStudentAvgMarks(7.2);
        student.setGroupId(1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = simpleDateFormat.parse("31.05.1990");
        student.setStudentBirth(new java.sql.Date(date.getTime()));

        int size_brefore = studentDao.getallStudentsDTO().size();
        Student student_out = studentDao.addStudent(student);
        int size_after = studentDao.getallStudentsDTO().size();
        Assert.assertTrue(size_brefore + 1 == size_after);
        Assert.assertTrue(student.getStudentName().equals(student_out.getStudentName()));
        Assert.assertTrue(student.getStudentBirth().equals(student_out.getStudentBirth()));
        Assert.assertTrue(student.getGroupId() == student_out.getGroupId());
        Assert.assertTrue(student.getStudentAvgMarks() == student_out.getStudentAvgMarks());
        Assert.assertTrue(student.getGroupId() != 0);
    }

    /**
     * getStudentById method test
     */
    @Test
    public void getStudentByIdTest() throws ParseException {
        Student student = new Student();
        student.setStudentName("Ivan Ivanov");
        student.setStudentAvgMarks(7.2);
        student.setGroupId(1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = simpleDateFormat.parse("31.05.1990");
        student.setStudentBirth(new java.sql.Date(date.getTime()));

        Student student_out = studentDao.addStudent(student);

        Student student_byid = studentDao.getStudentById(student_out.getStudentId());
        Assert.assertTrue(student_byid.getStudentName().equals(student_out.getStudentName()));
        Assert.assertTrue(student_byid.getStudentBirth().equals(student_out.getStudentBirth()));
        Assert.assertTrue(student_byid.getGroupId() == student_out.getGroupId());
        Assert.assertTrue(student_byid.getStudentAvgMarks() == student_out.getStudentAvgMarks());
        Assert.assertTrue(student_byid.getStudentId() == student_out.getStudentId());
    }

    /**
     * getStudentById method test
     */
    @Test(expected = IllegalArgumentException.class)
    public void getStudentByIdTest2() {
        studentDao.getStudentById(100000);
    }


}