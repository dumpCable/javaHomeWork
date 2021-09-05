package com.homework.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConditionalOnProperty(prefix = "school", name = "start", havingValue = "true")
public class SchoolAutoConfiguration {

    @Bean("student")
    public Student getStudent(){
        Student student = new Student();
        student.setName("Student");
        student.setId(111);
        return student;
    }
    @Bean("klass")
    public Klass getKlass(List<Student> students){
       Klass klass = new Klass();
       klass.setStudents(students);
       return klass;
    }
    @Bean("school")
    public School getSchool(Klass klass, Student student){
        School school = new School();
        school.setKlass(klass);
        school.setStudent100(student);
        return school;
    }
}
