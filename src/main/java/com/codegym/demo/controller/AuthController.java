package com.codegym.demo.controller;

import com.codegym.demo.dto.request.CompanyLoginForm;
import com.codegym.demo.dto.request.CompanyRegisterForm;
import com.codegym.demo.dto.request.UserLoginForm;
import com.codegym.demo.dto.request.UserRegisterForm;
import com.codegym.demo.dto.response.JwtResponse;
import com.codegym.demo.dto.response.ResponseMessage;
import com.codegym.demo.model.Company;
import com.codegym.demo.model.User;
import com.codegym.demo.security.jwt.CompanyJwtProvider;
import com.codegym.demo.security.jwt.UserJwtProvider;
import com.codegym.demo.security.userprincipal.CompanyPrinciple;
import com.codegym.demo.security.userprincipal.UserPrinciple;
import com.codegym.demo.service.company.ICompanyService;
import com.codegym.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    UserJwtProvider userJwtProvider;

    @Autowired
    CompanyJwtProvider companyJwtProvider;

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterForm userRegisterForm) {
        if (userService.existsByEmail(userRegisterForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Duplicated email"), HttpStatus.OK);
        }
        User user = new User(userRegisterForm.getName(), userRegisterForm.getEmail(), passwordEncoder.encode(userRegisterForm.getPassword()));
        user.setType("ROLE_USER");
        userService.save(user);
        return new ResponseEntity<>(new ResponseMessage("Register successfully!"), HttpStatus.CREATED);
    }

    @PostMapping("/company/register")
    public ResponseEntity<?> registerCompany(@Valid @RequestBody CompanyRegisterForm companyRegisterForm) {
        if (companyService.existsByEmail(companyRegisterForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Duplicated email"), HttpStatus.OK);
        }
        if (companyService.existsByCompanyName(companyRegisterForm.getName())) {
            return new ResponseEntity<>(new ResponseMessage("Duplicated company name"), HttpStatus.OK);
        }
        Company company = new Company(companyRegisterForm.getName(), companyRegisterForm.getShortName(), companyRegisterForm.getEmail(), passwordEncoder.encode(companyRegisterForm.getPassword()), companyRegisterForm.getDescription());
        company.setType("ROLE_COMPANY");
        companyService.save(company);
        String companyCode = companyRegisterForm.getShortName().substring(0, 3) + company.getId() + (int) ((Math.random() * (9999 - 1000)) + 1000);
        company.setCompanyCode(companyCode);
        companyService.save(company);
        return new ResponseEntity<>(new ResponseMessage("Register successfully!"), HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> loginByUser(@Valid @RequestBody UserLoginForm userLoginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginForm.getEmail(), userLoginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = userJwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        User user = userService.findByEmail(userLoginForm.getEmail()).get();
        return ResponseEntity.ok(new JwtResponse(user.getId(), token, userPrinciple.getName(), userPrinciple.getAuthorities()));
    }

    @PostMapping("/company/login")
    public ResponseEntity<?> loginByCompany(@Valid @RequestBody CompanyLoginForm companyLoginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(companyLoginForm.getEmail(), companyLoginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = companyJwtProvider.createToken(authentication);
        CompanyPrinciple companyPrinciple = (CompanyPrinciple) authentication.getPrincipal();
        Company company = companyService.findByEmail(companyLoginForm.getEmail()).get();
        return ResponseEntity.ok(new JwtResponse(company.getId(), token, companyPrinciple.getName(), companyPrinciple.getAuthorities()));
    }
}
