package com.MindMatters.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginError", "");
        return "/login";
    }

    @GetMapping("/login?error")
    public String showLoginError(Model model){
        model.addAttribute("loginError", "Invalid username or password.");
        return "/login";
    }

    @GetMapping("/logout")
    public String showLogout(){
        return "/home";
    }

}