package com.MindMatters.application.Controllers;


import com.MindMatters.application.Models.User;
import com.MindMatters.application.Repositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SignupController {

    private UserRepo userDao;

    private PasswordEncoder passwordEncoder;

    public SignupController(UserRepo userDao, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model){
        List <User> isProviders = userDao.findByIsProvider(true);


        model.addAttribute("user", new User());
        model.addAttribute("providers", isProviders);
        return "/signup";
    }

    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setIsVerified(false);
        userDao.save(user);
        return "/home";
    }


}
