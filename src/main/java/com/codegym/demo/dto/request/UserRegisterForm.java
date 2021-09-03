package com.codegym.demo.dto.request;


import com.codegym.demo.constant.Constant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegisterForm {
    @NotBlank
    @Size(min = 6, max = 50)
    @Pattern(regexp = "^[a-z]{1}[a-z0-9._-]{5,15}$")
    private String username;

    @Pattern(regexp = "^[+84]+[0-9]{9}$")
    private String phoneNumber;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
    private Constant.TypeName type;

    public UserRegisterForm() {
    }

    public Constant.TypeName getType() {
        return type;
    }

    public void setChannel(Constant.TypeName channel) {
        this.type = channel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

}
