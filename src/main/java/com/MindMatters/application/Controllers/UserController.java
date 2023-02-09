package com.MindMatters.application.Controllers;

import com.MindMatters.application.Models.User;
import com.MindMatters.application.Repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserRepo userDao;

    public UserController(UserRepo userDao){
        this.userDao = userDao;
    }


}
