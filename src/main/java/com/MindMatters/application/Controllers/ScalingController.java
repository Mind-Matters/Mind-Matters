package com.MindMatters.application.Controllers;

import com.MindMatters.application.Controllers.Repositories.ScalingRepo;

public class ScalingController {

    private ScalingRepo scalingDao;

    public ScalingController(ScalingRepo scalingDao){
        this.scalingDao = scalingDao;
    }
}
