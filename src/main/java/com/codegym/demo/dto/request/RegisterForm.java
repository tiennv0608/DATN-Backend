package com.codegym.demo.dto.request;

public class RegisterForm {
    private String name;
    private String email;
    private String password;
    private String description;
    private String type;

    public RegisterForm() {
    }

    public RegisterForm(String name, String email, String password, String description, String type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.description = description;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
