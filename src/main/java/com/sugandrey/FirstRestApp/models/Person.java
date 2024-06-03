package com.sugandrey.FirstRestApp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotEmpty
    @Size(min = 2, max = 100, message = "Name should be grater than 2 and less than 100")
    private String userName;

    @Column(name = "age")
    @NotNull
    @Min(value = 0, message = "Age should be grater 0")
    private Integer age;

    @Column(name = "email")
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "updated_at")
    private String updatedAt;
    @Column(name = "created_who")
    @NotEmpty
    private String createdWho;

    public Person(final String userName, final Integer age) {
        this.userName = userName;
        this.age = age;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(final String createdWho) {
        this.createdWho = createdWho;
    }

    @Override
    public String toString() {
        return "Person{" +
               "id=" + id +
               ", userName='" + userName + '\'' +
               ", yearOfBirth=" + age +
               ", password='" + email + '\'' +
               '}';
    }
}
