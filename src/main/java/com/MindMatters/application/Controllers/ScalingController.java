package com.MindMatters.application.Controllers;


import com.MindMatters.application.Models.ScalingData;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.MindMatters.application.Repositories.ScalingRepo;
import java.time.DateTimeException;
import java.util.Date;

public class ScalingController {

    private ScalingRepo scalingDao;

    public ScalingController(ScalingRepo scalingDao){
        this.scalingDao = scalingDao;
    }

    @PostMapping("/mood")
    public String submitMood(@ModelAttribute ScalingData scalingData){
        scalingData.setDate(new Date());
    }
}
