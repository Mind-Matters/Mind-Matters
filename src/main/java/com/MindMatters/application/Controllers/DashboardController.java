package com.MindMatters.application.Controllers;

import com.MindMatters.application.Models.User;
import com.MindMatters.application.Repositories.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    public final UserRepo userDao;

    public DashboardController(UserRepo userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/dashboard")
    public String goDashboard(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(loggedInUser.getIsProvider()) {
            // get pending users list
            List<User> pendingUsers = userDao.findByIsVerified(false);
            model.addAttribute("pendingUsers", pendingUsers);
            return "provider-dashboard";
        }
        else {
            return "patient-dashboard";
        }

    }
}
