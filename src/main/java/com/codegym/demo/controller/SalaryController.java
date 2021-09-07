package com.codegym.demo.controller;

import com.codegym.demo.model.City;
import com.codegym.demo.model.Salary;
import com.codegym.demo.service.salary.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/salaries")
@CrossOrigin("*")
public class SalaryController {
    @Autowired
    ISalaryService salaryService;

    @GetMapping
    public ResponseEntity<Iterable<Salary>> findAll() {
        List<Salary> salaries = (List<Salary>) salaryService.findAll();
        if (salaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(salaries, HttpStatus.OK);
    }
}
