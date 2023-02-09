package com.MindMatters.application.Controllers;

import com.MindMatters.application.Models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String goDashboard(){
       User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(loggedInUser.getIsProvider())
            return "provider-dashboard";
        else
            return "patient-dashboard";

    }
}
