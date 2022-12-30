package com.example.controller;

/*
    created by Atiye Mousavi
    Date: 12/30/2022
    Time: 5:04 PM
*/


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/")
    public String getLoginPage() {
        return "You have successfully logged in Using Spring Security LDAP Authentication!";
    }
}