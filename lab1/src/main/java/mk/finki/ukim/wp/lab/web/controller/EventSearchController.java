package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.entity.Event;
import mk.finki.ukim.wp.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/search")
public class EventSearchController {

    private final EventService eventService;

    public EventSearchController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping
    public String handleSearch(Model model,
                         @RequestParam String eventText,
                         @RequestParam String rating,
                         @RequestParam String locationText) {


        long start = System.nanoTime();
        List<Event> results = eventService.dynamicSearch(eventText,rating,locationText);
        long end = System.nanoTime();

        System.out.println("Total time for search: " + ((end - start) / (Math.pow(10,6))) + "ms");

        if(!results.isEmpty()) {
            model.addAttribute("events", results);
        } else {
            model.addAttribute("events",eventService.listAll());
        }

        return "listEvents";

    }
}
