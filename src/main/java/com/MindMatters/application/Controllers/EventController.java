package com.MindMatters.application.Controllers;

import com.MindMatters.application.Repositories.EventRepo;
import org.springframework.stereotype.Controller;

@Controller
public class EventController {
    private final EventRepo eventDao;

    public EventController(EventRepo eventDao) {
        this.eventDao = eventDao;
    }
}
