package com.example.dev.controller;

/*
    created by Atiye Mousavi
    Date: 12/28/2022
    Time: 9:30 PM
*/


import com.example.dev.entity.User;
import com.example.dev.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;


@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    // Go to Registration Page
    @GetMapping("/register")
    public String register() {
        return "registerUser";
    }

    // Read Form data to save into DB
    @PostMapping("/saveUser")
    public String saveUser(
            @ModelAttribute User user,
            Model model
    )
    {
        Integer id = userService.saveUser(user);
        String message = "User '"+id+"' saved successfully !";
        model.addAttribute("msg", message);
        return "registerUser";
    }
}