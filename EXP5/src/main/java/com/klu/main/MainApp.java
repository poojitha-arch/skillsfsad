package com.klu.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.klu.config.AppConfig;
import com.klu.model.Student;
import com.klu.model.Certification;

public class MainApp {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Student student = context.getBean(Student.class);

        System.out.println("----- Student Details -----");
        System.out.println("Student ID      : " + student.getId());
        System.out.println("Student Name    : " + student.getName());
        System.out.println("Gender          : " + student.getGender());

        Certification cert = student.getCertification();

        System.out.println("\n----- Certification Details -----");
        System.out.println("Certification ID   : " + cert.getId());
        System.out.println("Certification Name : " + cert.getName());
        System.out.println("Completed On       : " + cert.getDateOfCompletion());
    }
}
