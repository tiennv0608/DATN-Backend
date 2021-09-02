package com.codegym.demo.controller;

import com.codegym.demo.dto.request.LoginForm;
import com.codegym.demo.dto.request.RegisterForm;
import com.codegym.demo.dto.response.JwtResponse;
import com.codegym.demo.dto.response.ResponseMessage;
import com.codegym.demo.model.Company;
import com.codegym.demo.model.User;
import com.codegym.demo.security.jwt.JwtProvider;
import com.codegym.demo.security.userprincipal.UserPrinciple;
import com.codegym.demo.service.company.ICompanyService;
import com.codegym.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auths")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    IUserService userService;

    @Autowired
    ICompanyService companyService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterForm registerForm) {
        switch (registerForm.getType()) {
            case "ROLE_USER":
                if (userService.existsByEmail(registerForm.getEmail())) {
                    return new ResponseEntity<>(new ResponseMessage("Duplicated email"), HttpStatus.OK);
                }
                User user = new User(registerForm.getName(), registerForm.getEmail(), passwordEncoder.encode(registerForm.getPassword()));
                user.setType(registerForm.getType());
                userService.save(user);
                break;
            case "ROLE_COMPANY":
                if (companyService.existsByEmail(registerForm.getEmail())) {
                    return new ResponseEntity<>(new ResponseMessage("Duplicated email"), HttpStatus.OK);
                }
                Company company = new Company(registerForm.getName(), registerForm.getEmail(), passwordEncoder.encode(registerForm.getPassword()), registerForm.getDescription());
                company.setType(registerForm.getType());
                companyService.save(company);
                break;
            default:
        }
        return new ResponseEntity<>(new ResponseMessage("Register successfully!"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginByUser(@Valid @RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getName(), userPrinciple.getAuthorities()));
    }
}
