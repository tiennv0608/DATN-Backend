package com.codegym.demo.controller;

import com.codegym.demo.model.Company;
import com.codegym.demo.model.Post;
import com.codegym.demo.service.company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
@CrossOrigin("*")
public class CompanyController {
    @Autowired
    ICompanyService companyService;

    @GetMapping
    public ResponseEntity<Iterable<Company>> findAll() {
        List<Company> companies = (List<Company>) companyService.findAll();
        if (companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id) {
        Optional<Company> companyOptional = companyService.findById(id);
        if (!companyOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(companyOptional.get(), HttpStatus.OK);
    }
}
