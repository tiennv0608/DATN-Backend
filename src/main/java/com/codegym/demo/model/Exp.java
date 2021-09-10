package com.codegym.demo.model;

import javax.persistence.*;

@Entity
public class Exp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sill;
    private Integer year;
    private String salary;
    @ManyToOne
    private User user;

    public Exp() {
    }

    public Exp(Long id, String sill, Integer year, String salary, User user) {
        this.id = id;
        this.sill = sill;
        this.year = year;
        this.salary = salary;
        this.user = user;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSill() {
        return sill;
    }

    public void setSill(String sill) {
        this.sill = sill;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}