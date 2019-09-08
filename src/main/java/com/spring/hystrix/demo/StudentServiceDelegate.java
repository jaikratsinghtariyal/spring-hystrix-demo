package com.spring.hystrix.demo;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spring.hystrix.demo.bean.Address;
import com.spring.hystrix.demo.bean.Name;
import com.spring.hystrix.demo.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceDelegate {

    @Autowired
    private
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "callStudentServiceAndGetData_Fallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60")
            })
    public List callStudentServiceAndGetData() {

       /* String response = restTemplate
                .exchange("http://localhost:8083/student"
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<String>() {
                        }, "").getBody();*/

        List response = restTemplate
                .getForEntity("https://spring-boot-demo-delightful-bonobo.cfapps.io/student", List.class, "").getBody();

        System.out.println("Response Received as " + response + " -  " + new Date());

        System.out.println( "NORMAL FLOW !!! :::  " +
                " Student Details " + response + " -  " + new Date());

        return response;
    }

    @SuppressWarnings("unused")
    private List<Student> callStudentServiceAndGetData_Fallback() {

        System.out.println("Student Service is down!!! fallback route enabled...");

        Address address = new Address("blank", "blank", "blank");
        Name name = new Name("blank", "blank");
        Student student = new Student(address, name, Boolean.TRUE);

        System.out.println("CIRCUIT BREAKER ENABLED!!! No Response From Student Service at this moment. " +
                " Service will be back shortly - " + new Date());

        return Collections.singletonList(student);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}