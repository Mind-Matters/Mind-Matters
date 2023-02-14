package com.MindMatters.application.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SplashController {
    @GetMapping("/home")
    public String renderSplash(){

        return "splash";
    }
}
