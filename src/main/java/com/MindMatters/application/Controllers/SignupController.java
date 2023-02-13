package com.MindMatters.application.Controllers;


import com.MindMatters.application.Models.ProviderPatient;
import com.MindMatters.application.Models.User;
import com.MindMatters.application.Repositories.ProviderPatientRepository;
import com.MindMatters.application.Repositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SignupController {

    private final UserRepo userDao;

    private final PasswordEncoder passwordEncoder;

    private final ProviderPatientRepository providerPatientDao;

    public SignupController(UserRepo userDao, PasswordEncoder passwordEncoder, ProviderPatientRepository providerPatientDao){
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.providerPatientDao = providerPatientDao;
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model){
        List <User> isProviders = userDao.findByIsProvider(true);


        model.addAttribute("user", new User());
        model.addAttribute("providers", isProviders);
        return "/signup";
    }

    @PostMapping("/signup")
    public String createPatient(@ModelAttribute User user, @RequestParam(name = "providerId") long providerId, @RequestParam(name = "isProvider") boolean isProvider){
        if(!isProvider){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setIsVerified(false);
        userDao.save(user);
        User provider = userDao.findById(providerId);
        ProviderPatient providerPatient = new ProviderPatient(provider, user);
        providerPatientDao.save(providerPatient);
        } else {
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            user.setIsVerified(false);
            userDao.save(user);
        }
        return "/home";
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
