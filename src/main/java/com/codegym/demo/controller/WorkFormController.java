package com.codegym.demo.controller;

import com.codegym.demo.model.WorkForm;
import com.codegym.demo.service.workform.IWorkFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/work-forms")
@CrossOrigin("*")
public class WorkFormController {
    @Autowired
    IWorkFormService workFormService;

    @GetMapping
    public ResponseEntity<Iterable<WorkForm>> findAll() {
        List<WorkForm> workForms = (List<WorkForm>) workFormService.findAll();
        if (workForms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(workForms, HttpStatus.OK);
    }
}
