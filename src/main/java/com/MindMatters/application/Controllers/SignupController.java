package com.MindMatters.application.Controllers;


import com.MindMatters.application.Models.Event;
import com.MindMatters.application.Models.User;
import com.MindMatters.application.Repositories.EventRepository;
import com.MindMatters.application.Repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SignupController {

    private final UserRepository userDao;

    private final PasswordEncoder passwordEncoder;

    private final EventRepository eventDao;


    public SignupController(UserRepository userDao, PasswordEncoder passwordEncoder, EventRepository eventDao){
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.eventDao = eventDao;
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
        // user now has provider id, make sure it is in view's signup form

    // public String createPatient(@ModelAttribute User user, @RequestParam(name = "providerId") long providerId, @RequestParam(name = "isProvider") boolean isProvider){
        if(!user.getIsProvider()){

            // hash the password before submitting to db
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            user.setIsVerified(false); // set to false until provider verifies identity
            userDao.save(user);

            // build default user event: Welcome to Mind Matters... also, our calendar requires at least one event to show up patient dashboard
            Event event = new Event();
            event.setUser(userDao.findByUsername(user.getUsername())); // get the user from db, so it has the user id
            event.setTitle("Welcome to Mind Matters!");
            event.setDescription("Welcome to Mind Matters! We are so excited to have you as a part of our community. We are here to help you with your mental health needs. Please feel free to reach out to us with any questions or concerns you may have.");
            Date eventDate = new Date();
            event.setDate(eventDate);
            eventDao.save(event);

        // User provider = userDao.findById(providerId);
        // ProviderPatient providerPatient = new ProviderPatient(provider, user);
        // providerPatientDao.save(providerPatient);
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
