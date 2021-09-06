package com.codegym.demo.controller;

import com.codegym.demo.dto.response.PostResponse;
import com.codegym.demo.model.Company;
import com.codegym.demo.model.User;
import com.codegym.demo.service.post.IPostService;
import com.codegym.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    private IPostService postService;

    @GetMapping
    public ResponseEntity<Iterable<User>> findAll() {
        List<User> users = (List<User>) userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}

