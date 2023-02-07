package com.MindMatters.application.Controllers;


import com.MindMatters.application.Repositories.RelationalRepo;
import org.springframework.stereotype.Controller;

@Controller
public class RelationalController {

    private RelationalRepo relationalDao;





    public RelationalController(RelationalRepo relationalDao) {
        this.relationalDao = relationalDao;

    }
}
