package com.MindMatters.application.Controllers;


import com.MindMatters.application.Models.User;
import com.MindMatters.application.Repositories.EventRepository;
import com.MindMatters.application.Repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private UserRepository userDao;

    private EventRepository eventDao;

    public UserController(UserRepository userDao){
        this.userDao = userDao;
    }

}
