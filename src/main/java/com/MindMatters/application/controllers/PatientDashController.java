package com.MindMatters.application.controllers;


import com.MindMatters.application.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientDashController {
    @GetMapping("/patient-dashboard")
    public String goPatientDashboard(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "/patient-dashboard";
    }
}
