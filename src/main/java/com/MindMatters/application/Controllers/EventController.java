package com.MindMatters.application.Controllers;

import com.MindMatters.application.Models.Event;
import com.MindMatters.application.Controllers.Repositories.EventRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.Date;

@Controller
public class EventController {
    private final EventRepo eventDao;

    private Event event;

    public EventController(EventRepo eventDao) {
        this.eventDao = eventDao;
    }

    @GetMapping ("/event-dashboard")
    public String addEvent(Model model) {
        model.addAttribute("event", new Event());
        return "/patient-dashboard";
    }
    @PostMapping("/event-dashboard")
    public String createEvent(@RequestParam(name = "title") String title,
                              @RequestParam(name = "description") String description,
                              @RequestParam(name = "date") String date
    ){
        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);
        event.setDate(new Date());
        eventDao.save(event);
        return "redirect:/event-dashboard";
    }
/*  using Form Model Binding
    Abandoned this approach because we encountered errors. Need instructor assistsance to implement.
    @GetMapping("/add-event")
    public String showPatientDashboard(Model model) {
        model.addAttribute("event", new Event());
        return "/patient-dashboard";
    }

    @PostMapping("/add-event")
    public String addEvent(@ModelAttribute Event event) {
        eventDao.save(event);
        return "patient-dashboard";
    }*/

    @GetMapping("/add-event")
    public String showPatientDashboard() {
        return "/patient-dashboard";
    }

    @PostMapping("/add-event")
    public String addEvent(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "date") String date
    ){
        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);
        event.setDate(new Date(date));
        eventDao.save(event);
        return "patient-dashboard";
    }
}
