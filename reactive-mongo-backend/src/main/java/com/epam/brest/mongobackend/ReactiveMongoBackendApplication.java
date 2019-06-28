package com.epam.brest.mongobackend;

import com.epam.brest.mongobackend.model.Group;
import com.epam.brest.mongobackend.model.Student;
import com.epam.brest.mongobackend.repository.ReactiveGroupRepository;
import com.epam.brest.mongobackend.repository.ReactiveStudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ReactiveMongoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMongoBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ReactiveGroupRepository groupRepository, ReactiveStudentRepository studentRepository) throws ParseException {
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-mm-dd");
        Student st1=new Student("1","Ivan Ivanov",dateFormat.parse("1985-12-01"),7.3);
        Student st2=new Student("2","Petr Petrov",dateFormat.parse("1985-01-30"),3.3);
        Student st3=new Student("3","Sidor Sidorov",dateFormat.parse("1995-03-01"),2.1);
        Student st4=new Student("4","Semen Semenov",dateFormat.parse("1987-04-02"),4.3);
        Student st5=new Student("5","Kostia Kostin",dateFormat.parse("1983-07-22"),9.7);
        Group[] groupsArr= {new Group("1","A12","Architecture 12","Learn Architecture",
                Arrays.asList(st1, st2)),
                new Group("2","A17","Architecture 17","Learn Architecture",
                        Arrays.asList( st3,st4)),
                new Group("3","R15","Roads 5","Learn roads building",
                        Arrays.asList(st5)),
                new Group("4","E30","Electronics and programming","Learn programable microcontrollers",
                        Arrays.asList())};

        return strings -> {
            List<Group>groups =Arrays.asList(groupsArr);
            List<Student>students = Arrays.asList( st1, st2,  st3,  st4, st5
            );
            groupRepository.saveAll( groups)
                    .subscribe(System.out::println);

            studentRepository.saveAll(students)
                    .subscribe(System.out::println);
        };
    }
}
