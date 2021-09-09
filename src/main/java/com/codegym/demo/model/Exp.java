package com.codegym.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Exp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String work;
    private Integer year;
    @ManyToOne
    private User user;

    public Exp() {
    }

    public Exp(Long id, String work, Integer year, User user) {
        this.id = id;
        this.work = work;
        this.year = year;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
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
