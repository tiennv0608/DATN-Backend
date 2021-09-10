package com.codegym.demo.controller;

import com.codegym.demo.constant.Constant;
import com.codegym.demo.dto.request.CompanyLoginForm;
import com.codegym.demo.dto.request.CompanyRegisterForm;
import com.codegym.demo.dto.request.UserLoginForm;
import com.codegym.demo.dto.request.UserRegisterForm;
import com.codegym.demo.dto.response.JwtResponse;
import com.codegym.demo.dto.response.Response;
import com.codegym.demo.model.Company;
import com.codegym.demo.model.User;
import com.codegym.demo.security.jwt.CompanyJwtService;
import com.codegym.demo.security.jwt.UserJwtService;
import com.codegym.demo.security.principal.CompanyPrinciple;
import com.codegym.demo.security.principal.UserPrinciple;
import com.codegym.demo.service.company.ICompanyService;
import com.codegym.demo.service.email.EmailService;
import com.codegym.demo.service.user.IUserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.codegym.demo.dto.response.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/auth")
@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private IUserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserJwtService userJwtService;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private CompanyJwtService companyJwtService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmailService emailService;

    @PostMapping("/users/register")
    public ResponseEntity<?> register(@Validated @RequestBody UserRegisterForm registerForm, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        try {
            if (bindingResult.hasFieldErrors()) {
                return new ResponseEntity<>(new ResponseBody(Response.OBJECT_INVALID, null), HttpStatus.BAD_REQUEST);
            }
            if (companyService.existsByEmail(registerForm.getEmail()) || userService.existsByEmail(registerForm.getEmail())) {
                return new ResponseEntity<>(new ResponseBody(Response.EMAIL_IS_EXISTS, null), HttpStatus.CONFLICT);
            }
            User user = userService.register(registerForm);
            User user1 = userService.save(user);
            if (user1 != null) {
                emailService.sendVerificationEmail(user);
            }
            return new ResponseEntity<>(new ResponseBody(Response.SUCCESS, user1), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBody(Response.SYSTEM_ERROR, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users/login")
    public ResponseEntity<ResponseBody> login(@Validated @RequestBody UserLoginForm loginForm) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = userJwtService.generateTokenLogin(authentication);
            UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
            User currentUser = userService.findByEmail(loginForm.getEmail()).get();
            return new ResponseEntity<>(new ResponseBody(Response.SUCCESS,
                    new JwtResponse(currentUser.getId(), jwt, currentUser.getName(), userPrinciple.getAuthorities())),
                    HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ResponseBody(Response.OBJECT_NOT_FOUND, null), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/companies/register")
    public ResponseEntity<ResponseBody> registerMerchant(@Validated @RequestBody CompanyRegisterForm registerForm, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        try {
            if (bindingResult.hasFieldErrors()) {
                return new ResponseEntity<>(new ResponseBody(Response.OBJECT_INVALID, null), HttpStatus.BAD_REQUEST);
            }
            if (companyService.existsByCompanyName(registerForm.getCompanyName())) {
                return new ResponseEntity<>(new ResponseBody(Response.NAME_IS_EXISTS, null), HttpStatus.CONFLICT);
            }
            if (companyService.existsByEmail(registerForm.getEmail()) || userService.existsByEmail(registerForm.getEmail())) {
                return new ResponseEntity<>(new ResponseBody(Response.EMAIL_IS_EXISTS, null), HttpStatus.CONFLICT);
            }
            String encode = passwordEncoder.encode(registerForm.getPassword());
            Company company = new Company();
            companyService.save(company);
            String companyCode = company.getShortName().substring(0, 3) + company.getId() + (int) (Math.random() * (9999 - 1000) + 1000);
            company.setCompanyName(registerForm.getCompanyName().trim());
            company.setShortName(registerForm.getShortName().trim());
            company.setEmail(registerForm.getEmail().trim());
            company.setPassword(encode);
            company.setDescription(registerForm.getDescription());
            company.setImage(Constant.IMAGE_COMPANY_DEFAULT);
            company.setType(Constant.TypeName.COMPANY);
            company.setCompanyCode(companyCode);
            Company company1 = companyService.save(company);
            if (company1 != null) {
                emailService.sendVerificationEmailCompany(company);
            }
            return new ResponseEntity<>(new ResponseBody(Response.SUCCESS, company1), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBody(Response.SYSTEM_ERROR, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/companies/login")
    public ResponseEntity<ResponseBody> login(@Validated @RequestBody CompanyLoginForm companyLoginForm) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(companyLoginForm.getEmail(), companyLoginForm.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = companyJwtService.generateTokenLogin(authentication);
            CompanyPrinciple companyPrinciple = (CompanyPrinciple) authentication.getPrincipal();
            Company company = companyService.findByEmail(companyLoginForm.getEmail()).get();
            return new ResponseEntity<>(new ResponseBody(Response.SUCCESS,
                    new JwtResponse(company.getId(), jwt, company.getCompanyName(), companyPrinciple.getAuthorities())),
                    HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ResponseBody(Response.OBJECT_NOT_FOUND, null), HttpStatus.FORBIDDEN);
        }

    }
}