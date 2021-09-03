package com.codegym.demo.dto.request;

public class CompanyRegisterForm {
    private String name;
    private String shortName;
    private String email;
    private String password;
    private String description;
    private String type;

    public CompanyRegisterForm() {
    }

    public CompanyRegisterForm(String name, String shortName, String email, String password, String description) {
        this.name = name;
        this.shortName = shortName;
        this.email = email;
        this.password = password;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
