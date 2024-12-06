package mk.finki.ukim.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.wp.lab.entity.Event;
import mk.finki.ukim.wp.lab.entity.Location;
import mk.finki.ukim.wp.lab.service.EventService;
import mk.finki.ukim.wp.lab.service.LocationService;
import mk.finki.ukim.wp.lab.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;
    private final ReviewService reviewService;

    public EventController(EventService eventService, LocationService locationService, ReviewService reviewService) {
        this.eventService = eventService;
        this.locationService = locationService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model) {


        model.addAttribute("events", eventService.listAll());
        return "listEvents";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam double popularityScore,
                            @RequestParam Long locationId, Model model) {

        try {
            List<Location> locations = locationService.findAll();
            eventService.add(name, description, popularityScore, locationId);
            model.addAttribute("locations", locations);
            model.addAttribute("status","Added new event: " + name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
        }

        return "redirect:/events";
    }

    @PostMapping("/edit/{eventId}")
    public String editEvent(@PathVariable Long eventId,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam double popularityScore,
                            @RequestParam Long locationId,
                            RedirectAttributes redirectAttributes) {

        try {
            eventService.update(eventId, name, description, popularityScore, locationId);
           redirectAttributes.addFlashAttribute("status","Edited event: " + name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error","Can't edit event: " + name);
            e.printStackTrace();
        }


        return "redirect:/events";

    }

    @GetMapping("/add-form")
    public String getAddEventPage(Model model) {
        List<Location> locations = locationService.findAll();
        model.addAttribute("path", "/events/add");
        model.addAttribute("locations", locations);
        return "add-event";
    }

    @GetMapping("edit-review/{eventId}")
    public String getEditReviewPage(@PathVariable Long eventId,Model model){
        model.addAttribute("path", "/events/add-review");

        try{
            Event event = eventService.getEventById(eventId);
            model.addAttribute("event", event);

            return "add-review";
        }
        catch (Exception e){
            System.out.println(e.getMessage());

            return "redirect:/events";
        }

    }

    @PostMapping("add-review")
    public String addReview(@RequestParam String comment,
                            @RequestParam int rating,
                            @RequestParam Long eventId,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        try{;
            reviewService.addReview(eventId,comment,rating);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "redirect:/events";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditEventPage(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        try {
            Event event = eventService.getEventById(id);
            List<Location> locations = locationService.findAll();
            String path = "/events/edit/" + event.getId();

            System.out.println();

            model.addAttribute("path", path);
            model.addAttribute("event", event);
            model.addAttribute("locations", locations);

            return "add-event";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error","ERROR: Can't edit event.\n" + "No event with id: " + id + " exists");
            return "redirect:/events";
        }
    }


    @GetMapping("/delete/{eventId}")
    public String deleteEvent(@PathVariable Long eventId,RedirectAttributes redirectAttributes) {
        try{
            eventService.delete(eventId);
            redirectAttributes.addFlashAttribute("status","Deleted event: " + eventId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/events";
    }


}
