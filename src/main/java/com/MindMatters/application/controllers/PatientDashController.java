package com.MindMatters.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientDashController {
    @GetMapping("/patient-dashboard")
    public String goPatientDashboard(){
        return "/patient-dashboard";
    }
}