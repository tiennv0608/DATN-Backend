package com.codegym.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Salary salary;

    private String address;

    private String position;
    @ManyToOne
    private Exp exp;

    @ManyToOne
    private WorkForm workForm;

    private Date expiredDate;

    private String description;
    @NotBlank
    private int quantity;

    @ManyToOne
    private Gender gender;

    private String code;

    @ManyToOne
    private Company company;

    @ManyToOne
    private City city;
    public Post() {
    }

    public Post(String title, Category category, Salary salary, String address, String position, Exp exp, WorkForm workForm, Date expiredDate, String description, int quantity, Gender gender, String code, Company company, City city) {
        this.title = title;
        this.category = category;
        this.salary = salary;
        this.address = address;
        this.position = position;
        this.exp = exp;
        this.workForm = workForm;
        this.expiredDate = expiredDate;
        this.description = description;
        this.quantity = quantity;
        this.gender = gender;
        this.code = code;
        this.company = company;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public WorkForm getWorkForm() {
        return workForm;
    }

    public void setWorkForm(WorkForm workForm) {
        this.workForm = workForm;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }
}
