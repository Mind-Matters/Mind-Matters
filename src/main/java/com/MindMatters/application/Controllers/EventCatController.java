package com.MindMatters.application.Controllers;


import com.MindMatters.application.Repositories.EventCatRepo;
import org.springframework.stereotype.Controller;

@Controller
public class EventCatController {

    private EventCatRepo eventDao;

    public EventCatController(EventCatRepo eventDao) {
        this.eventDao = eventDao;
    }
}
