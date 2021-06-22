package com.codegym.model;

import javax.validation.constraints.*;

public class User {
    @NotEmpty
    @Size(max = 45, min = 5)
    private String firstName;

    @NotEmpty
    @Size(max = 45, min = 5)
    private String lastName;

    @Pattern(regexp = "^[0-9]{10,11}",message = "sai số")
    private String phoneNumber;


    @Min(value = 18, message = "Lớn hơn hoặc bằng 18")
    private int age;

    @NotEmpty
    @Pattern(regexp = "^(.+)@(.+)$", message = "Sai định dạng email")
    private String email;

    public User() {
    }

    public User(String firstName, String lastName, String phoneNumber, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
