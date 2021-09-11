package com.codegym.demo.controller;

import com.codegym.demo.model.CV;
import com.codegym.demo.model.Candidate;
import com.codegym.demo.service.candidate.ICandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
@CrossOrigin("*")
public class CandidateController {
@Autowired
private ICandidateService iCandidateService;
    @GetMapping
    public ResponseEntity<Iterable<Candidate>> findAll() {
        List<Candidate> cvs = (List<Candidate>) iCandidateService.findAll();
        if (cvs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cvs, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Candidate> save(@RequestBody Candidate cv) {
        return new ResponseEntity<>(iCandidateService.save(cv), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Candidate>> findByUserId(@PathVariable Long id){
        List<Candidate> candidates = (List<Candidate>) iCandidateService.findAllByPostId(id);
        if (candidates.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }
}
