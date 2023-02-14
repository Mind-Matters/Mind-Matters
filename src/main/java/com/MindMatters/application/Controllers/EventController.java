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
                                @RequestParam(name = "categoryDB1") boolean category1,
                                @RequestParam(name = "categoryDB2") boolean category2,
                                @RequestParam(name = "categoryDB3") boolean category3,
                                @RequestParam(name = "categoryDB4") boolean category4,
                                @RequestParam(name = "categoryDB5") boolean category5,
                                @RequestParam(name = "categoryDB6") boolean category6,
                                @RequestParam(name = "categoryDB7") boolean category7,
                                @RequestParam(name = "categoryDB8") boolean category8,
                                @RequestParam(name = "categoryDB9") boolean category9,
                                @RequestParam(name = "categoryDB10") boolean category10
    ){
        System.out.println("category1 = " + category1);
        System.out.println("category2 = " + category2);
        System.out.println("category3 = " + category3);


        Boolean[] categories = new Boolean[10];
        /*categories[0] = Boolean.parseBoolean(category1);
        categories[1] = Boolean.parseBoolean(category2);
        categories[2] = Boolean.parseBoolean(category3);
        categories[3] = Boolean.parseBoolean(category4);
        categories[4] = Boolean.parseBoolean(category5);
        categories[5] = Boolean.parseBoolean(category6);
        categories[6] = Boolean.parseBoolean(category7);
        categories[7] = Boolean.parseBoolean(category8);
        categories[8] = Boolean.parseBoolean(category9);
        categories[9] = Boolean.parseBoolean(category10);*/



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

        for(int i = 0; i < 10; i++) {
            if(categories[i]) {
                System.out.println("categoryDao.findById((long) i + 1) = " + categoryDao.findById((long) i + 1));
                categoryDao.findById((long) i + 1).ifPresent(categoriesToSave::add);
            }
        }
        /* hardcode categories for testing
        categoriesToSave.add(categoryDao.findByCategory("Work"));
        categoriesToSave.add(categoryDao.findByCategory("Education"));*/

        // pull category info from user input
        /*Map<Long, Boolean> categoryMap = new HashMap<>();
        categories.forEach(line -> {
            String[] splitLine = line.split(":");
            categoryMap.put(Long.parseLong(splitLine[0].trim()), Boolean.parseBoolean(splitLine[1].trim()));
        });
        System.out.println("category map test output: ");
        categoryMap.forEach((categoryId, checked) -> {
            System.out.println("categoryId = " + categoryId);
            System.out.println("checked = " + checked);*/
/*            if (checked && categoryId != null) {
                categoriesToSave.add(categoryDao.findById(categoryId));
            }
        });*/
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
