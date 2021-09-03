package com.codegym.demo.controller;

import com.codegym.demo.constant.Constant;
import com.codegym.demo.dto.request.MerchantRegisterForm;
import com.codegym.demo.dto.request.UserLoginForm;
import com.codegym.demo.dto.request.UserRegisterForm;
import com.codegym.demo.dto.response.JwtResponse;
import com.codegym.demo.dto.response.Response;
import com.codegym.demo.model.Company;
import com.codegym.demo.model.User;
import com.codegym.demo.security.jwt.CompanyJwtService;
import com.codegym.demo.security.jwt.UserJwtService;
import com.codegym.demo.security.principal.UserPrinciple;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalTime;
import com.codegym.demo.dto.response.ResponseBody;
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

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody UserRegisterForm registerForm, BindingResult bindingResult) {
        try {
            if (bindingResult.hasFieldErrors()) {
                return new ResponseEntity<>(new ResponseBody(Response.OBJECT_INVALID, null), HttpStatus.BAD_REQUEST);
            }
            if (userService.existsByEmail(registerForm.getUsername())) {
                return new ResponseEntity<>(new ResponseBody(Response.USERNAME_IS_EXISTS, null), HttpStatus.CONFLICT);
            }
            if (userService.existsByEmail(registerForm.getEmail())) {
                return new ResponseEntity<>(new ResponseBody(Response.EMAIL_IS_EXISTS, null), HttpStatus.CONFLICT);
            }
            User user = new User(registerForm.getUsername(), registerForm.getPassword(), registerForm.getUsername(), registerForm.getEmail(), registerForm.getPhoneNumber());
            user.setType(Constant.TypeName.USER);
            return new ResponseEntity<>(new ResponseBody(Response.SUCCESS, userService.save(user)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBody(Response.SYSTEM_ERROR, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseBody> login(@Validated @RequestBody UserLoginForm loginForm) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = userJwtService.generateTokenLogin(authentication);
            UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
            User currentUser = userService.findByEmail(loginForm.getUsername()).get();
            return new ResponseEntity<ResponseBody>(new ResponseBody(Response.SUCCESS,
                    new JwtResponse(currentUser.getId(), jwt, userPrinciple.getUsername(), currentUser.getName(), userPrinciple.getAuthorities())),
                    HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ResponseBody(Response.OBJECT_NOT_FOUND, null), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/merchants/register")
    public ResponseEntity<ResponseBody> registerMerchant(@Validated @RequestBody MerchantRegisterForm registerForm, BindingResult bindingResult) {
        try {
            if (bindingResult.hasFieldErrors()) {
                return new ResponseEntity<>(new ResponseBody(Response.OBJECT_INVALID, null), HttpStatus.BAD_REQUEST);
            }
            if (companyService.existsByCompanyName(registerForm.getTaxIdentificationNumber())) {
                return new ResponseEntity<>(new ResponseBody(Response.TAXCODE_IS_EXISTS, null), HttpStatus.CONFLICT);
            }
            if (companyService.existsByEmail(registerForm.getUsername())) {
                return new ResponseEntity<>(new ResponseBody(Response.USERNAME_IS_EXISTS, null), HttpStatus.CONFLICT);
            }
            if (companyService.existsByEmail(registerForm.getEmail())) {
                return new ResponseEntity<>(new ResponseBody(Response.EMAIL_IS_EXISTS, null), HttpStatus.CONFLICT);
            }
            Company company = new Company(
                    registerForm.
                    registerForm.getPassword(),
                    registerForm.getName(),
                    registerForm.getEmail(),
                    registerForm.getPhoneNumber(),
                    registerForm.getAddress(),
                    registerForm.getRegistrationCertificate(),
                    registerForm.getTaxIdentificationNumber(),
                    registerForm.getRepresentative());

            return new ResponseEntity<>(new ResponseBody(Response.SUCCESS, merchantService.save(company)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBody(Response.SYSTEM_ERROR, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/merchants/login")
    public ResponseEntity<ResponseBody> login(@Validated @RequestBody MerchantLoginForm merchantLoginForminForm) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(merchantLoginForminForm.getUsername(), merchantLoginForminForm.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = merchantJwtService.generateTokenLogin(authentication);
            MerchantPrinciple merchantPrinciple = (MerchantPrinciple) authentication.getPrincipal();
            Merchant merchant = merchantService.findMerchantByUsername(merchantLoginForminForm.getUsername()).get();
            return new ResponseEntity<ResponseBody>(new ResponseBody(Response.SUCCESS,
                    new JwtResponse(merchant.getId(), jwt, merchantPrinciple.getUsername(), merchant.getName(), merchantPrinciple.getAuthorities())),
                    HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<ResponseBody>(new ResponseBody(Response.OBJECT_NOT_FOUND, null), HttpStatus.FORBIDDEN);
        }
    }
}