package mk.finki.ukim.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.wp.lab.entity.Event;
import mk.finki.ukim.wp.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {

    private final EventService eventService;

    public EventBookingController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String getEventBookingPage(Model model) {
        return "bookingConfirmation";
    }

    @PostMapping
    public String postEventBooking(Model model,
                                   @RequestParam long eventId,
                                   HttpServletRequest request,
                                   @RequestParam int numTickets,
                                   RedirectAttributes redirectAttributes) {


        try{
            Event event = eventService.getEventById(eventId);
            String ip = request.getRemoteAddr() + ":" + request.getRemotePort();

            model.addAttribute("event", event);
            model.addAttribute("clientIpAddr", ip);
            model.addAttribute("numTickets", numTickets);
            return "bookingConfirmation";
        } catch (Exception ex){
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/events";
        }

    }
}
