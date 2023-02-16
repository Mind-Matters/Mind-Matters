package com.MindMatters.application.controllers;


import com.MindMatters.application.repositories.UserRepository;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserRepository userDao;

    public UserController(UserRepository userDao){
        this.userDao = userDao;
    }


}
