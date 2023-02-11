package com.MindMatters.application.Controllers;


import com.MindMatters.application.Repositories.EventCategoryRepository;
import org.springframework.stereotype.Controller;

@Controller
public class EventCatController {

    private EventCategoryRepository eventDao;

    public EventCatController(EventCategoryRepository eventDao) {
        this.eventDao = eventDao;
    }
}
