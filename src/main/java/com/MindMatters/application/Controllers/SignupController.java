package com.MindMatters.application.Controllers;


import com.MindMatters.application.Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

//    @GetMapping("/signup")
//    public String showSignupForm(Model model){
//        model.addAttribute("user", new User());
//        return "/signup";
//    }
//
//    @PostMapping("/signup")
//    public String createUser(@ModelAttribute User user){
//        userDao.save(user);
//    }
}