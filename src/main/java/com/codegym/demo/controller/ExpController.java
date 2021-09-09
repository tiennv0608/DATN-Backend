package com.codegym.demo.controller;

import com.codegym.demo.model.Exp;
import com.codegym.demo.service.exp.IExpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/exp")
public class ExpController {
    @Autowired
    private IExpService expService;
    @GetMapping("")
    public ResponseEntity<Iterable<Exp>> findAll(){
        List<Exp> expIterable = (List<Exp>) expService.findAll();
        if(expIterable.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(expIterable, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Exp>findById(@PathVariable("id") Long id){
        Optional<Exp> expOptional = expService.findById(id);
        if(!expOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(expOptional.get(), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Exp> create(@RequestBody Exp exp){
        return new ResponseEntity<>(expService.save(exp),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Exp> update(@PathVariable("id") Long id, @RequestBody Exp exp){
        Optional<Exp>expOptional = expService.findById(id);
        if(!expOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        exp.setId(id);
        expService.save(exp);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Exp>delete(@PathVariable("id") Long id){
        Optional<Exp>expOptional=expService.findById(id);
        if(!expOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        expService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
