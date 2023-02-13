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
import java.text.DateFormat;
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
    public String createEvent(@RequestParam(name = "title") String title,
                              @RequestParam(name = "description") String description,
                              @RequestParam(name = "date") String date,
                              @RequestParam(name = "categories") List<String> categories
    ){
        System.out.println("categories = " + categories);

        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);
        // using depreciated method
        event.setDate(new Date(date));

        // trying new simpledateformat
        /*System.out.println("date = " + date);
        DateFormat df = new SimpleDateFormat();
        try {
            event.setDate(df.parse(date));
        } catch (Exception e) {
            System.out.println("Error processing date in EventController");
            e.printStackTrace();
        }*/


        // categoryName | boolean
        // event.setCategories(categories);
        List<Category> categoriesToSave = new ArrayList<>();

        /* hardcode categories for testing
        categoriesToSave.add(categoryDao.findByCategory("Work"));
        categoriesToSave.add(categoryDao.findByCategory("Education"));*/

        // pull category info from user input
        Map<String, Boolean> categoryMap = new HashMap<>();
        categories.forEach(line -> {
            String[] splitLine = line.split(":");
            categoryMap.put(splitLine[0].trim(), Boolean.parseBoolean(splitLine[1].trim()));
        });
        /*categoryMap.forEach((categoryId, checked) -> {
            if (checked) {
                categoriesToSave.add(categoryDao.findById(categoryId));
            }
        });*/
        event.setCategories(categoriesToSave);
        event.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        /* testing to soutv*/
        System.out.println("event.getTitle() = " + event.getTitle());
        System.out.println("event.getDescription() = " + event.getDescription());
        System.out.println("event.getDate() = " + event.getDate());
        System.out.println("categories = " + categories);

        eventDao.save(event);
        return "redirect:/dashboard";
    }

}
