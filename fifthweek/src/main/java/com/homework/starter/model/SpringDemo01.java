package com.homework.starter.model;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo01 {
    
    public static void main(String[] args) {
        
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        Student student123 = context.getBean(Student.class);
        
        Student student123 = (Student) context.getBean("student123");
        System.out.println(student123.toString());


        Student student100 = (Student) context.getBean("student100");
        System.out.println(student100.toString());

        Student student = (Student) context.getBean("student");
        System.out.println(student.toString());



    }
}
