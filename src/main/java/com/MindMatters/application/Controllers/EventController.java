package com.MindMatters.application.Controllers;

import com.MindMatters.application.Models.Category;
import com.MindMatters.application.Models.Event;
import com.MindMatters.application.Repositories.CategoryRepository;
import com.MindMatters.application.Repositories.EventRepository;
import com.MindMatters.application.Models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*import java.text.DateFormat;*/
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class EventController {
    private final EventRepository eventDao;
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
        for(int i = 0; i < categories.length; i++) {
            System.out.println("categories[i] = " + categories[i]);
        }

        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);

        Date eventDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        // using depreciated method
        event.setDate(eventDate);

        List<Category> categoriesToSave = new ArrayList<>();
        for(String category : categories) {
            categoryDao.findById(Long.parseLong(category)).ifPresent(categoriesToSave::add);
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

}
