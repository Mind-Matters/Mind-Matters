package com.MindMatters.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "/login";
    }


    @GetMapping("/logout")
    public String showLogout(){
        return "/home";
    }

}