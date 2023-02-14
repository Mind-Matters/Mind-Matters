package com.MindMatters.application.Controllers;

import com.MindMatters.application.Models.Event;
import com.MindMatters.application.Repositories.EventRepository;
import com.MindMatters.application.Models.User;
import com.MindMatters.application.Repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EventController {
    private final EventRepository eventDao;
//    private UserRepository userDao;

    public EventController(EventRepository eventDao) {
        this.eventDao = eventDao;
    }

    @PostMapping("/submit-event")
    public String createEvent(@RequestParam(name = "title") String title,
                              @RequestParam(name = "description") String description,
                              @RequestParam(name = "date") String date
    ){
        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);
        event.setDate(new Date());
        event.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        eventDao.save(event);
        return "redirect:/dashboard";
    }


//this shows my events
    @GetMapping(path = "/my-events")
    public String myEvents(Model model){
//        User loggedInUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userDao.findById(loggedInUser.getId());
        List<Event> events = eventDao.findAll();
        List<Event> myEvents = new ArrayList<>();
        for (Event event : events){
            if(event.getUser().getId() == 1){
                myEvents.add(event);
            }
        }
        model.addAttribute("events", myEvents);
        return "/my-events";
    }


//this edits events
    @GetMapping(path = "/edit-events/{id}")
    public String getEdit(@PathVariable long id, Model model){
        Event event = eventDao.getReferenceById(id);
        model.addAttribute("event", event);
        return "/edit-events";
    }
    @PostMapping(path = "/edit-events/{id}")
    public String postEdit(@PathVariable long id, @RequestParam String title, @RequestParam String body){
        Event event = eventDao.getReferenceById(id);
        event.setTitle(title);
        event.setDescription(body);
        eventDao.save(event);
        return "patient-dashboard";
    }

}
