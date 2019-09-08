package com.spring.hystrix.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolServiceController {

    @Autowired
    StudentServiceDelegate studentServiceDelegate;

    @RequestMapping(value = "/school", method = RequestMethod.GET)
    public String getStudents() {
        System.out.println("Going to call student service to get data!");
        return studentServiceDelegate.callStudentServiceAndGetData();
    }
}