package com.sugandrey.FirstRestApp.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonDTO {

    @NotEmpty
    @Size(min = 2, max = 100, message = "Name should be grater than 2 and less than 100")
    private String userName;
    @NotNull
    @Min(value = 0, message = "Age should be grater 0")
    private Integer age;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
