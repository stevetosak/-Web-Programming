package mk.finki.ukim.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.wp.lab.service.EventBookingService;
import mk.finki.ukim.wp.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "EventBookingServlet", urlPatterns = "/eventBooking")
public class EventBookingServlet extends HttpServlet {

    private final EventBookingService eventBookingService;
    private final SpringTemplateEngine springTemplateEngine;

    public EventBookingServlet(EventBookingService eventBookingService, SpringTemplateEngine springTemplateEngine) {
        this.eventBookingService = eventBookingService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange =
                JakartaServletWebApplication
                        .buildApplication(getServletContext())
                        .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);

        springTemplateEngine.process("bookingConfirmation.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange =
                JakartaServletWebApplication
                        .buildApplication(getServletContext())
                        .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);

        String eventName = req.getParameter("selectedEvent");
        System.out.println(eventName + " 1");
        String numTickets = req.getParameter("numTickets");

        System.out.println("VLEZE");

        //nemat potreba tuka od event booking service zasega

        webContext.setVariable("eventName", eventName);
        webContext.setVariable("numTickets", numTickets);
        webContext.setVariable("ipAddress", req.getRemoteAddr() + ":" + req.getRemotePort());


        springTemplateEngine.process("bookingConfirmation.html", webContext, resp.getWriter());
    }
}
