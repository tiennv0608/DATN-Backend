package com.codegym.demo.controller;

import com.codegym.demo.model.Company;
import com.codegym.demo.service.company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
@CrossOrigin("*")
public class CompanyController {
    @Autowired
    ICompanyService companyService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<Iterable<Company>> findAll() {
        List<Company> companies = (List<Company>) companyService.findAll();
        if (companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> edit(@PathVariable Long id, @RequestBody Company company) {
        Optional<Company> companyOptional = companyService.findById(id);
        if (!companyOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        company.setId(id);
        company.setEmail(companyOptional.get().getEmail());
        company.setType(companyOptional.get().getType());
        if (company.getCompanyName() == null || company.getCompanyName().trim().equals("")) {
            company.setCompanyName(companyOptional.get().getCompanyName());
        }
        if (company.getShortName() == null || company.getShortName().trim().equals("")) {
            company.setShortName(companyOptional.get().getShortName());
        }
        if (company.getCompanyCode() == null || company.getCompanyCode().trim().equals("")) {
            company.setCompanyName(companyOptional.get().getCompanyCode());
        }
        if (company.getAddress() == null || company.getAddress().trim().equals("")) {
            company.setAddress(companyOptional.get().getAddress());
        }
        if (company.getBranch() == null || company.getBranch().trim().equals("")) {
            company.setBranch(companyOptional.get().getBranch());
        }
        if (company.getPassword() == null || company.getPassword().trim().equals("")) {
            company.setPassword(companyOptional.get().getPassword());
        } else {
            String encode = passwordEncoder.encode(company.getPassword());
            company.setPassword(encode);
        }
        if (company.getImage() == null || company.getImage().trim().equals("")) {
            company.setImage(companyOptional.get().getImage());
        }
        if (company.getLinkGoogle() == null || company.getLinkGoogle().trim().equals("")) {
            company.setLinkGoogle(companyOptional.get().getLinkGoogle());
        }
        if (company.getWebsite() == null || company.getWebsite().trim().equals("")) {
            company.setWebsite(companyOptional.get().getWebsite());
        }
        if (company.getDescription() == null || company.getDescription().trim().equals("")) {
            company.setDescription(companyOptional.get().getDescription());
        }
        if (company.getPhone() == null || company.getPhone().trim().equals("")) {
            company.setPhone(companyOptional.get().getPhone());
        }
        if (company.getNumberOfStaff() == 0) {
            company.setNumberOfStaff(companyOptional.get().getNumberOfStaff());
        }
        companyService.save(company);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
}
