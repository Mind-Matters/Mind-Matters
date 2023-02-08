package com.MindMatters.application.Controllers;

import com.MindMatters.application.Repositories.UserRepo;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserRepo userDao;

    public UserController(UserRepo userDao){
        this.userDao = userDao;
    }



}
