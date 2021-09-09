package com.codegym.demo.model;

import javax.persistence.*;

@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Company company;

    @ManyToOne
    private CV cv;

    public Candidate() {
    }

    public Candidate(Company company, CV cv) {
        this.company = company;
        this.cv = cv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public CV getCv() {
        return cv;
    }

    public void setCv(CV cv) {
        this.cv = cv;
    }
}
