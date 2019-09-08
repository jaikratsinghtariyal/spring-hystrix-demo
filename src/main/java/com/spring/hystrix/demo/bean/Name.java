package com.spring.hystrix.demo.bean;

public class Name {
    private String firstName;
    private String secondName;

    public Name(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getSecondName() {
        return secondName;
    }

}
