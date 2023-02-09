package com.MindMatters.application.Controllers;


import com.MindMatters.application.Models.User;
import com.MindMatters.application.Controllers.Repositories.UserRepo;
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
        List <User> isproviders = userDao.findAll();
        List<User> providers = new ArrayList<User>();
        for( User provider : isproviders) {
            if (provider.getIsProvider()) {
                providers.add(provider);
            }
        }

        model.addAttribute("user", new User());
        model.addAttribute("providers", providers);
        return "/signup";
    }

    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "/home";
    }

//    @GetMapping("/signup")
//    public String allProviders(Model model){
//
//        model.addAttribute("providers", userDao.findAll());
//        return "/login";
//    }


}
