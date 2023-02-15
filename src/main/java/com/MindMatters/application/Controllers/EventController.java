package com.MindMatters.application.Controllers;

import com.MindMatters.application.Models.Event;
import com.MindMatters.application.Repositories.EventRepository;
import com.MindMatters.application.Models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class EventController {
    private final EventRepository eventDao;

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

}
