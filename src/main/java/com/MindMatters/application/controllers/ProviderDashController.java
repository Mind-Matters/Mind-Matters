package com.MindMatters.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ProviderDashController {
    @GetMapping("/provider-dashboard")
    public String goProviderDashboard(){
        return "provider-dashboard";
    }

}
