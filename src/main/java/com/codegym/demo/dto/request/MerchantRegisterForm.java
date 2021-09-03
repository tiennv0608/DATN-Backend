package com.codegym.demo.dto.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CompanyRegisterForm {
    @NotBlank
    @Size(min = 6, max = 50)
    @Pattern(regexp = "^[a-z]{1}[a-z0-9._-]{5,15}$")
    private String username;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    @Size(min = 3, max = 100)
    private String representative;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Pattern(regexp = "^[+84]+[0-9]{9}$")
    private String phoneNumber;

    @NotBlank
    private String registrationCertificate;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    private String taxIdentificationNumber;

    private Constant.ChannelName channel;

    public MerchantRegisterForm() {
    }

    public MerchantRegisterForm(String username, String password, String name, String address, String representative, String email, String phoneNumber, String registrationCertificate, String taxIdentificationNumber, Constant.ChannelName channel) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.representative = representative;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registrationCertificate = registrationCertificate;
        this.taxIdentificationNumber = taxIdentificationNumber;
        this.channel = channel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRegistrationCertificate() {
        return registrationCertificate;
    }

    public void setRegistrationCertificate(String registrationCertificate) {
        this.registrationCertificate = registrationCertificate;
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public void setTaxIdentificationNumber(String taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    public Constant.ChannelName getChannel() {
        return channel;
    }

    public void setChannel(Constant.ChannelName channel) {
        this.channel = channel;
    }
}
