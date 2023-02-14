package com.MindMatters.application.Controllers;


import com.MindMatters.application.Models.User;
import com.MindMatters.application.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SignupController {

    private final UserRepository userDao;

    private final PasswordEncoder passwordEncoder;


    public SignupController(UserRepository userDao, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model){
        List <User> isProviders = userDao.findByIsProvider(true);


        model.addAttribute("user", new User());
        model.addAttribute("providers", isProviders);
        return "signup";
    }

    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user){
        // user now has provider id, make sure it is in view's signup form

    // public String createPatient(@ModelAttribute User user, @RequestParam(name = "providerId") long providerId, @RequestParam(name = "isProvider") boolean isProvider){
        if(!user.getIsProvider()){

            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            user.setIsVerified(false);
            userDao.save(user);

        // User provider = userDao.findById(providerId);
        // ProviderPatient providerPatient = new ProviderPatient(provider, user);
        // providerPatientDao.save(providerPatient);
        } else {
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            user.setIsVerified(false);
            userDao.save(user);
        }
        return "splash";
    }
//    @PostMapping("/signup")
//    public String createUser(@ModelAttribute User user){
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        user.setIsVerified(false);
//        userDao.save(user);
//        return "/home";
//    }


}
