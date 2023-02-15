package com.MindMatters.application.Controllers;


import com.MindMatters.application.Repositories.UserRepository;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserRepository userDao;

    public UserController(UserRepository userDao){
        this.userDao = userDao;
    }


}
