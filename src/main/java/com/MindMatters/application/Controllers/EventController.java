
package com.MindMatters.application.Controllers;

import com.MindMatters.application.Models.Category;
import com.MindMatters.application.Models.Event;
import com.MindMatters.application.Repositories.CategoryRepository;
import com.MindMatters.application.Repositories.EventRepository;
import com.MindMatters.application.Models.User;
import com.MindMatters.application.Repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/*import java.text.DateFormat;*/
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class EventController {
    private final EventRepository eventDao;

//    private UserRepository userDao;

    private final CategoryRepository categoryDao;


    public EventController(EventRepository eventDao, CategoryRepository categoryDao) {
        this.eventDao = eventDao;
        this.categoryDao = categoryDao;
    }

    @PostMapping("/submit-event")
    public String createEvent(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "date") String date,
            @RequestParam(name = "categories") String[] categories
    ) throws ParseException {
        // if no categories are selected, set to default category
        if(categories.length == 0) {
            categories = new String[]{"1"};
        }

        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);

        Date eventDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);

        // using depreciated method
        event.setDate(eventDate);

        List<Category> categoriesToSave = new ArrayList<>();
        for(String category : categories) {
            // 11 is the default category, don't save it
            if(Long.parseLong(category) != 11) {
                categoryDao.findById(Long.parseLong(category)).ifPresent(categoriesToSave::add);
            }
        }

        event.setCategories(categoriesToSave);
        event.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        /* testing to soutv*/
        System.out.println("event.getTitle() = " + event.getTitle());
        System.out.println("event.getDescription() = " + event.getDescription());
        System.out.println("event.getDate() = " + event.getDate());


        eventDao.save(event);
        return "redirect:/dashboard";
    }


    //this shows my events
    @GetMapping(path = "/my-events")
    public String myEvents(Model model){
        User loggedInUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userDao.findById(loggedInUser.getId());
        List<Event> events = eventDao.findAllByUser(loggedInUser);
//        List<Event> myEvents = new ArrayList<>();
//        for (Event event : events){
//            if(event.getUser().getId() == 1){
//                myEvents.add(event);
//            }
//        }
        model.addAttribute("events", events);
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
        return "redirect:/dashboard";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteEvent(@PathVariable long id){
        eventDao.deleteById(id);

        return "redirect:/dashboard";
    }

}