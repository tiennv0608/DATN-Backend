package com.codegym.demo.controller;

import com.codegym.demo.model.Province;
import com.codegym.demo.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/province")
public class ProvinceController {
@Autowired
    private IProvinceService provinceService;
@GetMapping("")
    public ResponseEntity<Iterable<Province>> findAll(){
    List<Province>provinceList = (List<Province>) provinceService.findAll();
    return new ResponseEntity<>(provinceList, HttpStatus.OK);
}

}
