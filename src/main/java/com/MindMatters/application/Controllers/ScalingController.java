package com.MindMatters.application.Controllers;


import com.MindMatters.application.Models.ScalingData;
import com.MindMatters.application.Models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.MindMatters.application.Repositories.ScalingRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class ScalingController {

    private ScalingRepository scalingDao;

    public ScalingController(ScalingRepository scalingDao) {
        this.scalingDao = scalingDao;
    }

    @GetMapping("/mood")
    public String displayMood() {
        return "/mood-scale";
    }


    @PostMapping("/mood")
    public String submitMood(@RequestParam(name="value") long scalingData){
        ScalingData data = new ScalingData();
        data.setScore(scalingData);
        data.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        data.setDate(new Date());
        scalingDao.save(data);
        return "redirect:/dashboard";

    }
}
