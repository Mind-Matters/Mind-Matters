package com.MindMatters.application.Controllers;

import com.MindMatters.application.Models.Event;
import com.MindMatters.application.Repositories.EventRepo;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {
    private final EventRepo eventDao;

    public EventController(EventRepo eventDao) {
        this.eventDao = eventDao;
    }

    @PostMapping("/add-event")
    public String addEvent(@ModelAttribute Event event) {
    eventDao.save(event);
    return "Templates/patient-dashboard";
    }
}
